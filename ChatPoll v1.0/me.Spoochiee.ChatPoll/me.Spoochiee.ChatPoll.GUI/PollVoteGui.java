package me.Spoochiee.ChatPoll.GUI;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Spoochiee.ChatPoll.Main;
import me.Spoochiee.ChatPoll.Utils.Utils;

public class PollVoteGui implements Listener {
	
	private Main plugin;
	private PollResultsGUI pollresultsgui;
	private Utils utils;
	
	public String option1;
	public String option2;
	
	public PollVoteGui(Main plugin, PollResultsGUI pollresultsgui, Utils utils) {
		this.plugin = plugin;
		this.pollresultsgui = pollresultsgui;
		this.utils = utils;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		try {
			if(event.getInventory().equals(this.plugin.VoteGUI)) {
				if(!event.getClickedInventory().equals(this.plugin.VoteGUI))
					event.setCancelled(true);
				if(!event.getView().getTitle().contains(ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Vote on the Poll"))
					event.setCancelled(true);
			    if(event.getCurrentItem() == null)
			    	event.setCancelled(true);
			    if(event.getCurrentItem().getItemMeta() == null)
			    	event.setCancelled(true);
			    if(event.getRawSlot() > 9)
			    	event.setCancelled(true);
			    
			    Player player = (Player) event.getWhoClicked();
				
				if(event.getRawSlot() == 3) {
					//Option 1
					this.pollresultsgui.setPollOption1(this.pollresultsgui.getPollOption1() + 1);
					
					//Check to see if the player already exists in the voted HashMap
					if(!this.plugin.playerVoted.containsKey(player.getName())) {
						//If the have'nt voted, add them to the HashMap
						//Note they should not be in the HashMap as they wouldnt be able to open the GUI if they were
						this.plugin.playerVoted.put(player.getName(), true);	
					}

					player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rYou have voted for option 1!"));
					
					//Close the Vote Inv
					player.closeInventory();
					
					//Recreate the results inventory
					this.pollresultsgui.createInv();
				}
				
				if(event.getRawSlot() == 4) {
					player.closeInventory();
				}
				
				if(event.getRawSlot() == 5) {
					//Option 2
					this.pollresultsgui.setPollOption2(this.pollresultsgui.getPollOption2() + 1);
					
					if(!this.plugin.playerVoted.containsKey(player.getName())) {
						this.plugin.playerVoted.put(player.getName(), true);	
					}
					
					player.sendMessage(utils.chat("&5[&3ChatPoll&5]&rYou have voted for Option 2"));
					
					//Close the Vote Inv
					player.closeInventory();
					
					//Recreate the results inventory
					this.pollresultsgui.createInv();
				}
			}
		}	
		catch(Exception e) {
			
		}	
	}
	
	public void createInv() {
		this.plugin.VoteGUI = Bukkit.createInventory(null, 9, ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Vote on the Poll");
		
		ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta meta = item.getItemMeta();
		
		//Vote for Option 1
		meta.setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "Option 1");
		List<String> lore = new ArrayList<String>();
		lore.add(this.option1);
		meta.setLore(lore);
		item.setItemMeta(meta);
		this.plugin.VoteGUI.setItem(3, item);
		
		
		//Vote for Option 2 
		meta.setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "Option 2");
		lore.clear();
		lore.add(this.option2);
		meta.setLore(lore);
		item.setItemMeta(meta);
		this.plugin.VoteGUI.setItem(5, item);
		
		//Exit button
		item.setType(Material.BARRIER);
		meta.setDisplayName("Exit GUI");
		lore.clear();
		lore.add("Click here to exit GUI!");
		meta.setLore(lore);
		item.setItemMeta(meta);
		this.plugin.VoteGUI.setItem(4, item);
	}
	
}
