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

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("zilanttop")) {
			
			//Get top20 long values (time played) and sort from high to low
			if(sender instanceof Player) {
				Player player = (Player) sender;
				plugin.updateTimePlayed();
				if(args.length == 0) {
					int counter = 0;
					HashMap<Player, Long> players = new HashMap<Player, Long>();
					ArrayList<Long> values = new ArrayList<>();
					for(String key : plugin.getConfig().getConfigurationSection("players").getKeys(false)) {
						counter++;
						UUID pUuid = UUID.fromString(key);
						Player p = Bukkit.getPlayer(pUuid);
						players.put(p, plugin.getConfig().getLong(key));
						values.add(plugin.getConfig().getLong(key));
						
					}
					Collections.sort(values, Collections.reverseOrder());
					for(int i=0; i<counter; i++) {
						if(counter <= 20) {
							Long v = values.get(i);
							Player player2 = plugin.getKeyFromValue(players, v);
							player.sendMessage(plugin.prefix + " 1. " + player2.getName() + ": " + v);
						}
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
