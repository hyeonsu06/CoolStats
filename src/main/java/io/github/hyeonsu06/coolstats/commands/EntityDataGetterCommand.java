package io.github.hyeonsu06.coolstats.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Objects;

import static io.github.hyeonsu06.coolstats.Initialization.getEntityData;
import static io.github.hyeonsu06.coolstats.commands.GetNearestEntity.getNearest;

public class EntityDataGetterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            Entity entity = getNearest(player, args);
            if (Objects.isNull(entity)) return false;

            Object ced = getEntityData().getEntityData(entity).getData(args[1]);
            if (ced == null) {
                player.sendMessage(ChatColor.RED + "Targeted entity does not have data of " + args[1] + "!");
                return false;
            }
            player.sendMessage("Entity " + entity.getUniqueId() + "'s data value of " + args[1] + " is : " + ced);
        }
        return true;
    }
}
