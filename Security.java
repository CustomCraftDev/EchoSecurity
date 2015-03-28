import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.dsh105.echopet.compat.api.event.PetInteractEvent;
import com.dsh105.echopet.compat.api.event.PetRideMoveEvent;
import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;


public class Security extends JavaPlugin implements Listener{
		
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	
	@EventHandler
	public void onPetMove(PetRideMoveEvent e) {
        try {
        	Player p = e.getPet().getOwner();
        		if(!p.isOp()) {
			        Faction faction = BoardColl.get().getFactionAt(PS.valueOf(p.getLocation()));
			        String name = ChatColor.stripColor(faction.getName());
			        if(!name.equalsIgnoreCase("Wilderness") && !name.equalsIgnoreCase("SafeZone") && !name.equalsIgnoreCase("WarZone")) {
			        	if(checkFaction(p, faction)) {
			        		p.setSneaking(true);
			        	    e.setCancelled(true);
			        	}
			        }
        		}
        }catch(Exception ex) {}
	}
	
	
	@EventHandler
	public void onPetInteract(PetInteractEvent e) {
        try {
        	Player p = e.getPet().getOwner();
	    		if(!p.isOp()) {
			        Faction faction = BoardColl.get().getFactionAt(PS.valueOf(p.getLocation()));
			        String name = ChatColor.stripColor(faction.getName());
			        if(!name.equalsIgnoreCase("Wilderness") && !name.equalsIgnoreCase("SafeZone") && !name.equalsIgnoreCase("WarZone")) {
			        	if(checkFaction(p, faction)) {
			        		p.sendMessage(ChatColor.RED + "[EchoSecurity] You cant interact with your Pet here.");
			        	    e.setCancelled(true);
			        	}
			        }
	    		}
        }catch(Exception ex) {}  
	}
		
	
	private boolean checkFaction(Player p, Faction f) {
		//
		// player (p) is in random faction (f) ...
    	// TODO check if player is permitted to build in specified faction !
		// return: true  if he is not allowed
		// return: false if he is allowed
		//
		return true;
	}

}
