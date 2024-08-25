package io.github.hyeonsu06.coolstats.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class GetNearestEntity {
    public static Entity getNearest(Player player, String[] args) {
        Entity entity = null;
        try {
            if (args[0].equals("0")) {
                entity = player;
            } else {
                entity = player
                        .getNearbyEntities(Double.parseDouble(args[0]), Double.parseDouble(args[0]), Double.parseDouble(args[0]))
                        .stream()
                        .filter(e -> e instanceof LivingEntity)
                        .findFirst()
                        .orElse(null);
            }
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Please enter the number!", ChatColor.RED + "To target yourself, set distance to 0.");
            Bukkit.getLogger().warning(e.getMessage());
        }

        return entity;
    }
}
