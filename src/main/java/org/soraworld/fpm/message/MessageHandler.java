package org.soraworld.fpm.message;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageHandler implements IMessageHandler<TestMessage, IMessage> {
    @Override
    public IMessage onMessage(TestMessage message, MessageContext ctx) {
        System.out.println(message);
        return null;
    }
}
