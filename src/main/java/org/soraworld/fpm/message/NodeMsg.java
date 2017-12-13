package org.soraworld.fpm.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.soraworld.fpm.core.Node;

import javax.annotation.Nonnull;
import java.io.IOException;

public class NodeMsg implements IMessage {

    private Node node;

    @Override
    public void fromBytes(ByteBuf buf) {
        ByteBufInputStream input = new ByteBufInputStream(buf);
        node = new Node();
        try {
            node.read(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufOutputStream output = new ByteBufOutputStream(buf);
        if (node == null) node = new Node();
        try {
            node.write(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNode(@Nonnull Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }
}
