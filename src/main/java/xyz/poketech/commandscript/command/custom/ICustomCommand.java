package xyz.poketech.commandscript.command.custom;

import net.minecraft.command.ICommand;

public interface ICustomCommand extends ICommand {

    public String getName();

    public String getUsage();

    public String getPath();
}
