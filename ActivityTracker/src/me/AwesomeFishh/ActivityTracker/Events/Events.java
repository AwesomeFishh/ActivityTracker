package me.AwesomeFishh.ActivityTracker.Events;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.AwesomeFishh.ActivityTracker.Main;

public class Events implements Listener {

	private Main plugin = Main.getPlugin(Main.class);

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		if (!p.hasPlayedBefore()) {
			plugin.getConfig().set("players." + uuid.toString(), 0);
		}

		plugin.playersMap.put(uuid, System.currentTimeMillis());
	}

	@EventHandler
	public void onPlayerQuitEvent(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		long timePlayed = plugin.getConfig().getLong("players." + uuid.toString());
		long newTimePlayed = timePlayed + (System.currentTimeMillis() - plugin.playersMap.get(uuid));
		plugin.getConfig().set("players." + uuid.toString(), newTimePlayed);
	}
}
