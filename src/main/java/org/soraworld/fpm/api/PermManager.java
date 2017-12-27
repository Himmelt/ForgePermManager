package org.soraworld.fpm.api;

import net.minecraft.entity.player.EntityPlayer;

public interface PermManager {

    boolean hasPermission(String player, String permission);

    boolean hasPermission(EntityPlayer player, String permission);

    void addPermission(String player, String permission);

    void addPermission(EntityPlayer player, String permission);

    void removePermission(String player, String permission);

    void removePermission(EntityPlayer player, String permission);

    boolean inGroup(String player, String group);

    boolean inGroup(EntityPlayer player, String group);

    boolean inTheGroup(String player, String group);

    boolean inTheGroup(EntityPlayer player, String group);

    boolean moveTo(String player, String group);

    boolean moveTo(EntityPlayer player, String group);

    void moveToDefault(String player);

    void moveToDefault(EntityPlayer player);

    boolean addPermGroup(String player, String group);

    boolean addPermGroup(EntityPlayer player, String group);

    void removePermGroup(String player, String group);

    void removePermGroup(EntityPlayer player, String group);

}
