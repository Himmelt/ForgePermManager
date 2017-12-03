package org.soraworld.fpm.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;

public class Node {

    private static final byte FULL = 42;
    private static final byte END = 0;

    private boolean full = false;
    private HashMap<String, Node> childMap;

    public void write(DataOutput output) throws IOException {
        if (full) {
            output.writeByte(FULL);
            return;
        }
        if (childMap == null || childMap.isEmpty()) return;
        for (String key : childMap.keySet()) {
            if (key != null && !key.isEmpty()) {
                // 写 length <name>
                byte[] name = key.getBytes("UTF-8");
                output.writeByte(name.length);
                output.write(name);
                childMap.get(key).write(output);
                // 写 end
                output.writeByte(END);
            }
        }
    }

    public Node read(DataInput input) throws IOException {
        byte length = input.readByte();
        if (length == 0) {
            return this;
        }
        byte[] buff = new byte[length];
        input.readFully(buff);
        if (buff.length == 1 && buff[0] == FULL) {
            full = true;
        } else {
            String name = new String(buff, "UTF-8");
            childMap.put(name, new Node().read(input));
        }
        return read(input);
    }

    public boolean has(String[] nodes) {
        return full || nodes == null || nodes.length == 0 || has(nodes, 0);
    }

    private boolean has(String[] nodes, int i) {
        return full || i >= nodes.length || childMap != null
                && !childMap.isEmpty() && childMap.containsKey(nodes[i])
                && childMap.get(nodes[i]).has(nodes, i + 1);
    }
}
