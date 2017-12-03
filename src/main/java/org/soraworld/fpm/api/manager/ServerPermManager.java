package org.soraworld.fpm.api.manager;

import net.minecraft.entity.player.EntityPlayer;

public interface ServerPermManager {

    void init();

    boolean available();

    boolean has(EntityPlayer player, String permission);

    void add(EntityPlayer player, String permission);

    void remove(EntityPlayer player, String permission);


}
