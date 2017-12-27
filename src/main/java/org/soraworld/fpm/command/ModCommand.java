package org.soraworld.fpm.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import org.soraworld.avalon.command.AvalonCommand;

import java.util.ArrayList;

public class ModCommand extends AvalonCommand {

    public ModCommand(String name, String... aliases) {
        super(name, aliases);
        addSub(new AvalonCommand("perm") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (sender instanceof EntityPlayerMP) {
                    EntityPlayerMP player = (EntityPlayerMP) sender;
                    if (args.get(0).equals("add") && args.size() == 2) {
                        //ServerPermManager.getInstance().add(player, args.get(1));
                    } else if (args.get(0).equals("remove") && args.size() == 2) {
                        //ServerPermManager.getInstance().remove(player, args.get(1));
                    }
                }
            }
        });
    }

}
