package xyz.poketech.commandscript.sandbox.wrapper.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import xyz.poketech.commandscript.sandbox.wrapper.NBTWrapper;

public class ItemStackWrapper {

    private ItemStack stack;

    public ItemStackWrapper(ItemStack stack) {
        this.stack = stack;
    }

    public int getRepairCost() {
        return stack.getRepairCost();
    }

    public ItemWrapper getItem() {
        return new ItemWrapper(stack.getItem());
    }

    public int getCount() {
        return stack.getCount();
    }

    public String getDisplayName() {
        return stack.getDisplayName();
    }

    public int getItemDamage() {
        return stack.getItemDamage();
    }

    public int getMaxStackSize() {
        return stack.getMaxStackSize();
    }

    public int getMetadata() {
        return stack.getMetadata();
    }

    public String getUnlocalizedName() {
        return stack.getUnlocalizedName();
    }

    public String getLocalizedName() {
        return new TextComponentTranslation(stack.getUnlocalizedName()).getUnformattedComponentText();
    }

    public NBTWrapper getTagCompound() {
        return new NBTWrapper(stack.getTagCompound());
    }
}
