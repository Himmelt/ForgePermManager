package org.soraworld.fpm.handler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.soraworld.fpm.message.MessageManager;
import org.soraworld.fpm.message.TestMessage;

public class EventBusHandler {

    @SubscribeEvent
    public void onPlayerClickBlock(PlayerInteractEvent event) {
        System.out.println("Side:" + event.getSide());
        if (event.getEntityPlayer() instanceof EntityPlayerMP) {
            MessageManager.getNetwork().sendTo(new TestMessage("server->client"), (EntityPlayerMP) event.getEntityPlayer());
        }

    }

}
