package org.soraworld.fpm.core;

import net.minecraft.entity.player.EntityPlayer;
import org.soraworld.fpm.Constants;
import org.soraworld.fpm.api.PermManager;
import org.soraworld.fpm.config.Config;
import org.soraworld.fpm.storage.StorageManager;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class PermissionManager implements PermManager {

    private Config config;


    private GroupManager groupManager;
    private StorageManager storageManager;
    private HashMap<String, Permission> players;

    private static final Pattern PERM_REGEX = Pattern.compile("[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*(\\.\\*)*");

    private boolean ready = false;

    public boolean isReady() {
        return ready;
    }

    public void initialize(File root) {
        config = new Config(new File(root, "fpm/settings.cfg"), Constants.MOD_VERSION);
        groupManager = new GroupManager();
        storageManager = new StorageManager(new File(root, "fpm"), groupManager);
        players = new HashMap<>();
        loadGroups();
        groupManager.addGroup("test", new Permission(groupManager));
        storageManager.saveGroups();
        ready = true;
    }

    @Nonnull
    private Permission get(String username) {
        // TODO 离线玩家处理
        Permission perm = players.get(username);
        if (perm == null) {
            perm = new Permission(groupManager);
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

    @Override
    public boolean groupHasPerm(String name, String permission) {
        Permission group = groupManager.getGroup(name);
        return PERM_REGEX.matcher(permission).matches() && group != null && group.hasNodes(split_dot(permission));
    }

    @Override
    public void groupAddPerm(String name, String permission) {
        Permission group = groupManager.getGroup(name);
        if (PERM_REGEX.matcher(permission).matches() && group != null) {
            group.addNodes(split_dot(permission));
        }
    }

    @Override
    public void groupRemovePerm(String name, String permission) {
        Permission group = groupManager.getGroup(name);
        if (PERM_REGEX.matcher(permission).matches() && group != null) {
            group.removeNodes(split_dot(permission));
        }
    }

    public boolean inGroup(String player, String group) {
        return get(player).inGroup(group);
    }

    public boolean inGroup(EntityPlayer player, String group) {
        return inGroup(player.getName(), group);
    }

    public boolean inTheGroup(String player, String group) {
        return get(player).inTheGroup(group);
    }

    public boolean inTheGroup(EntityPlayer player, String group) {
        return inTheGroup(player.getName(), group);
    }

    public void moveTo(String player, String group) {
        get(player).moveTo(group);
    }

    public void moveTo(EntityPlayer player, String group) {
        moveTo(player.getName(), group);
    }

    public void moveToDefault(String player) {
        get(player).moveTo(null);
    }

    public void moveToDefault(EntityPlayer player) {
        moveToDefault(player.getName());
    }

    public void addSub(String player, String sub) {
        get(player).addSub(sub);
    }

    public void addSub(EntityPlayer player, String sub) {
        addSub(player.getName(), sub);
    }

    public void removeSub(String player, String sub) {
        get(player).removeSub(sub);
    }

    public void removeSub(EntityPlayer player, String sub) {
        removeSub(player.getName(), sub);
    }

    public GroupManager getGroupManager() {
        return groupManager;
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }

    public void setConfig(@Nonnull Config config) {
        this.config = config;
        config.load();
        config.save();
    }

    private void loadGroups() {
        String[] strings = config.getDefaultPerms();
        if (strings != null && strings.length >= 1) {
            for (String perm : strings) {
                if (PERM_REGEX.matcher(perm).matches()) {
                    groupManager.getDefaultGroup().addNodes(split_dot(perm));
                }
            }
        }
        strings = config.getGroupNames();
        if (strings != null && strings.length >= 1) {
            for (String name : strings) {
                groupManager.addGroup(name, storageManager.getGroupFromFile(name));
            }
        }
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
