package org.soraworld.fpm.handler;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler {

    @SubscribeEvent
    public void onPlayerClickBlock(PlayerInteractEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        System.out.println(player + "|" + event.getSide());
        if (player instanceof EntityPlayerSP) {
            System.out.println(player + "|SP|" + player.hashCode());
        }

        if (player instanceof EntityPlayerMP) {
            System.out.println(player + "|MP|" + player.hashCode());
        }
    }
}
