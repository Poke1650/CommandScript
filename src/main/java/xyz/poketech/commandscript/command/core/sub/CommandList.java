package xyz.poketech.commandscript.command.core.sub;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import xyz.poketech.commandscript.CommandScript;
import xyz.poketech.commandscript.command.custom.CommandRegistry;
import xyz.poketech.commandscript.command.custom.ICustomCommand;
import xyz.poketech.commandscript.util.LocalizationHelper;

/**
 * List all commands registered
 */
public class CommandList extends CommandBase {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return LocalizationHelper.getCommandUsage(CommandScript.MODID,this);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        StringBuilder builder = new StringBuilder();

        //TODO: format commands a little better
        for(ICustomCommand command : CommandRegistry.getRegistry()) {
            builder.append(command + "\n");
        }
        sender.sendMessage(new TextComponentString(builder.toString()));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
