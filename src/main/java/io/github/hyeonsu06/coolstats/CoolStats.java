package io.github.hyeonsu06.coolstats;

import io.github.hyeonsu06.coolstats.commands.AutoBuilder;
import io.github.hyeonsu06.coolstats.commands.EntityDataGetterCommand;
import io.github.hyeonsu06.coolstats.commands.EntityDataRemoverCommand;
import io.github.hyeonsu06.coolstats.commands.EntityDataSetterCommand;
import io.github.hyeonsu06.coolstats.loops.TickLoop;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static io.github.hyeonsu06.coolstats.Initialization.getEntityData;
import static io.github.hyeonsu06.coolstats.Utilities.Methods.getAllEntities;
import static io.github.hyeonsu06.coolstats.Utilities.Variables.path;
import static io.github.hyeonsu06.coolstats.Utilities.Variables.prefix;

public final class CoolStats extends JavaPlugin {
    public JavaPlugin plugin = this;

    @Override
    public void onEnable() {
        for (Entity e : getAllEntities()) getEntityData().loadEntityDataFromFile(e);

        File entity = new File(path + "entities.json");
        if (!entity.exists()) {
            saveResource("entities.json", false);
            Bukkit.getLogger().info(prefix + "Entity base data file not found, created default one");
        } else Bukkit.getLogger().info(prefix + "Entity base data file already exists, skipping it");

        File item = new File(path + "items.json");
        if (!item.exists()) {
            saveResource("items.json", false);
            Bukkit.getLogger().info(prefix + "Item base data file not found, created default one");
        } else Bukkit.getLogger().info(prefix + "Item base data file already exists, skipping it");

        for (World w : getServer().getWorlds()) {
            w.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            w.setGameRule(GameRule.DO_ENTITY_DROPS, false);
            w.setGameRule(GameRule.DO_FIRE_TICK, false);
            w.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
            w.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
            w.setGameRule(GameRule.KEEP_INVENTORY, true);
            w.setGameRule(GameRule.RANDOM_TICK_SPEED, 0);
            w.setGameRule(GameRule.SPAWN_RADIUS, 0);
            w.setGameRule(GameRule.NATURAL_REGENERATION, false);
        }

        plugin.getCommand("autobuild").setExecutor(new AutoBuilder());

        plugin.getCommand("getentitydata").setExecutor(new EntityDataGetterCommand());
        plugin.getCommand("setentitydata").setExecutor(new EntityDataSetterCommand());
        plugin.getCommand("removeentitydata").setExecutor(new EntityDataRemoverCommand());
        new TickLoop().runTaskTimer(plugin, 0, 1);
    }

    @Override
    public void onDisable() {
        for (Entity e : getAllEntities()) {
            Bukkit.getLogger().info("Saving data of " + e.getUniqueId() + "(" + e.getType() + ")");
            try {
                getEntityData().saveEntityDataToFile(e, getEntityData().getEntityData(e));
                Bukkit.getLogger().info("Saved data of " + e.getUniqueId() + "(" + e.getType() + ")");
            } catch (NullPointerException npe) {
                Bukkit.getLogger().warning(npe.getMessage());
                Bukkit.getLogger().warning("Entity " + e.getUniqueId() + "(" + e.getType() + ") has been removed due to error.");
                e.remove();
            }
        }
    }
}
