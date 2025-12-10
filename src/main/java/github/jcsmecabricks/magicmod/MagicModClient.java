package github.jcsmecabricks.magicmod;

import github.jcsmecabricks.magicmod.block.entity.ModBlockEntities;
import github.jcsmecabricks.magicmod.block.entity.renderer.ExhibitStandBlockEntityRenderer;
import github.jcsmecabricks.magicmod.entity.*;
import github.jcsmecabricks.magicmod.entity.client.BroomRenderer;
import github.jcsmecabricks.magicmod.entity.client.ModModelLayers;
import github.jcsmecabricks.magicmod.entity.custom.BroomModel;
import github.jcsmecabricks.magicmod.fluid.ModFluids;
import github.jcsmecabricks.magicmod.screen.ModScreenHandlers;
import github.jcsmecabricks.magicmod.screen.custom.ExhibitStandScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class MagicModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(ModBlockEntities.EXHIBIT_BE, ExhibitStandBlockEntityRenderer::new);
        HandledScreens.register(ModScreenHandlers.EXHIBIT_STAND_SCREEN_HANDLER, ExhibitStandScreen::new);
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_MYSTIC_WATER, ModFluids.FLOWING_MYSTIC_WATER,
                SimpleFluidRenderHandler.coloredWater(0xFFEE9EF7));
        BlockRenderLayerMap.putFluids(BlockRenderLayer.TRANSLUCENT,
                ModFluids.STILL_MYSTIC_WATER, ModFluids.FLOWING_MYSTIC_WATER);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BROOM, BroomModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BROOM, BroomRenderer::new);
    }
}