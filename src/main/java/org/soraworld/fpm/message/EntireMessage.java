package org.soraworld.fpm.message;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.soraworld.fpm.core.Group;
import org.soraworld.fpm.core.Permission;

import java.util.HashMap;

public class EntireMessage implements IMessage {

    private Group base = new Group();
    private HashMap<String, Group> groups = new HashMap<>();
    private Permission permission = new Permission();

    @Override
    public void fromBytes(ByteBuf buf) {
        System.out.println("fromBytes:" + buf.readByte());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        System.out.println("toBytes:" + buf.writeByte(23));
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
