package tosutosu.betterwithbackpacks.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.net.handler.PacketHandlerClient;
import net.minecraft.core.net.packet.PacketContainerOpen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;
import tosutosu.betterwithbackpacks.IPlayerDisplay;

@Mixin(value= PacketHandlerClient.class,remap = false)
public abstract class PacketHandlerClientMixin {
    @Final
    @Shadow
    private Minecraft mc;

    @Inject(method="handleContainerOpen", at=@At("TAIL"))
    public void handleOpenWindow_injection(final PacketContainerOpen packet, final CallbackInfo ci) {
        if (packet.inventoryType == BetterWithBackpacks.GUI_BACKPACK_ID) {
            //noinspection CastToIncompatibleInterface
            ((IPlayerDisplay) (this.mc.thePlayer)).bta_backpacks$displayGUIBackpack(this.mc.thePlayer.getHeldItem());
            this.mc.thePlayer.containerMenu.containerId = packet.windowId;
        }
    }
}
