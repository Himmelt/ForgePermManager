package org.soraworld.fpm.core;

import java.util.HashMap;

public class GroupManager {

    private final UserGroup defaultGroup = new UserGroup();
    private final HashMap<String, UserGroup> userGroups = new HashMap<>();
    private final HashMap<String, PermGroup> permGroups = new HashMap<>();

    public UserGroup getUserGroup(String group) {
        return userGroups.get(group);
    }

    public PermGroup getPermGroup(String group) {
        return permGroups.get(group);
    }

    public UserGroup getDefaultGroup() {
        return defaultGroup;
    }

}
