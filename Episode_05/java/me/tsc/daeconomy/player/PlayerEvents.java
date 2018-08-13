package me.tsc.daeconomy.player;

import me.tsc.daeconomy.DaeConomyMain;
import me.tsc.daeconomy.utils.MessageManager;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

    private DaeConomyMain plugin = DaeConomyMain.getPlugin();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.mongoConnect.addNewPlayer(player);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        plugin.playerManagerHashMap.remove(player.getUniqueId());
    }
}
