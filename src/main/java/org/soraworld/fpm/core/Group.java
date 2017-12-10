package org.soraworld.fpm.core;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.HashSet;

public class Group {

    private Node node;
    private HashSet<String> parents;

    public void read(DataInput input) {

    }

    public void write(DataOutput output) {

    }

    public boolean isEmpty() {
        return (node == null || node.isEmpty()) && (parents == null || parents.isEmpty());
    }
}
