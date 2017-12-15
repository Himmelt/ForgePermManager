package org.soraworld.fpm.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.soraworld.fpm.core.Group;
import org.soraworld.fpm.core.Permission;

import java.io.IOException;
import java.util.HashMap;

public class EntireMessage implements IMessage {

    private Group base = new Group();
    private HashMap<String, Group> groups = new HashMap<>();
    private Permission permission = new Permission();

    @Override
    public void fromBytes(ByteBuf buf) {
        ByteBufInputStream input = new ByteBufInputStream(buf);
        try {
            base.read(input);
            byte size = input.readByte();
            for (int i = 0; i < size; i++) {
                byte length = input.readByte();
                byte[] bytes = new byte[length];
                input.readFully(bytes);
                String name = new String(bytes);
                Group group = new Group();
                group.read(input);
                if (!name.isEmpty() && !group.isEmpty()) {
                    groups.put(name, group);
                }
            }
            permission.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufOutputStream output = new ByteBufOutputStream(buf);
        try {
            base.write(output);
            output.writeByte(groups.size());
            for (String name : groups.keySet()) {
                Group group = groups.get(name);
                if (group == null) group = new Group();
                if (name == null) name = "";
                byte[] bytes = name.getBytes("UTF-8");
                output.writeByte(bytes.length);
                output.write(bytes);
                group.write(output);
            }
            permission.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Group getBase() {
        return base;
    }

    public HashMap<String, Group> getGroups() {
        return groups;
    }

    public Permission getPermission() {
        return permission;
    }

    public void set(Group base, HashMap<String, Group> groups, Permission permission) {
        if (base != null) this.base = base;
        if (groups != null) this.groups = groups;
        if (permission != null) this.permission = permission;
    }
}
