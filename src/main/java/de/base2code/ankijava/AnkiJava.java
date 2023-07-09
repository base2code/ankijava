package de.base2code.ankijava;

import com.google.gson.Gson;
import de.base2code.ankijava.model.db.DbCard;
import de.base2code.ankijava.model.db.DbCollection;
import de.base2code.ankijava.model.db.DbNote;
import de.base2code.ankijava.model.json.JsonDeck;
import de.base2code.ankijava.model.json.JsonModel;
import de.base2code.ankijava.sql.SqlConnector;
import de.base2code.ankijava.sql.stmt.CardQueries;
import de.base2code.ankijava.sql.stmt.CollectionQueries;
import de.base2code.ankijava.sql.stmt.NoteQueries;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class AnkiJava {
    public static void main(String[] args) throws IOException {
        File dbFile = new File("anki.db");
        if (dbFile.exists()) {
            dbFile.delete();
        }
        AnkiJava ankiJava = new AnkiJava(new SqlConnector(dbFile));
        ankiJava.sqlConnector.createInitialTables();
        int collectionId = (int) System.currentTimeMillis() / 1000;
        ankiJava.createCollection(collectionId);
        int modelId = (int) System.currentTimeMillis() / 1000;
        int deckId = (int) System.currentTimeMillis() / 1000;
        ankiJava.createNote("Front", "Back", modelId, deckId, collectionId);
        ankiJava.createCard();

        ankiJava.sqlConnector.disconnect();

        Map<String, String> env = new HashMap<>();
// Create the zip file if it doesn't exist
        env.put("create", "true");

        Files.write(Path.of("media"), "{}".getBytes());

        try (FileSystem zipfs = FileSystems.newFileSystem(URI.create("jar:file:/Users/niklasgrenningloh/dev/private/ankijava/asd.apkg"),env)) {
            Path externalTxtFile = Path.of("anki.db");
            Path pathInZipfile = zipfs.getPath("/collection.anki2");
            // Copy a file into the zip file
            Files.copy(externalTxtFile, pathInZipfile, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(Path.of("media"), zipfs.getPath("/media"), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private SqlConnector sqlConnector;

    public AnkiJava(SqlConnector sqlConnector) {
        this.sqlConnector = sqlConnector;
        sqlConnector.connect();
    }

    public DbCollection createCollection(int id) {
        DbCollection dbCollection = new DbCollection(
            id,
                (int) (System.currentTimeMillis() / 1000),
                0,
                0,
                11,
                0,
                -1,
                0,
                "{}",
                "{}",
                "{}",
                "{}",
                "{}"
        );

        new CollectionQueries(sqlConnector.getDataSource()).insertCollection(dbCollection).join();
        System.out.printf("Created collection with id %d%n", id);

        return dbCollection;
    }

    public DbNote createNote(String key, String value, int modelId, int deckId, int collectionId) {
        int id = (int) System.currentTimeMillis() / 1000;
        String flds = key + "\u001F" + value;
        DbNote dbNote = new DbNote(
            id,
                UUID.randomUUID().toString(),
                modelId, // Model id
                (int) System.currentTimeMillis() / 1000,
                -1,
                "",
                flds,
                key,
                0, // Can be ignored. See https://github.com/kerrickstaley/genanki/blob/0fa4f74f100c24738d3fbb542e3c81c07e5d6bf5/genanki/note.py#L164
                0,
                ""
        );

        new NoteQueries(sqlConnector.getDataSource()).insertNote(
            dbNote
        ).join();


        JsonModel.JsonField jsonField1 = new JsonModel.JsonField("Front 10", 0);
        JsonModel.JsonField jsonField2 = new JsonModel.JsonField("Back 10", 1);
        JsonModel.JsonField[] jsonFields = new JsonModel.JsonField[]{jsonField1, jsonField2};

        JsonModel.JsonTmpls jsonTmpls = new JsonModel.JsonTmpls("Card 1", 0);
        JsonModel.JsonTmpls[] jsonTmplsArray = new JsonModel.JsonTmpls[]{jsonTmpls};

        JsonModel jsonModel = new JsonModel(deckId, jsonFields, modelId, "Model", jsonTmplsArray);

        JSONObject jsonObject = new JSONObject();
        JSONObject model = new JSONObject(new Gson().toJson(jsonModel));
        jsonObject.put(String.valueOf(jsonModel.getId()), model);

        String models = jsonObject.toString();
        new CollectionQueries(sqlConnector.getDataSource()).updateModels(collectionId, models).join();

        JsonDeck jsonDeck = new JsonDeck("Test", (int) System.currentTimeMillis() / 1000, "Desc");
        JSONObject jsonObjectDeck = new JSONObject();
        JSONObject deck = new JSONObject(new Gson().toJson(jsonDeck));
        jsonObjectDeck.put(String.valueOf(deckId), deck);

        String decks = jsonObjectDeck.toString();
        new CollectionQueries(sqlConnector.getDataSource()).updateDecks(collectionId, decks).join();

        return dbNote;
    }

    public DbCard createCard() {
        int id = 0;
        DbCard dbCard = new DbCard(
                id,
                0, // Note id
                0, // Deck id
                0, // ord
                0, // mod
                0, // usn
                0, // type
                0, // queue
                0, // due
                0, // ivl
                0, // factor
                0, // reps
                0, // lapses
                0, // left
                0, // odue
                0, // odid
                0, // flags
                "{}"
        );

        new CardQueries(sqlConnector.getDataSource()).insertCard(
            dbCard
        ).join();

        return dbCard;
    }
}
