package org.soraworld.fpm.server;

import net.minecraft.entity.player.EntityPlayer;
import org.soraworld.fpm.core.GroupManager;
import org.soraworld.fpm.core.Permission;
import org.soraworld.fpm.manager.StorageManager;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class ServerPermManager {

    private static ServerPermManager instance;
    private static final GroupManager manager = new GroupManager();
    private static final HashMap<String, Permission> players = new HashMap<>();

    public static ServerPermManager getInstance() {
        return instance == null ? instance = new ServerPermManager() : instance;
    }

    public boolean has(@Nonnull EntityPlayer player, String permission) {
        if (!permission.matches("([a-zA-Z0-9_]+\\.)+(\\1|\\*)")) return false;
        String[] nodes = permission.split("\\.");
        return false;
    }

    public void add(@Nonnull EntityPlayer player, String permission) {
    }

    public void remove(@Nonnull EntityPlayer player, String permission) {
    }

    public void load(@Nonnull EntityPlayer player) {
        players.put(player.getName(), StorageManager.get(player));
    }

    public void unload(@Nonnull EntityPlayer player) {
        StorageManager.save(player, players.get(player.getName()));
        players.remove(player.getName());
    }

    public void saveAll() {
        for (String player : players.keySet()) {
            StorageManager.save(player, players.get(player));
        }
    }

}
