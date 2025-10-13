package github.jcsmecabricks.magicmod.block.entity.renderer;

import github.jcsmecabricks.magicmod.block.entity.custom.ExhibitStandBlockEntity;
import github.jcsmecabricks.magicmod.block.entity.renderer.ExhibitStandBlockRenderState;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ExhibitStandBlockEntityRenderer implements BlockEntityRenderer<ExhibitStandBlockEntity, ExhibitStandBlockRenderState> {
    private final ItemModelManager itemModelManager;

    public ExhibitStandBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        itemModelManager = context.itemModelManager();
    }

    @Override
    public ExhibitStandBlockRenderState createRenderState() {
        return new ExhibitStandBlockRenderState();
    }

    @Override
    public void updateRenderState(ExhibitStandBlockEntity blockEntity, ExhibitStandBlockRenderState state, float tickProgress,
                                  Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay) {
        BlockEntityRenderer.super.updateRenderState(blockEntity, state, tickProgress, cameraPos, crumblingOverlay);

        state.lightPosition = blockEntity.getPos();
        state.blockEntityWorld = blockEntity.getWorld();
        state.rotation = blockEntity.getRenderingRotation();

        itemModelManager.clearAndUpdate(state.itemRenderState,
                blockEntity.getStack(0), ItemDisplayContext.FIXED, blockEntity.getWorld(), null, 0);
    }

    @Override
    public void render(ExhibitStandBlockRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        matrices.push();

        matrices.translate(0.5f, 1.15f, 0.5f);
        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.rotation));

        state.itemRenderState.render(matrices, queue, getLightLevel(state.blockEntityWorld, state.pos), OverlayTexture.DEFAULT_UV, 0);

        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}