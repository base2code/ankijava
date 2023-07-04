package de.base2code.ankijava.sql.stmt;

import de.base2code.ankijava.db.model.DbCollection;
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
}
