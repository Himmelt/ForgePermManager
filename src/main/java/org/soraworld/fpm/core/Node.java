package org.soraworld.fpm.core;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Node {

    private boolean full = false;
    private boolean light = false;
    private HashMap<String, Node> children;
    private static final String STAR = "*";

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

    public boolean hasNodes(ArrayList<String> nodes) {
        return full || hasNodes(nodes, 0);
    }

    private boolean hasNodes(ArrayList<String> nodes, int i) {
        if (full || light && i >= nodes.size()) return true;
        if (children != null && !children.isEmpty()) {
            Node node = children.get(nodes.get(i));
            if (node != null) return node.hasNodes(nodes, i + 1);
        }
        return false;
    }

    public void addNodes(ArrayList<String> nodes) {
        if (!full && !nodes.isEmpty()) addNodes(nodes, 0);
    }

    private void addNodes(ArrayList<String> nodes, int i) {
        if (!full) {
            if (i >= nodes.size()) {
                light = true;
                return;
            }
            String str = nodes.get(i);
            if (STAR.equals(str)) {
                full = true;
                return;
            }
            if (children == null) children = new HashMap<>();
            Node node = children.get(str);
            if (node == null) {
                node = new Node();
                children.put(str, node);
            }
            node.addNodes(nodes, i + 1);
        }
    }

    public void removeNodes(ArrayList<String> nodes) {
        if (nodes != null && !nodes.isEmpty() && children != null && !children.isEmpty()) {
            removeNodes(nodes, 0);
        }
    }

    private void removeNodes(ArrayList<String> nodes, int i) {
        if (i >= nodes.size()) {
            light = false;
            return;
        }
        if (children == null || children.isEmpty()) {
            return;
        }
        String str = nodes.get(i);
        if (STAR.equals(str)) {
            full = false;
            children = null;
            return;
        }
        Node node = children.get(str);
        if (node != null) {
            node.removeNodes(nodes, i + 1);
            if (node.isEmpty()) children.remove(str);
        } else {
            children.remove(str);
        }
        if (children.isEmpty()) children = null;
    }

    private boolean isEmpty() {
        return !full && !light && (children == null || children.isEmpty());
    }

}
