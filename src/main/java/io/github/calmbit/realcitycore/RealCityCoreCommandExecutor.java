package io.github.calmbit.realcitycore;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RealCityCoreCommandExecutor implements CommandExecutor {

	private RealCityCore pluginCity;
	public RealCityCoreCommandExecutor(RealCityCore plugin)
	{
		pluginCity = plugin;
	}
	public boolean onCommand(CommandSender sender,Command cmd,String label, String[] args)
	{
		if(args.length > 1)
		{
			sender.sendMessage("Too Many Arguments! You Inputed " + args.length + " arguments.");
			return false;
		}
		if(args.length < 1)
		{
			if(cmd.getName().equalsIgnoreCase("saveGenerators"))
			{
				try{
					HashMapHandler.save(pluginCity.generators,pluginCity.getDataFolder() + File.separator + "generators.bin");
					HashMapHandler.save(pluginCity.generatorState,pluginCity.getDataFolder() + File.separator + "generatorStates.bin");
					sender.sendMessage("Saved!");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				
			}
			if(cmd.getName().equalsIgnoreCase("loadGenerators"))
			{
				try{
					pluginCity.generators = (Map<String, BlockSerializable>) HashMapHandler.load(pluginCity.getDataFolder() + File.separator + "generators.bin");
					pluginCity.generatorState = (Map<String, Boolean>) HashMapHandler.load(pluginCity.getDataFolder() + File.separator + "generatorStates.bin");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
			}
		}
		else {
	if(cmd.getName().equalsIgnoreCase("startGenerator"))
	{
		if(pluginCity.generators.get(args[0])== null)
		{
			sender.sendMessage("That Generator Doesn't Exist!");
			return false;
		}
		else if(pluginCity.generatorState.get(args[0]))
		{
			sender.sendMessage("That Generator Is Already Active!");
			return false;
		}
		else {
			pluginCity.generatorState.put(args[0], true);
			sender.sendMessage("Generator " + args[0] + " activated!");
		}
		return true;
	}
	
	if(cmd.getName().equalsIgnoreCase("setGenerator"))
	{
		if(!(sender instanceof Player)) {
			sender.sendMessage("You must be a player to do this.");
			return false;
		}
		else {
			Player player = (Player)sender;
			Block target = player.getTargetBlock((Set<Material>)null, 100);
			BlockSerializable targetserial = new BlockSerializable(target);
		
		if(pluginCity.generators.containsKey(args[0]))
		{
			player.sendMessage("That Generator Already Exists!");
			return false;
		}
		if(pluginCity.generators.containsValue(targetserial))
		{
			player.sendMessage("That Block Is Already a Generator!");
			return false;
		}
		else {
			
			pluginCity.generators.put(args[0],targetserial);
			pluginCity.blockIDMap.put(args[0],target.getTypeId());
			target.setTypeId(35);
			target.setData((byte) 1);
			player.sendMessage("Generator " + args[0] + " created!");
			return true;
		}
		}
			
			
		}
	if(cmd.getName().equalsIgnoreCase("removeGenerator")) {
		{
			if(!(sender instanceof Player)) {
				sender.sendMessage("You must be a player to do this.");
				return false;
			}
			else {
				Player player = (Player)sender;
				Block target = player.getTargetBlock((Set<Material>)null, 100);
				
				if(!(pluginCity.generators.containsKey(args[0])))
				{
					player.sendMessage("That block isn't a generator!");
				}
				else {
					pluginCity.generators.remove(args[0]);
					target.setTypeId(pluginCity.blockIDMap.get(args[0]));
					pluginCity.blockIDMap.remove(args[0]);
					player.sendMessage("Generator removed!");
				}
			}
	}
	
	}
		}
	return false;
}

}
