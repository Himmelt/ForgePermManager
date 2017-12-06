package org.soraworld.fpm.manager;

import org.soraworld.fpm.core.GroupImpl;

import java.util.HashMap;

public class GroupManager {

    private final GroupImpl base = new GroupImpl();
    private final HashMap<String, GroupImpl> groups = new HashMap<>();

    private static GroupManager instance;

    public static GroupManager getInstance() {
        return instance == null ? instance = new GroupManager() : instance;
    }

    public GroupImpl getBase() {
        return base;
    }

    public boolean hasGroup(String name) {
        return groups.containsKey(name) && groups.get(name) != null;
    }

    public GroupImpl getGroup(String name) {
        return groups.get(name);
    }

    public void addGroup(String name) {
        if (!hasGroup(name)) groups.put(name, new GroupImpl());
    }

    public void removeGroup(String name) {
        groups.remove(name);
        // TODO 同步所有涉及该组的权限
    }

}
