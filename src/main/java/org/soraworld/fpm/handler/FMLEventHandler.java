package org.soraworld.fpm.handler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.soraworld.fpm.server.ServerPermManager;

public class FMLEventHandler {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPermManager.getInstance().load(event.player);
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        ServerPermManager.getInstance().unload(event.player);
    }
}
