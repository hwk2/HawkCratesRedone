package me.hawk.crate;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.TripwireHook;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	private Inventory inv;
	private ItemStack key;
	private ItemStack crate;
	
	public Main(Plugin p) {
		
		inv = Bukkit.getServer().createInventory(null, 21, "Crate");
		
		key = createItem(ChatColor.GRAY + "Crate Key");
		crate = createItem(ChatColor.GRAY + "Crate");
		
		inv.setItem(0, key);
		
		
	}
	@EventHandler
	public void clickChest(PlayerInteractEvent e) {
		Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Block b = e.getClickedBlock();
                if (b.getType() == Material.CHEST){
                    if(!p.getInventory().contains(key)) {
                    	p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "I'm Sorry," + ChatColor.RESET + ChatColor.GRAY + " you do not have a key for this crate!");
                    	return;
                    }
                    if(p.getInventory().contains(key)) {
                    	show(p);
                    }
                }
        }
	}
	private ItemStack createItem(String name) {
		ItemStack i = new ItemStack(new TripwireHook().toItemStack(0));
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList("Open a crate with one of these!"));
		return i;
	}
	//private
	public void show(Player p) {
		p.openInventory(inv);
		//Create the cool effects here!
		//Give items!
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("gcrate")) {

			Player target = Bukkit.getServer().getPlayer(args[0]);
			
			if(target == null) {
				p.getInventory().addItem(crate);
				p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Yay!" + ChatColor.RESET + ChatColor.GRAY + " You have received a crate!");
				p.sendMessage(ChatColor.GRAY + "Open it with a key!");
			}
			
			if (args.length < 1) {
				p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Oops!" + ChatColor.RESET + ChatColor.GRAY + "There are too many arguements!");
			}
		}
		return true;
	}
	public boolean onCommand1(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
			if(cmd.getName().equalsIgnoreCase("gkey")) {

				Player target = Bukkit.getServer().getPlayer(args[0]);
			
				if(target == null) {
					p.getInventory().addItem(key);
					p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Yay!" + ChatColor.RESET + ChatColor.GRAY + " You have received a crate key!");
					p.sendMessage(ChatColor.GRAY + "Use it to open a crate!");
				}
			
				if (args.length < 1) {
					p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Oops!" + ChatColor.RESET + ChatColor.GRAY + "There are too many arguements!");
				}
			}
		return true;

	}
}
