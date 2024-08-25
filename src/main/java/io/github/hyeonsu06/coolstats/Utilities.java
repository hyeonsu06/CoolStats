package io.github.hyeonsu06.coolstats;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;

import static java.io.File.separator;

public class Utilities {
    public static class Variables {
        final public static String pluginName = "CoolStats";
        final public static String prefix = "[" + Bukkit.getPluginManager().getPlugin(pluginName).getDescription().getPrefix() + "] ";
        public static final String path = Bukkit.getPluginManager().getPlugin(pluginName).getDataFolder().getAbsolutePath() + separator;
        public static File dataFolder = new File(path, "entity_data");
    }

    public static class Methods {
        public static List<Entity> getAllEntities() {
            List<Entity> value = new ArrayList<>();
            for (World w : Bukkit.getWorlds()) value.addAll(w.getEntities());
            return value;
        }

        public static List<ItemStack> getAllItemsOfPlayer(Player p) {
            ItemStack[] i1 = p.getInventory().getContents();
            ItemStack[] i2 = p.getInventory().getArmorContents();
            List<ItemStack[]> i3 = List.of(i1, i2);
            List<ItemStack> value = new ArrayList<>();
            for (ItemStack[] temp : i3) Collections.addAll(value, temp);
            return value;
        }

        public static List<ItemStack> getAllItemsOfLivingEntity(LivingEntity e) {
            ItemStack[] i1 = e.getEquipment().getArmorContents();
            ItemStack i2 = e.getEquipment().getItemInMainHand();
            ItemStack i3 = e.getEquipment().getItemInMainHand();

            List<ItemStack> items = new ArrayList<>(List.of(i1));
            items.add(i2);
            items.add(i3);

            List<ItemStack> value = new ArrayList<>();
            for (ItemStack temp : items) Collections.addAll(value, temp);
            return value;
        }

        public static boolean isItemValid(ItemStack i) {
            return Objects.nonNull(i) && i.getType() != Material.AIR && i.getAmount() != 0 && i.hasItemMeta();
        }

        public static String getText(long rarity) {
            return switch ((int) rarity) {
                case 1 -> ChatColor.WHITE + "COMMON";
                case 2 -> ChatColor.GREEN + "UNCOMMON";
                case 3 -> ChatColor.BLUE + "RARE";
                case 4 -> ChatColor.DARK_PURPLE + "EPIC";
                case 5 -> ChatColor.GOLD + "LEGENDARY";
                case 6 -> ChatColor.LIGHT_PURPLE + "MYTHIC";
                case 7 -> ChatColor.AQUA + "DIVINE";
                case 8 -> ChatColor.RED + "UNIQUE";
                case 9 -> ChatColor.DARK_RED + "TREMENDOUS";
                case 10 -> ChatColor.GRAY + "FORBIDDEN";
                case 11 -> ChatColor.DARK_BLUE + "ADMIN";
                default -> "";
            };
        }
        public static ChatColor getColor(long rarity) {
            return switch ((int) rarity) {
                case 1 -> ChatColor.WHITE;
                case 2 -> ChatColor.GREEN;
                case 3 -> ChatColor.BLUE;
                case 4 -> ChatColor.DARK_PURPLE;
                case 5 -> ChatColor.GOLD;
                case 6 -> ChatColor.LIGHT_PURPLE;
                case 7 -> ChatColor.AQUA;
                case 8 -> ChatColor.RED;
                case 9 -> ChatColor.DARK_RED;
                case 10 -> ChatColor.GRAY;
                case 11 -> ChatColor.DARK_BLUE;
                default -> null;
            };
        }

        public static String getTypeText(long type) {
            return switch ((int) type) {
                case 1 -> "SWORD";
                case 2 -> "BOW";

                case 3 -> "PICKAXE";
                case 4 -> "AXE";
                case 5 -> "SHOVEL";
                case 6 -> "HOE";

                case 7 -> "HELMET";
                case 8 -> "CHESTPLATE";
                case 9 -> "LEGGINGS";
                case 10 -> "BOOTS";

                default -> "";
            };
        }
    }
}