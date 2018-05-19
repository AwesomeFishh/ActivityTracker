package me.AwesomeFishh.ActivityTracker.Commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.AwesomeFishh.ActivityTracker.Main;

public class ZilantTop implements CommandExecutor {

	private Main plugin = Main.getPlugin(Main.class);

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

		if (cmd.getName().equalsIgnoreCase("zilanttop")) {

			// Get top20 long values (time played) and sort from high to low
			if (sender instanceof Player) {
				Player player = (Player) sender;
				plugin.updateTimePlayed();
				if (args.length == 0) {
					int counter = 0;
					HashMap<Player, Long> players = new HashMap<Player, Long>();
					for (String key : plugin.getConfig().getConfigurationSection("players").getKeys(false)) {
						counter++;
						UUID pUuid = UUID.fromString(key);
						Player p = Bukkit.getPlayer(pUuid);
						players.put(p, plugin.getConfig().getLong(key));
					}

					HashMap<Player, Long> clone = new HashMap<Player, Long>(players);
					HashMap<Player, Long> sorted = new HashMap<Player, Long>();
					Long highest = (long) 0;
					String highestP = "";
					if (counter > 20) {
						counter = 20;
					}
					for (int i = 0; i < counter; i++) {
						for (Player key1 : clone.keySet()) {
							Long value = players.get(key1);
							if (value > highest) {
								highest = value;
								highestP = key1.getName();
							}
						}
						clone.remove(Bukkit.getPlayer(highestP));
						sorted.put(Bukkit.getPlayer(highestP), highest);
					}
					int counter2 = 0;
					for (Player key2 : sorted.keySet()) {
						player.sendMessage(plugin.prefix + " " + counter + ". " + key2.getName() + ": "
								+ plugin.getDurationBreakdown(sorted.get(key2)));
					}
				} else {
					player.sendMessage(plugin.prefix + " Too many arguments! /zilanttop");
				}
			} else {
				sender.sendMessage(plugin.prefix + " Only players can use this command!");
			}
		}

		return false;
	}

}
