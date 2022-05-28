package com.github.helpfuldeer.deercommandmanager.manager.command;

import com.github.helpfuldeer.deercommandmanager.DeerCommandManager;
import com.github.helpfuldeer.deercommandmanager.annonations.BasicCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class Command extends org.bukkit.command.Command {

    public Command(final String name, final String description, final String usageMessage, final List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
        if (!getBasicCommand().permission().isEmpty() && !sender.hasPermission(getBasicCommand().permission())) {
            sender.sendMessage(DeerCommandManager.getDeerCommandManager().getCommandSettings().getInvalidPermission());
            return true;
        }
        run(sender, args);
        return true;
    }

    public abstract void run(final CommandSender p0, final String[] p1);

    private BasicCommand getBasicCommand() {
        return DeerCommandManager.getDeerCommandManager().getCommandManager().getBasicCommandMap().get(getName());
    }
}