package me.Spoochiee.ChatPoll.FileManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Spoochiee.ChatPoll.Utils.Utils;

public class ReloadCommand implements CommandExecutor {
	
	private DataManager data;
	private Utils utils;
	
	public ReloadCommand(DataManager data, Utils utils) {
		this.data = data;
		this.utils = utils;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("chatpoll")) {
			if(!(sender instanceof Player)) {
				if(args.length == 0) {
					sender.sendMessage(utils.chat("&5[&3ChatPoll&5]&rCorrect usage: chatpoll reload!"));
					return true;
				}
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("reload")) {
						sender.sendMessage(utils.chat("&5[&3ChatPoll&5]&rReloaded Config"));
						this.data.reloadConfig();
						return true;
					}
					else {
						sender.sendMessage(utils.chat("&5[&3ChatPoll&5]&rCorrect usage: chatpoll reload!"));
						return true;
					}
				}
				else {
					sender.sendMessage(utils.chat("&5[&3ChatPoll&5]&rCorrect usage: chatpoll reload!"));
					return true;
				}
			}
			
			Player player = (Player) sender;
			if(player.hasPermission("ChatPoll.reload") || player.hasPermission("ChatPoll.*")) {
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("reload")) {
						player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rReloaded Config"));
						this.data.reloadConfig();
						return true;
					}
					else {
						player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rCorrect Usage: /chatpoll reload"));
						return true;
					}
				}
				else {
					player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rCorrect Usage: /chatpoll reload"));
					return true;
				}
			}
			else {
				player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rYou do not have permission to perform this command!"));
				return true;
			}
		}
		return false;
	}
}
