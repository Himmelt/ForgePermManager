package org.soraworld.fpm.handler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.soraworld.fpm.ForgePermManager;
import org.soraworld.fpm.core.Group;
import org.soraworld.fpm.core.Permission;
import org.soraworld.fpm.message.EntireMessage;

import java.util.HashMap;

public class EventBusHandler {

    private final SimpleNetworkWrapper network = ForgePermManager.getProxy().getNetwork();

    @SubscribeEvent
    public void on(PlayerInteractEvent.RightClickBlock event) {
        if (event.getSide() == Side.SERVER && event.getEntityPlayer() instanceof EntityPlayerMP) {
            event.getEntityPlayer().sendMessage(new TextComponentString("Side.Server"));
            EntireMessage message = new EntireMessage();
            message.set(new Group(), new HashMap<>(), new Permission());
            network.sendTo(message, (EntityPlayerMP) event.getEntityPlayer());
        } else {
            event.getEntityPlayer().sendMessage(new TextComponentString("Side.Client"));
        }
    }
}
