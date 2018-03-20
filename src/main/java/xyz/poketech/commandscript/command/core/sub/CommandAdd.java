package xyz.poketech.commandscript.command.core.sub;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import xyz.poketech.commandscript.CommandScript;
import xyz.poketech.commandscript.command.custom.CustomCommand;
import xyz.poketech.commandscript.command.custom.CommandRegistry;
import xyz.poketech.commandscript.util.LocalizationHelper;

/**
 * Command to add custom commands
 */
public class CommandAdd extends CommandBase {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return LocalizationHelper.getCommandUsage(CommandScript.MODID,this);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 3 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(LocalizationHelper.getFormattedResultMessage(false, getUsage(sender)));
        } else {
            try {
                CommandRegistry.add(new CustomCommand(args[0], args[1], args[2]));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                sender.sendMessage(LocalizationHelper.getFormattedResultMessage(false, "command.commandscript.add.error", e.getMessage()));
            }
            sender.sendMessage(LocalizationHelper.getFormattedResultMessage(true,"command.commandscript.add.success", args[0]));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
