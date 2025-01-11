package github.jcsmecabricks.magicmod.screen;

import github.jcsmecabricks.magicmod.MagicMod;
import github.jcsmecabricks.magicmod.screen.custom.ExhibitStandScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<ExhibitStandScreenHandler> EXHIBIT_STAND_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(MagicMod.MOD_ID, "exhibit_stand_screen_handler"),
                    new ExtendedScreenHandlerType<>(ExhibitStandScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void loadModScreenHandlers() {
        MagicMod.LOGGER.info("Registering Screen Handlers for " + MagicMod.MOD_ID);
    }
}
