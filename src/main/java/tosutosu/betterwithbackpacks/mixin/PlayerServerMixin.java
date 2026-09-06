package tosutosu.betterwithbackpacks.mixin;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.packet.PacketContainerOpen;
import net.minecraft.server.entity.player.PlayerServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.IPlayerDisplay;
import tosutosu.betterwithbackpacks.gui.container.ContainerBackpack;
import tosutosu.betterwithbackpacks.item.ItemBackpack;

@Mixin(value = PlayerServer.class, remap = false)
public class PlayerServerMixin implements IPlayerDisplay {
    @Unique
    private final PlayerServer thisAs = (PlayerServer)(Object)this;
    @Shadow
    private void getNextWindowId() {}
    @Shadow
    private int currentWindowId = 0;
    @Override
    public void bta_backpacks$displayGUIBackpack(final ItemStack stack) {
        if (stack != null && stack.getItem() instanceof ItemBackpack) {
            getNextWindowId();
            final ContainerBackpack backpack = new ContainerBackpack(this.thisAs.inventory, stack);
            this.thisAs.playerNetServerHandler.sendPacket(new PacketContainerOpen(this.currentWindowId, BetterWithBackpacks.GUI_BACKPACK_ID, "Backpack", backpack.backpackInventory.getContainerSize()));
            this.thisAs.containerMenu = backpack;
            this.thisAs.containerMenu.containerId = this.currentWindowId;
            this.thisAs.containerMenu.addSlotListener(this.thisAs);
        }
    }
}
