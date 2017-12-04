package org.soraworld.fpm.server;

import net.minecraft.entity.player.EntityPlayer;
import org.soraworld.fpm.api.manager.ServerPermManager;
import org.soraworld.fpm.data.Permission;

import java.util.HashMap;

public class ServerPermManagerImpl implements ServerPermManager {

    private final HashMap<String, Permission> permMap = new HashMap<>();

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
            permMap.put(username, new Permission());
        }
        return permMap.get(username).hasPerm(permission);
    }

    @Override
    public void add(EntityPlayer player, String permission) {
        String username = player.getName();
        if (!permMap.containsKey(username)) {
            permMap.put(username, new Permission());
        }
        permMap.get(username).addPerm(permission);
    }

    @Override
    public void remove(EntityPlayer player, String permission) {
        String username = player.getName();
        if (!permMap.containsKey(username)) {
            permMap.put(username, new Permission());
        }
        permMap.get(username).removePerm(permission);
    }

}
