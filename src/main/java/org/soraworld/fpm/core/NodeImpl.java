package org.soraworld.fpm.core;

import org.soraworld.fpm.api.core.Node;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;

class NodeImpl implements Node {

    private static final byte FULL = 42;
    private static final byte END = 0;
    private static final String STAR = "*";

    private boolean full = false;
    private HashMap<String, NodeImpl> children;

    void write(DataOutput output) throws IOException {
        if (full) {
            output.writeByte(FULL);
            return;
        }
        if (children == null || children.isEmpty()) return;
        for (String key : children.keySet()) {
            if (key != null && !key.isEmpty()) {
                // 写 length <name>
                byte[] name = key.getBytes("UTF-8");
                output.writeByte(name.length);
                output.write(name);
                children.get(key).write(output);
                // 写 end
                output.writeByte(END);
            }
        }
    }

    /// 不应该设计成循环读取
    void read(DataInput input) throws IOException {
        byte length = input.readByte();
        if (length == 0) {
            return;
        }
        byte[] buff = new byte[length];
        input.readFully(buff);
        if (buff.length == 1 && buff[0] == FULL) {
            full = true;
        } else {
            String name = new String(buff, "UTF-8");
            children.put(name, new NodeImpl());
        }
        //read(input);
    }

    /**
     * 当前权限结点是否包含给定结点序列.
     * 注意：请保证{@code nodes}非空且至少包含一个非空元素.
     *
     * @param nodes 结点序列
     * @return 是否包含
     */
    boolean hasNodes(@Nonnull String[] nodes) {
        return full || hasNodes(nodes, 0);
    }

    private boolean hasNodes(String[] nodes, int i) {
        return full || i >= nodes.length || children != null
                && !children.isEmpty() && children.containsKey(nodes[i])
                && children.get(nodes[i]).hasNodes(nodes, i + 1);
    }

    void addNodes(String[] nodes) {
        if (!full && nodes != null && nodes.length > 0) addNodes(nodes, 0);
    }

    private void addNodes(String[] nodes, int i) {
        if (full || i >= nodes.length) return;
        if (STAR.equals(nodes[i])) {
            full = true;
            return;
        }
        if (children == null) children = new HashMap<>();
        if (!children.containsKey(nodes[i])) children.put(nodes[i], new NodeImpl());
        children.get(nodes[i]).addNodes(nodes, i + 1);
    }

    void removeNodes(String[] nodes) {
        if (nodes != null && nodes.length > 0 && children != null && !children.isEmpty()) {
            removeNodes(nodes, 0);
        }
    }

    private void removeNodes(String[] nodes, int i) {
        if (i >= nodes.length || children == null || children.isEmpty()) return;
        if (STAR.equals(nodes[i])) {
            full = false;
            children = null;
            return;
        }
        if (children.containsKey(nodes[i])) children.get(nodes[i]).removeNodes(nodes, i + 1);
    }

    public boolean isEmpty() {
        return !full && (children == null || children.isEmpty());
    }
}
