package org.soraworld.fpm.core;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.HashSet;

public final class Group {

    private Node node;
    private HashSet<String> parents;

    public Group() {
        node = new Node();
        parents = new HashSet<>();
    }

    public void read(DataInput input) {

    }

    public void write(DataOutput output) {

    }
}
