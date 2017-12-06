package org.soraworld.fpm.manager;

import net.minecraft.entity.player.EntityPlayer;
import org.soraworld.fpm.core.PermissionImpl;

import javax.annotation.Nonnull;
import java.io.*;

public class StorageManager {

    @Nonnull
    public static PermissionImpl get(@Nonnull EntityPlayer player) {
        PermissionImpl permission = new PermissionImpl();
        try {
            permission.read(new DataInputStream(new FileInputStream(player.getName() + ".dat")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return permission;
    }

    public static void save(String player, PermissionImpl permission) {
        if (permission == null) permission = new PermissionImpl();
        try {
            permission.write(new DataOutputStream(new FileOutputStream(player + ".dat")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void save(@Nonnull EntityPlayer player, PermissionImpl permission) {
        save(player.getName(), permission);
    }
}
