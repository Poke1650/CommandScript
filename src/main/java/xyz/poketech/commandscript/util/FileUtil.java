package xyz.poketech.commandscript.util;

import xyz.poketech.commandscript.CommandScript;
import xyz.poketech.commandscript.command.custom.CommandRegistry;

import java.io.File;

public class FileUtil {

    public static boolean isInDirectory(File file, File dir) {
        return file.getParentFile().getAbsolutePath().equals(dir.getAbsolutePath());
    }

    public static boolean isInCommandDirectory(File file) {
        return isInDirectory(file, CommandRegistry.COMMANDS_DIR);
    }
}
