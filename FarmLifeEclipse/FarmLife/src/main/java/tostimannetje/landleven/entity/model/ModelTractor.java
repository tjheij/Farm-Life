package tostimannetje.landleven.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTractor extends ModelBase{

	private final ModelRenderer bone;

	public ModelTractor () {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 0, 91, 2.0F, -27.0F, -8.0F, 20, 21, 16, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 44, -26.0F, -26.0F, -6.0F, 28, 18, 12, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 72, -27.0F, -26.0F, -14.0F, 18, 4, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 71, -27.0F, -26.0F, 6.0F, 18, 4, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 7.0F, -12.0F, -12.0F, 12, 12, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 16, -28.0F, -20.0F, -14.0F, 20, 20, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 16, -28.0F, -20.0F, 6.0F, 20, 20, 8, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 0, 0, 7.0F, -12.0F, 8.0F, 12, 12, 4, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 33, 1, 15.0F, -37.0F, -5.0F, 3, 10, 3, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 48, 0, -24.0F, -38.0F, -6.0F, 2, 12, 12, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
}
