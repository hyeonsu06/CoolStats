package io.github.hyeonsu06.coolstats;

import io.github.hyeonsu06.coolstats.entities.EntityDataHolder;
import io.github.hyeonsu06.coolstats.loops.TickLoop;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoolStats extends JavaPlugin {

    public final Plugin plugin = this;

    @Override
    public void onEnable() {
        EntityDataHolder holder = HolderInit.get();

        for (World w : Bukkit.getServer().getWorlds()) {
            for (Entity e : w.getEntities()) {
                holder.loadEntityDataFromFile(e);
            }
        }

        new TickLoop().runTaskTimer(plugin, 0, 1);
    }


    @Override
    public void onDisable() {
    }
}
