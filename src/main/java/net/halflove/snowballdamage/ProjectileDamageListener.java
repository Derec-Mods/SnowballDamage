package net.halflove.snowballdamage;

import org.bukkit.entity.Egg;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ProjectileDamageListener implements Listener {

    private final double snowballDamage;
    private final double eggDamage;

    public ProjectileDamageListener(double snowballDamage, double eggDamage) {
        this.snowballDamage = snowballDamage;
        this.eggDamage = eggDamage;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball) {
            e.setDamage(snowballDamage);
        }
        if (e.getDamager() instanceof Egg) {
            e.setDamage(eggDamage);
        }
    }
}

