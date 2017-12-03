package org.soraworld.fpm.data;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.util.HashSet;

public class Permission implements IMessage {

    private Node root = new Node();
    private HashSet<String> groups = new HashSet<>();

    public boolean has(String permission) {
        String[] nodes = permission.split("\\.");
        if (root.has(nodes)) return true;
        for (String group : groups) {
            if (group.has(permission)) return true;
        }
        return false;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }
}
