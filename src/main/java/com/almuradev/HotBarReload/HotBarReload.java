package com.almuradev.HotBarReload;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.almuradev.HotBarReload.listeners.BlockEvents;
import com.almuradev.HotBarReload.listeners.EntityEvents;
import com.almuradev.HotBarReload.listeners.PlayerEvents;

public class HotBarReload extends JavaPlugin {
  private final BlockEvents blockListener = new BlockEvents();
  private final PlayerEvents playerListener = new PlayerEvents();
  private final EntityEvents entityListener = new EntityEvents();

  public void onEnable() {
	  
    PluginDescriptionFile pdfFile = getDescription();
    Log.Init(pdfFile.getName());
    
    try {
      PluginManager pm = getServer().getPluginManager();

      pm.registerEvents(this.playerListener, this);
      pm.registerEvents(this.blockListener, this);
      pm.registerEvents(this.entityListener, this);

      System.out.println("[" + pdfFile.getName() + "] version " + pdfFile.getVersion() + " is enabled.");
    }
    catch (Throwable e) {
      System.out.println("[" + pdfFile.getName() + "]" + " error starting: " + e.getMessage() + " Disabling plugin");
      getServer().getPluginManager().disablePlugin(this);
    }
  }

  public void onDisable() {
    PluginDescriptionFile pdfFile = getDescription();
    System.out.println("[" + pdfFile.getName() + "] version " + pdfFile.getVersion() + " is disabled.");
  }
}