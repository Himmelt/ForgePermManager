package org.soraworld.fpm.message;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.soraworld.fpm.Constants;

public class MessageManager {

    private static SimpleNetworkWrapper network;

    public static SimpleNetworkWrapper getNetwork() {
        return network == null ? network = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MOD_ID) : network;
    }

}
