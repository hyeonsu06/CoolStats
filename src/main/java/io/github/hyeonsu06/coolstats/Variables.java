package io.github.hyeonsu06.coolstats;

import org.bukkit.Bukkit;

import java.io.File;

import static java.io.File.separator;

public class Variables {
    final public static String pluginName = "CoolStats";
    final public static String prefix =
            "[" + Bukkit.getPluginManager().getPlugin(pluginName).getDescription().getPrefix() + "] ";
    public static final String path =
            Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder().getAbsolutePath() + separator;
    public static File dataFolder =
            new File(Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder(), "entity_data");
}