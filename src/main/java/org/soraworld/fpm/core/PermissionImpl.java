package org.soraworld.fpm.core;

import org.soraworld.fpm.api.core.Permission;
import org.soraworld.fpm.manager.GroupManager;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.ArrayList;

public class PermissionImpl implements Permission {

    private NodeImpl root;
    private ArrayList<String> groups;
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
        // 权限正则格式
        if (!permission.matches("([a-zA-Z0-9_]+\\.)+(\\1|\\*)")) return false;
        // 权限节点解析数组
        String[] nodes = permission.split("\\.");
        // 默认组权限检查
        if (manager.getBase().hasNodes(nodes)) return true;
        // 组权限检查
        if (groups != null) {
            for (String name : groups) {
                GroupImpl group = manager.getGroup(name);
                if (group != null && group.hasNodes(nodes)) return true;
            }
        }
        // 自身权限检查
        return root != null && root.hasNodes(nodes);
    }

    public void addPerm(String permission) {
        // 权限正则格式
        if (!permission.matches("([a-zA-Z0-9_]+\\.)+(\\1|\\*)")) return;
        String[] nodes = permission.split("\\.");
        if (root == null) root = new NodeImpl();
        // 权限节点解析数组
        root.addNodes(nodes);
        update();
    }

    public void removePerm(String permission) {
        if (root != null) {
            // 权限正则格式
            if (!permission.matches("([a-zA-Z0-9_]+\\.)+(\\1|\\*)")) return;
            String[] nodes = permission.split("\\.");
            root.removeNodes(nodes);
            if (root.isEmpty()) root = null;
            update();
        }
    }

    public boolean hasGroup(String name) {
        return groups != null && groups.contains(name);
    }

    public void addGroup(String name) {
        if (groups == null) groups = new ArrayList<>();
        if (!groups.contains(name)) groups.add(name);
        update();
    }

    public void removeGroup(String name) {
        if (groups != null) {
            groups.remove(name);
            if (groups.isEmpty()) groups = null;
            update();
        }
    }

    private void update() {

    }

    public void read(DataInput input) {

    }

    public void write(DataOutput output) {

    }
}
