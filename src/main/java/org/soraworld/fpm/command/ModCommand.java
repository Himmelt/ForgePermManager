package org.soraworld.fpm.command;

import org.soraworld.avalon.command.AvalonCommand;

public class ModCommand extends AvalonCommand {

    public ModCommand(String name, String... aliases) {
        super(name, aliases);
        addSub(new CommandGroup("group", "g"));
    }

}
