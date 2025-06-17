package github.jcsmecabricks.magicmod.util;

import github.jcsmecabricks.magicmod.block.ModBlocks;
import github.jcsmecabricks.magicmod.fluid.ModFluids;
import net.minecraft.block.BlockState;
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

public class MilkCauldronInteraction implements CauldronBehavior {

    public static CauldronBehavior.CauldronBehaviorMap MILK_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("milk");
    public static Map<Item, CauldronBehavior> MILK = MILK_CAULDRON_BEHAVIOR.map();

    public static Map<Item, CauldronBehavior> EMPTY = EMPTY_CAULDRON_BEHAVIOR.map();

    @Override
    public ActionResult interact(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, ItemStack stack) {
        return CauldronBehavior.fillCauldron(world, pos, player, hand, stack, ModBlocks.MILK_CAULDRON.getDefaultState(), SoundEvents.ITEM_BOTTLE_EMPTY);
    }

    public static void registerBehaviour() {
        EMPTY.put(Items.MILK_BUCKET, new MilkCauldronInteraction());
        MILK.put(
                Items.BUCKET,
                (state, world, pos, player, hand, stack) -> CauldronBehavior.emptyCauldron(
                        state, world, pos, player, hand, stack, new ItemStack(Items.MILK_BUCKET), statex -> true, SoundEvents.ITEM_BUCKET_FILL
                )
        );
    }
}