package xyz.poketech.commandscript.sandbox.wrapper.player.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import xyz.poketech.commandscript.sandbox.wrapper.item.ItemStackWrapper;

public class PlayerMainInvWrapper {

    InventoryPlayer inventory;

    public PlayerMainInvWrapper(InventoryPlayer inventory) {
        this.inventory = inventory;
    }

    public ItemStackWrapper getStackInSlot(int slot) {
        return new ItemStackWrapper(this.inventory.getStackInSlot(slot));
    }

    public int getInventorySize() {
        return this.inventory.getSizeInventory();
    }
}
