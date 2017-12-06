package org.soraworld.fpm.manager;

import net.minecraft.entity.player.EntityPlayer;
import org.soraworld.fpm.api.manager.ServerPermManager;
import org.soraworld.fpm.core.PermissionImpl;
import org.soraworld.fpm.manager.StorageManager;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class ServerPermManagerImpl implements ServerPermManager {

    private final HashMap<String, PermissionImpl> players = new HashMap<>();

    @Override
    public boolean has(@Nonnull EntityPlayer player, String permission) {
        String username = player.getName();
        if (!players.containsKey(username)) {
            players.put(username, new PermissionImpl());
        }
        return players.get(username).hasPerm(permission);
    }

    @Override
    public void add(@Nonnull EntityPlayer player, String permission) {
        String username = player.getName();
        if (!players.containsKey(username)) {
            players.put(username, new PermissionImpl());
        }
        players.get(username).addPerm(permission);
    }

    @Override
    public void remove(@Nonnull EntityPlayer player, String permission) {
        String username = player.getName();
        if (!players.containsKey(username)) {
            players.put(username, new PermissionImpl());
        }
        players.get(username).removePerm(permission);
    }

    @Override
    public void load(@Nonnull EntityPlayer player) {
        players.put(player.getName(), StorageManager.get(player));
    }

    @Override
    public void unload(@Nonnull EntityPlayer player) {
        StorageManager.save(player, players.get(player.getName()));
        players.remove(player.getName());
    }

    @Override
    public void saveAll() {
        for (String player : players.keySet()) {
            StorageManager.save(player, players.get(player));
        }
    }

}
