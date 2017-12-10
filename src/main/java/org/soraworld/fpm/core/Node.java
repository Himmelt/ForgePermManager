package org.soraworld.fpm.core;

import org.soraworld.fpm.Constants;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class Node {

    private boolean full = false;
    public boolean light = false;
    private HashMap<String, Node> children;

    public boolean has(@Nonnull String[] nodes) {
        return full || has(nodes, 0);
    }

    private boolean has(String[] nodes, int i) {
        return full || i >= nodes.length || children != null
                && !children.isEmpty() && children.containsKey(nodes[i])
                && children.get(nodes[i]).has(nodes, i + 1);
    }
    void addNodes(String[] nodes) {
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
