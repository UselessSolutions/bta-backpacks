package tosutosu.betterwithbackpacks.item;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.IPlayerDisplay;

public class ItemBackpack extends Item {
    public final int backpackSize;
    public ItemBackpack(final String translationKey, final String namespaceId, final int id) {
        this(translationKey, namespaceId, id, 9);
    }
    public ItemBackpack(final String translationKey, final String namespaceId, final int id, final int backpackSize) {
        super(translationKey, namespaceId, id);
        this.maxStackSize = 1;
        this.backpackSize = backpackSize;
    }
    @Override
    public ItemStack onUse(final ItemStack itemstack, final World world, final Player entityplayer) {
        if (BetterWithBackpacks.ENABLE_BACKPACKS) {
            //noinspection CastToIncompatibleInterface
            ((IPlayerDisplay) entityplayer).bta_backpacks$displayGUIBackpack(itemstack);
        }
        return itemstack;
    }
}
