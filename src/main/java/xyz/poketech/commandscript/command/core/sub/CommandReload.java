package xyz.poketech.commandscript.command.core.sub;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import xyz.poketech.commandscript.CommandScript;
import xyz.poketech.commandscript.command.custom.CommandRegistry;
import xyz.poketech.commandscript.util.LocalizationHelper;

/**
 * Command to reload all custom commands
 */
public class CommandReload extends CommandBase {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return LocalizationHelper.getCommandUsage(CommandScript.MODID,this);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        CommandRegistry.reloadCommands();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
