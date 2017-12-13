package org.soraworld.fpm.client;

import org.soraworld.fpm.api.ClientManager;
import org.soraworld.fpm.core.Group;
import org.soraworld.fpm.core.GroupManager;
import org.soraworld.fpm.core.Permission;
import org.soraworld.fpm.message.EntireMessage;
import org.soraworld.fpm.message.GroupMsg;

import javax.annotation.Nonnull;

public class ClientPermManager implements ClientManager {

    private final GroupManager manager = new GroupManager();
    private Permission permission = new Permission();

    private static ClientPermManager instance;

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

    public void receiveNodeMsg(GroupMsg msg) {
        Group group = msg.getGroup();
    }
}
