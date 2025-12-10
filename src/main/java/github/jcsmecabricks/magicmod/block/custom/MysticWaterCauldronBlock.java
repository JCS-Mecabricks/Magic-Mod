package github.jcsmecabricks.magicmod.block.custom;

import com.mojang.serialization.MapCodec;
import github.jcsmecabricks.magicmod.util.MysticWaterCauldronInteraction;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCollisionHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class MysticWaterCauldronBlock extends AbstractCauldronBlock{
    public MysticWaterCauldronBlock(Settings settings, Map<Item, CauldronBehavior> cauldronBehaviorMap) {
        super(settings, MysticWaterCauldronInteraction.MYSTIC_WATER_CAULDRON_BEHAVIOR);
    }

    @Override
    protected MapCodec<? extends MysticWaterCauldronBlock> getCodec() {
        return null;
    }

    @Override
    protected double getFluidHeight(BlockState state) {
        return 0.25D;
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean bl) {
        if (entity instanceof LivingEntity livingEntity) {
            if (!livingEntity.hasStatusEffect(StatusEffects.REGENERATION)) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 0)); // 15 seconds
            }

            if (!livingEntity.hasStatusEffect(StatusEffects.STRENGTH)) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 300, 0)); // 15 seconds
            }
        }
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }
}