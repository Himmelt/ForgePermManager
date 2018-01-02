package org.soraworld.fpm.core;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class PermGroup implements Group {

    private Node root;
    private String name;
    private HashSet<PermGroup> parents;

    public PermGroup(String name) {
        this.name = name;
    }

    public boolean hasNodes(@Nonnull ArrayList<String> nodes) {
        if (parents != null) {
            for (PermGroup permGroup : parents) {
                if (permGroup.hasNodes(nodes)) return true;
            }
        }
        return root != null && root.hasNodes(nodes);
    }

    public void addNodes(@Nonnull ArrayList<String> nodes) {
        if (!nodes.isEmpty()) {
            if (root == null) root = new Node();
            root.addNodes(nodes);
        }
    }

    public void read(DataInput input) {
        try {
            root.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(DataOutput output) {
        try {
            root.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
