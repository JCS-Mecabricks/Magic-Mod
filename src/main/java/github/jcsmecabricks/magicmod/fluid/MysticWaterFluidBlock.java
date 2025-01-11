package github.jcsmecabricks.magicmod.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MysticWaterFluidBlock extends FluidBlock {
    public MysticWaterFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            if (!world.isClient()) {
                if (!livingEntity.hasStatusEffect(StatusEffects.REGENERATION)) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 0)); // 15 seconds
                }

                if (!livingEntity.hasStatusEffect(StatusEffects.STRENGTH)) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 300, 0)); // 15 seconds
                }
            }
        }
    }
}
