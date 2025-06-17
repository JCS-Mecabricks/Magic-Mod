package github.jcsmecabricks.magicmod.entity.client;

import github.jcsmecabricks.magicmod.MagicMod;
import github.jcsmecabricks.magicmod.entity.custom.BroomEntity;
import github.jcsmecabricks.magicmod.entity.custom.BroomModel;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;

public class BroomRenderer extends EntityRenderer<BroomEntity, BroomRenderState> {
    private static final Identifier TEXTURE = Identifier.of(MagicMod.MOD_ID, "textures/entity/broom/broom.png");
    private final EntityModel<BroomRenderState> model;

    public BroomRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new BroomModel<>(context.getPart(ModModelLayers.BROOM));
    }

    @Override
    public void render(BroomRenderState state, MatrixStack matrices, VertexConsumerProvider consumers, int light) {
        matrices.push();
        matrices.scale(-1.0F, -1.0F, 1.0F);
        matrices.translate(0.0F, -0.3f, 0.0f);
        model.setAngles(state);
        VertexConsumer buffer = consumers.getBuffer(model.getLayer(TEXTURE));
        model.render(matrices, buffer, light, OverlayTexture.DEFAULT_UV);
        matrices.pop();
        super.render(state, matrices, consumers, light);
    }

    @Override
    public BroomRenderState createRenderState() {
        return new BroomRenderState();
    }

    public static Identifier getTexture(BroomRenderState state) {
        return TEXTURE;
    }
}
