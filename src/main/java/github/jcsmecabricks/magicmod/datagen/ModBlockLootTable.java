package github.jcsmecabricks.magicmod.datagen;

import github.jcsmecabricks.magicmod.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTable extends FabricBlockLootTableProvider {
    public ModBlockLootTable(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.WIZARD_LAMP);
        addDrop(ModBlocks.EXHIBIT_STAND);
    }
}
