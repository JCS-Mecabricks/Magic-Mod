package github.jcsmecabricks.magicmod.group;

import github.jcsmecabricks.magicmod.MagicMod;
import github.jcsmecabricks.magicmod.block.ModBlocks;
import github.jcsmecabricks.magicmod.fluid.ModFluids;
import github.jcsmecabricks.magicmod.item.ModItems;
import github.jcsmecabricks.magicmod.potion.ModPotions;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.Optional;

public class ModGroups {
    public static final Text MAGIC_TITLE = Text.translatable("itemGroup." + MagicMod.MOD_ID + ".magic_mod_group");
    public static final ItemGroup MAGIC_MOD_GROUP = register("magic_mod_group", FabricItemGroup.builder()
            .displayName(MAGIC_TITLE)
            .icon(ModItems.WIZARD_STAFF::getDefaultStack)
            .entries((displayContext, entries) -> {
                entries.add(ModItems.WIZARD_STAFF);
                entries.add(ModBlocks.WIZARD_LAMP);
                entries.add(ModItems.WAND);
                entries.add(ModBlocks.EXHIBIT_STAND);
                entries.add(ModFluids.MYSTIC_WATER_BUCKET);
            }).build());
    public static <T extends ItemGroup> T register(String name, T itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, MagicMod.id(name), itemGroup);
    }
    public static void load() {
    }
}