package me.AwesomeFishh.ActivityTracker.Commands;

import java.util.ArrayList;
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

					ArrayList<Long> times = new ArrayList<Long>();
					ArrayList<UUID> players = new ArrayList<UUID>();
					for (String suuid : plugin.getConfig().getConfigurationSection("players").getKeys(false)) {
						UUID uuid = UUID.fromString(suuid);
						Long timePlayed = plugin.getConfig().getLong("players." + uuid);
						players.add(uuid);
						times.add(timePlayed);
					}

					ArrayList<Long> clone = new ArrayList<Long>(times);
					Collections.sort(clone, Collections.reverseOrder());
					HashMap<UUID, Long> map = new HashMap<UUID, Long>();

					int c1 = 0;
					int c2 = 0;
					for (Long lo : clone) {
						c2 = 0;
						for (Long l : times) {
							if (l == lo) {
								// clone.get(c1) == times.get(c2) == players.get(c2);
								if (c2 <= 20) {
									player.sendMessage(plugin.prefix + " " + (c2 + (int) 1) + ". " + Bukkit.getOfflinePlayer(players.get(c1)).getName() + ": "
											+ plugin.getDurationBreakdown(times.get(c1)));
								}
							}
							c2++;
						}
						c1++;
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