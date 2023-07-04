package de.base2code.ankijava.sql.stmt;

import de.base2code.ankijava.db.model.DbCard;
import de.chojo.sadu.base.QueryFactory;
import de.chojo.sadu.wrapper.util.UpdateResult;

import javax.sql.DataSource;
import java.util.concurrent.CompletableFuture;

public class CardQueries extends QueryFactory {
    public CardQueries(DataSource dataSource) {
        super(dataSource);
    }

    public CompletableFuture<Boolean> insertCard(DbCard dbCard) {
        System.out.printf("Inserting card: %s%n", dbCard.toString());
        return builder(Boolean.class)
                .query("""
                        INSERT INTO cards (
                            id,
                            nid,
                            did,
                            ord,
                            mod,
                            usn,
                            type,
                            queue,
                            due,
                            ivl,
                            factor,
                            reps,
                            lapses,
                            left,
                            odue,
                            odid,
                            flags,
                            data
                        ) VALUES (
                        ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
                        )
                        """)
                .parameter(
                        stmt ->
                                stmt.setInt(dbCard.getId())
                                        .setInt(dbCard.getNid())
                                        .setInt(dbCard.getDid())
                                        .setInt(dbCard.getOrd())
                                        .setInt(dbCard.getMod())
                                        .setInt(dbCard.getUsn())
                                        .setInt(dbCard.getType())
                                        .setInt(dbCard.getQueue())
                                        .setInt(dbCard.getDue())
                                        .setInt(dbCard.getIvl())
                                        .setInt(dbCard.getFactor())
                                        .setInt(dbCard.getReps())
                                        .setInt(dbCard.getLapses())
                                        .setInt(dbCard.getLeft())
                                        .setInt(dbCard.getOdue())
                                        .setInt(dbCard.getOdid())
                                        .setInt(dbCard.getFlags())
                                        .setString(dbCard.getData())
                ).insert()
                .send()
                .thenApply(UpdateResult::changed);
    }
}
