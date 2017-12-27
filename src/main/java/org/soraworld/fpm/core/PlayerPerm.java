package org.soraworld.fpm.core;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PlayerPerm {

    private Node root;
    private UserGroup userGroup;
    private HashSet<PermGroup> permGroups;
    private HashMap<String, String> values;

    public boolean hasNodes(@Nonnull ArrayList<String> nodes) {
        if (userGroup != null && userGroup.hasNodes(nodes)) return true;
        if (permGroups != null) {
            for (PermGroup permGroup : permGroups) {
                if (permGroup.hasNodes(nodes)) return true;
            }
        }
        return root != null && root.hasNodes(nodes);
    }

    public void addNodes(@Nonnull ArrayList<String> nodes) {
        if (!nodes.isEmpty()) {
            if (root == null) root = new Node();
            root.addNodes(nodes);
        }
    }

    public void removeNodes(@Nonnull ArrayList<String> nodes) {
        if (!nodes.isEmpty() && root != null) {
            root.removeNodes(nodes);
        }
    }

    public boolean inTheGroup(UserGroup group) {
        return group != null && userGroup != null && userGroup == group;
    }

    public boolean inGroup(UserGroup group) {
        return group != null && userGroup != null && (group == userGroup || userGroup.inherit(group));
    }

    public boolean moveTo(UserGroup userGroup) {
        if (userGroup != null) {
            this.userGroup = userGroup;
            return true;
        }
        return false;
    }

    public boolean addPermGroup(PermGroup permGroup) {
        if (permGroup != null) {
            if (permGroups == null) permGroups = new HashSet<>();
            permGroups.add(permGroup);
            return true;
        }
        return false;
    }

    public void removePermGroup(PermGroup permGroup) {
        if (permGroups != null) {
            permGroups.remove(permGroup);
        }
    }
}
