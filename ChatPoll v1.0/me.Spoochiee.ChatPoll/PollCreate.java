package me.Spoochiee.ChatPoll;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Spoochiee.ChatPoll.GUI.PollResultsGUI;
import me.Spoochiee.ChatPoll.GUI.PollVoteGui;
import me.Spoochiee.ChatPoll.Utils.Utils;

public class PollCreate implements CommandExecutor {
	
	private Main plugin; 
	private PollVoteGui pollvotegui;
	private PollResultsGUI pollresultsgui;
	private Utils utils;
	
	public PollCreate(Main plugin, PollVoteGui pollvotegui, Utils utils, PollResultsGUI pollresultsgui) {
		this.plugin = plugin;
		this.pollvotegui = pollvotegui;
		this.utils = utils;
		this.pollresultsgui = pollresultsgui;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("pollcreate") || label.equalsIgnoreCase("pc")) {
			if(this.plugin.activePoll == false) {
				if(!(sender instanceof Player)) {
					sender.sendMessage(utils.chat("&5[&3ChatPoll&5]&rPerform this command in Game!"));
					return true;
				}
				Player player = (Player) sender;
				
				if(player.hasPermission("ChatPoll.create") || player.hasPermission("ChatPoll.*")) {
					if(args.length == 0) {
						player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rCorrect Usage: /pollcreate option1 option2"));
						return true;
					}
					if(args.length == 1) {
						player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rCorrect Usage: /pollcreate option1 option2"));
						return true;
					}
					if(args.length == 2) {
						//Tell the server a poll has been created
						this.plugin.getServer().broadcastMessage(utils.chat("&5[&3ChatPoll&5]&r") + player.getDisplayName() + " has created a poll!");
						
						// Tell the code there is a poll active
						this.plugin.activePoll = true;
						
						//Assign the option strings
						this.pollvotegui.option1 = args[0];
						this.pollvotegui.option2 = args[1];
						
						//Say what the options are
						this.plugin.getServer().broadcastMessage(utils.chat("&5[&3ChatPoll&5]&r&bOption1: " + args[0]));
						this.plugin.getServer().broadcastMessage(utils.chat("&5[&3ChatPoll&5]&r&6Option2: " + args[1]));
						
						//Create the GUIs
						this.pollvotegui.createInv();
						this.pollresultsgui.createInv();
						return true;
					}
					if(args.length > 2) {
						player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rCorrect Usage: /pollcreate option1 option2"));
						return true;
					}
				}
				else {
					player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rYou do not have permission to perform that command!"));
					return true;
					}
				}
			else {
				sender.sendMessage(utils.chat("&5[&3ChatPoll&5]&rThere is already a poll active, finish that poll before creating a new one!"));
				return true;
				}
			}
		return false;
	}
}
