package me.tsc.daeconomy;

import me.tsc.daeconomy.utils.MongoConnect;
import org.bukkit.plugin.java.JavaPlugin;

public class DaeConomyMain extends JavaPlugin {
    private static DaeConomyMain plugin;
    public MongoConnect mongoConnect;

    public void onEnable() {
        plugin = this;
        instanceClasses();

        mongoConnect.connect();
    }

    private void instanceClasses() {
        mongoConnect = new MongoConnect();
    }

    public static DaeConomyMain getPlugin() {
        return plugin;
    }
}
