package org.soraworld.fpm.manager;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.soraworld.fpm.core.Permission;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class ClientPermManager {

    private Permission permission = new Permission();

    private static ClientPermManager instance;
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static ClientPermManager getInstance() {
        return instance == null ? instance = new ClientPermManager() : instance;
    }

    public boolean has(String permission) {
        return mc.player != null && this.permission.hasPerm(permission);
    }

    public void add(String permission) {
        this.permission.addPerm(permission);
    }

    public void remove(String permission) {
        this.permission.removePerm(permission);
    }

    public void download(@Nonnull Permission permission) {
        this.permission = permission;
    }
}
