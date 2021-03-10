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

    public String TranslateReplace(String text, String name) {
        text = text.replace("%player%", name);
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        String name = e.getPlayer().getName();
        e.setJoinMessage(TranslateReplace(getConfig().getString("join-message"), name));
        if (getConfig().getBoolean("join-title.enabled")) {
            String title = TranslateReplace(getConfig().getString("join-title.title"), name);
            String subtitle = TranslateReplace(getConfig().getString("join-title.subtitle"), name);
            int fadeIn = getConfig().getInt("join-title.fadeIn");
            int stay = getConfig().getInt("join-title.stay");
            int fadeOut = getConfig().getInt("join-title.fadeOut");
            e.getPlayer().sendTitle(title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent e) {
        String name = e.getPlayer().getName();
        e.setQuitMessage(TranslateReplace(getConfig().getString("quit-message"), name));
    }
}
