package org.soraworld.fpm.server;

import net.minecraft.entity.player.EntityPlayer;
import org.soraworld.fpm.api.manager.ServerPermManager;

import java.util.HashMap;

public class ServerPermManagerImpl implements ServerPermManager {

    private final HashMap<String, PlayerPermission> permMap = new HashMap<>();

    @Override
    public void init() {
    }

    @Override
    public boolean available() {
        return true;
    }

    @Override
    public boolean has(EntityPlayer player, String permission) {
        String username = player.getName();
        if (!permMap.containsKey(username)) {
            permMap.put(username, new PlayerPermission());
        }
        return permMap.get(username).has(permission);
    }

    @Override
    public void add(EntityPlayer player, String permission) {
        String username = player.getName();
        if (!permMap.containsKey(username)) {
            permMap.put(username, new PlayerPermission());
        }
        permMap.get(username).add(permission);
    }

    @Override
    public void remove(EntityPlayer player, String permission) {
        String username = player.getName();
        if (!permMap.containsKey(username)) {
            permMap.put(username, new PlayerPermission());
        }
        permMap.get(username).remove(permission);
    }

}
