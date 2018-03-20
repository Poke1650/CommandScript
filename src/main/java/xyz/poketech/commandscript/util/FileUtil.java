package xyz.poketech.commandscript.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility methods for various I/O task
 */
public class FileUtil {

    /**
     * Reads a file and returns its content
     * @param path the path of the file to be read
     * @param encoding the encoding of the file
     * @return the content of the file
     * @throws IOException
     */
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
