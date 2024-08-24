package io.github.hyeonsu06.coolstats;

import io.github.hyeonsu06.coolstats.loops.TickLoop;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class CoolStats extends JavaPlugin {

    public final Plugin plugin = this;

    @Override
    public void onEnable() {
        new TickLoop().runTaskTimer(plugin, 0, 1);
    }

    @Override
    public void onDisable() {
    }
}
