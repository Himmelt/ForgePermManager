package org.soraworld.fpm.core;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashSet;

abstract class PG {

    Node node;
    HashSet<String> names;

    public void add(String permission) {
        // 权限正则格式
        if (!permission.matches("[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*(\\.\\*)*")) return;
        String[] nodes = permission.split("\\.");
        if (node == null) node = new Node();
        // 权限节点解析数组
        node.addNodes(nodes);
    }

    public void remove(String permission) {
        // 权限正则格式
        if (!permission.matches("[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*(\\.\\*)*")) return;
        String[] nodes = permission.split("\\.");
        if (node != null) {
            node.removeNodes(nodes);
        }
    }

    public void read(DataInput input) throws IOException {
        byte size = input.readByte();
        if (size > 0) {
            names = new HashSet<>();
            while (size-- > 0) {
                byte length = input.readByte();
                byte[] bytes = new byte[length];
                input.readFully(bytes);
                names.add(new String(bytes, "UTF-8"));
            }
        }
        if (input.readByte() == 1) {
            node = new Node();
            node.read(input);
        }
    }

    public void write(DataOutput output) throws IOException {
        if (names == null || names.size() == 0) {
            output.writeByte(0);
        } else {
            output.writeByte(names.size());
            for (String parent : names) {
                byte[] bytes = parent.getBytes("UTF-8");
                output.writeByte(bytes.length);
                output.write(bytes);
            }
        }
        if (node == null) {
            output.writeByte(0);
        } else {
            output.writeByte(1);
            node.write(output);
        }
    }

    public boolean isEmpty() {
        return (node == null || node.isEmpty()) && (names == null || names.isEmpty());
    }

    public void addName(String name) {
        if (names == null) names = new HashSet<>();
        names.add(name);
    }

    public Node getNode() {
        return node != null ? node : new Node();
    }
}
