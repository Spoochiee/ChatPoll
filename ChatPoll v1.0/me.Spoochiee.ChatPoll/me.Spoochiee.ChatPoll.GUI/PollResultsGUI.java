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

public class PollResultsGUI implements Listener {	
	
	private Main plugin;
	
	public PollResultsGUI(Main plugin) {
		this.plugin = plugin;
	}
	
	private String voteOption1, voteOption2;
	
	public int polloption1 = 0;
	public int polloption2 = 0;
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		try {
			if(event.getInventory().equals(this.plugin.ResultsGui)) {
				if(!event.getClickedInventory().equals(this.plugin.ResultsGui))
					event.setCancelled(true);
				if(!event.getView().getTitle().contains(ChatColor.GOLD + "" + ChatColor.UNDERLINE + "Poll Results"))
					event.setCancelled(true);
			    if(event.getCurrentItem() == null)
			    	event.setCancelled(true);
			    if(event.getCurrentItem().getItemMeta() == null)
			    	event.setCancelled(true);
			    if(event.getRawSlot() > 9)
			    	event.setCancelled(true);
			    
			    Player player = (Player) event.getWhoClicked();
				
			    if(event.getRawSlot() == 3) {
			    	event.setCancelled(true);
			    }
			    
				if(event.getRawSlot() == 4) {
					player.closeInventory();
				}
				
				if(event.getRawSlot() == 5) {
					event.setCancelled(true);
				}
			}
		}
		catch(Exception e) {
			
		}
	}
	
	public void createInv() {
		this.plugin.ResultsGui = Bukkit.createInventory(null, 9,  ChatColor.GOLD + "" + ChatColor.UNDERLINE + "Poll Results");

		voteOption1 = String.valueOf(this.getPollOption1());
		voteOption2 = String.valueOf(this.getPollOption2());
		
		ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
		ItemMeta meta2 = item.getItemMeta();
		
		//Results for Option1
		meta2.setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "Option1 Results");
		List<String> lore = new ArrayList<String>();
		lore.add("Votes: " + voteOption1);
		meta2.setLore(lore);
		item.setItemMeta(meta2);
		this.plugin.ResultsGui.setItem(3, item);
		
		//Results for Option2
		item.setType(Material.REDSTONE_BLOCK);
		meta2.setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "Option2 Results");
		lore.clear();
		lore.add("Votes: " + voteOption2);
		meta2.setLore(lore);
		item.setItemMeta(meta2);
		this.plugin.ResultsGui.setItem(5, item);
		
		//Exit Button for GUI
		item.setType(Material.BARRIER);
		meta2.setDisplayName("Exit");
		lore.clear();
		lore.add("Click to Exit GUI");
		meta2.setLore(lore);
		item.setItemMeta(meta2);
		this.plugin.ResultsGui.setItem(4, item);
		
	}
	
	public int getPollOption1() {
		return polloption1;
	}
	
	public void setPollOption1(int polloption1) {
		this.polloption1 = polloption1;
	}

	public int getPollOption2() {
		return polloption2;
	}
	
	public void setPollOption2(int polloption2) {
		this.polloption2 = polloption2;
	}
}
