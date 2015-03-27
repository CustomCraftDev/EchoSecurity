import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.dsh105.echopet.compat.api.event.PetRideMoveEvent;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;


public class Security extends JavaPlugin implements Listener{
		
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	
	@EventHandler
	public void onPetMove(PetRideMoveEvent e) {
        try {
	        Faction faction = BoardColl.get().getFactionAt(PS.valueOf(e.getPet().getRider().getOwner().getLocation()));
	        if(!faction.getName().equalsIgnoreCase("Safezone") || (!faction.getName().equalsIgnoreCase("Warzone") || (!faction.getRelationTo(faction).isFriend() || (faction.hasLandInflation()) || (MPlayer.get(e.getPet().getRider().getOwner()).isInOwnTerritory())))) {
	        e.getPet().removeRider();
	        e.setCancelled(true);
	        }
        }catch(Exception ex) {
        	System.out.println("ERROR: PetRideMoveEvent");
        	ex.printStackTrace();
        }
        
	}
		
}
