package net.halflove.snowballdamage;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SnowballDamage extends JavaPlugin {

    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "SBD: Snowball Damage Version: " + getDescription().getVersion() + ChatColor.GREEN + " By Halflove Enabled!");
        if (!(new File(getDataFolder(), "config.yml")).exists()) {
            saveDefaultConfig();
        }

        double snowballDamage = getConfig().getDouble("Snowballs.Damage");
        double eggDamage = getConfig().getDouble("Eggs.Damage");

        getServer().getPluginManager().registerEvents(new ProjectileDamageListener(snowballDamage, eggDamage), this);
    }
}
