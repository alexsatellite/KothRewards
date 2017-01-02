package me.ByteMagic.KothRewards;

import me.ByteMagic.KothRewards.Utils.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private static Main INSTANCE;
	public static boolean FactionEnabled;
	private long timeEnableStart;

	public static Main getInstance(){
	    return INSTANCE;
	  }
	  
	  public Main(){
		  INSTANCE = this;
	  }

	public void onEnable(){
		if (preEnable()){
			postEnable();
		}
	}

	public boolean preEnable(){
		log(FancyMessage.color("&b=== &eSTART &b==="));
		this.timeEnableStart = System.currentTimeMillis();
		Plugin koth = Bukkit.getPluginManager().getPlugin("KoTH");
		if (koth != null){
			log("Koth hook.");
		}else{
			log("Koth plugin not found.");
			log("Disabling...");
			Bukkit.getPluginManager().disablePlugin(this);
			return false;
		}

		Plugin Factions = Bukkit.getPluginManager().getPlugin("Factions");
		if (Factions != null){
			FactionEnabled = true;
			log("Factions hook.");
		}else{
			FactionEnabled = false;
		}

		registerevents();
		loadconfig();
		return true;
	}

	public void postEnable(){
		long ms = System.currentTimeMillis() - this.timeEnableStart;
		log(FancyMessage.color("&b=== &eFINISH &7(&a" + ms + "ms&7) &b==="));
	}



	
	public void registerevents(){
		Bukkit.getServer().getPluginManager().registerEvents(new KothEvents(), this);
	}
	
	public void loadconfig(){
	    getConfig().options().copyDefaults(true);
	    saveConfig();
	}


	public void log(String text){
		String plugin = FancyMessage.color("&3[&b" + this.getDescription().getName() + " " + this.getDescription().getVersion() + "&3] ");
		Bukkit.getServer().getConsoleSender().sendMessage(plugin + " " + text);
	}
	
	
	

}
