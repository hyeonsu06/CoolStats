package io.github.hyeonsu06.coolstats.entities;

import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.json.simple.JSONObject;

public class EntityEventListener implements Listener {

    private final EntityDataHolder dataHolder;

    public EntityEventListener(EntityDataHolder dataHolder) {
        this.dataHolder = dataHolder;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();

        CustomEntityData customData = dataHolder.getEntityData(entity);
        if (customData != null) {
            JSONObject
        }
    }
}

