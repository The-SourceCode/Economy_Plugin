package me.tsc.daeconomy;

import me.tsc.daeconomy.player.PlayerManager;
import me.tsc.daeconomy.utils.MessageManager;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EconomyCore implements Economy {

    private DaeConomyMain plugin = DaeConomyMain.getPlugin();

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        return null;
    }

    @Override
    public String currencyNamePlural() {
        return null;
    }

    @Override
    public String currencyNameSingular() {
        return null;
    }

    @Override
    public boolean hasAccount(String uuid) {
        return plugin.mongoConnect.getPlayerDataCollection().find(new Document("uuid", uuid)).first() != null;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return plugin.mongoConnect.getPlayerDataCollection().find(new Document("uuid", offlinePlayer.getUniqueId())).first() != null;
    }

    @Override
    public boolean hasAccount(String uuid, String worldName) {
        if (Bukkit.getPlayer(uuid) != null) {
            Player player = Bukkit.getPlayer(uuid);
            if (Bukkit.getWorld(worldName).getPlayers().contains(player)) {

                return plugin.mongoConnect.getPlayerDataCollection().find(new Document("uuid", player.getUniqueId())).first() != null;
            } else {
                MessageManager.playerBad(player, "Sorry, " + player.getName() + " could not be found in this world.");
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String worldName) {
        if (offlinePlayer == null) return false;
        return plugin.mongoConnect.getPlayerDataCollection().find(new Document("uuid", offlinePlayer.getUniqueId())).first() != null;
    }

    @Override
    public double getBalance(String uuid) {
        Player player = Bukkit.getPlayer(UUID.fromString(uuid));
        if (hasAccount(uuid)) {
            System.out.print(this.getClass() + ": " + 0);
            if (plugin.playerManagerHashMap.containsKey(UUID.fromString(uuid))) {
                PlayerManager playerManager = plugin.playerManagerHashMap.get(UUID.fromString(uuid));
                System.out.print(this.getClass() + ": " + 1);
                return playerManager.getBalance();
            }
        } else {
            System.out.print(this.getClass() + ": " + 2);
            MessageManager.playerBad(player, "You do not have an account.");
        }
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return 0;
    }

    @Override
    public double getBalance(String s, String s1) {
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        return 0;
    }

    @Override
    public boolean has(String s, double v) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return false;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return false;
    }

    @Override
    public EconomyResponse withdrawPlayer(String uuid, double amount) {
        Player player = Bukkit.getPlayer(UUID.fromString(uuid));
        if (hasAccount(uuid)) {
            PlayerManager playerManager = plugin.playerManagerHashMap.get(UUID.fromString(uuid));
            double balance = playerManager.getBalance();

            if (balance >= amount) {
                playerManager.setBalance(balance - amount);
                MessageManager.playerGood(player, "You have paid $" + amount);
                return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.SUCCESS, "You paid $" + amount);
            } else {
                MessageManager.playerBad(player, "You do not have enough money!");
                return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.FAILURE, "You do not have enough money!");
            }
        }

        MessageManager.consoleBad("You do not have an account! (Withdraw)");
        return new EconomyResponse(amount, 0, EconomyResponse.ResponseType.FAILURE, "You do not have an account!");
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String uuid, double amount) {
        Player player = Bukkit.getPlayer(UUID.fromString(uuid));
        if (hasAccount(uuid)) {
            PlayerManager playerManager = plugin.playerManagerHashMap.get(player.getUniqueId());
            double balance = playerManager.getBalance();
            playerManager.setBalance((balance + amount));
            MessageManager.playerBad(player, "You have been paid $" + amount);
            return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.SUCCESS, "You have been paid $" + amount);
        } else {
            MessageManager.playerBad(player, "Player Does not have an account (Deposit)");
        }
        return new EconomyResponse(amount, 0, EconomyResponse.ResponseType.FAILURE, "Player does not have an account!");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String sender, String receiver, double v) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String uuid) {
        if (Bukkit.getPlayer(uuid) != null) {
            Player player = Bukkit.getPlayer(uuid);
            if (!hasAccount(player.getUniqueId().toString())) {
                plugin.playerManagerHashMap.put(player.getUniqueId(),
                        new PlayerManager(player.getUniqueId().toString(), 0, 0));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {

        if (!hasAccount(player.getUniqueId().toString())) {
            plugin.playerManagerHashMap.put(player.getUniqueId(),
                    new PlayerManager(player.getUniqueId().toString(), 0, 0));
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean createPlayerAccount(String uuid, String worldName) {
        if (Bukkit.getPlayer(uuid) != null) {
            Player player = Bukkit.getPlayer(uuid);

            if (Bukkit.getWorld(worldName).getPlayers().contains(player)) {
                if (!hasAccount(player.getUniqueId().toString())) {
                    plugin.playerManagerHashMap.put(player.getUniqueId(),
                            new PlayerManager(player.getUniqueId().toString(), 0, 0));
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String worldName) {

        if (Bukkit.getWorld(worldName).getPlayers().contains(offlinePlayer)) {
            if (!hasAccount(offlinePlayer.getUniqueId().toString())) {
                plugin.playerManagerHashMap.put(offlinePlayer.getUniqueId(),
                        new PlayerManager(offlinePlayer.getUniqueId().toString(), 0, 0));
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
