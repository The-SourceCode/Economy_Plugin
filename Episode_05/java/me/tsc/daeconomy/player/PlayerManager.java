package me.tsc.daeconomy.player;

import me.tsc.daeconomy.DaeConomyMain;
import me.tsc.daeconomy.utils.MongoConnect;
import org.bson.Document;


public class PlayerManager {

    private String uuid;
    private double balance;
    private double bankaccount;
    private DaeConomyMain plugin = DaeConomyMain.getPlugin();
    private MongoConnect mongoConnect = plugin.mongoConnect;

    public PlayerManager(String uuid, double balance, double bankaccount) {
        this.uuid = uuid;
        this.balance = balance;
        this.bankaccount = bankaccount;

        Document document = new Document("uuid", uuid);
        if (mongoConnect.getPlayerDataCollection().find(document).first() == null) {
            document.append("balance", balance);
            document.append("bank-account", bankaccount);
            mongoConnect.getPlayerDataCollection().insertOne(document);
        }
    }

    public String getUuid() {
        return uuid;
    }

    public double getBalance() {
        return (double) mongoConnect.getPlayerDataDocument("balance", uuid);
    }

    public void setBalance(double balance) {
        this.balance = balance;
        mongoConnect.setPlayerDataDocument(balance, "balance", uuid);
    }

    public double getBankaccount() {
        return (double) mongoConnect.getPlayerDataDocument("bank-account", uuid);
    }

    public void setBankaccount(double bankaccount) {
        this.bankaccount = bankaccount;
        mongoConnect.setPlayerDataDocument(bankaccount, "bank-account", uuid);

    }
}
