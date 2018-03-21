package xyz.poketech.commandscript.util;

import org.apache.commons.io.FileUtils;
import xyz.poketech.commandscript.command.custom.CommandRegistry;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

public class PasteHelper {

    public static final String PASTEBIN_RAW = "https://pastebin.com/raw/";

    public static final String HASTEBIN_RAW = "https://hastebin.com/raw/";

    public static void downloadPaste(String id, String filename) throws IOException {
        FileUtils.copyURLToFile(
                new URL(PASTEBIN_RAW + id.replaceAll("[\\\\.\\\\/\\\\/]", "_")),
                new File(CommandRegistry.COMMANDS_DIR , filename)
        );
    }
}
