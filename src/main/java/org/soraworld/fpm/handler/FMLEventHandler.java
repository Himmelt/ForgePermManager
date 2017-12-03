package org.soraworld.fpm.handler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class FMLEventHandler {

    protected FMLEventChannel channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("fpm");

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        SimpleNetworkWrapper wrapper = NetworkRegistry.INSTANCE.newSimpleChannel("n");
        channel.sendTo(null, (EntityPlayerMP) event.player);
    }
}
