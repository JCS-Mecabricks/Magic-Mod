package github.jcsmecabricks.magicmod.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class WizardStaffItem extends Item {
    public WizardStaffItem(Settings settings) {
        super(settings);
    }

    public static float POWER = 1.5F;

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return super.getUseAction(stack);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (world instanceof ServerWorld serverWorld) {
            ProjectileEntity.spawnWithVelocity((world2, shooter, stack) -> new WindChargeEntity(player, world, player.getPos().getX(), player.getEyePos().getY(), player.getPos().getZ()), serverWorld, itemStack, player, 0.0F, POWER, 1.0F);

            itemStack.damage(1, player, EquipmentSlot.MAINHAND);
    }
        world.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_WIND_CHARGE_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return ActionResult.SUCCESS;
    }
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(!Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.magicmod.wizard_staff.tooltip.shift"));
        } else {
            tooltip.add(Text.translatable("tooltip.magicmod.wizard_staff.tooltip.1"));
            tooltip.add(Text.translatable("tooltip.magicmod.wizard_staff.tooltip.2"));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
