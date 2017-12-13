package org.soraworld.fpm.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.soraworld.fpm.server.ServerPermManager;

public class EventBusHandler {

    private final ServerPermManager server = ServerPermManager.getInstance();

    @SubscribeEvent
    public void on(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player instanceof EntityPlayerMP) {
            server.sendGroup((EntityPlayerMP) player);
        }
    }
}
