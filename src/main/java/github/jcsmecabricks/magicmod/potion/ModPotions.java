package github.jcsmecabricks.magicmod.potion;

import github.jcsmecabricks.magicmod.MagicMod;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {

    public static final RegistryEntry<Potion> LEVITATION_POTION = registerPotion("levitation_potion",
            new Potion("levitation_potion", new StatusEffectInstance(StatusEffects.LEVITATION, 600, 0),
                    new StatusEffectInstance(StatusEffects.SLOW_FALLING, 1200, 0)));


    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(MagicMod.MOD_ID, name), potion);
    }

    public static void loadPotions(){

    }
}
