package github.jcsmecabricks.magicmod.entity.client;

import github.jcsmecabricks.magicmod.MagicMod;
import github.jcsmecabricks.magicmod.entity.client.BroomRenderState;
import github.jcsmecabricks.magicmod.entity.custom.BroomEntity;
import github.jcsmecabricks.magicmod.entity.custom.BroomModel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.LightType;

import java.util.List;

public class BroomRenderer extends EntityRenderer<BroomEntity, BroomRenderState> {
    private static final Identifier TEXTURE = Identifier.of(MagicMod.MOD_ID, "textures/entity/broom/broom.png");
    private final EntityModel<BroomRenderState> model;

    public BroomRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new BroomModel<>(ctx.getPart(ModModelLayers.BROOM));
    }

    @Override
    public BroomRenderState createRenderState() {
        return new BroomRenderState();
    }

    @Override
    public void updateRenderState(BroomEntity entity, BroomRenderState state, float tickDelta) {
        // Always call super first so basic state is filled
        super.updateRenderState(entity, state, tickDelta);

        // Now copy what your renderer needs
        state.light = computePackedLight(entity);
        state.pitch = entity.getPitch();  // if you want rotation
        state.yaw = entity.getYaw();
        // ... any other fields you need in the state
    }

    @Override
    public void render(BroomRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        matrices.push();
        // Flip / invert axes as needed depending on model orientation
        matrices.scale(-1.0F, -1.0F, 1.0F);
        matrices.translate(0.0F, -0.3f, 0.0f);

        model.setAngles(state);  // pass the state, not entity
        List<RenderLayer> layers = ItemRenderer.getGlintRenderLayers(model.getLayer(TEXTURE), false, false);

        for (int i = 0; i < layers.size(); i++) {
            queue.getBatchingQueue(i).submitModel(
                    model,
                    state,
                    matrices,
                    layers.get(i),
                    state.light,
                    OverlayTexture.DEFAULT_UV,
                    -1,
                    (Sprite) null,
                    state.outlineColor,
                    null
            );
        }

        matrices.pop();

        super.render(state, matrices, queue, cameraState);
    }

    public static Identifier getTEXTURE() {
        return TEXTURE;
    }

    private int computePackedLight(BroomEntity entity) {
        if (entity.getEntityWorld() == null) return 0;
        var pos = entity.getBlockPos();
        int b = entity.getEntityWorld().getLightLevel(LightType.BLOCK, pos);
        int s = entity.getEntityWorld().getLightLevel(LightType.SKY, pos);
        return net.minecraft.client.render.LightmapTextureManager.pack(b, s);
    }
}