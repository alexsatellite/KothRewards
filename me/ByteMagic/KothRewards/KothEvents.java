package me.ByteMagic.KothRewards;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import me.ByteMagic.KothRewards.Hooks.FactionsUUID;
import me.ByteMagic.KothRewards.Hooks.MassiveFactions;
import me.ByteMagic.KothRewards.Utils.FancyMessage;
import subside.plugins.koth.events.KothEndEvent;
import subside.plugins.koth.gamemodes.RunningKoth;

public class KothEvents implements Listener {

	@EventHandler
	public void OnKothEnd(KothEndEvent e){
		if (e.getWinner() != null){
		   String CaptureType = e.getWinner().getUniqueClassIdentifier().toString();
			if (e.getReason() == RunningKoth.EndReason.FORCED){
				return;
			}
		    List<String> list = Main.getInstance().getConfig().getStringList("Commands");
                new BukkitRunnable()
                {
                  public void run()
                  {
                	 if (CaptureType.equalsIgnoreCase("player")){
                		 String pwinner = e.getWinner().getName();
               		    for (String cmds : list){
              		    	Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), FancyMessage.color(cmds.replace("%player%", pwinner).replace("%koth%", e.getKoth().getName())));
              		    	
              		    }  
                	 }else if (CaptureType.equalsIgnoreCase("faction")){
                		 if (Main.FactionEnabled){
                       		 String factionid = e.getWinner().getUniqueObjectIdentifier().toString();
                       		 MassiveFactions massivefactions = new MassiveFactions(factionid);
                       		if (Main.getInstance().getConfig().getBoolean("Rewards.Reward-only-factionplayers-in-cap-area")){
                       			List<Player> PlayersInside = e.getWinner().getAvailablePlayers(e.getKoth());
                       			for (Player factionplayer : massivefactions.GetOnlinePlayers()){ 
                       				for (Player inside : PlayersInside){
                       					if (factionplayer == inside){
                       					 for (String cmds : list){
                             		    	Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), FancyMessage.color(cmds.replace("%player%", factionplayer.getName()).replace("%faction%", massivefactions.GetFactionName()).replace("%koth%", e.getKoth().getName())));
                             		    } 
                       				}                     				
                       			}                     			
                       		 }
                       			
                       		}else{
                       			for (Player factionplayer : massivefactions.GetOnlinePlayers()){             	 
                       			 for (String cmds : list){
                           		    	Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), FancyMessage.color(cmds.replace("%player%", factionplayer.getName()).replace("%faction%", massivefactions.GetFactionName()).replace("%koth%", e.getKoth().getName())));
                           		    } 
                       		 }
                       		}
                    		 
                		 }else{
                			 Main.getInstance().log("§c----------------------------------------");
							 Main.getInstance().log("§e§c[ERROR] §aFactions plugin not found.");
							 Main.getInstance().log("§c----------------------------------------");
                		 }
 
                		 
                	 }else if (CaptureType.equalsIgnoreCase("Factionuuid")){
                		 if (Main.FactionEnabled){
                			 String factionid = e.getWinner().getUniqueObjectIdentifier().toString();
                			 FactionsUUID factionsuuid = new FactionsUUID(factionid);
                			 if (Main.getInstance().getConfig().getBoolean("Rewards.Reward-only-factionplayers-in-cap-area")){
                				 List<Player> PlayersInside = e.getWinner().getAvailablePlayers(e.getKoth());
                				 for (Player factionplayer : factionsuuid.GetOnlinePlayers()){
                					 for (Player inside : PlayersInside){
                						 if (factionplayer == inside){
                							 for (String cmds : list){
                                  		    	Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), FancyMessage.color(cmds.replace("%player%", factionplayer.getName()).replace("%faction%", factionsuuid.GetFactionName()).replace("%koth%", e.getKoth().getName())));
                                  		    	
                                  		    }
                						 }
                					 }                						 
                				}                       			  
                        		 
                			 }else{
                				 for (Player factionplayer : factionsuuid.GetOnlinePlayers()){
                        			 for (String cmds : list){
                            		    	Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), FancyMessage.color(cmds.replace("%player%", factionplayer.getName()).replace("%faction%", factionsuuid.GetFactionName()).replace("%koth%", e.getKoth().getName())));
                            		    	
                            		    } 
                        		 }
                			 }
                			 
                		 }else{
							 Main.getInstance().log("§c----------------------------------------");
							 Main.getInstance().log("§e§c[ERROR] §aFactionsUUID plugin not found.");
							 Main.getInstance().log("§c----------------------------------------");
                		 }
                			
                		 
                	 }
         		    	               
                  }
                }.runTaskLater(Main.getInstance(), 10L);
		    		
		}
	}
}
