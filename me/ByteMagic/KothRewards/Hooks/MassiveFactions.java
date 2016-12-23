package me.ByteMagic.KothRewards.Hooks;

import java.util.List;

import org.bukkit.entity.Player;

import com.massivecraft.factions.entity.Faction;

public class MassiveFactions {
	private Faction faction;
	
	public MassiveFactions(String id){
		this.faction = Faction.get(id);
	}
	
	public List<Player> GetOnlinePlayers(){
		return faction.getOnlinePlayers();
	}
	
	public String GetFactionName(){
		return faction.getName();
	}

}
