package me.tsc.daeconomy.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import me.tsc.daeconomy.DaeConomyMain;

public class MongoConnect {
    private MongoDatabase database;
    private DaeConomyMain plugin = DaeConomyMain.getPlugin();

    public void connect(){
        MongoClient client = new MongoClient(new MongoClientURI(plugin.getConfig().getString("uri")));
        setDatabase(client.getDatabase("DaeConomy"));
        MessageManager.consoleGood("Database Connected");
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }
}
