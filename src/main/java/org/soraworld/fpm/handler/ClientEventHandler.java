package org.soraworld.fpm.handler;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.soraworld.fpm.message.MessageManager;
import org.soraworld.fpm.message.TestMessage;

public class ClientEventHandler {

    @SubscribeEvent
    public void onPlayerClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntityPlayer() instanceof EntityPlayerSP) {
            MessageManager.getNetwork().sendToServer(new TestMessage("client->server"));
        }
    }
}
