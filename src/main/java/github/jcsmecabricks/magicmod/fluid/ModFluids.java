package github.jcsmecabricks.magicmod.fluid;

import github.jcsmecabricks.magicmod.MagicMod;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static final FlowableFluid STILL_MYSTIC_WATER = Registry.register(Registries.FLUID,
            Identifier.of(MagicMod.MOD_ID, "mystic_water"), new MysticWaterFluid.Still());
    public static final FlowableFluid FLOWING_MYSTIC_WATER = Registry.register(Registries.FLUID,
            Identifier.of(MagicMod.MOD_ID, "flowing_mystic_water"), new MysticWaterFluid.Flowing());

    public static final Block MYSTIC_WATER_BLOCK = Registry.register(Registries.BLOCK, Identifier.of(MagicMod.MOD_ID,
            "mystic_water_block"), new MysticWaterFluidBlock(ModFluids.STILL_MYSTIC_WATER, Block.Settings.copy(Blocks.WATER)
            .replaceable().liquid()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MagicMod.MOD_ID, "mystic_water_block")))));

    public static final Item MYSTIC_WATER_BUCKET = Registry.register(Registries.ITEM, Identifier.of(MagicMod.MOD_ID,
            "mystic_water_bucket"), new BucketItem(ModFluids.STILL_MYSTIC_WATER,
            new Item.Settings().recipeRemainder(Items.BUCKET)
                    .maxCount(1)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MagicMod.MOD_ID, "mystic_water_bucket")))));
    public static void loadFluids() {
    }
}
