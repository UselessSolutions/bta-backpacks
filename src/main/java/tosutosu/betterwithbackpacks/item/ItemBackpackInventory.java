package tosutosu.betterwithbackpacks.item;

import com.mojang.nbt.tags.CompoundTag;
import com.mojang.nbt.tags.ListTag;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventorySorter;
import net.minecraft.core.player.inventory.container.Container;
import tosutosu.betterwithbackpacks.BetterWithBackpacks;

public class ItemBackpackInventory implements Container {
    public final ItemStack stack;
    protected ItemStack[] backpackItemStacks;
    public ItemBackpackInventory(final ItemStack stack){
        assert stack.getItem() instanceof ItemBackpack;
        this.stack = stack;
        final ItemBackpack itemBackpack = (ItemBackpack) stack.getItem();
        this.backpackItemStacks = new ItemStack[itemBackpack.backpackSize];
        readFromNBT(stack.getData());
    }
    @Override
    public int getContainerSize(){
        return this.backpackItemStacks.length;
    }

    @Override
    public ItemStack getItem(final int i) {
        return this.backpackItemStacks[i];
    }

    @Override
    public ItemStack removeItem(final int i, final int j) {
        if (this.backpackItemStacks[i] != null) {
            if (this.backpackItemStacks[i].stackSize <= j) {
                final ItemStack itemstack = this.backpackItemStacks[i];
                this.backpackItemStacks[i] = null;
                return itemstack;
            }
            final ItemStack itemstack1 = this.backpackItemStacks[i].splitStack(j);
            if (this.backpackItemStacks[i].stackSize <= 0) {
                this.backpackItemStacks[i] = null;
            }
            return itemstack1;
        }
        return null;
    }

    @Override
    public void setItem(final int i, final ItemStack itemStack) {
        this.backpackItemStacks[i] = itemStack;
        if (itemStack != null && itemStack.stackSize > this.getMaxStackSize()) {
            itemStack.stackSize = this.getMaxStackSize();
        }
    }

    @Override
    public String getNameTranslationKey() {
        final String name = this.stack.getCustomName();
        if (name != null){
            return name;
        }
        return this.stack.getDisplayName();
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public void setChanged() {
        writeToNBT(this.stack.getData());
    }

    public final void readFromNBT(final CompoundTag nbttagcompound) {
        final ListTag nbttaglist = nbttagcompound.getList("Items");
        this.backpackItemStacks = new ItemStack[this.getContainerSize()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            final CompoundTag nbttagcompound1 = (CompoundTag)nbttaglist.tagAt(i);
            final byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 < 0 || byte0 >= this.backpackItemStacks.length) continue;
            this.backpackItemStacks[byte0] = ItemStack.readItemStackFromNbt(nbttagcompound1);
        }
    }
    public void writeToNBT(final CompoundTag nbttagcompound) {
        final ListTag nbttaglist = new ListTag();
        for (int i = 0; i < this.backpackItemStacks.length; ++i) {
            if (this.backpackItemStacks[i] == null) continue;
            final CompoundTag nbttagcompound1 = new CompoundTag();
            nbttagcompound1.putByte("Slot", (byte)i);
            this.backpackItemStacks[i].writeToNBT(nbttagcompound1);
            nbttaglist.addTag(nbttagcompound1);
        }
        nbttagcompound.put("Items", nbttaglist);
    }
    @Override
    public boolean stillValid(final Player entityPlayer) {
        if (!BetterWithBackpacks.ENABLE_BACKPACKS){
            return false;
        }
        if (entityPlayer.getHeldItem() == null){
            return false;
        }
        final ItemStack heldItem = entityPlayer.getHeldItem();
        return heldItem.getItem() == this.stack.getItem() &&
            heldItem.getMetadata() == this.stack.getMetadata() &&
            heldItem.stackSize == this.stack.stackSize;
    }

    @Override
    public void sort() {
        InventorySorter.sortInventory(this.backpackItemStacks);
    }
}
