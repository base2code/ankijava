package de.base2code.ankijava.sql.stmt;

import de.base2code.ankijava.model.db.DbCollection;
import de.chojo.sadu.base.QueryFactory;
import de.chojo.sadu.wrapper.util.UpdateResult;

import javax.sql.DataSource;
import java.util.concurrent.CompletableFuture;

public class CollectionQueries extends QueryFactory {
    public CollectionQueries(DataSource dataSource) {
        super(dataSource);
    }

    public CompletableFuture<Boolean> insertCollection(DbCollection dbCollection) {
        System.out.printf("Inserting collection: %s%n", dbCollection.toString());
        return builder(Boolean.class)
                .query("""
                        INSERT INTO col (
                            id,
                            crt,
                            mod,
                            scm,
                            ver,
                            dty,
                            usn,
                            ls,
                            conf,
                            models,
                            decks,
                            dconf,
                            tags
                        ) VALUES (
                            ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
                        )
                        """)
                .parameter(
                        stmt ->
                                stmt.setInt(dbCollection.getId())
                                        .setInt(dbCollection.getCrt())
                                        .setInt(dbCollection.getMod())
                                        .setInt(dbCollection.getScm())
                                        .setInt(dbCollection.getVer())
                                        .setInt(dbCollection.getDty())
                                        .setInt(dbCollection.getUsn())
                                        .setInt(dbCollection.getLs())
                                        .setString(dbCollection.getConf())
                                        .setString(dbCollection.getModels())
                                        .setString(dbCollection.getDecks())
                                        .setString(dbCollection.getDconf())
                                        .setString(dbCollection.getTags())
                )
                .insert()
                .send()
                .thenApply(UpdateResult::changed);
    }

    public CompletableFuture<Boolean> updateModels(int dbCollectionId, String models) {
        System.out.printf("Updating models for collection %d: %s%n", dbCollectionId, models);
        return builder(Boolean.class)
                .query("""
                        UPDATE col SET models = ? WHERE id = ?
                        """)
                .parameter(
                        stmt ->
                                stmt.setString(models)
                                        .setInt(dbCollectionId)
                )
                .update()
                .send()
                .thenApply(UpdateResult::changed);
    }

    public CompletableFuture<Boolean> updateDecks(int dbCollectionId, String decks) {
        System.out.printf("Updating decks for collection %d: %s%n", dbCollectionId, decks);
        return builder(Boolean.class)
                .query("""
                        UPDATE col SET decks = ? WHERE id = ?
                        """)
                .parameter(
                        stmt ->
                                stmt.setString(decks)
                                        .setInt(dbCollectionId)
                )
                .update()
                .send()
                .thenApply(UpdateResult::changed);
    }
}
