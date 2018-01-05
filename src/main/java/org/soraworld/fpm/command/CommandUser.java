package org.soraworld.fpm.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;
import org.soraworld.avalon.command.AvalonCommand;
import org.soraworld.fpm.api.ForgePermAPI;
import org.soraworld.fpm.core.GroupManager;
import org.soraworld.fpm.core.Permission;

import java.util.ArrayList;

public class CommandUser extends AvalonCommand {

    private final GroupManager groupManager = ForgePermAPI.getPermManager().getGroupManager();

    protected CommandUser(String name, String... aliases) {
        super(name, aliases);
        addSub(new AvalonCommand("addp") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
        addSub(new AvalonCommand("delp") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
        addSub(new AvalonCommand("setparent","setpa") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
        addSub(new AvalonCommand("delparent","delpa") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
        addSub(new AvalonCommand("addsub") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
        addSub(new AvalonCommand("delsub") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
        addSub(new AvalonCommand("setvalue","setv") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
        addSub(new AvalonCommand("delvalue","delv") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
        addSub(new AvalonCommand("save") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
        addSub(new AvalonCommand("export") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
        addSub(new AvalonCommand("import") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
            }
        });
    }
}
