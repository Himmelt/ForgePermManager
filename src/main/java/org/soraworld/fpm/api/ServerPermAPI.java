package org.soraworld.fpm.api;

import org.soraworld.fpm.api.manager.ServerPermManager;
import org.soraworld.fpm.manager.ServerPermManagerImpl;

//@SideOnly(Side.SERVER)
public class ServerPermAPI {

    private static final ServerPermManager server = new ServerPermManagerImpl();

    public static ServerPermManager getPermManager() {
        return server;
    }
}
