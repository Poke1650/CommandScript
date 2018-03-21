package xyz.poketech.commandscript.command.core.pastebin.sub;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.FilenameUtils;
import xyz.poketech.commandscript.CommandScript;
import xyz.poketech.commandscript.util.LocalizationHelper;
import xyz.poketech.commandscript.util.PasteHelper;

import java.io.IOException;

public class CommandGet extends CommandBase {
    @Override
    public String getName() {
        return "get";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.commandscript.pastebin.get.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        if (args.length < 2 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(LocalizationHelper.getFormattedResultMessage(false, getUsage(sender)));
        } else {
            new Thread(() -> {
                try {
                    PasteHelper.downloadPaste(args[0], args[1]);
                    sender.sendMessage(LocalizationHelper.getFormattedResultMessage(true, "command.commandscript.pastebin.get.success", args[0], args[1]));
                } catch (IOException e) {
                    sender.sendMessage(LocalizationHelper.getFormattedResultMessage(false, "command.commandscript.pastebin.get.error", args[0]));
                    CommandScript.LOGGER.error("Error downloading paste " + args[0], e);
                }
            }).start();
        }
    }
}
