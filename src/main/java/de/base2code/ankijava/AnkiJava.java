package de.base2code.ankijava;

import de.base2code.ankijava.db.model.DbCard;
import de.base2code.ankijava.db.model.DbCollection;
import de.base2code.ankijava.db.model.DbNote;
import de.base2code.ankijava.sql.SqlConnector;
import de.base2code.ankijava.sql.stmt.CardQueries;
import de.base2code.ankijava.sql.stmt.CollectionQueries;
import de.base2code.ankijava.sql.stmt.NoteQueries;
import de.base2code.ankijava.utils.Utils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.zip.ZipInputStream;

public class AnkiJava {
    public static void main(String[] args) {
        File dbFile = new File("anki.db");
        if (dbFile.exists()) {
            dbFile.delete();
        }
        AnkiJava ankiJava = new AnkiJava(new SqlConnector(dbFile));
        ankiJava.sqlConnector.createInitialTables();
        ankiJava.createCollection();
        ankiJava.createNote();
        ankiJava.createCard();

        ankiJava.sqlConnector.disconnect();
    }

    private SqlConnector sqlConnector;

    public AnkiJava(SqlConnector sqlConnector) {
        this.sqlConnector = sqlConnector;
        sqlConnector.connect();
    }

    public DbCollection createCollection() {
        int id = 0;
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

    public DbNote createNote(String key, String value, int modelId) {
        int id = 0;
        String flds = key + "\u001F" + value;
        DbNote dbNote = new DbNote(
            id,
                UUID.randomUUID().toString(),
                modelId, // Model id
                (int) System.currentTimeMillis() / 1000,
                -1,
                "{}",
                flds,
                key,
                Utils.sha1Start(flds),
                0,
                "{}"
        );

        new NoteQueries(sqlConnector.getDataSource()).insertNote(
            dbNote
        ).join();

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
