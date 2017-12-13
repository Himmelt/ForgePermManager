package org.soraworld.fpm.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.soraworld.fpm.ForgePermManager;
import org.soraworld.fpm.api.ServerManager;
import org.soraworld.fpm.core.Group;
import org.soraworld.fpm.core.GroupManager;
import org.soraworld.fpm.core.Node;
import org.soraworld.fpm.core.Permission;
import org.soraworld.fpm.manager.StorageManager;
import org.soraworld.fpm.message.EntireMessage;
import org.soraworld.fpm.message.GroupMsg;

import javax.annotation.Nonnull;
import java.util.HashMap;

public class ServerPermManager implements ServerManager {

    private final GroupManager groupManager = new GroupManager();
    private final HashMap<String, Permission> players = new HashMap<>();
    private final SimpleNetworkWrapper network = ForgePermManager.getProxy().getNetwork();

    private static ServerPermManager instance;

    public static ServerPermManager getInstance() {
        return instance == null ? instance = new ServerPermManager() : instance;
    }

    public boolean has(@Nonnull EntityPlayer player, String permission) {
        if (!permission.matches("([a-zA-Z0-9_]+\\.)+(\\1|\\*)")) return false;
        String[] nodes = permission.split("\\.");
        return false;
    }

    public void add(@Nonnull EntityPlayer player, String permission) {
    }

    public void remove(@Nonnull EntityPlayer player, String permission) {
    }

    public void load(@Nonnull EntityPlayer player) {
        players.put(player.getName(), StorageManager.get(player));
    }

    public void unload(@Nonnull EntityPlayer player) {
        StorageManager.save(player, players.get(player.getName()));
        players.remove(player.getName());
    }

    public void saveAll() {
        for (String player : players.keySet()) {
            StorageManager.save(player, players.get(player));
        }
    }

    public void sendEntire(EntityPlayerMP player) {
        EntireMessage message = new EntireMessage();
        Permission permission = players.get(player.getName());
        message.set(groupManager.getBase(), groupManager.getGroups(), permission);
        network.sendTo(message, player);
    }

    public void sendGroup(EntityPlayerMP player) {
        Node node = new Node();
        node.addNodes("abc.d.e".split("\\."));
        node.addNodes("mc.gm.c".split("\\."));
        Group group = new Group();
        group.addParent("parent1");
        group.addParent("pt2");
        group.setNode(node);
        GroupMsg msg = new GroupMsg();
        msg.setGroup(group);
        network.sendTo(msg, player);
    }
}
