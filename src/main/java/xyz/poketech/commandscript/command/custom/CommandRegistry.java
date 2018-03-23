package xyz.poketech.commandscript.command.custom;

import com.google.common.base.Stopwatch;
import com.google.gson.reflect.TypeToken;
import net.minecraftforge.client.ClientCommandHandler;
import xyz.poketech.commandscript.CommandScript;
import xyz.poketech.commandscript.util.CommandHelper;
import xyz.poketech.commandscript.util.JsonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CommandRegistry {

    public static final File COMMANDS_DIR = new File("commands/");

    public static final File INDEX = new File("commands/index.json");

    private static List<ICustomCommand> registry = new ArrayList<>();

    /**
     * Register all commands in the index
     */
    public static void init() {

        Stopwatch stopwatch = Stopwatch.createStarted();
        CommandScript.LOGGER.info("Loading indexed commands at {}", INDEX.getAbsoluteFile());

        List<CustomCommand> commands = JsonUtil.fromJson(new TypeToken<List<CustomCommand>>(){}, INDEX);

        if (commands != null) {
            commands.forEach(CommandRegistry::add);
        }

        CommandScript.LOGGER.info("Loaded {} command(s) from index in {}", registry.size(), stopwatch.stop());
    }

    /**
     * @return a copy of the registry
     */
    public static List<ICustomCommand> getRegistry() {
        ArrayList<ICustomCommand> copy = new ArrayList<>();
        copy.addAll(registry);
        return copy;
    }

    /**
     * Remove all commands and reload them from the index
     */
    public static void reloadCommands() {

        //Remove all commands from minecraft registries
        for(ICustomCommand command : registry) {
            CommandHelper.removeCommand(command.getName());
        }

        registry.clear();

        //Load all commands from index
        init();
    }

    /**
     * Remove a command from the Minecraft command registry and from the internal registry
     * @throws NoSuchElementException if there is no command with such name registered
     */
    public static void remove(ICustomCommand command) throws NoSuchElementException {
        remove(command.getName());
    }

    /**
     * Remove a command from the Minecraft command registry and from the internal registry
     * @throws NoSuchElementException if there is no command with such name registered
     * @param command the command to remove
     */
    public static void remove(String command) throws NoSuchElementException {
        if(!commandNameRegistred(command)) {
            throw new NoSuchElementException("No command found for name " + command);
        }

        CommandHelper.removeCommand(command);
        registry.removeIf(customCommand -> customCommand.getName().equals(command));
        writeToDisk();
        CommandScript.LOGGER.info("Removed command {}", command);
    }

    /**
     * Adds a command to the client command handler and to the internal registry
     * @throws IllegalArgumentException if a command is already registered with the same name
     * @param command the command to add
     */
    public static void add(ICustomCommand command) throws IllegalArgumentException {
        if(commandNameRegistred(command.getName())) {
            throw new IllegalArgumentException(String.format("Command with name %s is already registered!", command.getName()));
        }

        ClientCommandHandler.instance.registerCommand(command);
        registry.add(command);
        writeToDisk();
        CommandScript.LOGGER.info("Registered command {}", command.getName());
    }

    /**
     * Serialize the registry and writes it to the index file
     */
    private static void writeToDisk() {
        JsonUtil.toJson(registry, new TypeToken<List<ICustomCommand>>(){}, INDEX);
    }

    /**
     * @param command the command to check
     * @return if a command with that name is already registred
     */
    private static boolean commandNameRegistred(ICustomCommand command) {
        return commandNameRegistred(command.getName());
    }

    /**
     * @param name the name of the command to check
     * @return if a command with that name is already registred
     */
    private static boolean commandNameRegistred(String name) {
        for (ICustomCommand command : registry) {
            if (command.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
