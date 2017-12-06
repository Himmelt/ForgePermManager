package org.soraworld.fpm.manager;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashSet;

@SideOnly(Side.CLIENT)
public class ClientPermManager {

    private final HashSet<String> permissions = new HashSet<>();
    private static final Minecraft mc = Minecraft.getMinecraft();

    public boolean has(String permission) {
        return mc.player != null && permissions.contains(permission);
    }

    public void add(String permission) {
        permissions.add(permission);
    }

    public void remove(String permission) {
        permissions.remove(permission);
    }

}
