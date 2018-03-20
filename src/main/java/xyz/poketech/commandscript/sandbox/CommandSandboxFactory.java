package xyz.poketech.commandscript.sandbox;

import com.google.common.base.Stopwatch;
import com.google.common.reflect.ClassPath;
import delight.nashornsandbox.NashornSandbox;
import delight.nashornsandbox.NashornSandboxes;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import xyz.poketech.commandscript.CommandScript;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Class that provides configured instances of a NashornSandbox to run the commands in
 */
public class CommandSandboxFactory {

    /**
     * A list of every wrappers to be allowed in the sandbox
     */
    private static List<Class> wrappers = new ArrayList<>();

    /**
     * Outside class allowed in the sandbox
     */
    private static List<Class> allowed = Arrays.asList(
            EnumHand.class,
            BlockPos.class
    );

    /**
     * Gather and load in memory all wrapper classes
     */
    public static void init() {
        wrappers.clear();
        Stopwatch stopwatch = Stopwatch.createStarted();
        CommandScript.LOGGER.info("Loading wrappers");
        try {

            //Gather all classes that are in the wrapper package recursively
            ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
            Collection<ClassPath.ClassInfo> classes = classPath.getTopLevelClassesRecursive("xyz.poketech.commandscript.engine.wrapper");

            classes.forEach(classInfo -> {
                wrappers.add(classInfo.load());
                CommandScript.LOGGER.info("Loaded wrapper " + classInfo.getSimpleName());
            });

        } catch (Exception e) {
            CommandScript.LOGGER.error("Error loading wrappers", e);
        }
        CommandScript.LOGGER.info("Loaded {} wrapper(s) in {}", wrappers.size(), stopwatch.stop());
    }

    /**
     * @return a pre-configured instance of a NashornSandbox
     */
    public static NashornSandbox getSandbox() {
        NashornSandbox sandbox = NashornSandboxes.create();

        //Allow all wrappers
        for(Class clazz : wrappers) {
            sandbox.allow(clazz);
        }

        //Allow other predefined outside classes
        for(Class clazz : allowed) {
            sandbox.allow(clazz);
        }

        sandbox.setExecutor(Executors.newSingleThreadExecutor());
        sandbox.allowPrintFunctions(true);

        return sandbox;
    }
}
