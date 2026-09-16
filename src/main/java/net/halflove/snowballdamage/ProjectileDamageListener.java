package net.halflove.snowballdamage;

import org.bukkit.EntityEffect;
import org.bukkit.entity.Egg;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

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

        LivingEntity victim = (LivingEntity) event.getHitEntity();
        Vector direction = projectile.getVelocity().clone();
        direction.setY(0);
        if (direction.lengthSquared() < 1.0E-6) {
            return;
        }
        direction.normalize().multiply(0.4);

        Vector current = victim.getVelocity();
        victim.setVelocity(new Vector(
                current.getX() / 2.0 + direction.getX(),
                victim.isOnGround() ? 0.4 : current.getY(),
                current.getZ() / 2.0 + direction.getZ()
        ));
        victim.playEffect(EntityEffect.HURT);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Snowball || event.getDamager() instanceof Egg) {
            event.setDamage(0.0);
            event.setCancelled(true);
        }
    }
}
