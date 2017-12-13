package org.soraworld.fpm.core;

import org.soraworld.fpm.Constants;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;

public class Node {

    private boolean full = false;
    private boolean light = false;
    private HashMap<String, Node> children;

    public void write(DataOutput output) throws IOException {
        output.writeByte(full ? (light ? 3 : 1) : (light ? 2 : 0));
        if (!full) {
            if (children == null || children.isEmpty()) {
                output.writeByte(0);
            } else {
                output.writeByte(children.size());
                for (String name : children.keySet()) {
                    byte[] bytes = name.getBytes("UTF-8");
                    output.writeByte(bytes.length);
                    output.write(bytes);
                    Node node = children.get(name);
                    if (node == null) node = new Node();
                    node.write(output);
                }
            }
        }
    }

    public void read(DataInput input) throws IOException {
        byte flag = input.readByte();
        full = flag == 1 || flag == 3;
        light = flag == 2 || flag == 3;
        if (!full) {
            byte size = input.readByte();
            if (size > 0) {
                children = new HashMap<>();
                while (size-- > 0) {
                    byte length = input.readByte();
                    byte[] bytes = new byte[length];
                    input.readFully(bytes);
                    String name = new String(bytes, "UTF-8");
                    Node node = new Node();
                    node.read(input);
                    children.put(name, node);
                }
            }
        }
    }

    public boolean has(@Nonnull String[] nodes) {
        return full || has(nodes, 0);
    }

    private boolean has(String[] nodes, int i) {
        return full || i >= nodes.length || children != null
                && !children.isEmpty() && children.containsKey(nodes[i])
                && children.get(nodes[i]).has(nodes, i + 1);
    }

    public void addNodes(String[] nodes) {
        if (!full && nodes != null && nodes.length > 0) addNodes(nodes, 0);
    }

    private void addNodes(String[] nodes, int i) {
        if (full || i >= nodes.length) return;
        if (Constants.STAR.equals(nodes[i])) {
            full = true;
            return;
        }
        if (children == null) children = new HashMap<>();
        if (!children.containsKey(nodes[i])) children.put(nodes[i], new Node());
        children.get(nodes[i]).addNodes(nodes, i + 1);
    }

    void removeNodes(String[] nodes) {
        if (nodes != null && nodes.length > 0 && children != null && !children.isEmpty()) {
            removeNodes(nodes, 0);
        }
    }

    private void removeNodes(String[] nodes, int i) {
        if (i >= nodes.length || children == null || children.isEmpty()) return;
        if (Constants.STAR.equals(nodes[i])) {
            full = false;
            children = null;
            return;
        }
        if (children.containsKey(nodes[i])) children.get(nodes[i]).removeNodes(nodes, i + 1);
    }

    public boolean isEmpty() {
        return !full && (children == null || children.isEmpty());
    }

}
