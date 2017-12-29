package org.soraworld.fpm.core;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;

public class UserGroup implements Group {

    private Node root;
    private String name;
    private UserGroup parent;
    private HashMap<String, String> values;

    public UserGroup(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        // TODO fix Null
        return name;
    }

    public boolean hasNodes(@Nonnull ArrayList<String> nodes) {
        return parent != null && parent.hasNodes(nodes) || root != null && root.hasNodes(nodes);
    }

    public boolean inherit(UserGroup group) {
        return group != null && parent != null && (group == parent || parent.inherit(group));
    }
}
