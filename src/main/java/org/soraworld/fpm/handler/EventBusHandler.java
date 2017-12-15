package org.soraworld.fpm.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.soraworld.fpm.client.ClientPermManager;
import org.soraworld.fpm.server.ServerPermManager;

public class EventBusHandler {

    private final ServerPermManager server = ServerPermManager.getInstance();
    private final ClientPermManager client = ClientPermManager.getInstance();

    @SubscribeEvent
    public void on(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player instanceof EntityPlayerMP) {
            player.sendMessage(new TextComponentString("Server:" + server.has(player, "perm.test")));
        } else {
            player.sendMessage(new TextComponentString("Client:" + client.has("perm.test")));
        }
    }
}
