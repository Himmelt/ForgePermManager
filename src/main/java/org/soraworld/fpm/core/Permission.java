package org.soraworld.fpm.core;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Permission {

    private String parent;
    private HashSet<String> subs;
    private HashMap<String, String> values;
    private Node root;
    private final GroupManager manager;
    private static final Pattern PERM_REGEX = Pattern.compile("[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*(\\.\\*)*");

    public Permission(GroupManager manager) {
        this.manager = manager;
    }

    public boolean hasNodes(@Nonnull ArrayList<String> nodes) {
        Permission group;
        group = manager.getGroup(parent);
        if (group != null && group.hasNodes(nodes)) return true;
        if (subs != null) {
            for (String sub : subs) {
                group = manager.getGroup(sub);
                if (group.hasNodes(nodes)) return true;
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
        subs.add(sub);
    }

    public void removeSub(String sub) {
        subs.remove(sub);
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
