package io.github.hyeonsu06.coolstats.items;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryEventListener implements Listener {

    private final ItemDataHolder itemDataHolder;

    public InventoryEventListener(ItemDataHolder itemDataHolder) {
        this.itemDataHolder = itemDataHolder;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack currentItem = event.getCurrentItem();
        if (currentItem != null) {
            String key = "uses"; // Example key
            if (!itemDataHolder.hasItemData(currentItem, key)) {
                // Initialize custom data if it doesn't exist
                itemDataHolder.addOrUpdateItemData(currentItem, key, "0");
            } else {
                // Example: Increment "uses" count each time the item is clicked
                String usesStr = itemDataHolder.getItemData(currentItem, key);
                int uses = Integer.parseInt(usesStr);
                uses++;
                itemDataHolder.addOrUpdateItemData(currentItem, key, String.valueOf(uses));
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Load custom data for all items in the player's inventory
        for (ItemStack item : event.getPlayer().getInventory().getContents()) {
            if (item != null) {
                // This will just initialize the custom data if it exists; no need to do anything else
                itemDataHolder.getItemData(item, "some_key"); // Example to ensure data loading
            }
        }
    }
}
