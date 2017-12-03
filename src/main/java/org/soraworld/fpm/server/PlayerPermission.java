package org.soraworld.fpm.server;

import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class PlayerPermission {

    private final NBTTagCompound compound = new NBTTagCompound();
    private final Node root = new Node();
    private final HashSet<Group> groups = new HashSet<>();

    public boolean has(String permission) {
        ArrayList<String> nodes = new ArrayList<>(Arrays.asList(permission.split("\\.")));
        if (root.has(nodes)) return true;
        for (Group group : groups) {
            if (group.has(permission)) return true;
        }
        return false;
    }

    public void add(String permission) {
        ArrayList<String> nodes = new ArrayList<>(Arrays.asList(permission.split("\\.")));
        root.add(nodes);
    }

    public void remove(String permission) {
        ArrayList<String> nodes = new ArrayList<>(Arrays.asList(permission.split("\\.")));
        root.remove(nodes);
    }
}
