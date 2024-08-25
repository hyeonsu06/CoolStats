package io.github.hyeonsu06.coolstats.loops;

import io.github.hyeonsu06.coolstats.items.LoreWriter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static io.github.hyeonsu06.coolstats.Utilities.Methods.*;

public class TickLoop extends BukkitRunnable {
    @Override
    public void run() {
        for (Entity e : getAllEntities()) {
            if (e instanceof Player) {
                for (ItemStack i : getAllItemsOfPlayer((Player) e))
                    new LoreWriter().write(i);
            } else if (e instanceof LivingEntity) {
                for (ItemStack i : getAllItemsOfLivingEntity((LivingEntity) e))
                    new LoreWriter().write(i);
            }
        }
    }
}
