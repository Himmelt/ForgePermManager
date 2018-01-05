package org.soraworld.fpm.core;

import net.minecraftforge.fml.relauncher.Side;
import org.soraworld.fpm.api.PermManager;
import org.soraworld.fpm.config.Config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

public class PermissionManager implements PermManager {

    private final Side side;
    private final Config config;
    private final StorageManager storage;
    private final Permission defaultGroup = new Permission();
    private final HashMap<String, Permission> groups = new HashMap<>();
    private final HashMap<String, Permission> players = new HashMap<>();

    private static final Pattern PERM_REGEX = Pattern.compile("[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*(\\.\\*)*");

    public PermissionManager(File root, Side side) {
        config = new Config(new File(root, "settings.cfg"));
        storage = new StorageManager(root);
        this.side = side;
        config.load();
        config.save();
    }

    public Permission getPlayer(String username) {
        // TODO 离线玩家处理
        return players.get(username);
    }

    public Permission getGroup(String groupName) {
        return groups.get(groupName);
    }

    public boolean hasUserPerm(String username, String perm) {
        return PERM_REGEX.matcher(perm).matches() && hasPerm(getPlayer(username), perm);
    }

    public void addUserPerm(String username, String perm) {
        if (PERM_REGEX.matcher(perm).matches()) {
            Permission player = getPlayer(username);
            if (player != null) player.addNodes(split_dot(perm));
        }
    }

    public void delUserPerm(String username, String perm) {
        if (PERM_REGEX.matcher(perm).matches()) {
            Permission player = getPlayer(username);
            if (player != null) player.removeNodes(split_dot(perm));
        }
    }

    private boolean hasGroupPerm(String groupName, ArrayList<String> nodes) {
        Permission group = groups.get(groupName);
        if (group != null) {
            if (hasGroupPerm(group.getParent(), nodes)) return true;
            HashSet<String> subs = group.getSubs();
            if (subs != null) {
                for (String sub : subs) {
                    if (hasGroupPerm(sub, nodes)) return true;
                }
            }
            return group.hasNodes(nodes);
        }
        return false;
    }

    private boolean hasPerm(Permission ps, String perm) {
        if (ps != null) {
            ArrayList<String> nodes = split_dot(perm);
            if (defaultGroup.hasNodes(nodes)) return true;
            if (hasGroupPerm(ps.getParent(), nodes)) return true;
            HashSet<String> subs = ps.getSubs();
            if (subs != null) {
                for (String sub : subs) {
                    if (hasGroupPerm(sub, nodes)) return true;
                }
            }
            return ps.hasNodes(nodes);
        }
        return false;
    }

    public boolean hasGroupPerm(String groupName, String perm) {
        return PERM_REGEX.matcher(perm).matches() && hasPerm(groups.get(groupName), perm);
    }

    public void addGroupPerm(String groupName, String perm) {
        Permission group = groups.get(groupName);
        if (group != null && PERM_REGEX.matcher(perm).matches()) {
            group.addNodes(split_dot(perm));
        }
    }

    public void delGroupPerm(String groupName, String perm) {
        Permission group = groups.get(groupName);
        if (group != null && PERM_REGEX.matcher(perm).matches()) {
            group.removeNodes(split_dot(perm));
        }
    }

    public void moveTo(String player, String group) {
        getPlayer(player).moveTo(group);
    }

    public void moveToDefault(String player) {
        getPlayer(player).moveTo(null);
    }

    public void addSub(String player, String sub) {
        getPlayer(player).addSub(sub);
    }

    public void delSub(String player, String sub) {
        getPlayer(player).removeSub(sub);
    }

    public StorageManager getStorage() {
        return storage;
    }

    private void loadGroups() {
        String[] strings = config.getDefaultPerms();
        if (strings != null && strings.length >= 1) {
            for (String perm : strings) {
                if (PERM_REGEX.matcher(perm).matches()) {
                    defaultGroup.addNodes(split_dot(perm));
                }
            }
        }
        strings = config.getGroupNames();
        if (strings != null && strings.length >= 1) {
            for (String name : strings) {
                addGroup(name, storage.getGroupFromFile(name));
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

    public boolean hasGroup(String name) {
        return groups.containsKey(name);
    }

    public void addGroup(String name, Permission group) {
        if (name != null && group != null && !name.isEmpty() && !groups.containsKey(name)) {
            groups.put(name, group);
        }
    }

    public void setGroup(String name, Permission group) {
        if (name != null && group != null && !name.isEmpty()) {
            groups.put(name, group);
        }
    }

    public void removeGroup(String name) {
        groups.remove(name);
    }

    public void saveGroup(String groupname) {
        storage.saveGroup(groupname, groups.get(groupname));
    }

    public void saveGroupJson(String groupname) {
        storage.saveGroupJson(groupname, groups.get(groupname));
    }

    public Permission getDefaultGroup() {
        return defaultGroup;
    }

    public HashMap<String, Permission> getGroups() {
        return groups;
    }

}
