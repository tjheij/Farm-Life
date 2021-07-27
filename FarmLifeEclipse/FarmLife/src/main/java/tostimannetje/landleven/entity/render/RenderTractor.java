package tostimannetje.landleven.entity.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.entity.EntityTractor;
import tostimannetje.landleven.entity.model.ModelTractor;

@SideOnly(Side.CLIENT)
public class RenderTractor extends Render<EntityTractor>{

	protected ResourceLocation texture= new ResourceLocation(Reference.MODID, "textures/entity/tractor.png");
	protected ModelBase modelTractor = new ModelTractor();
	
	public RenderTractor(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityTractor entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		Minecraft.getMinecraft().getTextureManager().bindTexture(getEntityTexture(entity));
		GlStateManager.translate(x, y+1.35, z);
		GlStateManager.rotate(180, 0, 0, 1);
		GlStateManager.rotate(entityYaw - 90.0f, 0, 1, 0);
		this.modelTractor.render(entity, 0, 0, 0, 0, 0, (1.0F / 16.0F) * (16.0F / 18.0F));
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityTractor entity) {
		return texture;
	}
}
