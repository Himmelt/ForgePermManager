package org.soraworld.fpm.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface BinarySerialize {

    void read(DataInput input) throws IOException;

    void write(DataOutput output) throws IOException;

}
