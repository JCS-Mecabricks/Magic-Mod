package github.jcsmecabricks.magicmod.util;

import github.jcsmecabricks.magicmod.block.ModBlocks;
import github.jcsmecabricks.magicmod.fluid.ModFluids;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class MysticWaterCauldronInteraction implements CauldronBehavior {

    public static CauldronBehavior.CauldronBehaviorMap MYSTIC_WATER_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("mystic_water");
    public static Map<Item, CauldronBehavior> MYSTIC_WATER = MYSTIC_WATER_CAULDRON_BEHAVIOR.map();

    public static Map<Item, CauldronBehavior> EMPTY = EMPTY_CAULDRON_BEHAVIOR.map();

    @Override
    public ActionResult interact(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        return CauldronBehavior.fillCauldron(world, pos, player, hand, stack, ModBlocks.MYSTIC_WATER_CAULDRON.getDefaultState(), SoundEvents.ITEM_BOTTLE_EMPTY);
    }

    public static void registerBehaviour() {
        EMPTY.put(ModFluids.MYSTIC_WATER_BUCKET, new MysticWaterCauldronInteraction());
        MYSTIC_WATER.put(
                Items.BUCKET,
                (state, world, pos, player, hand, stack) -> CauldronBehavior.emptyCauldron(
                        state, world, pos, player, hand, stack, new ItemStack(ModFluids.MYSTIC_WATER_BUCKET), statex -> true, SoundEvents.ITEM_BUCKET_FILL
                )
        );
    }
}