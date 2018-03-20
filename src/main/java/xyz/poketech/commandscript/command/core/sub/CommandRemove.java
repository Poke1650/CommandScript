package xyz.poketech.commandscript.command.core.sub;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import xyz.poketech.commandscript.CommandScript;
import xyz.poketech.commandscript.command.custom.CommandRegistry;
import xyz.poketech.commandscript.util.LocalizationHelper;

import java.util.NoSuchElementException;

/**
 * Command to remove a custom command
 */
public class CommandRemove extends CommandBase {
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return LocalizationHelper.getCommandUsage(CommandScript.MODID, this);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(LocalizationHelper.getFormattedResultMessage(false, getUsage(sender)));
        } else {
            try {
                CommandRegistry.remove(args[0]);
            } catch (NoSuchElementException e) {
                e.printStackTrace();
                sender.sendMessage(LocalizationHelper.getFormattedResultMessage(false, "command.commandscript.remove.error", args[0], e.getMessage()));
            }
            sender.sendMessage(LocalizationHelper.getFormattedResultMessage(true,"command.commandscript.remove.success", args[0]));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
