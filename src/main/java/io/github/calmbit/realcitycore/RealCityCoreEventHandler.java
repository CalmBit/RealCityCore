package io.github.calmbit.realcitycore;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class RealCityCoreEventHandler implements Listener {
	RealCityCore plugin;
	public RealCityCoreEventHandler(RealCityCore plugin)
	{
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler
	public void normalLogin(PlayerLoginEvent event)
	{
		event.getPlayer().sendMessage(plugin.getConfig().getString("MOTD", "MOTD wasn't found - tell your server admin!"));
	}
	
	@EventHandler
	public void blockBreak(BlockBreakEvent event)
	{
		if(event.isCancelled()) return;
		if(plugin.generators.containsValue(event.getBlock()))
		{
			event.setCancelled(true);
			event.getPlayer().sendMessage("§cThat block is a generator! You'll need to /removeGenerator it first!");
		}
	}
}
