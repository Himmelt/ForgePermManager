package org.soraworld.fpm.data;

import javax.annotation.Nonnull;
import java.util.HashSet;

public class Permission {

    private Node root = new Node();
    private HashSet<Group> groups = new HashSet<>();
    private static final GroupManager manager = GroupManager.getInstance();

    /**
     * 当前权限主体是否拥有给定权限.
     * 注意：{@code permission}必须匹配{@code ([a-zA-Z0-9_]+\.)+(\1|\*)}
     * 否则返回{@code false}
     *
     * @param permission 给定权限
     * @return 是否拥有
     */
    public boolean hasPerm(@Nonnull String permission) {
        if (!permission.matches("([a-zA-Z0-9_]+\\.)+(\\1|\\*)")) return false;
        String[] nodes = permission.split("\\.");
        if (manager.getBase().hasNodes(nodes) || root.hasNodes(nodes)) return true;
        for (Group group : groups) {
            if (group.hasNodes(nodes)) return true;
        }
        return false;
    }

    public void addPerm(String permission) {
        root.addNodes(permission.split("\\."));
    }

    public void removePerm(String permission) {
        root.removeNodes(permission.split("\\."));
    }

}
