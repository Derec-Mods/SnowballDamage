package io.github.derexxd.snowballdamage;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnowballDamage implements ModInitializer {
    public static final String MOD_ID = "snowballdamage";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        SnowballDamageConfig.load();
        LOGGER.info("Snowballs and eggs knock back with 0 damage.");
    }

    public static boolean allowsZeroDamage(DamageSource source) {
        return enabled(source.getSource());
    }

    public static boolean enabled(Entity projectile) {
        if (projectile instanceof SnowballEntity) {
            return SnowballDamageConfig.INSTANCE.snowballs;
        }
        if (projectile instanceof EggEntity) {
            return SnowballDamageConfig.INSTANCE.eggs;
        }
        return false;
    }

    public static float resolveDamage(Entity projectile, float vanilla) {
        if (!enabled(projectile)) {
            return vanilla;
        }
        return SnowballDamageConfig.INSTANCE.damage;
    }
}
