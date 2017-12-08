package org.soraworld.fpm.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.soraworld.fpm.core.Group;
import org.soraworld.fpm.core.Permission;

import java.util.HashMap;

public class EntireMessage implements IMessage {

    private Group base;
    private HashMap<String, Group> groups;
    private Permission permission;

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufOutputStream output = new ByteBufOutputStream(buf);
        output.writeByte();
    }

    private EntireMessage() {
    }

    public EntireMessage(Permission permission, Group base, HashMap<String, Group> groups) {
        this.permission = permission;
        this.base = base;
        this.groups = groups;
    }

    public Permission getPermission() {
        return permission;
    }

    public Group getBase() {
        return base;
    }

    public HashMap<String, Group> getGroups() {
        return groups;
    }

}
