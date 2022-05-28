package com.github.helpfuldeer.deercommandmanager.manager;

import org.bukkit.ChatColor;

public interface CommandSettings {

    default String getInvalidArguments() {
        return ChatColor.translateAlternateColorCodes('&', "&cThe command you're trying to execute has missing arguments!");
    }

    default String getInvalidPermission() {
        return ChatColor.translateAlternateColorCodes('&', "&cYou don't have the right permission to execute this command");
    }

}
