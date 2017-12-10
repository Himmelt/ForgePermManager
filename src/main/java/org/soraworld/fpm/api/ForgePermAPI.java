package org.soraworld.fpm.api;

import org.soraworld.fpm.client.ClientPermManager;
import org.soraworld.fpm.server.ServerPermManager;

public final class ForgePermAPI {

    private static final ClientManager client = ClientPermManager.getInstance();
    private static final ServerManager server = ServerPermManager.getInstance();

    public static ClientManager getClientManager() {
        return client;
    }

    public static ServerManager getServerManager() {
        return server;
    }

}
