package org.soraworld.fpm.core;

import org.soraworld.fpm.api.core.Group;
import org.soraworld.fpm.manager.GroupManager;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.HashSet;

public class GroupImpl extends NodeImpl implements Group {

    private String display;
    private final HashSet<GroupImpl> parents = new HashSet<>();
    private static final GroupManager manager = GroupManager.getInstance();

    public boolean hasParent(String parent) {
        return parents.contains(manager.getGroup(parent));
    }

    public void addParent(String parent) {
        if (manager.hasGroup(parent)) parents.add(manager.getGroup(parent));
    }

    public void removeParent(String parent) {
        if (manager.hasGroup(parent)) parents.remove(manager.getGroup(parent));
        // TODO 同步该组权限
    }

    @Override
    public void write(DataOutput output) {

    }

    @Override
    public void read(DataInput input) {

    }

    @Override
    public boolean hasNodes(@Nonnull String[] nodes) {
        for (GroupImpl parent : parents) {
            if (parent.hasNodes(nodes)) return true;
        }
        return super.hasNodes(nodes);
    }
}
