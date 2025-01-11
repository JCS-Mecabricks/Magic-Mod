package github.jcsmecabricks.magicmod.block.entity;

import github.jcsmecabricks.magicmod.MagicMod;
import github.jcsmecabricks.magicmod.block.ModBlocks;
import github.jcsmecabricks.magicmod.block.entity.custom.ExhibitStandBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<ExhibitStandBlockEntity> EXHIBIT_BE = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(MagicMod.MOD_ID, "podium_be"),
            FabricBlockEntityTypeBuilder.create(ExhibitStandBlockEntity::new, ModBlocks.EXHIBIT_STAND).build()
    );

    public static void loadBlockEntities() {
        MagicMod.LOGGER.info("Registering Block Entities for " + MagicMod.MOD_ID);
    }
}
