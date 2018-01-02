package org.soraworld.fpm.api;

import net.minecraft.entity.player.EntityPlayer;

import java.io.File;

public interface PermManager {

    void initialize(File root);

    boolean isReady();

    boolean hasPermission(String player, String permission);

    boolean hasPermission(EntityPlayer player, String permission);

    void addPermission(String player, String permission);

    void addPermission(EntityPlayer player, String permission);

    void removePermission(String player, String permission);

    void removePermission(EntityPlayer player, String permission);

    boolean groupHasPerm(String group, String permission);

    void groupAddPerm(String group, String permission);

    void groupRemovePerm(String group, String permission);

    boolean inGroup(String player, String group);

    boolean inGroup(EntityPlayer player, String group);

    boolean inTheGroup(String player, String group);

    boolean inTheGroup(EntityPlayer player, String group);

    void moveTo(String player, String group);

    void moveTo(EntityPlayer player, String group);

    void moveToDefault(String player);

    void moveToDefault(EntityPlayer player);

    void addSub(String player, String group);

    void addSub(EntityPlayer player, String group);

    void removeSub(String player, String group);

    void removeSub(EntityPlayer player, String group);

}
