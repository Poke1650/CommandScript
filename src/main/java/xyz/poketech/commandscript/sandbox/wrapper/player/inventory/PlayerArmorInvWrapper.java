package xyz.poketech.commandscript.sandbox.wrapper.player.inventory;

import net.minecraft.entity.player.InventoryPlayer;

import xyz.poketech.commandscript.sandbox.wrapper.item.ItemStackWrapper;

public class PlayerArmorInvWrapper {

    private InventoryPlayer inventory;

    public PlayerArmorInvWrapper(InventoryPlayer inventoryPlayer) {
        this.inventory = inventoryPlayer;
    }

    public ItemStackWrapper getArmorItemInSlot(int slot) {
        return new ItemStackWrapper(inventory.armorItemInSlot(slot));
    }
}
