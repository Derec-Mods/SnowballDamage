package net.halflove.snowballdamage;

import org.bukkit.plugin.java.JavaPlugin;

public class SnowballDamage extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ProjectileDamageListener(), this);
        getLogger().info("Snowballs and eggs knock back with 0 damage.");
    }
}
