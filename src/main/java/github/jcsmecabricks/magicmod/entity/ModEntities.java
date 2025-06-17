package github.jcsmecabricks.magicmod.entity;

import github.jcsmecabricks.magicmod.MagicMod;
import github.jcsmecabricks.magicmod.entity.custom.BroomEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<BroomEntity> BROOM = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MagicMod.MOD_ID, "broom"),
            EntityType.Builder.create(BroomEntity::new, SpawnGroup.MISC).dimensions(0.5f, 0.5f)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE,
                    Identifier.of(MagicMod.MOD_ID, "broom"))));

    public static void loadEntities() {

    }
}
