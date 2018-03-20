package xyz.poketech.commandscript.command.core;

import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import xyz.poketech.commandscript.CommandScript;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * CommandScript core commands
 */
public class CSCommands {

    public static final ICommand COMMAND_CUSTOM_COMMANDS = new CommandCustomCommands();

    /**
     * Register every static ICommand fields in this class on the client
     */
    public static void register() {
        Field[] fields = CSCommands.class.getDeclaredFields();

        for (Field field : fields) {
            if (field.getType() == ICommand.class && Modifier.isStatic(field.getModifiers())) {
                try {
                    ICommand cmd = (ICommand) field.get(null);
                    ClientCommandHandler.instance.registerCommand(cmd);
                    CommandScript.LOGGER.info("Registered command {}", cmd.getName());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
