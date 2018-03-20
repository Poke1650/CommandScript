package xyz.poketech.commandscript.util;

import net.minecraft.command.CommandHandler;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.Set;

public class CommandHelper {

    /**
     * Remove a command from the ClientCommandHandler
     * @param name
     */
    public static void removeCommand(String name) {
        Set<ICommand> commandSet = ReflectionHelper.getPrivateValue(
                CommandHandler.class,
                ClientCommandHandler.instance,
                "field_71561_b", "commandSet"
        );

        commandSet.removeIf(iCommand -> iCommand.getName().equals(name));
        ClientCommandHandler.instance.getCommands().remove(name);
    }
}
