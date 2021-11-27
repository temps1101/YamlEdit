package com.temps1101.yamledit.yamledit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class YamlEdit extends JavaPlugin {
    private static YamlEdit plugin;
    private static HashMap<Entity, File> selectedYamlCache = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginCommand("yamledit").setExecutor(new YamlEditCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static YamlEdit getPlugin() {
        return plugin;
    }

    public static HashMap<Entity, File> getSelectedYamlCache() {
        return selectedYamlCache;
    }
}
