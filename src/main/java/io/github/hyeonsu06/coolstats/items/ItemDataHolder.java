package io.github.hyeonsu06.coolstats.items;

import org.bukkit.inventory.ItemStack;

public class ItemDataHolder {

    // Add or update custom data directly through CustomItemData
    public void addOrUpdateItemData(ItemStack item, String key, String value) {
        CustomItemData customItemData = CustomItemData.fromItem(item);
        customItemData.setData(key, value);
        customItemData.applyChanges(item); // Ensure changes are saved to the item
    }

    // Retrieve custom data from an item
    public String getItemData(ItemStack item, String key) {
        CustomItemData customItemData = CustomItemData.fromItem(item);
        return customItemData.getData(key);
    }

    // Remove custom data from an item
    public void removeItemData(ItemStack item, String key) {
        CustomItemData customItemData = CustomItemData.fromItem(item);
        customItemData.removeData(key);
        customItemData.applyChanges(item); // Ensure changes are saved to the item
    }

    // Check if the item has specific custom data
    public boolean hasItemData(ItemStack item, String key) {
        CustomItemData customItemData = CustomItemData.fromItem(item);
        return customItemData.hasData(key);
    }
}
