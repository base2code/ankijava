package de.base2code.ankijava.sql.stmt;

import de.base2code.ankijava.model.db.DbNote;
import de.chojo.sadu.base.QueryFactory;
import de.chojo.sadu.wrapper.util.UpdateResult;

import javax.sql.DataSource;
import java.util.concurrent.CompletableFuture;

public class NoteQueries extends QueryFactory {
    public NoteQueries(DataSource dataSource) {
        super(dataSource);
    }

    public CompletableFuture<Boolean> insertNote(DbNote note) {
        System.out.printf("Inserting note: %s%n", note.toString());
        return builder(Boolean.class)
                .query("""
                        INSERT INTO notes (
                            id,
                            guid,
                            mid,
                            mod,
                            usn,
                            tags,
                            flds,
                            sfld,
                            csum,
                            flags,
                            data
                        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """)
                .parameter(
                    stmt ->
                            stmt.setInt(note.getId())
                                    .setString(note.getGuid())
                                    .setInt(note.getMid())
                                    .setInt(note.getMod())
                                    .setInt(note.getUsn())
                                    .setString(note.getTags())
                                    .setString(note.getFlds())
                                    .setString(note.getSfld())
                                    .setInt(note.getCsum())
                                    .setInt(note.getFlags())
                                    .setString(note.getData())
                ).insert()
                .send()
                .thenApply(UpdateResult::changed);
    }
}
