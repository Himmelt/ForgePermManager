package org.soraworld.fpm.core;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class UserGroup implements Group {

    private Node root;
    private String name;
    private String parentName;
    private UserGroup parent;
    private HashMap<String, String> values;
    private static final Pattern PERM_REGEX = Pattern.compile("[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*(\\.\\*)*");

    public UserGroup(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        // TODO fix Null
        return name;
    }

    public boolean hasNodes(@Nonnull ArrayList<String> nodes) {
        return parent != null && parent.hasNodes(nodes) || root != null && root.hasNodes(nodes);
    }

    public void addNodes(@Nonnull ArrayList<String> nodes) {
        if (!nodes.isEmpty()) {
            if (root == null) root = new Node();
            root.addNodes(nodes);
        }
    }

    public boolean inherit(UserGroup group) {
        return group != null && parent != null && (group == parent || parent.inherit(group));
    }

    public void read(DataInput input) {
        try {
            byte length;
            byte[] bytes;
            // parent
            length = input.readByte();
            if (length > 0) {
                bytes = new byte[length];
                input.readFully(bytes);
                parentName = new String(bytes, "UTF-8");
            } else {
                parentName = null;
            }
            // values
            byte size = input.readByte();
            if (size > 0) {
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
            root.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(DataOutput output) {
        try {
            // parent
            if (parent != null && parent.name != null) {
                output.writeByte(parent.name.length());
                output.write(parent.name.getBytes("UTF-8"));
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
            root.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setParent(UserGroup parent) {
        this.parent = parent;
    }

    public UserGroup getParent() {
        return parent;
    }

    public void addPerm(String permission) {
        if (PERM_REGEX.matcher(permission).matches()) {
            if (root == null) root = new Node();
            root.addNodes(split_dot(permission));
        }
    }

    private static ArrayList<String> split_dot(String src) {
        ArrayList<String> list = new ArrayList<>();
        if (src != null) {
            int i, last = -1, length = src.length();
            for (i = 0; i < length; i++) {
                if (src.charAt(i) == '.') {
                    if (i > last + 1) list.add(src.substring(last + 1, i));
                    last = i;
                }
            }
            if (i > last + 1) list.add(src.substring(last + 1, i));
        }
        return list;
    }

}
