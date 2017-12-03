package org.soraworld.fpm.handler;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PermissionMessage implements IMessage {
    @Override
    public void fromBytes(ByteBuf buf) {
        NBTTagCompound compound = new NBTTagCompound();
        ByteBufUtils.writeTag(buf, compound);
    }

    @Override
    public void toBytes(ByteBuf buf) {

    }
}
