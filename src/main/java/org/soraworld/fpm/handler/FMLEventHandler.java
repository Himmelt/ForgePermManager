package org.soraworld.fpm.handler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.soraworld.fpm.api.ServerPermAPI;
import org.soraworld.fpm.api.manager.ServerPermManager;

public class FMLEventHandler {

    private static final ServerPermManager manager = ServerPermAPI.getPermManager();

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        manager.load(event.player);
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        manager.unload(event.player);
    }
}
