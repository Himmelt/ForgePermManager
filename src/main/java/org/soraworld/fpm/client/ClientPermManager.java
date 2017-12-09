package org.soraworld.fpm.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.soraworld.fpm.core.GroupManager;
import org.soraworld.fpm.core.Permission;
import org.soraworld.fpm.message.EntireMessage;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class ClientPermManager {

    private static ClientPermManager instance;
    private static final GroupManager manager = new GroupManager();
    private Permission permission = new Permission();


    public static ClientPermManager getInstance() {
        return instance == null ? instance = new ClientPermManager() : instance;
    }

    public boolean has(@Nonnull String permission) {
        return false;
    }

    public void add(String permission) {
    }

    public void remove(String permission) {
    }

    public void download(@Nonnull EntireMessage entire) {
        System.out.println(entire);
        manager.set(entire.getBase(), entire.getGroups());
        this.permission = entire.getPermission();
    }

}
