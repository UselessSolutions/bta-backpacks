package tosutosu.betterwithbackpacks;

import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;

import java.util.Properties;

public class BetterWithBackpacks implements GameStartEntrypoint {
    public static final String MOD_ID = HalpLibe.registerMod("betterwithbackpacks");
    public static int GUI_LABEL_COLOR = 0x404040;
    public static int GUI_BACKPACK_ID;
    public static boolean ENABLE_BACKPACKS;
    public static int itemID;

    static {
        final Properties prop = new Properties();
        prop.setProperty("starting_item_id","21370");
        prop.setProperty("gui_backpack_id","10");
        prop.setProperty("enable_backpacks", "true");
        final ConfigHandler config = new ConfigHandler(MOD_ID, prop);
        itemID = config.getInt("starting_item_id");

        config.updateConfig();
        ENABLE_BACKPACKS = config.getBoolean("enable_backpacks");
        GUI_BACKPACK_ID = config.getInt("gui_backpack_id");
    }


    @Override
    public void beforeGameStart() {
        ModItems.init();
    }

    @Override
    public void afterGameStart() {

    }
}
