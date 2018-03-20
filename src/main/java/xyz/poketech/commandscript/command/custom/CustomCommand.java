package xyz.poketech.commandscript.command.custom;

import delight.nashornsandbox.NashornSandbox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import xyz.poketech.commandscript.CommandScript;
import xyz.poketech.commandscript.sandbox.CommandSandboxFactory;
import xyz.poketech.commandscript.sandbox.wrapper.player.PlayerWrapper;
import xyz.poketech.commandscript.sandbox.wrapper.WorldWrapper;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

/**
 * A custom command that will execute Javascript when ran
 */
public class CustomCommand extends CommandBase implements ICustomCommand {

    private String name;
    private String path;
    private String usage;

    public CustomCommand(String name, String scriptPath, String usage) {
        this.name = name;
        this.path = scriptPath;
        this.usage = usage;
    }

    private CustomCommand() {}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return usage;
    }

    @Override
    public String getUsage() {
        return usage;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        //Even if the sandbox execution is on an other thread
        //this will hang the thread on which commands execute if not done on its own thread.
        new Thread(() -> {
            StringWriter writer = new StringWriter();

            NashornSandbox sandbox = CommandSandboxFactory.getSandbox();

            sandbox.setWriter(writer);

            sandbox.inject("world", new WorldWrapper(sender.getEntityWorld()));
            sandbox.inject("args", args);
            sandbox.inject("player", new PlayerWrapper((EntityPlayerSP) sender));

            try {
                sandbox.eval(FileUtils.readFileToString(new File(CommandRegistry.COMMANDS_DIR + File.separator + path), StandardCharsets.UTF_8));
            } catch (ScriptException e) {
                CommandScript.LOGGER.error("Error running script %s: ", path, e);
                writer.append(
                        new TextComponentTranslation("command.commandscript.error.run", getName() , getPath(), e.getMessage())
                                .setStyle(new Style().setColor(TextFormatting.RED)).getFormattedText()
                );
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Remove every '\r' and the last newline character from the output
            String output = StringUtils.removeEnd(writer.toString().replace("\r", ""),"\n");

            Minecraft.getMinecraft().addScheduledTask(() -> sender.sendMessage(new TextComponentString(output)));
        }).start();
    }

    @Override
    public String toString() {
        return String.format("CustomCommand[name=%s, path=%s, usage=%s]", getName(), getPath(), getUsage());
    }
}
