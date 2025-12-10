package github.jcsmecabricks.magicmod.block.custom;

import com.mojang.serialization.MapCodec;
import github.jcsmecabricks.magicmod.util.MilkCauldronInteraction;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class MilkCauldronBlock extends AbstractCauldronBlock {
    public MilkCauldronBlock(Settings settings, Map<Item, CauldronBehavior> cauldronBehaviorMap) {
        super(settings, MilkCauldronInteraction.MILK_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends MilkCauldronBlock> getCodec() {
        return null;
    }

    @Override
    protected double getFluidHeight(BlockState state) {
        return 0.25D;
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean bl) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.removeStatusEffect(StatusEffects.POISON);
            livingEntity.removeStatusEffect(StatusEffects.SLOWNESS);
            livingEntity.removeStatusEffect(StatusEffects.MINING_FATIGUE);
            livingEntity.removeStatusEffect(StatusEffects.NAUSEA);
            livingEntity.removeStatusEffect(StatusEffects.BLINDNESS);
            livingEntity.removeStatusEffect(StatusEffects.HUNGER);
            livingEntity.removeStatusEffect(StatusEffects.WEAKNESS);
            livingEntity.removeStatusEffect(StatusEffects.WITHER);
            livingEntity.removeStatusEffect(StatusEffects.BAD_OMEN);
            livingEntity.removeStatusEffect(StatusEffects.DARKNESS);
        }
        super.onEntityCollision(state, world, pos, entity, handler, bl);
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }
}