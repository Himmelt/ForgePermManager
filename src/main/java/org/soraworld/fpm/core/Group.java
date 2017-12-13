package org.soraworld.fpm.core;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashSet;

public class Group {

    private Node node;
    private HashSet<String> parents;

    public void read(DataInput input) throws IOException {
        byte size = input.readByte();
        if (size > 0) {
            parents = new HashSet<>();
            while (size-- > 0) {
                byte length = input.readByte();
                byte[] bytes = new byte[length];
                input.readFully(bytes);
                parents.add(new String(bytes, "UTF-8"));
            }
        }
        if (input.readByte() == 1) {
            node = new Node();
            node.read(input);
        }
    }

    public void write(DataOutput output) throws IOException {
        if (parents == null || parents.size() == 0) {
            output.writeByte(0);
        } else {
            output.writeByte(parents.size());
            for (String parent : parents) {
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
        return (node == null || node.isEmpty()) && (parents == null || parents.isEmpty());
    }

    public void addParent(String parent) {
        if (parents == null) parents = new HashSet<>();
        parents.add(parent);
    }

    public void setNode(@Nonnull Node node) {
        this.node = node;
    }
}
