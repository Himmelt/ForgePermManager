package org.soraworld.fpm.handler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.soraworld.fpm.server.ServerPermManager;

public class FMLServerEventHandler {

    private final ServerPermManager server = ServerPermManager.getInstance();

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            server.load(event.player);
            server.sendEntire((EntityPlayerMP) event.player);
        }
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            server.unload(event.player);
        }
    }
}
