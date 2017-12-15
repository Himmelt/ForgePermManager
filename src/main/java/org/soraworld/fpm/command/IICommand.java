package org.soraworld.fpm.command;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public abstract class IICommand implements ICommand {

    private final String name;
    private final List<String> aliases;
    private final TreeMap<String, IICommand> subMap = new TreeMap<>();

    IICommand(String name, String... aliases) {
        this.name = name;
        this.aliases = new ArrayList<>(Arrays.asList(aliases));
    }

    private static List<String> getMatchList(String arg, Collection<String> possibles) {
        if (arg.isEmpty()) return new ArrayList<>(possibles);
        return possibles.stream().filter(s -> s.startsWith(arg)).collect(Collectors.toList());
    }

    final void addSub(IICommand sub) {
        this.subMap.put(sub.getName(), sub);
        for (String alias : sub.getAliases()) {
            IICommand command = this.subMap.get(alias);
            if (command == null || !command.getName().equals(alias)) {
                this.subMap.put(alias, sub);
            }
        }
    }

    @Nonnull
    public String getUsage(@Nonnull ICommandSender sender) {
        return "";
    }

    @Nonnull
    public final String getName() {
        return name;
    }

    @Override
    public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) {
        execute(sender, new ArrayList<>(Arrays.asList(args)));
    }

    @Nonnull
    public final List<String> getAliases() {
        return aliases;
    }

    @Override
    public boolean checkPermission(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender) {
        return true;
    }

    @Nonnull
    public List<String> getTabCompletions(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args, @Nullable BlockPos targetPos) {
        return getTabCompletions(sender, new ArrayList<>(Arrays.asList(args)));
    }

    public void execute(ICommandSender sender, ArrayList<String> args) {
        if (args.size() >= 1) {
            IICommand sub = subMap.get(args.remove(0));
            if (sub != null) {
                sub.execute(sender, args);
                //return;
            }
        }
        //I19n.sendChat2(getUsage(sender));
    }

    @Override
    public boolean isUsernameIndex(@Nonnull String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(@Nonnull ICommand command) {
        return getName().compareTo(command.getName());
    }

    private List<String> getTabCompletions(ICommandSender sender, ArrayList<String> args) {
        if (args.size() == 1) {
            return getMatchList(args.get(0), subMap.keySet());
        } else if (args.size() >= 2) {
            IICommand sub = subMap.get(args.remove(0));
            if (sub != null) return sub.getTabCompletions(sender, args);
            else return new ArrayList<>();
        } else {
            return new ArrayList<>(subMap.keySet());
        }
    }

}
