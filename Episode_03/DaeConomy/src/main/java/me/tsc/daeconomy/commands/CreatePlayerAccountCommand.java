package me.tsc.daeconomy.commands;

import me.tsc.daeconomy.DaeConomyMain;
import me.tsc.daeconomy.commands.manager.SubCommand;
import me.tsc.daeconomy.player.PlayerManager;
import me.tsc.daeconomy.utils.MessageManager;
import org.bson.Document;
import org.bukkit.entity.Player;

public class CreatePlayerAccountCommand extends SubCommand {
    private DaeConomyMain plugin = DaeConomyMain.getPlugin();

    @Override
    public void onCommand(Player player, String[] args) {
        if (plugin.mongoConnect.getPlayerDataCollection().find(new Document("uuid", player.getUniqueId().toString())).first() == null) {
            plugin.playerManagerHashMap.put(player.getUniqueId(), new PlayerManager(player.getUniqueId().toString(), 0, 0));
            MessageManager.playerGood(player,"Your account has been created!");
        }else{
            MessageManager.playerBad(player,"Your account already exists!");
        }
    }

    @Override
    public String name() {
        return "createaccount";
    }

    @Override
    public String info() {
        return "This command is used to create a player account.";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
