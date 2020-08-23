package me.Spoochiee.ChatPoll.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.Spoochiee.ChatPoll.Main;
import me.Spoochiee.ChatPoll.GUI.PollVoteGui;
import me.Spoochiee.ChatPoll.Utils.Utils;

public class PlayerJoin implements Listener {
	
	private Main plugin;
	private Utils utils;
	private PollVoteGui pollvotegui;
	
	public PlayerJoin(Main plugin, Utils utils, PollVoteGui pollvotegui) {
		this.plugin = plugin;
		this.utils = utils;
		this.pollvotegui = pollvotegui;
	}
	
	@EventHandler
	public void playerJoined(PlayerJoinEvent event) {
		
		Player player = (Player) event.getPlayer();
	
		if(this.plugin.data.getConfig().getBoolean("playerjoin.message.enabled") == true) {
			if(this.plugin.activePoll == true) {			
				player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rThere is an active poll. The two options are:"));
				player.sendMessage(utils.chat("&5[&3ChatPoll&5]&r&bOption1: " + this.pollvotegui.option1));
				player.sendMessage(utils.chat("&5[&3ChatPoll&5]&r&6Option2: " + this.pollvotegui.option2));
				player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rTo vote on this Poll please do, /pollvote"));
			}
			else {
				player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rThere is no active poll at the moment!"));
			}
		}
	}
}
