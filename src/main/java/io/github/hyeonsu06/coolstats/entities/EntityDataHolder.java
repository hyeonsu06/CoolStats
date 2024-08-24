package io.github.hyeonsu06.coolstats.entities;

import org.bukkit.entity.Entity;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.github.hyeonsu06.coolstats.Variables.dataFolder;

public class EntityDataHolder {

    private final Map<UUID, CustomEntityData> entityDataMap;

    public EntityDataHolder() {
        this.entityDataMap = new HashMap<>();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    // Add or update data for an entity
    public void addOrUpdateEntityData(Entity entity, CustomEntityData data) {
        entityDataMap.put(entity.getUniqueId(), data);
        saveEntityDataToFile(entity, data);
    }

    // Get data for an entity
    public CustomEntityData getEntityData(Entity entity) {
        return entityDataMap.get(entity.getUniqueId());
    }

    // Remove data for an entity
    public void removeEntityData(Entity entity) {
        entityDataMap.remove(entity.getUniqueId());
        deleteEntityDataFile(entity);
    }

    // Modify data for an entity
    public void modifyEntityData(Entity entity, String key, Object newValue) {
        CustomEntityData existingData = entityDataMap.get(entity.getUniqueId());
        if (existingData != null) {
            existingData.addData(key, newValue);
            saveEntityDataToFile(entity, existingData);
        }
    }

    // Save data to a file
    private void saveEntityDataToFile(Entity entity, CustomEntityData data) {
        File file = new File(dataFolder, entity.getUniqueId().toString());
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.print(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Delete the file associated with an entity
    private void deleteEntityDataFile(Entity entity) {
        File file = new File(dataFolder, entity.getUniqueId().toString());
        if (file.exists()) {
            file.delete();
        }
    }

    // Load data from a file
    public void loadEntityDataFromFile(Entity entity) {
        File file = new File(dataFolder, entity.getUniqueId().toString());
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder dataBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    dataBuilder.append(line).append("\n");
                }
                CustomEntityData data = CustomEntityData.fromString(dataBuilder.toString());
                entityDataMap.put(entity.getUniqueId(), data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
