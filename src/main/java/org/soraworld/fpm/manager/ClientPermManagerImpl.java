package org.soraworld.fpm.manager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.soraworld.fpm.api.manager.ClientPermManager;

import java.util.HashSet;

@SideOnly(Side.CLIENT)
public class ClientPermManagerImpl implements ClientPermManager {

    private HashSet<String> permissions = new HashSet<>();
    private EntityPlayerSP player = Minecraft.getMinecraft().player;

    @Override
    public void init() {
        permissions = new HashSet<>();
        player = Minecraft.getMinecraft().player;
    }

    @Override
    public boolean available() {
        return player != null && permissions != null;
    }

    @Override
    public boolean has(String permission) {
        return player != null && permissions.contains(permission);
    }

    public void add(String permission) {
        permissions.add(permission);
    }

    public void remove(String permission) {
        permissions.remove(permission);
    }

}
