package tosutosu.betterwithbackpacks.gui.container;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.client.Minecraft;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import net.minecraft.core.player.inventory.menu.MenuAbstract;
import net.minecraft.core.player.inventory.slot.Slot;
import tosutosu.betterwithbackpacks.gui.slots.SlotBackpack;
import tosutosu.betterwithbackpacks.item.ItemBackpackInventory;
import tosutosu.betterwithbackpacks.BackpacksClient;

import java.util.List;

public class ContainerBackpack extends MenuAbstract {
    public ItemBackpackInventory backpackInventory;

    public ContainerBackpack(final ContainerInventory playerInv, final ItemStack backpack) {
        super();
        this.backpackInventory = new ItemBackpackInventory(backpack);
        final int slotsNum = this.backpackInventory.getContainerSize();
        final int rows = (int) Math.ceil(slotsNum / 9d);
        for (int i = 0; i < rows; ++i) {
            int width = 9;
            if (i == rows - 1) {
                width = slotsNum - 9 * i;
            }
            for (int k = 0; k < width; ++k) {
                this.addSlot(new SlotBackpack(this.backpackInventory, k + i * 9, 8 + k * 18, 18 + 18 * i));
            }
        }
        for (int i = 0; i < 3; ++i) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInv, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }
        for (int j = 0; j < 9; ++j) {
            this.addSlot(new Slot(playerInv, j, 8 + j * 18, 142));
        }
    }
    @Override
    public IntList getMoveSlots(final InventoryAction inventoryAction, final Slot slot, final int i, final Player entityPlayer) {
        final int chestSize = this.backpackInventory.getContainerSize();
        if (slot.index >= 0 && slot.index < chestSize) {
            return this.getSlots(0, chestSize, false);
        }
        if (inventoryAction == InventoryAction.MOVE_ALL) {
            if (slot.index >= chestSize && slot.index < chestSize + 27) {
                return this.getSlots(chestSize, 27, false);
            }
            if (slot.index >= chestSize + 27 && slot.index < chestSize + 36) {
                return this.getSlots(chestSize + 27, 9, false);
            }
        } else if (slot.index >= chestSize && slot.index < chestSize + 36) {
            return this.getSlots(chestSize, 36, false);
        }
        return null;
    }
    @Override
    public IntList getTargetSlots(final InventoryAction inventoryAction, final Slot slot, final int i, final Player entityPlayer) {
        final int chestSize = this.backpackInventory.getContainerSize();
        if (slot.index < chestSize) {
            return this.getSlots(chestSize, 36, true);
        }
        return this.getSlots(0, chestSize, false);
    }

    @Override
    public boolean stillValid(final Player entityPlayer) {
        return this.backpackInventory.stillValid(entityPlayer);
    }

    @Override
    public ItemStack clicked(final InventoryAction action, final int[] args, final Player player) {
        int slotId = this.backpackInventory.getContainerSize() + player.inventory.getContainerSize() - 9;
        if (player.inventory.getContainerSize() < 9) slotId = this.backpackInventory.getContainerSize() + player.inventory.getContainerSize() + (9 * 3);
        assert player.world != null;
        if (args != null && args.length >= 1 && args[0] == slotId) {
            if (player.world.isClientSide) {
                Minecraft.getMinecraft().thePlayer.closeScreen();
            }
            return player.getHeldItem();
        }
        final ItemStack is = super.clicked(action, args, player);
        if (player.world.isClientSide) {
            this.backpackInventory.setChanged();
            BackpacksClient.sendStackUpdate(this.backpackInventory.stack.getData());
            if (player.getHeldItem() == null) {
                // Good bye
                Minecraft.getMinecraft().thePlayer.closeScreen();
                return is;
            }
            player.getHeldItem().setData(this.backpackInventory.stack.getData());
        }
        return is;
    }
}
