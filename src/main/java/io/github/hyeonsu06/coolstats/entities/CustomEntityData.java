package io.github.hyeonsu06.coolstats.entities;

import java.util.HashMap;
import java.util.Map;

public class CustomEntityData {

    private final Map<String, Object> dataMap;

    public CustomEntityData() {
        this.dataMap = new HashMap<>();
    }

    public void addData(String key, Object value) {
        dataMap.put(key, value);
    }

    public Object getData(String key) {
        return dataMap.get(key);
    }

    public void removeData(String key) {
        dataMap.remove(key);
    }

    public Map<String, Object> getAllData() {
        return dataMap;
    }

    @Override
    public String toString() {
        // Convert the map to a string representation for saving
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public static CustomEntityData fromString(String data) {
        CustomEntityData customEntityData = new CustomEntityData();
        String[] lines = data.split("\n");
        for (String line : lines) {
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                customEntityData.addData(parts[0], parts[1]);
            }
        }
        return customEntityData;
    }
}
