package xyz.poketech.commandscript.sandbox.wrapper;

import com.google.common.collect.ImmutableSet;
import net.minecraft.nbt.NBTTagCompound;

public class NBTWrapper {

    NBTTagCompound tag;

    public NBTWrapper(NBTTagCompound tag) {
        this.tag = tag;

        tag.getKeySet();
    }

    public ImmutableSet<String> getKeySet() {
        return new ImmutableSet.Builder<String>().addAll(tag.getKeySet()).build();
    }
    public String getString(String key) {
        return tag.getString(key);
    }
    public boolean getBoolean(String key) {
        return tag.getBoolean(key);
    }

    public byte getByte(String key) {
        return tag.getByte(key);
    }

    public byte[] getByteArray(String key) {
        return tag.getByteArray(key);
    }

    public NBTWrapper getCompoundTag(String key) {
        return new NBTWrapper(tag.getCompoundTag(key));
    }

    public double getDouble(String key) {
        return tag.getDouble(key);
    }

    public float getFloat(String key) {
        return tag.getFloat(key);
    }

    public int getID() {
        return tag.getId();
    }

    public int[] getIntArray(String key) {
        return tag.getIntArray(key);
    }

    public int getInteger(String key) {
        return tag.getInteger(key);
    }









}
