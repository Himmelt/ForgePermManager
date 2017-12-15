package org.soraworld.fpm.core;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class GroupManager {

    private Group base = new Group();
    private HashMap<String, Group> groups = new HashMap<>();

    public void set(@Nonnull Group base, @Nonnull HashMap<String, Group> groups) {
        this.base = base;
        this.groups = groups;
    }

    @Nonnull
    public Group getBase() {
        return base;
    }

    public HashMap<String, Group> getGroups() {
        return groups;
    }

    public boolean hasPerm(String name, String[] nodes) {
        Group group = groups.get(name);
        if (group == null) return false;
        for (String parent : group.getParents()) {
            Group parentGroup = groups.get(parent);
            if (parentGroup != null && parentGroup.getNode().has(nodes)) return true;
        }
        return false;
    }
}
