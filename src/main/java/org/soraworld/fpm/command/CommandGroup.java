package org.soraworld.fpm.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;
import org.soraworld.avalon.command.AvalonCommand;
import org.soraworld.fpm.api.ForgePermAPI;
import org.soraworld.fpm.core.GroupManager;
import org.soraworld.fpm.core.Permission;

import java.util.ArrayList;

public class CommandGroup extends AvalonCommand {

    private final GroupManager groupManager = ForgePermAPI.getPermManager().getGroupManager();

    protected CommandGroup(String name, String... aliases) {
        super(name, aliases);
        addSub(new AvalonCommand("create") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (args.size() == 1) {
                    String name = args.get(0);
                    if (name != null && !name.isEmpty() && name.matches("[0-9a-zA-Z_]+") && !groupManager.hasGroup(name)) {
                        groupManager.setGroup(name, new Permission(groupManager));
                        sender.sendMessage(new TextComponentString("create success!"));
                    } else {
                        sender.sendMessage(new TextComponentString("group exist or invalid name"));
                    }
                } else {
                    sender.sendMessage(new TextComponentString("invalid parameters!"));
                }
            }
        });
        addSub(new AvalonCommand("delete") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (args.size() == 1) {
                    groupManager.removeGroup(args.get(0));
                    sender.sendMessage(new TextComponentString("delete success!"));
                } else {
                    sender.sendMessage(new TextComponentString("invalid parameters!"));
                }
            }
        });
        addSub(new AvalonCommand("addp") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (args.size() == 2) {
                    ForgePermAPI.getPermManager().addGroupPerm(args.get(0), args.get(1));
                    sender.sendMessage(new TextComponentString("addp success!"));
                } else {
                    sender.sendMessage(new TextComponentString("invalid parameters!"));
                }
            }
        });
        addSub(new AvalonCommand("delp") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (args.size() == 2) {
                    ForgePermAPI.getPermManager().delGroupPerm(args.get(0), args.get(1));
                    sender.sendMessage(new TextComponentString("delp success!"));
                } else {
                    sender.sendMessage(new TextComponentString("invalid parameters!"));
                }
            }
        });
        addSub(new AvalonCommand("setparent","setpa") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (args.size() == 2) {
                    groupManager.getGroup(args.get(0)).moveTo(args.get(1));
                    sender.sendMessage(new TextComponentString("setparent success!"));
                } else {
                    sender.sendMessage(new TextComponentString("invalid parameters!"));
                }
            }
        });
        addSub(new AvalonCommand("delparent","delpa") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (args.size() == 1) {
                    groupManager.getGroup(args.get(0)).moveTo(null);
                    sender.sendMessage(new TextComponentString("delparent success!"));
                } else {
                    sender.sendMessage(new TextComponentString("invalid parameters!"));
                }
            }
        });
        addSub(new AvalonCommand("addsub") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (args.size() == 2) {
                    groupManager.getGroup(args.get(0)).addSub(args.get(1));
                    sender.sendMessage(new TextComponentString("addsub success!"));
                } else {
                    sender.sendMessage(new TextComponentString("invalid parameters!"));
                }
            }
        });
        addSub(new AvalonCommand("delsub") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (args.size() == 2) {
                    groupManager.getGroup(args.get(0)).removeSub(args.get(1));
                    sender.sendMessage(new TextComponentString("delsub success!"));
                } else {
                    sender.sendMessage(new TextComponentString("invalid parameters!"));
                }
            }
        });
        addSub(new AvalonCommand("setvalue","setv") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (args.size() == 3) {
                    groupManager.getGroup(args.get(0)).setValue(args.get(1), args.get(2));
                    sender.sendMessage(new TextComponentString("setvalue success!"));
                } else {
                    sender.sendMessage(new TextComponentString("invalid parameters!"));
                }
            }
        });
        addSub(new AvalonCommand("delvalue","delv") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (args.size() == 2) {
                    groupManager.getGroup(args.get(0)).delValue(args.get(1));
                    sender.sendMessage(new TextComponentString("delvalue success!"));
                } else {
                    sender.sendMessage(new TextComponentString("invalid parameters!"));
                }
            }
        });
        addSub(new AvalonCommand("save") {
            @Override
            public void execute(ICommandSender sender, ArrayList<String> args) {
                if (args.size() == 1) {
                    ForgePermAPI.getPermManager().getStorage().saveGroup(args.get(0));
                    sender.sendMessage(new TextComponentString("save success!"));
                } else {
                    sender.sendMessage(new TextComponentString("invalid parameters!"));
                }
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
