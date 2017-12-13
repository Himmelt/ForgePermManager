package org.soraworld.fpm.core;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashSet;

public class Permission {

    private Node root;
    private HashSet<String> groups;

    public void read(DataInput input) {

    }

    public void write(DataOutput output) throws IOException {
        if (groups == null || groups.size() == 0) {
            output.writeByte(0);
        } else {
            output.writeByte(groups.size());
            for (String group : groups) {
                byte[] bytes = group.getBytes("UTF-8");
                output.writeByte(bytes.length);
                output.write(bytes);
            }
        }
        if (root == null) {
            output.writeByte(0);
        } else {
            output.writeByte(1);
            root.write(output);
        }
    }
}
