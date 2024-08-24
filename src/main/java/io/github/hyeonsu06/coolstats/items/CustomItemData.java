package io.github.hyeonsu06.coolstats.items;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class CustomItemData {

    private static final String NBT_KEY_PREFIX = "CustomItemData:";

    private final NBTCompound nbtCompound;

    private CustomItemData(NBTCompound nbtCompound) {
        this.nbtCompound = nbtCompound;
    }

    // Factory method to create or get CustomItemData from an ItemStack
    public static CustomItemData fromItem(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound customDataCompound = nbtItem.getCompound(NBT_KEY_PREFIX);

        if (customDataCompound == null) {
            customDataCompound = nbtItem.addCompound(NBT_KEY_PREFIX);
            nbtItem.applyNBT(item); // Save changes to the item
        }

        return new CustomItemData(customDataCompound);
    }

    // Set data directly into the NBT
    public void setData(String key, String value) {
        nbtCompound.setString(key, value);
    }

    // Get data directly from the NBT
    public String getData(String key) {
        return nbtCompound.getString(key);
    }

    // Check if a specific key exists in the NBT data
    public boolean hasData(String key) {
        return nbtCompound.hasKey(key);
    }

    // Remove data directly from the NBT
    public void removeData(String key) {
        nbtCompound.removeKey(key);
    }

    // Convert all NBT data into a readable map format (for debugging or export)
    public Map<String, String> getAllData() {
        Map<String, String> dataMap = new HashMap<>();
        for (String key : nbtCompound.getKeys()) {
            dataMap.put(key, nbtCompound.getString(key));
        }
        return dataMap;
    }

    // Apply changes back to the ItemStack after modifications
    public void applyChanges(ItemStack item) {
        NBTItem nbtItem = new NBTItem(item);
        nbtItem.mergeCompound(nbtCompound);
        nbtItem.applyNBT(item);
    }
}
