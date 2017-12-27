package org.soraworld.fpm.core;

import net.minecraft.entity.player.EntityPlayer;
import org.soraworld.fpm.api.PermManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class PermissionManager implements PermManager {

    private final GroupManager groupManager = new GroupManager();
    private final HashMap<String, PlayerPerm> players = new HashMap<>();

    private static final Pattern PERM_REGEX = Pattern.compile("[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*(\\.\\*)*");

    @Nonnull
    private PlayerPerm get(String username) {
        PlayerPerm perm = players.get(username);
        if (perm == null) {
            perm = new PlayerPerm();
            players.put(username, perm);
        }
        return perm;
    }

    public boolean hasPermission(String player, String permission) {
        return PERM_REGEX.matcher(permission).matches() && get(player).hasNodes(split_dot(permission));
    }

    public boolean hasPermission(EntityPlayer player, String permission) {
        return hasPermission(player.getName(), permission);
    }

    public void addPermission(String player, String permission) {
        if (PERM_REGEX.matcher(permission).matches()) {
            get(player).addNodes(split_dot(permission));
        }
    }

    public void addPermission(EntityPlayer player, String permission) {
        addPermission(player.getName(), permission);
    }

    public void removePermission(String player, String permission) {
        if (PERM_REGEX.matcher(permission).matches()) {
            get(player).removeNodes(split_dot(permission));
        }
    }

    public void removePermission(EntityPlayer player, String permission) {
        removePermission(player.getName(), permission);
    }

    public boolean inGroup(String player, String group) {
        return get(player).inGroup(groupManager.getUserGroup(group));
    }

    public boolean inGroup(EntityPlayer player, String group) {
        return inGroup(player.getName(), group);
    }

    public boolean inTheGroup(String player, String group) {
        return get(player).inTheGroup(groupManager.getUserGroup(group));
    }

    public boolean inTheGroup(EntityPlayer player, String group) {
        return inTheGroup(player.getName(), group);
    }

    public boolean moveTo(String player, String group) {
        return get(player).moveTo(groupManager.getUserGroup(group));
    }

    public boolean moveTo(EntityPlayer player, String group) {
        return moveTo(player.getName(), group);
    }

    public void moveToDefault(String player) {
        get(player).moveTo(groupManager.getDefaultGroup());
    }

    public void moveToDefault(EntityPlayer player) {
        moveToDefault(player.getName());
    }

    public boolean addPermGroup(String player, String group) {
        return get(player).addPermGroup(groupManager.getPermGroup(group));
    }

    public boolean addPermGroup(EntityPlayer player, String group) {
        return addPermGroup(player.getName(), group);
    }

    public void removePermGroup(String player, String group) {
        get(player).removePermGroup(groupManager.getPermGroup(group));
    }

    public void removePermGroup(EntityPlayer player, String group) {
        removePermGroup(player.getName(), group);
    }

    private static ArrayList<String> split_dot(String src) {
        ArrayList<String> list = new ArrayList<>();
        if (src != null) {
            int i, last = -1, length = src.length();
            for (i = 0; i < length; i++) {
                if (src.charAt(i) == '.') {
                    if (i > last + 1) list.add(src.substring(last + 1, i));
                    last = i;
                }
            }
            if (i > last + 1) list.add(src.substring(last + 1, i));
        }
        return list;
    }
}
