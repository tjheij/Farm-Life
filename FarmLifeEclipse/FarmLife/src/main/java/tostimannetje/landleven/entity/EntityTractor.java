package tostimannetje.landleven.entity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tostimannetje.landleven.init.ModItems;

public class EntityTractor extends Entity{

	private static final DataParameter<Float> DAMAGE = EntityDataManager.<Float>createKey(EntityTractor.class, DataSerializers.FLOAT);
	private final float speed = 0.35f;
	
	public EntityTractor(World worldIn) {
		super(worldIn);
		this.stepHeight = 1.0F;
		this.setSize(2.1F, 1.5F);
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
            return false;
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
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		
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
}
