package org.soraworld.fpm.api.manager;

import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nonnull;

public interface ServerPermManager {

    boolean has(@Nonnull EntityPlayer player, String permission);

    void add(@Nonnull EntityPlayer player, String permission);

    void remove(@Nonnull EntityPlayer player, String permission);

    void load(@Nonnull EntityPlayer player);

    void unload(@Nonnull EntityPlayer player);

    void saveAll();
}
