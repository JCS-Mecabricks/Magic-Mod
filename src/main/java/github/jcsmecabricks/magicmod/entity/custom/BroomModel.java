package github.jcsmecabricks.magicmod.entity.custom;

import github.jcsmecabricks.magicmod.entity.client.BroomRenderState;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class BroomModel<T extends Entity> extends EntityModel<BroomRenderState> {
    private final ModelPart bb_main;

    public BroomModel(ModelPart root) {
        super(root);
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        bb_main.addChild("cube_r1", ModelPartBuilder.create()
                        .uv(46, 19).cuboid(-1.0F, -1.5F, -2.0F, 1.0F, 1.0F, 3.0F),
                ModelTransform.of(18.5F, 0.0F, 0.5F, 0.0F, -1.5708F, 0.0F));

        bb_main.addChild("cube_r2", ModelPartBuilder.create()
                        .uv(0, 45).cuboid(-1.0F, -2.0F, -4.0F, 1.0F, 1.0F, 5.0F)
                        .uv(28, 38).cuboid(-1.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F)
                        .uv(44, 0).cuboid(-1.0F, 0.0F, -5.0F, 1.0F, 1.0F, 6.0F)
                        .uv(42, 43).cuboid(-1.0F, -1.0F, -5.0F, 1.0F, 1.0F, 6.0F)
                        .uv(44, 13).cuboid(-1.0F, 1.0F, -4.0F, 1.0F, 1.0F, 5.0F),
                ModelTransform.of(13.0F, -1.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        bb_main.addChild("cube_r3", ModelPartBuilder.create()
                        .uv(16, 30).cuboid(-1.0F, -2.0F, -6.0F, 1.0F, 1.0F, 7.0F)
                        .uv(0, 30).cuboid(-1.0F, -3.0F, -6.0F, 1.0F, 1.0F, 7.0F)
                        .uv(32, 29).cuboid(-1.0F, -4.0F, -5.0F, 1.0F, 1.0F, 6.0F)
                        .uv(32, 22).cuboid(-1.0F, -1.0F, -5.0F, 1.0F, 1.0F, 6.0F),
                ModelTransform.of(13.0F, 1.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        bb_main.addChild("cube_r4", ModelPartBuilder.create()
                        .uv(16, 22).cuboid(-1.0F, -2.0F, -6.0F, 1.0F, 1.0F, 7.0F)
                        .uv(0, 38).cuboid(-1.0F, -3.0F, -5.0F, 1.0F, 1.0F, 6.0F)
                        .uv(32, 36).cuboid(-1.0F, 0.0F, -5.0F, 1.0F, 1.0F, 6.0F)
                        .uv(0, 22).cuboid(-1.0F, -1.0F, -6.0F, 1.0F, 1.0F, 7.0F),
                ModelTransform.of(13.0F, 0.0F, 1.0F, 0.0F, -1.5708F, 0.0F));

        bb_main.addChild("cube_r5", ModelPartBuilder.create()
                        .uv(28, 43).cuboid(-1.0F, -2.0F, -5.0F, 1.0F, 1.0F, 6.0F)
                        .uv(14, 38).cuboid(-1.0F, -1.0F, -5.0F, 1.0F, 1.0F, 6.0F)
                        .uv(12, 45).cuboid(-1.0F, 0.0F, -3.0F, 1.0F, 1.0F, 4.0F)
                        .uv(44, 7).cuboid(-1.0F, -3.0F, -4.0F, 1.0F, 1.0F, 5.0F),
                ModelTransform.of(13.0F, 0.0F, 2.0F, 0.0F, -1.5708F, 0.0F));

        bb_main.addChild("cube_r6", ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-1.0F, -2.0F, -12.0F, 2.0F, 2.0F, 20.0F),
                ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(BroomRenderState state) {
    }

    public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int color, float red, float green, float blue, float alpha) {
        bb_main.render(matrices, vertexConsumer, light, color);
    }
}
