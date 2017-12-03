package org.soraworld.fpm.server;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {

    private boolean hasAll = false;
    private HashMap<String, Node> childMap;

    public Node() {
    }

    public Node(ArrayList<String> nodes) {
        add(nodes);
    }

    public boolean has(ArrayList<String> nodes) {
        if (hasAll || nodes == null || nodes.isEmpty()) return true;
        if (childMap == null || childMap.isEmpty()) return false;
        String head = nodes.remove(0);
        return childMap.containsKey(head) && childMap.get(head).has(nodes);
    }

    public boolean isHasAll() {
        return hasAll;
    }

    public void setHasAll(boolean hasAll) {
        this.hasAll = hasAll;
    }

    public void add(ArrayList<String> nodes) {
        if (hasAll || nodes == null || nodes.isEmpty()) return;
        String head = nodes.remove(0);
        if (head.equals("*")) {
            hasAll = true;
            return;
        }
        if (childMap == null) childMap = new HashMap<>();
        if (!childMap.containsKey(head)) childMap.put(head, new Node());

        childMap.get(head).add(nodes);
    }

    public void remove(ArrayList<String> nodes) {
        if (nodes == null || nodes.isEmpty() || childMap == null || childMap.isEmpty()) return;
        String head = nodes.remove(0);
        if (head.equals("*")) {
            hasAll = false;
            return;
        }
        if (nodes.isEmpty()) {
            childMap.remove(head);
        } else if (childMap.containsKey(head)) {
            childMap.get(head).remove(nodes);
        }
    }
}
