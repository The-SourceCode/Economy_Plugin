package me.tsc.daeconomy.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.tsc.daeconomy.DaeConomyMain;
import org.bson.Document;
import org.bson.conversions.Bson;


public class MongoConnect {
    private MongoDatabase database;
    private MongoCollection playerData;
    private DaeConomyMain plugin = DaeConomyMain.getPlugin();

    public void connect() {
        MongoClient client = new MongoClient(new MongoClientURI(plugin.getConfig().getString("uri")));
        setDatabase(client.getDatabase("DaeConomy"));
        setPlayerDataCollection(database.getCollection("PlayerData"));
        MessageManager.consoleGood("Database Connected");
    }

    public void setPlayerDataDocument(Object value, String identifier, String uuid) {
        Document document = new Document("uuid", uuid);
        Bson newValue = new Document(identifier, value);
        Bson updateOperation = new Document("$set", newValue);
        playerData.updateOne(document, updateOperation);
    }

    public Object getPlayerDataDocument(String identifier, String uuid) {
        Document document = new Document("uuid", uuid);
        if (playerData.find(document).first() != null) {
            Document found = (Document) playerData.find().first();
            return found.get(identifier);
        }

        MessageManager.consoleBad("Document is null for UUID: " + uuid);
        return null;
    }


    public MongoCollection getPlayerDataCollection() {
        return playerData;
    }

    public void setPlayerDataCollection(MongoCollection playerData) {
        this.playerData = playerData;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void setDatabase(MongoDatabase database) {
        this.database = database;
    }
}
