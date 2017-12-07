package org.soraworld.fpm.data;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface IOMessage extends IMessage {

    void read(DataInput input) throws IOException;

    void write(DataOutput output) throws IOException;

}
