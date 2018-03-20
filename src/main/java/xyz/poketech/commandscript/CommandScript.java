package xyz.poketech.commandscript;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import xyz.poketech.commandscript.command.core.CSCommands;
import xyz.poketech.commandscript.command.custom.CommandRegistry;
import xyz.poketech.commandscript.sandbox.CommandSandboxFactory;

@Mod(modid = CommandScript.MODID, name = CommandScript.NAME, version = CommandScript.VERSION, clientSideOnly = true)
public class CommandScript {

    public static final String MODID = "commandscript";
    public static final String NAME = "Command Script";
    public static final String VERSION = "0.1";

    @Mod.Instance
    public static CommandScript instance;

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //Either first launch of the user deleted the directory
        if (!CommandRegistry.COMMANDS_DIR.exists()) {
            CommandRegistry.COMMANDS_DIR.mkdirs();
            LOGGER.info("Created missing directory for commands at {}", CommandRegistry.COMMANDS_DIR.getAbsoluteFile());
        }

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        CSCommands.register(); //Register CommandScript commands

        CommandSandboxFactory.init();
        CommandRegistry.init(); //Register Custom commands
    }
}
