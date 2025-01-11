package github.jcsmecabricks.magicmod;

import github.jcsmecabricks.magicmod.datagen.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MagicModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockLootTable::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModBlockTags::new);
		pack.addProvider(ModFluidTagProvider::new);
	}
}
