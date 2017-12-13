package org.soraworld.fpm.core;

public class Permission extends PG {

    public void add(String permission) {
        // 权限正则格式
        if (!permission.matches("[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*(\\.\\*)*")) return;
        String[] nodes = permission.split("\\.");
        if (node == null) node = new Node();
        // 权限节点解析数组
        node.addNodes(nodes);
    }
}
