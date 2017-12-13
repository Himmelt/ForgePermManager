package org.soraworld.fpm.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.soraworld.fpm.core.Group;

import javax.annotation.Nonnull;
import java.io.IOException;

public class GroupMsg implements IMessage {

    private Group group;

    @Override
    public void fromBytes(ByteBuf buf) {
        ByteBufInputStream input = new ByteBufInputStream(buf);
        group = new Group();
        try {
            group.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufOutputStream output = new ByteBufOutputStream(buf);
        if (group == null) group = new Group();
        try {
            group.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setGroup(@Nonnull Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
}
