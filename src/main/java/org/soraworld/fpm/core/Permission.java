package org.soraworld.fpm.core;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Permission {

    @SerializedName("parent")
    private String parent;
    @SerializedName("subs")
    private HashSet<String> subs;
    @SerializedName("values")
    private HashMap<String, String> values;
    @SerializedName("node")
    private Node node;

    public boolean hasNodes(@Nonnull ArrayList<String> nodes) {
        return node != null && node.hasNodes(nodes);
    }

    public void addNodes(@Nonnull ArrayList<String> nodes) {
        if (!nodes.isEmpty()) {
            if (node == null) node = new Node();
            node.addNodes(nodes);
        }
    }

    public void removeNodes(@Nonnull ArrayList<String> nodes) {
        if (!nodes.isEmpty() && node != null) {
            node.removeNodes(nodes);
        }
    }

    public void moveTo(String group) {
        parent = group;
    }

    public void addSub(String sub) {
        if (subs == null) subs = new HashSet<>();
        subs.add(sub);
    }

    public void removeSub(String sub) {
        if (subs != null) subs.remove(sub);
    }

    public void setValue(String key, String value) {
        if (values == null) values = new HashMap<>();
        values.put(key, value);
    }

    public void delValue(String key) {
        if (values != null) values.remove(key);
    }

    public void read(DataInput input) {
        try {
            byte size, length;
            byte[] bytes;
            // parent
            length = input.readByte();
            if (length > 0) {
                bytes = new byte[length];
                input.readFully(bytes);
                parent = new String(bytes, "UTF-8");
            } else {
                parent = null;
            }
            // subs
            size = input.readByte();
            if (size > 0) {
                if (subs == null) subs = new HashSet<>();
                for (int i = 0; i < size; i++) {
                    length = input.readByte();
                    bytes = new byte[length];
                    input.readFully(bytes);
                    subs.add(new String(bytes, "UTF-8"));
                }
            } else {
                subs = null;
            }
            // values
            size = input.readByte();
            if (size > 0) {
                values = new HashMap<>();
                for (int i = 0; i < size; i++) {
                    length = input.readByte();
                    bytes = new byte[length];
                    input.readFully(bytes);
                    String key = new String(bytes, "UTF-8");
                    length = input.readByte();
                    bytes = new byte[length];
                    input.readFully(bytes);
                    String value = new String(bytes, "UTF-8");
                    values.put(key, value);
                }
            } else {
                values = null;
            }
            // node
            if (input.readByte() == 1) {
                node = new Node();
                node.read(input);
            } else {
                node = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(DataOutput output) {
        try {
            // parent
            if (parent != null) {
                output.writeByte(parent.length());
                output.write(parent.getBytes("UTF-8"));
            } else {
                output.writeByte(0);
            }
            // subs
            if (subs != null) {
                output.writeByte(subs.size());
                for (String sub : subs) {
                    output.writeByte(sub.length());
                    output.write(sub.getBytes("UTF-8"));
                }
            } else {
                output.writeByte(0);
            }
            // values
            if (values != null) {
                output.writeByte(values.size());
                for (String key : values.keySet()) {
                    String value = values.get(key);
                    output.writeByte(key.length());
                    output.write(key.getBytes("UTF-8"));
                    output.writeByte(value.length());
                    output.write(value.getBytes("UTF-8"));
                }
            } else {
                output.writeByte(0);
            }
            // node
            if (node != null) {
                output.writeByte(1);
                node.write(output);
            } else {
                output.writeByte(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getParent() {
        return parent;
    }

    public HashSet<String> getSubs() {
        return subs;
    }
}
