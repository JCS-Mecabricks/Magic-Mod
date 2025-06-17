package github.jcsmecabricks.magicmod.block;

import github.jcsmecabricks.magicmod.MagicMod;
import github.jcsmecabricks.magicmod.block.custom.ExhibitStandBlock;
import github.jcsmecabricks.magicmod.block.custom.MilkCauldronBlock;
import github.jcsmecabricks.magicmod.block.custom.MysticWaterCauldronBlock;
import github.jcsmecabricks.magicmod.block.custom.WizardLampBlock;
import github.jcsmecabricks.magicmod.util.MilkCauldronInteraction;
import github.jcsmecabricks.magicmod.util.MysticWaterCauldronInteraction;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block WIZARD_LAMP = registerBlock("wizard_lamp",
            new WizardLampBlock(AbstractBlock.Settings.create()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MagicMod.MOD_ID, "wizard_lamp")))
                    .strength(0.5f).luminance(state -> state.get(WizardLampBlock.CLICK) ? 15 : 0)));

    public static final Block EXHIBIT_STAND = registerBlock("exhibit_stand",
            new ExhibitStandBlock(AbstractBlock.Settings.create()
                    .requiresTool()
                    .nonOpaque()
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MagicMod.MOD_ID, "exhibit_stand")))
                    .strength(4f)));

    public static final Block MYSTIC_WATER_CAULDRON = registerBlock("mystic_water_cauldron",
            new MysticWaterCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MagicMod.MOD_ID, "mystic_water_cauldron"))), MysticWaterCauldronInteraction.MYSTIC_WATER));

    public static final Block MILK_CAULDRON = registerBlock("milk_cauldron",
            new MilkCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON)
                    .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MagicMod.MOD_ID, "milk_cauldron"))), MilkCauldronInteraction.MILK));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MagicMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MagicMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MagicMod.MOD_ID, name))).useBlockPrefixedTranslationKey()));
    }

    public static void load() {
    }
}
