package github.jcsmecabricks.magicmod.datagen;

import github.jcsmecabricks.magicmod.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTags extends FabricTagProvider.BlockTagProvider {
    public ModBlockTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.EXHIBIT_STAND);

        valueLookupBuilder(BlockTags.CAULDRONS)
                .add(ModBlocks.MILK_CAULDRON)
                .add(ModBlocks.MYSTIC_WATER_CAULDRON);

        valueLookupBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.EXHIBIT_STAND);
    }
}
