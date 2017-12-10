package org.soraworld.fpm.manager;

import net.minecraft.entity.player.EntityPlayer;
import org.soraworld.fpm.core.Permission;

import javax.annotation.Nonnull;
import java.io.*;

public class StorageManager {

    @Nonnull
    public static Permission get(@Nonnull EntityPlayer player) {
        Permission permission = new Permission();
        try {
            permission.read(new DataInputStream(new FileInputStream(player.getName() + ".dat")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return permission;
    }

    public static void save(String player, Permission permission) {
        if (permission == null) permission = new Permission();
        try {
            permission.write(new DataOutputStream(new FileOutputStream(player + ".dat")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(@Nonnull EntityPlayer player, Permission permission) {
        save(player.getName(), permission);
    }
}
