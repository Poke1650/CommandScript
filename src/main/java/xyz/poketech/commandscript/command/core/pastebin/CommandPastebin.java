package xyz.poketech.commandscript.command.core.pastebin;

import net.minecraft.command.ICommandSender;
import net.minecraftforge.server.command.CommandTreeBase;
import xyz.poketech.commandscript.command.core.pastebin.sub.CommandGet;


public class CommandPastebin extends CommandTreeBase{

    public CommandPastebin() {
        addSubcommand(new CommandGet());
    }

    @Override
    public String getName() {
        return "pastebin";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/pastebin";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
