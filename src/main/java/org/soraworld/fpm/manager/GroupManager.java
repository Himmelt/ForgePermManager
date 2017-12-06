package org.soraworld.fpm.manager;

import org.soraworld.fpm.core.Group;

import java.util.HashMap;

public class GroupManager {

    private final Group base = new Group();
    private final HashMap<String, Group> groups = new HashMap<>();

    private static GroupManager instance;

    public static GroupManager getInstance() {
        return instance == null ? instance = new GroupManager() : instance;
    }

    public Group getBase() {
        return base;
    }

    public boolean hasGroup(String name) {
        return groups.containsKey(name) && groups.get(name) != null;
    }

    public Group getGroup(String name) {
        return groups.get(name);
    }

    public void addGroup(String name) {
        if (!hasGroup(name)) groups.put(name, new Group());
    }

    public void removeGroup(String name) {
        groups.remove(name);
        // TODO 同步所有涉及该组的权限
    }

}
