package com.github.helpfuldeer.deercommandmanager.manager;

import com.github.helpfuldeer.deercommandmanager.annonations.BasicCommand;
import com.github.helpfuldeer.deercommandmanager.annonations.GroupCommand;
import com.github.helpfuldeer.deercommandmanager.manager.command.Command;
import com.github.helpfuldeer.deercommandmanager.manager.command.SubCommand;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import lombok.Getter;
public class CommandManager {

    private final JavaPlugin javaPlugin;
    private final String commandPackage;
    @Getter private final Map<String, BasicCommand> basicCommandMap;
    @Getter private final Map<String, GroupCommand> groupCommandMap;

    public CommandManager(final JavaPlugin javaPlugin, final String commandPackage) {
        this.javaPlugin = javaPlugin;
        this.commandPackage = commandPackage;
        basicCommandMap = new HashMap<String, BasicCommand>();
        groupCommandMap = new HashMap<String, GroupCommand>();
    }

    @SneakyThrows
    public void registerBasicCommands() {
        for (final Class<? extends Command> clazz : new Reflections(commandPackage).getSubTypesOf(Command.class)) {
            if (!clazz.isAnnotationPresent(BasicCommand.class) || clazz.isAnnotationPresent(com.github.helpfuldeer.deercommandmanager.annonations.Command.class)) {
                javaPlugin.getLogger().log(Level.WARNING, "[" + javaPlugin.getDescription().getName() + "] Failed to load command of class " + clazz.getSimpleName() + " because the BasicCommand annotation is not present or the Command annotation is present");
            } else {
                final BasicCommand basicCommand = clazz.getDeclaredAnnotation(BasicCommand.class);
                final List<String> aliases = new ArrayList<>();
                Collections.addAll(aliases, basicCommand.aliases());
                final Command command = clazz.getDeclaredConstructor(String.class, String.class, String.class, List.class).newInstance(basicCommand.name(), basicCommand.description(), basicCommand.usage(), aliases);
                getCommandMap().register(basicCommand.name(), command);
                basicCommandMap.put(basicCommand.name(), basicCommand);
            }
        }
    }

    @SneakyThrows
    public void registerSubCommands() {
        for (final Class<? extends SubCommand> clazz : new Reflections(commandPackage).getSubTypesOf(SubCommand.class)) {
            if (!clazz.isAnnotationPresent(GroupCommand.class)) {
                javaPlugin.getLogger().log(Level.WARNING, "[" + javaPlugin.getDescription().getName() + "] Failed to load command of class " + clazz.getSimpleName() + " because the SubCommand annotation is not present");
            } else {
                final GroupCommand groupCommand = clazz.getDeclaredAnnotation(GroupCommand.class);
                final SubCommand command = clazz.getDeclaredConstructor(String.class).newInstance(groupCommand.name());
                getCommandMap().register(groupCommand.name(), command);
                groupCommandMap.put(groupCommand.name(), groupCommand);
            }
        }
    }

    @SneakyThrows
    public CommandMap getCommandMap() {
        final Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        field.setAccessible(true);
        return (CommandMap) field.get(Bukkit.getServer());
    }


}
