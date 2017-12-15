package org.soraworld.fpm.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import org.soraworld.fpm.server.ServerPermManager;

import java.util.ArrayList;

public class CommandFPM extends IICommand {

    public CommandFPM(String name, String... aliases) {
        super(name, aliases);
        addSub(new IICommand("perm") {

            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (sender instanceof EntityPlayerMP) {
                    EntityPlayerMP player = (EntityPlayerMP) sender;
                    if (args.get(0).equals("add") && args.size() == 2) {
                        ServerPermManager.getInstance().add(player, args.get(1));
                    }
                }
            }
        });
    }

}
