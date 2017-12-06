package org.soraworld.fpm.manager;

import net.minecraft.entity.player.EntityPlayer;
import org.soraworld.fpm.core.Permission;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class ServerPermManager {

    private static ServerPermManager instance;

    private final HashMap<String, Permission> players = new HashMap<>();

    public static ServerPermManager getInstance() {
        return instance == null ? instance = new ServerPermManager() : instance;
    }

    public boolean has(@Nonnull EntityPlayer player, String permission) {
        String username = player.getName();
        if (!players.containsKey(username)) {
            players.put(username, new Permission());
        }
        return players.get(username).hasPerm(permission);
    }

    public void add(@Nonnull EntityPlayer player, String permission) {
        String username = player.getName();
        if (!players.containsKey(username)) {
            players.put(username, new Permission());
        }
        players.get(username).addPerm(permission);
    }

    public void remove(@Nonnull EntityPlayer player, String permission) {
        String username = player.getName();
        if (!players.containsKey(username)) {
            players.put(username, new Permission());
        }
        players.get(username).removePerm(permission);
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
