package xyz.poketech.commandscript.util;

import net.minecraft.command.ICommand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

/**
 * Utility methods to localize
 */
public class LocalizationHelper {

    /**
     * Get the localized command usage
     * Assuming that you follow the format command.modid.commandname.usage for the lang key
     *
     * @param command the command we want the usage of
     * @return the usage of the command localized
     */
    public static String getCommandUsage(String modid, ICommand command) {
        return String.format("command.%s.%s.usage", modid, command.getName());
    }

    /**
     * Returns a formatted message for a result that either failed or succeeded.
     * If it did error, the message will be formatted in red, else it will be in green
     * @param success if the result is a success or a failure
     * @param key the lang key
     * @param args arguments for the lang entry
     * @return localized text component formatted accordingly
     */
    public static ITextComponent getFormattedResultMessage(boolean success, String key, String... args) {
        return new TextComponentTranslation(key, args).setStyle(new Style().setColor(success ? TextFormatting.GREEN : TextFormatting.RED));
    }
}
