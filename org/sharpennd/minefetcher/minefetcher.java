package org.sharpennd.minefetcher;

import org.bukkit.plugin.java.JavaPlugin;

public final class McNeofetch extends JavaPlugin {
   public void onEnable() {
      this.getCommand("neofetch").setExecutor(new sendOutput());
   }
}
