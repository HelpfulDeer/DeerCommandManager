package com.github.helpfuldeer.deercommandmanager;

import com.github.helpfuldeer.deercommandmanager.manager.CommandManager;
import com.github.helpfuldeer.deercommandmanager.manager.CommandSettings;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import lombok.Getter;
public final class DeerCommandManager {

    @Getter private static DeerCommandManager deerCommandManager;
    @Getter private final JavaPlugin javaPlugin;
    @Getter private final CommandManager commandManager;
    @Getter @Setter
    private CommandSettings commandSettings = new CommandSettings(){};

    public DeerCommandManager(final JavaPlugin javaPlugin, final String commandPackage) {
        this.commandSettings = new CommandSettings() {};
        DeerCommandManager.deerCommandManager = this;
        this.javaPlugin = javaPlugin;
        this.commandManager = new CommandManager(javaPlugin, commandPackage);
    }



}
