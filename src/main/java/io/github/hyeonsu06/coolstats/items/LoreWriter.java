package io.github.hyeonsu06.coolstats.items;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static io.github.hyeonsu06.coolstats.Initialization.getItemJSON;
import static io.github.hyeonsu06.coolstats.Utilities.Methods.*;

public class LoreWriter {
    public void write(ItemStack item) {
        if (isItemValid(item)) {
            String id = new NBTItem(item).getString("id");
            JSONObject itemData = (JSONObject) getItemJSON().get(id);

            String name = (String) itemData.get("name");
            Material material = Material.getMaterial((String) itemData.get("material"));
            long rarity = (long) itemData.get("rarity");
            long type = (long) itemData.get("type");

            double damage = (double) itemData.get("damage");
            double strength = (double) itemData.get("strength");
            double defense = (double) itemData.get("defense");
            double intelligence = (double) itemData.get("max_mana");

            double health = (double) itemData.get("health");
            double speed = (double) itemData.get("speed");

            double ferocity = (double) itemData.get("ferocity");
            double regen = (double) itemData.get("regen");

            ItemMeta meta = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            if (damage > 0) {
                lore.add(ChatColor.WHITE + "Damage: " + ChatColor.GREEN + "+" + damage);
            } else if (damage < 0) {
                lore.add(ChatColor.WHITE + "Damage: " + ChatColor.RED + damage);
            }
            if (strength > 0) {
                lore.add(ChatColor.WHITE + "Strength: " + ChatColor.GREEN + "+" + strength);
            } else if (strength < 0) {
                lore.add(ChatColor.WHITE + "Strength: " + ChatColor.RED + strength);
            }
            if (defense > 0) {
                lore.add(ChatColor.WHITE + "Defense: " + ChatColor.GREEN + "+" + defense);
            } else if (defense < 0) {
                lore.add(ChatColor.WHITE + "Defense: " + ChatColor.RED + defense);
            }
            if (intelligence > 0) {
                lore.add(ChatColor.WHITE + "Intelligence: " + ChatColor.GREEN + "+" + intelligence);
            } else if (intelligence < 0) {
                lore.add(ChatColor.WHITE + "Intelligence: " + ChatColor.RED + intelligence);
            }
            if (health > 0) {
                lore.add(ChatColor.WHITE + "Health: " + ChatColor.GREEN + "+" + health);
            } else if (health < 0) {
                lore.add(ChatColor.WHITE + "Health: " + ChatColor.RED + health);
            }
            if (speed > 0) {
                lore.add(ChatColor.WHITE + "Speed: " + ChatColor.GREEN + "+" + speed);
            } else if (speed < 0) {
                lore.add(ChatColor.WHITE + "Speed: " + ChatColor.RED + speed);
            }
            if (ferocity > 0) {
                lore.add(ChatColor.WHITE + "Ferocity: " + ChatColor.GREEN + "+" + ferocity);
            } else if (ferocity < 0) {
                lore.add(ChatColor.WHITE + "Ferocity: " + ChatColor.RED + ferocity);
            }
            if (regen > 0) {
                lore.add(ChatColor.WHITE + "Health regen: " + ChatColor.GREEN + "+" + regen);
            } else if (regen < 0) {
                lore.add(ChatColor.WHITE + "Health Regen: " + ChatColor.RED + regen);
            }

            lore.add(getText(rarity) + getTypeText(type));

            meta.setDisplayName(getColor(rarity) + name);
            meta.setLore(lore);

            item.setType(material);
            item.setItemMeta(meta);
        }
    }
}
