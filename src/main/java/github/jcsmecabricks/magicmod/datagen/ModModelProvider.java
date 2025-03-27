package github.jcsmecabricks.magicmod.datagen;

import github.jcsmecabricks.magicmod.block.ModBlocks;
import github.jcsmecabricks.magicmod.block.custom.WizardLampBlock;
import github.jcsmecabricks.magicmod.fluid.ModFluids;
import github.jcsmecabricks.magicmod.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.util.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EXHIBIT_STAND);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.WIZARD_STAFF);
        itemModelGenerator.register(ModItems.WAND);
        itemModelGenerator.register(ModFluids.MYSTIC_WATER_BUCKET, Models.GENERATED);

    }
}
