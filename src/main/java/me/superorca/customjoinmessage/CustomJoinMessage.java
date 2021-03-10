package me.superorca.customjoinmessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomJoinMessage extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("join-message")));
        if (getConfig().getBoolean("join-title.enabled")) {
            String title = ChatColor.translateAlternateColorCodes('&', getConfig().getString("join-title.title"));
            String subtitle = ChatColor.translateAlternateColorCodes('&', getConfig().getString("join-title.subtitle"));
            int fadeIn = getConfig().getInt("join-title.fadeIn");
            int stay = getConfig().getInt("join-title.stay");
            int fadeOut = getConfig().getInt("join-title.fadeOut");
            e.getPlayer().sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("quit-message")));
    }
}
