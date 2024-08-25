package io.github.hyeonsu06.coolstats.commands;

import io.github.hyeonsu06.coolstats.entities.CustomEntityData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Objects;

import static io.github.hyeonsu06.coolstats.Initialization.getEntityData;
import static io.github.hyeonsu06.coolstats.commands.GetNearestEntity.getNearest;

public class EntityDataSetterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            Entity entity = getNearest(player, args);
            if (Objects.isNull(entity)) return false;

            CustomEntityData ced = getEntityData().getEntityData(entity);
            ced.addData(args[1], args[2]);
            player.sendMessage("Entity " + entity.getUniqueId() + "'s data value of " + args[1] + "has been set to : " + args[2]);
        }
        return true;
    }
}
