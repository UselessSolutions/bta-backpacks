package tosutosu.betterwithbackpacks.mixin;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.net.packet.PacketCustomPayload;
import net.minecraft.server.entity.player.PlayerServer;
import net.minecraft.server.net.handler.PacketHandlerServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tosutosu.betterwithbackpacks.BackpacksClient;

@Mixin(value= PacketHandlerServer.class,remap = false)
public abstract class PacketHandlerServerMixin {
    @Shadow
    private PlayerServer playerEntity;

    @Inject(method="handleCustomPayload",at=@At("HEAD"),cancellable=true)
    public void inject(final PacketCustomPayload packet, final CallbackInfo ci) {
        if ("backpacks$setItems".equals(packet.channel)) {
            ci.cancel();
            final int len = packet.getEstimatedSize() - (packet.channel.length() + 4);
            final CompoundTag tag = BackpacksClient.getStackUpdate(packet.data, len);
            final ItemStack heldItem;
            if ((heldItem = this.playerEntity.inventory.getItem(this.playerEntity.inventory.getContainerSize())) != null && tag != null) {
                heldItem.setData(tag);
            }
        }
    }
}
