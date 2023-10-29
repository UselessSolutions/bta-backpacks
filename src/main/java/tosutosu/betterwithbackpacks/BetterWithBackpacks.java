package tosutosu.betterwithbackpacks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Global;
import net.minecraft.server.net.handler.NetServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tosutosu.betterwithbackpacks.crafting.ModCraftingManager;
import tosutosu.betterwithbackpacks.item.ModItems;
import turniplabs.halplibe.util.ConfigHandler;

import java.util.Properties;

public class BetterWithBackpacks implements ModInitializer {
    public static final String MOD_ID = "betterwithbackpacks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static int GUI_LABEL_COLOR = 0x404040;
    public static int GUI_BACKPACK_ID = 10;
    public static boolean ENABLE_BACKPACKS = true;

    private void handleConfig() {
        Properties prop = new Properties();
        prop.setProperty("starting_block_id","2137");
        prop.setProperty("starting_item_id","21370");
        prop.setProperty("gui_backpack_id","10");
        prop.setProperty("enable_backpacks", "true");
        ConfigHandler config = new ConfigHandler(MOD_ID,prop);
        UtilIdRegistrar.initIds(
                config.getInt("starting_block_id"),
                config.getInt("starting_item_id"));
        config.updateConfig();
        ENABLE_BACKPACKS = config.getBoolean("enable_backpacks");
        GUI_BACKPACK_ID = config.getInt("gui_backpack_id");
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Better with Backpacks! is loading...");
        handleConfig();

        UtilIdRegistrar.setIdToItem();
        ModItems.register();
        UtilIdRegistrar.setIdToBlock();
        if (ENABLE_BACKPACKS){
            ModCraftingManager.register();
        }


        LOGGER.info("Better with Backpacks! initialized.");
    }
    public static void Log(String message){
        if (Global.isServer && NetServerHandler.logger != null){
            NetServerHandler.logger.info(message);
        } else {
            LOGGER.info(message);
        }
    }
}