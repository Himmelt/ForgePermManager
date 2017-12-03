package org.soraworld.fpm.api;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.soraworld.fpm.api.manager.ClientPermManager;
import org.soraworld.fpm.client.ClientPermManagerImpl;

@SideOnly(Side.CLIENT)
public class PermClientAPI {

    private static final ClientPermManager client = new ClientPermManagerImpl();

    public static boolean available() {
        return client.available();
    }

    public static ClientPermManager getPermManager() {
        return client;
    }

}
