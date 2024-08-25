package io.github.hyeonsu06.coolstats.items;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static io.github.hyeonsu06.coolstats.Utilities.Methods.isItemValid;

public class CustomItemData {

    private static final String NBT_KEY_PREFIX = "CustomItemData:";

    // Set data directly into the NBT of the item
    public void setData(ItemStack item, String key, String value) {
        if (isItemValid(item)) return;
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound customDataCompound = nbtItem.getCompound(NBT_KEY_PREFIX);

        if (customDataCompound == null) customDataCompound = nbtItem.addCompound(NBT_KEY_PREFIX);

        customDataCompound.setString(key, value);
        nbtItem.applyNBT(item); // Apply changes back to the ItemStack
    }

    // Get data directly from the NBT of the item
    public String getData(ItemStack item, String key) {
        if (isItemValid(item)) return null;
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound customDataCompound = nbtItem.getCompound(NBT_KEY_PREFIX);

        if (customDataCompound != null) return customDataCompound.getString(key);

        return null;
    }

    // Check if a specific key exists in the NBT data of the item
    public boolean hasData(ItemStack item, String key) {
        if (isItemValid(item)) return false;
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound customDataCompound = nbtItem.getCompound(NBT_KEY_PREFIX);

        return customDataCompound != null && customDataCompound.hasKey(key);
    }

    // Remove data directly from the NBT of the item
    public void removeData(ItemStack item, String key) {
        if (isItemValid(item)) return;
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound customDataCompound = nbtItem.getCompound(NBT_KEY_PREFIX);

        if (customDataCompound != null) {
            customDataCompound.removeKey(key);
            nbtItem.applyNBT(item); // Apply changes back to the ItemStack
        }
    }

    // Convert all NBT data into a readable map format (for debugging or export)
    public Map<String, Object> getAllData(ItemStack item) {
        Map<String, Object> dataMap = new HashMap<>();
        if (isItemValid(item)) return dataMap;
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound customDataCompound = nbtItem.getCompound(NBT_KEY_PREFIX);

        if (customDataCompound != null) {
            for (String key : customDataCompound.getKeys()) dataMap.put(key, customDataCompound.getString(key));
        }

        return dataMap;
    }
}
