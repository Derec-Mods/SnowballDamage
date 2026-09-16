package net.halflove.snowballdamage;

import org.bukkit.entity.Egg;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileDamageListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (!(projectile instanceof Snowball) && !(projectile instanceof Egg)) {
            return;
        }
        if (!(event.getHitEntity() instanceof LivingEntity)) {
            return;
        }

        ((LivingEntity) event.getHitEntity()).damage(0.0, projectile);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Snowball || event.getDamager() instanceof Egg) {
            event.setDamage(0.0);
        }
    }
}
