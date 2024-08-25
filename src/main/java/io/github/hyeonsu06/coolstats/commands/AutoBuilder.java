package io.github.hyeonsu06.coolstats.commands;

import io.github.hyeonsu06.coolstats.CoolStats;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AutoBuilder extends Command implements CommandExecutor {

    public AutoBuilder() {
        super("autobuild", "Renews plugin automatically.", "autobuild", List.of("recompile"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return true;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        new Thread("AutoBuilder") {
            @Override
            public void run() {
                try {
                    Process process = Runtime.getRuntime().exec("./reload-server.sh");
                    Scanner scanner = new Scanner(process.getInputStream());
                    while(process.isAlive()) {
                        String st = "[AutoBuilder] " + scanner.nextLine();
                        System.out.println(st);
                    }
                } catch (IOException e) {
                    Bukkit.getLogger().warning(e.getMessage());
                    Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(e.getMessage()));
                    return;
                } catch (NoSuchElementException ignored) {
                }
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload confirm");
                    }
                }.runTask(CoolStats.getPlugin(CoolStats.class));
            }
        }.start();
        return true;
    }
}