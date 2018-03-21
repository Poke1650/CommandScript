package xyz.poketech.commandscript.sandbox.wrapper.item;

import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentTranslation;

public class ItemWrapper {

    private Item item;

    public ItemWrapper(Item item) {
        this.item = item;

        item.getUnlocalizedName();
    }

    public String getUnlocalizedName() {
        return item.getUnlocalizedName();
    }

    public String getLocalizedName() {
        return new TextComponentTranslation(item.getUnlocalizedName()).getUnformattedComponentText();
    }

    public String getRegistryName() {
        return item.getRegistryName().toString();
    }
}
