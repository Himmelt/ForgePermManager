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

    @SerializedName(">")
    private String parent;
    @SerializedName(">>")
    private HashSet<String> subs;
    @SerializedName("<>")
    private HashMap<String, String> values;
    @SerializedName("-")
    private Node root;

    public boolean hasNodes(@Nonnull ArrayList<String> nodes) {
        return root != null && root.hasNodes(nodes);
    }

    public void addNodes(@Nonnull ArrayList<String> nodes) {
        if (!nodes.isEmpty()) {
            if (root == null) root = new Node();
            root.addNodes(nodes);
        }
    }

    public void removeNodes(@Nonnull ArrayList<String> nodes) {
        if (!nodes.isEmpty() && root != null) {
            root.removeNodes(nodes);
        }
    }

    public boolean inTheGroup(String group) {
        return parent != null && group != null && parent.equals(group);
    }

    public boolean inGroup(String group) {
        if (inTheGroup(group)) return true;
        Permission father = manager.getGroup(parent);
        return father != null && father.inGroup(group);
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
            // root
            if (input.readByte() == 1) {
                root = new Node();
                root.read(input);
            } else {
                root = null;
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
            // root
            if (root != null) {
                output.writeByte(1);
                root.write(output);
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
