package org.soraworld.fpm.command;

import net.minecraft.command.ICommandSender;
import org.soraworld.avalon.command.AvalonCommand;

import java.util.ArrayList;

public class CommandForgePerm extends AvalonCommand {

    public CommandForgePerm(String name, String... aliases) {
        super(name, aliases);
        addSub(new CommandGroup("group", "g"));
        addSub(new CommandUser("user", "u"));
        addSub(new AvalonCommand("reload") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
    }

    /*
    /fpm user addp [user|@selector] <perm>
	/fpm user delp [user|@selector] <perm>
	/fpm user setparent [user|@selector] <parent>
	/fpm user delparent [user|@selector]
	/fpm user addsub [user|@selector] <sub>
	/fpm user delsub [user|@selector] <sub>
	/fpm user setv [user|@selector] <key> <value>
	/fpm user delv [user|@selector] <key>
    /fpm user info [user|@selector]
	/fpm user save [user|@selector]
	/fpm user export [user|@selector] json|yml
	/fpm user import [user|@selector] json|yml
*/

}
