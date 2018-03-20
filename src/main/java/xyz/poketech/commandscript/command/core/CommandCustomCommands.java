package xyz.poketech.commandscript.command.core;

import com.google.common.collect.Lists;
import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;
import xyz.poketech.commandscript.command.core.sub.CommandAdd;
import xyz.poketech.commandscript.command.core.sub.CommandList;
import xyz.poketech.commandscript.command.core.sub.CommandReload;
import xyz.poketech.commandscript.command.core.sub.CommandRemove;

import java.util.List;

/**
 * Tree for all CommandScript commands
 */
public class CommandCustomCommands extends CommandTreeBase {

    public static final List<String> aliases = Lists.newArrayList("commandscript");

    public CommandCustomCommands() {
        addSubcommand(new CommandAdd());
        addSubcommand(new CommandRemove());
        addSubcommand(new CommandReload());
        addSubcommand(new CommandList());
    }

    @Override
    public String getName() {
        return "commandscript";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "";
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
