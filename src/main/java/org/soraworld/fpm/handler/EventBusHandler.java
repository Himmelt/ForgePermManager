package org.soraworld.fpm.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventBusHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void on(PlayerInteractEvent.LeftClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player instanceof EntityPlayerMP) {
            //player.sendMessage(new TextComponentString("Server:" + server.has(player, "perm.test")));
            event.setCanceled(true);
        } else {
            //player.sendMessage(new TextComponentString("Client:" + client.has("perm.test")));
            event.setCanceled(true);
        }
    }
}
