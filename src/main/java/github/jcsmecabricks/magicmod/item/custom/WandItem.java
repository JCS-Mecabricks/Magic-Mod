package github.jcsmecabricks.magicmod.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;

public class WandItem extends Item {
    private static final Random RANDOM = Random.create();

    public WandItem(Settings settings) {
        super(settings);
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (RANDOM.nextFloat() < 0.25) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1, 0));
        }
        if (RANDOM.nextFloat() < 0.25) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 0));
        }
        if (RANDOM.nextFloat() < 0.25) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 0));
        }
        if (RANDOM.nextFloat() < 0.25) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 600, 0));
        }

        stack.damage(1, attacker, EquipmentSlot.MAINHAND);

        super.postHit(stack, target, attacker);
    }
}