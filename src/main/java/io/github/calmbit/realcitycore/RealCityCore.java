package io.github.calmbit.realcitycore;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

public final class RealCityCore extends JavaPlugin {

	public Map<String,BlockSerializable> generators = new HashMap<String,BlockSerializable>();
	public Map<String,Boolean> generatorState = new HashMap<String,Boolean>();
	public Map<String, Integer> blockIDMap = new HashMap<String, Integer>();
	public void onEnable(){
		createDataDirectory();
		this.saveDefaultConfig();
		try{
			
			generators = (Map<String, BlockSerializable>) HashMapHandler.load(getDataFolder() + File.separator + "generators.bin");
			getLogger().info("Loaded Generators List");
			generatorState = (Map<String, Boolean>) HashMapHandler.load(getDataFolder() + File.separator + "generatorStates.bin");
			getLogger().info("Loaded Generator States");
			blockIDMap = (Map<String, Integer>) HashMapHandler.load(getDataFolder() + File.separator + "origID.bin");
			getLogger().info("Loaded Generator OrigID");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		getServer().getPluginManager().registerEvents(new PlayerLoginHandler(), this);
		
		getCommand("startGenerator").setExecutor(new RealCityCoreCommandExecutor(this));
		getCommand("setGenerator").setExecutor(new RealCityCoreCommandExecutor(this));
		getCommand("removeGenerator").setExecutor(new RealCityCoreCommandExecutor(this));
		getCommand("saveGenerators").setExecutor(new RealCityCoreCommandExecutor(this));
		getCommand("loadGenerators").setExecutor(new RealCityCoreCommandExecutor(this));
		getLogger().info("RealCityCore loaded.");
	}
	
	private boolean createDataDirectory() {
	    File file = this.getDataFolder();
	    if (!file.isDirectory()){
	        if (!file.mkdirs()) {
	            // failed to create the non existent directory, so failed
	            return false;
	        }
	    }
	    return true;
	}
	
	 
	
	public void onDisable(){
		try{
			HashMapHandler.save(generators,getDataFolder() + File.separator + "generators.bin");
			getLogger().info("Saved Generators List");
			HashMapHandler.save(generatorState,getDataFolder() + File.separator + "generatorStates.bin");
			getLogger().info("Saved Generator States");
			HashMapHandler.save(generatorState,getDataFolder() + File.separator + "origID.bin");
			getLogger().info("Saved Generator OrigID");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
}
