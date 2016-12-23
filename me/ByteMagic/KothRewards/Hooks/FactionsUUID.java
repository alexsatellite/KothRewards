package me.ByteMagic.KothRewards.Hooks;

import java.util.List;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import org.bukkit.entity.Player;


public class FactionsUUID {
	private Faction faction;
	
	public FactionsUUID(String id){
		this.faction = Factions.getInstance().getFactionById(id);
	}

	public List<Player> GetOnlinePlayers(){
		return faction.getOnlinePlayers();
	}
	
	public String GetFactionName(){
		return faction.getTag();
	}

}
