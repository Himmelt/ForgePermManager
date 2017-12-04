package org.soraworld.fpm.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.soraworld.fpm.api.PermServerAPI;
import org.soraworld.fpm.api.manager.ServerPermManager;

public class EventHandler {

    @SubscribeEvent
    public void onPlayerClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getSide() == Side.SERVER) {
            func(event.getEntityPlayer());
        }
    }


    private void func(EntityPlayer player) {
        player.sendMessage(player.getDisplayName());
        ServerPermManager manager = PermServerAPI.getPermManager();
        manager.add(player, "perm.test.*");
        boolean has = manager.has(player, "perm");
        player.sendMessage(new TextComponentString("I hasGroup ? " + has));
    }

}
