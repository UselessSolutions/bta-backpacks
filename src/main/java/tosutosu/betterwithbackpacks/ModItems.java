package tosutosu.betterwithbackpacks;

import net.minecraft.core.item.Item;
import net.minecraft.core.item.Items;
import tosutosu.betterwithbackpacks.item.ItemBackpack;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;

import static tosutosu.betterwithbackpacks.BetterWithBackpacks.MOD_ID;
import static tosutosu.betterwithbackpacks.BetterWithBackpacks.itemID;

public class ModItems {
    public static final Item leatherBackpack = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.ARMOR_BOOTS_LEATHER))
            .build(new ItemBackpack("backpack.leather", MOD_ID + ":item/backpack_leather", itemID++, 9));

    public static final Item ironBackpack = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.ARMOR_BOOTS_IRON))
            .build(new ItemBackpack("backpack.iron", MOD_ID + ":item/backpack_iron", itemID++, 15));

    public static final Item goldBackpack = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.ARMOR_BOOTS_GOLD))
            .build(new ItemBackpack("backpack.gold", MOD_ID + ":item/backpack_gold", itemID++, 21));

    public static final Item diamondBackpack = new ItemBuilder(MOD_ID)
            .setCreativeInventoryPlacement(new CreativeInventoryPlacement.After(() -> Items.ARMOR_BOOTS_DIAMOND))
            .build(new ItemBackpack("backpack.diamond", MOD_ID + ":item/backpack_diamond", itemID++, 27));

    public static void init() {
    }
}
