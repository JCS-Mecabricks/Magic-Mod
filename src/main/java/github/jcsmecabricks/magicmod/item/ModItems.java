package github.jcsmecabricks.magicmod.item;

import github.jcsmecabricks.magicmod.MagicMod;
import github.jcsmecabricks.magicmod.item.custom.WandItem;
import github.jcsmecabricks.magicmod.item.custom.WizardStaffItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item WIZARD_STAFF = registerItem("wizard_staff",
            new WizardStaffItem(new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.RARE)
                    .maxDamage(255)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM,
                            Identifier.of(MagicMod.MOD_ID, "wizard_staff")))));

    public static final Item WAND = registerItem("wand",
            new WandItem(new Item.Settings()
                    .maxCount(1)
                    .maxDamage(110)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM,
                            Identifier.of(MagicMod.MOD_ID, "wand")))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MagicMod.MOD_ID, name), item);
    }

    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, MagicMod.id(name), item);
    }

    public static void load() {}
}
