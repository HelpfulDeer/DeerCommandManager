package com.github.helpfuldeer.deercommandmanager.manager.command;

import com.github.helpfuldeer.deercommandmanager.DeerCommandManager;
import com.github.helpfuldeer.deercommandmanager.annonations.Command;
import lombok.SneakyThrows;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class SubCommand extends BukkitCommand {

    public SubCommand(String name) {
        super(name);
    }

    @SneakyThrows
    @Override
    public boolean execute(final CommandSender sender, final String commandLabel, final String[] args) {
        if (args.length > 0) {
            for (final Method method : getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(Command.class)) {
                    final Command cmd = method.getDeclaredAnnotation(Command.class);
                    List<String> aliases = new ArrayList<>();
                    Collections.addAll(aliases, cmd.aliases());
                    if ((args[0].equals(cmd.name())) || (aliases.contains(args[0]))) {
                        if (!cmd.permission().isEmpty() && !sender.hasPermission(cmd.permission())) {
                            sender.sendMessage(DeerCommandManager.getDeerCommandManager().getCommandSettings().getInvalidPermission());
                            return true;
                        }
                        method.invoke(this, sender, args);
                    }
                }
            }
        }
        return true;
    }
}
