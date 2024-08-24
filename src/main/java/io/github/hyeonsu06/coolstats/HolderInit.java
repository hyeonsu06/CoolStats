package io.github.hyeonsu06.coolstats;

import io.github.hyeonsu06.coolstats.entities.EntityDataHolder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class HolderInit {

    private static EntityDataHolder EDH;

    public static EntityDataHolder get() {
        if (EDH == null) {
            EDH = new EntityDataHolder();
            for (World w : Bukkit.getServer().getWorlds()) {
                for (Entity e : w.getEntities()) {
                    EDH.loadEntityDataFromFile(e);
                }
            }
        }
        return EDH;
    }
}
