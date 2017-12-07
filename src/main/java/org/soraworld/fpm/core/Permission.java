package org.soraworld.fpm.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.soraworld.fpm.ForgePermManager;
import org.soraworld.fpm.data.IOMessage;
import org.soraworld.fpm.manager.GroupManager;

import javax.annotation.Nonnull;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashSet;

public class Permission implements IOMessage {

    private Node root;
    private HashSet<String> groups;
    private static final GroupManager manager = GroupManager.getInstance();

    /**
     * 当前权限主体是否拥有给定权限.
     * 注意：{@code permission}必须匹配{@code ([a-zA-Z0-9_]+\.)+(\1|\*)}
     * 否则返回{@code false}
     *
     * @param permission 给定权限
     * @return 是否拥有
     */
    public boolean hasPerm(@Nonnull String permission) {
        // 权限正则格式
        if (!permission.matches("([a-zA-Z0-9_]+\\.)+(\\1|\\*)")) return false;
        // 权限节点解析数组
        String[] nodes = permission.split("\\.");
        // 默认组权限检查
        if (manager.getBase().hasNodes(nodes)) return true;
        // 组权限检查
        if (groups != null) {
            for (String name : groups) {
                Group group = manager.getGroup(name);
                if (group != null && group.hasNodes(nodes)) return true;
            }
        }
        // 自身权限检查
        return root != null && root.hasNodes(nodes);
    }

    public void addPerm(String permission) {
        // 权限正则格式
        if (!permission.matches("([a-zA-Z0-9_]+\\.)+(\\1|\\*)")) return;
        String[] nodes = permission.split("\\.");
        if (root == null) root = new Node();
        // 权限节点解析数组
        root.addNodes(nodes);
        update();
    }

    public void removePerm(String permission) {
        if (root != null) {
            // 权限正则格式
            if (!permission.matches("([a-zA-Z0-9_]+\\.)+(\\1|\\*)")) return;
            String[] nodes = permission.split("\\.");
            root.removeNodes(nodes);
            if (root.isEmpty()) root = null;
            update();
        }
    }

    public boolean hasGroup(String name) {
        return groups != null && groups.contains(name);
    }

    public void addGroup(String name) {
        if (groups == null) groups = new HashSet<>();
        groups.add(name);
        update();
    }

    public void removeGroup(String name) {
        if (groups != null) {
            groups.remove(name);
            if (groups.isEmpty()) groups = null;
            update();
        }
    }

    private void update() {

    }

    @Override
    public void read(DataInput input) throws IOException {
        byte size = input.readByte();
        if (size <= 0) {
            groups = null;
        } else {
            groups = new HashSet<>();
            for (int i = 0; i < size; i++) {
                byte length = input.readByte();
                byte[] bytes = new byte[length];
                input.readFully(bytes);
                groups.add(new String(bytes, "UTF-8"));
            }
        }
        root = new Node();
        root.read(input);
    }

    @Override
    public void write(DataOutput output) throws IOException {
        if (groups == null) {
            output.writeByte(0);
        } else {
            output.writeByte(groups.size());
            for (String group : groups) {
                byte[] bytes = group.getBytes("UTF-8");
                if (bytes.length >= 1) {
                    output.writeByte(bytes.length);
                    output.write(bytes);
                }
            }
        }
        root.write(output);
        output.write(0);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            read(new ByteBufInputStream(buf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        try {
            write(new ByteBufOutputStream(buf));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class PermissionHandler implements IMessageHandler<Permission, IMessage> {

        @Override
        public IMessage onMessage(Permission message, MessageContext ctx) {
            System.out.println(ctx.side);
            ForgePermManager.getProxy().getPermManager().process(message, ctx);
            return null;
        }
    }
}
