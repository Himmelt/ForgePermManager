package org.soraworld.fpm.manager;

import net.minecraft.entity.player.EntityPlayer;
import org.apache.commons.io.FileUtils;
import org.soraworld.fpm.Constants;
import org.soraworld.fpm.FPManager;
import org.soraworld.fpm.core.Group;
import org.soraworld.fpm.core.Permission;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.HashMap;

public class StorageManager {

    private static File root = new File("data");
    private static File gRoot = new File("data/group");

    @Nonnull
    public static Permission get(@Nonnull EntityPlayer player) {
        Permission permission = new Permission();
        try {
            permission.read(new DataInputStream(new FileInputStream(new File(root, player.getName()))));
        } catch (IOException e) {
            FPManager.LOGGER.info(player.getName() + "'s data read failed or doesn't exist!");
        }
        return permission;
    }

    public static void save(String player, Permission permission) {
        if (permission == null) permission = new Permission();
        try {
            permission.write(new DataOutputStream(FileUtils.openOutputStream(new File(root, player))));
        } catch (IOException e) {
            e.printStackTrace();
            FPManager.LOGGER.error(player + "'s data file save/write failed!");
        }
    }

    public static void save(@Nonnull EntityPlayer player, Permission permission) {
        save(player.getName(), permission);
    }

    public static void init(File directory) {
        root = new File(directory, Constants.MOD_ID + "/data");
        gRoot = new File(root, "group");
    }

    public static void saveGroups(Group base, HashMap<String, Group> groups) {
        if (base == null) base = new Group();
        try {
            base.write(new DataOutputStream(FileUtils.openOutputStream(new File(gRoot, "default"))));
        } catch (IOException e) {
            e.printStackTrace();
            FPManager.LOGGER.error("default group data file save/write failed!");
        }
        if (groups != null && groups.size() > 0) {
            for (String name : groups.keySet()) {
                Group group = groups.get(name);
                if (group != null) {
                    try {
                        group.write(new DataOutputStream(FileUtils.openOutputStream(new File(gRoot, name))));
                    } catch (IOException e) {
                        FPManager.LOGGER.error(name + " group data file save/write failed!");
                    }
                }
            }
        }
    }

    public static Group getBase() {
        Group base = new Group();
        try {
            base.read(new DataInputStream(new FileInputStream(new File(gRoot, "default"))));
        } catch (IOException e) {
            FPManager.LOGGER.info("Default group data read failed or doesn't exist!");
        }
        return base;
    }

    public static HashMap<String, Group> getGroups() {
        HashMap<String, Group> groups = new HashMap<>();
        File[] files = gRoot.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                Group group = new Group();
                try {
                    group.read(new DataInputStream(new FileInputStream(file)));
                } catch (IOException e) {
                    FPManager.LOGGER.info(file.getName() + " read failed!");
                }
                groups.put(file.getName(), group);
            }
        }
        return groups;
    }
}
