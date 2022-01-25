package tostimannetje.landleven.entity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import tostimannetje.landleven.Main;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.init.ModItems;

public class EntityTractor extends Entity{

	private static final DataParameter<Float> DAMAGE = EntityDataManager.<Float>createKey(EntityTractor.class, DataSerializers.FLOAT);
	private final float speed = 0.25f;
	private ItemStackHandler itemHandler;
	private ItemStackHandler harvestHandler;
	private ItemStackHandler fertilizerHandler;
	private TractorModes mode;
	
	public EntityTractor(World worldIn) {
		super(worldIn);
		this.stepHeight = 1.0F;
		this.setSize(2.1F, 1.5F);
		this.itemHandler = new ItemStackHandler(27);
		this.harvestHandler = new ItemStackHandler(27);
		this.fertilizerHandler = new ItemStackHandler(9);
		this.setTractorMode(TractorModes.PLANTING);
	}
	
	public EntityTractor(World worldIn, double x, double y, double z) {
		super(worldIn);
		this.setPosition(x, y, z);
		this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
	}
	
	public static EntityTractor create(World worldIn, double x, double y, double z) {
		return new EntityTractor(worldIn, x, y, z);
	}
	
	@Override
	protected void entityInit() {
		this.dataManager.register(DAMAGE, Float.valueOf(0.0F));
	}

	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand){
        if (super.processInitialInteract(player, hand)) return true;

        if (player.isSneaking()) {
        	player.openGui(Main.instance, 3000, world, this.getEntityId(), 0, 0);
            return true;
        } else if (this.isBeingRidden()) {
            return true;
        } else{
            if (!this.world.isRemote) {
            	player.rotationYaw = this.rotationYaw;
            	player.startRiding(this);
            }

            return true;
        }
    }
	
	public boolean canBeRidden() {
		return true;
	}
	
	@Override
	public boolean canRiderInteract() {
		return true;
	}
	
	@Override
	public void updatePassenger(Entity passenger) {
        if (this.isPassenger(passenger)) {
            float f = -1.0F;
            float f1 = (float)((this.isDead ? 0.009999999776482582D : this.getMountedYOffset()) + passenger.getYOffset());

            Vec3d vec3d = (new Vec3d((double)f, 0.0D, 0.0D)).rotateYaw(-this.rotationYaw * 0.017453292F - ((float)Math.PI / 2F));
            passenger.setPosition(this.posX + vec3d.x, this.posY + (double)f1, this.posZ + vec3d.z);
            passenger.rotationYaw = this.rotationYaw;
            passenger.setRenderYawOffset(this.rotationYaw);
        }
    }
	
	@Override
	@Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn) {
        return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
    }

	@Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox() {
        return this.getEntityBoundingBox();
    }

	@Override
	public void applyEntityCollision(Entity entityIn) {
        if (entityIn instanceof EntityTractor) {
            if (entityIn.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY) {
                super.applyEntityCollision(entityIn);
            }
        }
        else if (entityIn.getEntityBoundingBox().minY <= this.getEntityBoundingBox().minY) {
            super.applyEntityCollision(entityIn);
        }
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
        if (!this.world.isRemote && !this.isDead) {
            if (this.isEntityInvulnerable(source)) {
                return false;
            } else {
                this.setDamage(this.getDamage() + amount * 10.0F);
                boolean flag = source.getTrueSource() instanceof EntityPlayer && ((EntityPlayer)source.getTrueSource()).capabilities.isCreativeMode;

                if (flag || this.getDamage() > 40.0F) {
                    this.removePassengers();

                    if (flag && !this.hasCustomName()) {
                        this.setDead();
                    } else {
                        this.killTractor(source);
                    }
                }

                return true;
            }
        } else {
            return true;
        }
    }

    public void killTractor(DamageSource source) {
        this.setDead();
        
        if (this.world.getGameRules().getBoolean("doEntityDrops")) {
            ItemStack itemstack = new ItemStack(ModItems.itemTractor, 1);

            if (this.hasCustomName()) {
                itemstack.setStackDisplayName(this.getCustomNameTag());
            }

            this.entityDropItem(itemstack, 0.0F);
        }
    }
	
    @Override
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation() {
        this.setDamage(this.getDamage() * 11.0F);
    }
    
    public void setDamage(float damage) {
        this.dataManager.set(DAMAGE, Float.valueOf(damage));
    }

    public float getDamage() {
        return ((Float)this.dataManager.get(DAMAGE)).floatValue();
    }
    
    @Override
    public boolean canBeCollidedWith() {
        return !this.isDead;
    }
    
    @Override
    public boolean canBePushed() {
        return false;
    }

	public void setPosition(double x, double y, double z) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        float f = this.width / 2.0F;
        float f1 = this.height;
        this.setEntityBoundingBox(new AxisAlignedBB(x - (double)f, y, z - (double)f, x + (double)f, y + (double)f1, z + (double)f));
    }
	
	@Override
	public Entity getControllingPassenger() {
		List<Entity> list = this.getPassengers();
		return list.isEmpty() ? null : (Entity) list.get(0);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        
        //Update movement
        if (this.canPassengerSteer()) {
        	if(this.getControllingPassenger() instanceof EntityLivingBase) {
	        	EntityLivingBase entitylivingbase = (EntityLivingBase)this.getControllingPassenger();
	            this.rotationYaw = entitylivingbase.rotationYaw;
	            this.rotationPitch = entitylivingbase.rotationPitch * 0.5F;
	            this.setRotation(this.rotationYaw, this.rotationPitch);
	            if(this.rotationYaw - this.prevRotationYaw > 180) {
	            	this.prevRotationYaw += 360;
	            } else if(this.rotationYaw - this.prevRotationYaw < -180) {
	            	this.prevRotationYaw -= 360;
	            }
	            float strafe = entitylivingbase.moveStrafing * 0.5F;
	            float forward = entitylivingbase.moveForward;
	            float vertical = entitylivingbase.moveVertical;
	            this.travel(strafe, vertical, forward);
        	}
        } else {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
        }
        
        //Suck up items into harvest inventory
        if (!this.world.isRemote && ForgeEventFactory.getMobGriefingEvent(this.world, this)) {
            for (EntityItem entityitem : this.world.getEntitiesWithinAABB(EntityItem.class, this.getEntityBoundingBox().grow(1.0D, 0.0D, 1.0D))) {
                if (!entityitem.isDead && !entityitem.getItem().isEmpty() && !entityitem.cannotPickup()) {
                    this.pickupItem(entityitem);
                }
            }
        }
        
        //Depending on the mode, plant seeds/harvest crops/fertilize farmland
        if(this.isBeingRidden()) {
	        int blockPosX = (int)Math.floor(this.posX);
	        int blockPosY = (int)Math.floor(this.posY);
	        int blockPosZ = (int)Math.floor(this.posZ);
	        
	        for(int i = -2; i < 3; i++) {
				for(int j = -2; j < 3; j++) {
					for(int k = -1; k < 1; k++) {
						BlockPos checkPos = new BlockPos(blockPosX + i, blockPosY + k, blockPosZ + j);
						BlockPos cropPos = checkPos.up();
						//Harvest needs farmland or fert. farmland and crop
						//Plant needs farmland or fert. farmland and air
						//Fertilize needs farmland and air
						if(world.getBlockState(checkPos).getBlock() == Blocks.FARMLAND || world.getBlockState(checkPos).getBlock() == ModBlocks.blockFertilizedLand) {
							if(this.getTractorMode() == TractorModes.HARVESTING && world.getBlockState(cropPos).getBlock() instanceof BlockCrops) {
								harvestCrop(world.getBlockState(cropPos), world, cropPos, (EntityPlayer)this.getControllingPassenger());
								continue;
							}
							
							if(this.getTractorMode() == TractorModes.FERTILIZING && world.getBlockState(cropPos).getBlock() instanceof BlockCrops) {
								bonemeal((IItemHandler)this.fertilizerHandler, world, checkPos);
								continue;
							}
							
							if(world.getBlockState(cropPos).getBlock() == Blocks.AIR) {
								if(this.getTractorMode() == TractorModes.PLANTING) {
									plantSeed((IItemHandler)this.itemHandler, world, cropPos);
									continue;
								}
								
								if(this.getTractorMode() == TractorModes.FERTILIZING 
										&& world.getBlockState(checkPos).getBlock() == Blocks.FARMLAND) {
									fertilize((IItemHandler)this.fertilizerHandler, world, checkPos);
									continue;
								}
							}
						}
					}
				}
			}
        }
	}
    
	private void pickupItem(EntityItem itemEntity) {
		ItemStack entityStack = itemEntity.getItem();
		
		//Insert item in existing stack if possible
		int slot = isItemInInventory(entityStack.getItem(), this.harvestHandler);
		if(slot != -1) {
			entityStack = this.harvestHandler.insertItem(slot, entityStack, false);
			if(entityStack.getCount() <= 0) {
				itemEntity.setDead();
				return;
			}
		}
		
		//Insert remainder in empty stack if possible
		if(!entityStack.isEmpty()) {
			for(int i = 0; i < this.harvestHandler.getSlots(); i++) {
				if(this.harvestHandler.getStackInSlot(i).isEmpty()) {
					entityStack = this.harvestHandler.insertItem(i, entityStack, false);
					if(entityStack.getCount() <= 0) {
						itemEntity.setDead();
						return;
					}
				}
			}
		}
		
		//Leave remainder in itemEntity if inventory is full
		if(!entityStack.isEmpty()) {
			//itemEntity.getDataManager().set(EntityDataManager.<ItemStack>createKey(EntityItem.class, DataSerializers.ITEM_STACK), entityStack);
		}
	}
	
	private int isItemInInventory(Item item, ItemStackHandler handler) {
		for(int i = 0; i < handler.getSlots(); i++) {
			ItemStack stack = handler.getStackInSlot(i);
			if(!stack.isEmpty()) {
				if(stack.getItem() == item) {
					if(stack.getCount() < stack.getMaxStackSize()) {
						return i;
					}
				}
			}
		}
		
		return -1;
	}
	
	//Called to plant a seed from the IItemHandler at checkPos
	private void plantSeed(IItemHandler bag, World world, BlockPos cropPos) {
		for(int k = 0;  k < bag.getSlots(); k++) {
			if(!bag.getStackInSlot(k).isEmpty()) {
				//If the seed is planted successfully, the block should update and the seed should be removed
				IBlockState toPlant = ((ItemSeeds) bag.getStackInSlot(k).getItem()).getPlant(world, cropPos);
	            world.setBlockState(cropPos, toPlant);
	            world.markBlockRangeForRenderUpdate(cropPos, cropPos);
				world.notifyBlockUpdate(cropPos, Blocks.AIR.getDefaultState(), world.getBlockState(cropPos), 3);
				world.scheduleBlockUpdate(cropPos, world.getBlockState(cropPos).getBlock(),0,0);
				bag.extractItem(k, 1, false);
				break;
			}
		}
	}
	
	private void bonemeal(IItemHandler bag, World world, BlockPos checkPos) {
		for(int k = 0;  k < bag.getSlots(); k++) {
			if(!bag.getStackInSlot(k).isEmpty()) {
				
				if(bag.getStackInSlot(k).getItem() == Items.DYE) {
					if(!applyBonemeal(bag.getStackInSlot(k), world, checkPos.up())) {
						return;
					}
				}
				
				bag.extractItem(k, 1, false);
				break;
			}
		}
	}
	
	private void fertilize(IItemHandler bag, World world, BlockPos checkPos) {
		for(int k = 0;  k < bag.getSlots(); k++) {
			if(!bag.getStackInSlot(k).isEmpty()) {
				
				if (bag.getStackInSlot(k).getItem() == ModItems.itemFertilizer) {
					if(!applyFertilizer(bag.getStackInSlot(k), world, checkPos)) {
						return;
					}
				}
				
				bag.extractItem(k, 1, false);
				break;
			}
		}
	}
	
	private boolean applyFertilizer(ItemStack stack, World worldIn, BlockPos target){

        Block block = worldIn.getBlockState(target).getBlock();
		if(block == Blocks.FARMLAND) {
			worldIn.setBlockState(target, ModBlocks.blockFertilizedLand.getDefaultState());
			return true;
		}

        return false;
    }
	
	private boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos target){
        IBlockState iblockstate = worldIn.getBlockState(target);
        int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal((EntityPlayer) this.getControllingPassenger(), worldIn, target, iblockstate, stack, null);
        if (hook != 0) return hook > 0;

        if (iblockstate.getBlock() instanceof IGrowable) {
            IGrowable igrowable = (IGrowable)iblockstate.getBlock();
            if (igrowable.canGrow(worldIn, target, iblockstate, worldIn.isRemote)) {
                if (!worldIn.isRemote){
                    if (igrowable.canUseBonemeal(worldIn, worldIn.rand, target, iblockstate)) {
                        igrowable.grow(worldIn, worldIn.rand, target, iblockstate);
                    }
                }
                return true;
            }
        }

        return false;
    }
	
	private void harvestCrop(IBlockState crop, World world, BlockPos cropPos, EntityPlayer player) {
		if(!world.isRemote) {
			if(crop.getBlock() instanceof BlockCrops) {
				BlockCrops blockCrop = (BlockCrops)crop.getBlock();
				if(blockCrop.isMaxAge(crop)) {
					blockCrop.harvestBlock(world, player, cropPos, crop, null, new ItemStack(ModItems.itemTractor));
					world.setBlockState(cropPos, Blocks.AIR.getDefaultState());
		            world.markBlockRangeForRenderUpdate(cropPos, cropPos);
					world.notifyBlockUpdate(cropPos, crop, world.getBlockState(cropPos), 3);
					world.scheduleBlockUpdate(cropPos, world.getBlockState(cropPos).getBlock(),0,0);
				}
			}
		}
	}
	
	public void travel(float strafe, float vertical, float forward) {
        float f6 = 0.91F;
        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ);

        if (this.onGround) {
            IBlockState underState = this.world.getBlockState(blockpos$pooledmutableblockpos);
            f6 = underState.getBlock().getSlipperiness(underState, this.world, blockpos$pooledmutableblockpos, this) * 0.91F;
        }

        float f7 = 0.16277136F / (f6 * f6 * f6);
        
        this.moveRelative(strafe, vertical, forward, speed * f7);
        f6 = 0.91F;

        if (this.onGround) {
            IBlockState underState = this.world.getBlockState(blockpos$pooledmutableblockpos.setPos(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ));
            f6 = underState.getBlock().getSlipperiness(underState, this.world, blockpos$pooledmutableblockpos, this) * 0.91F;
        }

        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        
        blockpos$pooledmutableblockpos.setPos(this.posX, 0.0D, this.posZ);

        if (!this.world.isRemote || this.world.isBlockLoaded(blockpos$pooledmutableblockpos) && this.world.getChunkFromBlockCoords(blockpos$pooledmutableblockpos).isLoaded()) {
            if (!this.hasNoGravity()) {
                this.motionY -= 0.08D;
            }
        } else if (this.posY > 0.0D) {
            this.motionY = -0.1D;
        } else {
            this.motionY = 0.0D;
        }
        
        this.motionY *= 0.9800000190734863D;
        this.motionX *= (double)f6;
        this.motionZ *= (double)f6;
        blockpos$pooledmutableblockpos.release();
    }
	
	@SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
        	switch(facing) {
        	case UP:
        		return (T) itemHandler;
        	case DOWN:
        		return (T) harvestHandler;
        	case NORTH:
        		return (T) fertilizerHandler;
			case EAST:
				break;
			case SOUTH:
				break;
			case WEST:
				break;
        	}
        	
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) { 
		itemHandler.deserializeNBT(compound.getCompoundTag("itemhandler"));
		harvestHandler.deserializeNBT(compound.getCompoundTag("harvesthandler"));
		fertilizerHandler.deserializeNBT(compound.getCompoundTag("fertilizerhandler"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) { 
		if(itemHandler == null) {
			itemHandler = new ItemStackHandler(27);
			harvestHandler = new ItemStackHandler(27);
			fertilizerHandler = new ItemStackHandler(9);
		}
		compound.setTag("itemhandler", itemHandler.serializeNBT());
		compound.setTag("harvesthandler", harvestHandler.serializeNBT());
		compound.setTag("fertilizerhandler", fertilizerHandler.serializeNBT());
	}
	
	public void receiveButtonEvent(int id) {
		setTractorMode(TractorModes.values()[id]);
	}
	
	public TractorModes getTractorMode() {
		return this.mode;
	}
	
	public void setTractorMode(TractorModes mode) {
		this.mode = mode;
	}
	
	public enum TractorModes{
		PLANTING(0),
		HARVESTING(1),
		FERTILIZING(2);
		
		private final int value;
		
	    private TractorModes(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
}
