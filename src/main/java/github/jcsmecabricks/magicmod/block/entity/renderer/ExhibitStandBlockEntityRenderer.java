package github.jcsmecabricks.magicmod.block.entity.renderer;

import github.jcsmecabricks.magicmod.block.entity.custom.ExhibitStandBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ExhibitStandBlockEntityRenderer implements BlockEntityRenderer<ExhibitStandBlockEntity, ExhibitStandBlockRenderState> {
    public ExhibitStandBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }

    @Override
    public ExhibitStandBlockRenderState createRenderState() {
        return new ExhibitStandBlockRenderState();
    }

    @Override
    public void updateRenderState(ExhibitStandBlockEntity blockEntity, ExhibitStandBlockRenderState state, float tickProgress, Vec3d cameraPos, @Nullable ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlay) {
        state.itemStack = blockEntity.getStack(0);
        state.renderingRotation = blockEntity.getRenderingRotation();
        state.pos = blockEntity.getPos();
    }

    @Override
    public void render(ExhibitStandBlockRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        ItemStack stack = state.itemStack;
        if (stack == null || stack.isEmpty()) return;

        matrices.push();
        matrices.translate(0.5f, 1.15f, 0.5f);
        matrices.scale(0.5f, 0.5f, 0.5f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(state.getRenderingRotation()));

        HeldItemRenderer itemRenderer = MinecraftClient.getInstance().getEntityRenderDispatcher().getHeldItemRenderer();

        itemRenderer.renderItem(
                MinecraftClient.getInstance().player,
                stack,
                ItemDisplayContext.GUI,
                matrices,
                queue,
                getLightLevel(state.getWorld(), state.pos)
        );

        matrices.pop();
    }

}
