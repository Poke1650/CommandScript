package xyz.poketech.commandscript.sandbox.wrapper.player;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

import xyz.poketech.commandscript.sandbox.wrapper.item.ItemStackWrapper;
import xyz.poketech.commandscript.sandbox.wrapper.player.inventory.PlayerArmorInvWrapper;
import xyz.poketech.commandscript.sandbox.wrapper.player.inventory.PlayerMainInvWrapper;

/**
 * Wrapper for an EntityPlayer, only allows for restricted read only access
 */
public class PlayerWrapper {

    /**
     * The player wrapped
     */
    EntityPlayer player;

    public PlayerWrapper(EntityPlayer player) {
        this.player = player;
    }

    public BlockPos getPosition() {
        return player.getPosition();
    }

    public String getName() {
        return player.getName();
    }

    public ItemStackWrapper getHeldItem(EnumHand hand) {
        return new ItemStackWrapper(player.getHeldItem(hand));
    }

    public PlayerArmorInvWrapper getArmorInventory() {
        return new PlayerArmorInvWrapper(player.inventory);
    }

    public PlayerMainInvWrapper getMainInventory() {
        return new PlayerMainInvWrapper(player.inventory);
    }
}
