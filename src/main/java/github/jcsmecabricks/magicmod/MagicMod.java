package github.jcsmecabricks.magicmod;

import github.jcsmecabricks.magicmod.block.ModBlocks;
import github.jcsmecabricks.magicmod.block.entity.ModBlockEntities;
import github.jcsmecabricks.magicmod.fluid.ModFluids;
import github.jcsmecabricks.magicmod.group.ModGroups;
import github.jcsmecabricks.magicmod.item.ModItems;
import github.jcsmecabricks.magicmod.potion.ModPotions;
import github.jcsmecabricks.magicmod.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MagicMod implements ModInitializer {
	public static final String MOD_ID = "magicmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
//I credit Kaupenjoe's Tutorial Mod for this mod
	@Override
	public void onInitialize() {
		ModItems.load();
		ModGroups.load();
		ModBlocks.load();
		ModBlockEntities.loadBlockEntities();
		ModScreenHandlers.loadModScreenHandlers();
		ModPotions.loadPotions();
		ModFluids.loadFluids();
		LOGGER.info("Loading...");
		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.AWKWARD, Items.WIND_CHARGE, ModPotions.LEVITATION_POTION);
		});
		AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if(entity instanceof LivingEntity livingEntity && !world.isClient()) {
				if(player.getMainHandStack().getItem() == ModItems.WIZARD_STAFF) {
					livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 600, 1));
					livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 600, 1));
				}

				return ActionResult.PASS;
			}

			return ActionResult.PASS;
		});

	}
	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}