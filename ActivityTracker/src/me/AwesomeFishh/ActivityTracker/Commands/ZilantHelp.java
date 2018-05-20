package me.AwesomeFishh.ActivityTracker.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.AwesomeFishh.ActivityTracker.Main;

public class ZilantHelp implements CommandExecutor{
	
	private Main plugin = Main.getPlugin(Main.class);

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("zilanthelp")) {
			if(args.length == 0) {
				sender.sendMessage(plugin.prefix + " Command list");
				sender.sendMessage(plugin.prefix + " /zilantactive <player>: Get the playtime of a player");
				sender.sendMessage(plugin.prefix + " /zilanttop: Get the top 20 most active players");
			}
		}
		
		return false;
	}
	
	

}
