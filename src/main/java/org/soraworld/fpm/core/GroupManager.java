package org.soraworld.fpm.core;

import java.util.HashMap;

public class GroupManager {

    private final UserGroup defaultGroup = new UserGroup("defaultGroup");
    private final HashMap<String, Group> groups = new HashMap<>();

    public void addGroup(String name, Group group) {
        if (name != null && group != null && !name.isEmpty()) {
            if (group instanceof UserGroup) ((UserGroup) group).setName(name);
            groups.put(name, group);
        }
    }

    public UserGroup getUserGroup(String name) {
        Group group = groups.get(name);
        return group instanceof UserGroup ? (UserGroup) group : null;
    }

    public PermGroup getPermGroup(String name) {
        Group group = groups.get(name);
        return group instanceof PermGroup ? (PermGroup) group : null;
    }

    public UserGroup getDefaultGroup() {
        return defaultGroup;
    }

}
