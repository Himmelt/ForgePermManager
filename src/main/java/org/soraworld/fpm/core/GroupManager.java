package org.soraworld.fpm.core;

import java.util.HashMap;

public class GroupManager {

    private final Permission defaultGroup = new Permission(this);
    private final HashMap<String, Permission> groups = new HashMap<>();

    public boolean hasGroup(String name) {
        return groups.containsKey(name);
    }

    public Permission getGroup(String name) {
        return groups.get(name);
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

    public Permission getDefaultGroup() {
        return defaultGroup;
    }

    public HashMap<String, Permission> getGroups() {
        return groups;
    }

}
