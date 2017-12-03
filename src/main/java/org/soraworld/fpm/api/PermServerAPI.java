package org.soraworld.fpm.api;

import org.soraworld.fpm.api.manager.ServerPermManager;
import org.soraworld.fpm.server.ServerPermManagerImpl;

//@SideOnly(Side.SERVER)
public class PermServerAPI {

    private static final ServerPermManager server = new ServerPermManagerImpl();

    public static boolean available() {
        return server.available();
    }

    public static ServerPermManager getPermManager() {
        return server;
    }
}
