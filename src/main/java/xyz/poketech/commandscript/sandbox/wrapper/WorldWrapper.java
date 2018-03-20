package xyz.poketech.commandscript.sandbox.wrapper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class WorldWrapper {

    private World world;

    public WorldWrapper(World world) {
        this.world = world;
    }

    public long getTotalTime() {
        return world.getTotalWorldTime();
    }

    public long getCurrentTime() {
        return world.getWorldTime();
    }

    public String getBiomeName(int x, int y, int z) {
        BlockPos pos = new BlockPos(x,y,z);
        if(world.isBlockLoaded(pos)) {
            return world.getBiome(pos).getBiomeName();
        }
        return "UNKNOWN";
    }

    public String getDifficultyName() {
        return new TextComponentTranslation(world.getDifficulty().getDifficultyResourceKey()).getUnformattedComponentText();
    }

    public int getDifficultyID() {
        return world.getDifficulty().getDifficultyId();
    }

    public int getMoonPhase() {
        return world.getMoonPhase();
    }

    public int getHeight() {
        return world.getHeight();
    }
}
