package io.github.hyeonsu06.coolstats;

import io.github.hyeonsu06.coolstats.entities.EntityDataHolder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Objects;

import static io.github.hyeonsu06.coolstats.Utilities.Variables.path;

public class Initialization {

    private static EntityDataHolder EDH;
    private static JSONObject entityJSON;
    private static JSONObject itemJSON;

    public static EntityDataHolder getEntityData() {
        if (Objects.isNull(EDH)) {
            EDH = new EntityDataHolder();
            for (World w : Bukkit.getServer().getWorlds()) {
                for (Entity e : w.getEntities()) {
                    EDH.loadEntityDataFromFile(e);
                }
            }
        }
        return EDH;
    }

    public static JSONObject getEntityJSON(){
        if (Objects.isNull(entityJSON)) {
            String file = path + "entities.json";
            try {
                entityJSON = (JSONObject) new JSONParser().parse(file);
            } catch (ParseException e) {
                throw new RuntimeException("Failed to parse JSON file: " + file, e);
            }
        }
        return entityJSON;
    }
    public static JSONObject getItemJSON(){
        if (Objects.isNull(itemJSON)) {
            String file = path + "items.json";
            try {
                itemJSON = (JSONObject) new JSONParser().parse(file);
            } catch (ParseException e) {
                throw new RuntimeException("Failed to parse JSON file: " + file, e);
            }
        }
        return entityJSON;
    }
}
