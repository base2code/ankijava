package de.base2code.ankijava.sql;

import com.zaxxer.hikari.HikariDataSource;
import de.chojo.sadu.databases.SqLite;
import de.chojo.sadu.datasource.DataSourceCreator;
import de.chojo.sadu.wrapper.QueryBuilderConfig;

import java.io.File;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SqlConnector {
    private File dbFile;
    private HikariDataSource dataSource;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public SqlConnector(File dbFile) {
        this.dbFile = dbFile;

        QueryBuilderConfig.setDefault(QueryBuilderConfig.builder()
                .withExceptionHandler(err -> {
                    System.out.printf("Error while executing query: %s%n", err.getMessage());
                    throw new RuntimeException(err);
                })
                .withExecutor(executorService)
                .build());
    }

    public void connect() {
        dataSource = DataSourceCreator.create(SqLite.get())
                // We configure the usual stuff.
                .configure(config -> config.path(dbFile))
                // We create the hikari data source
                .create()
                // We set a max of 3 parallel connections.
                //.withMaximumPoolSize(1)
                // And define that we want to keep always at least one connecction.
                //.withMinimumIdle(1)
                // in the end we build our datasource.
                .build();
    }

    public void disconnect() {
        executorService.shutdown();
        dataSource.close();
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public void createInitialTables() {
        ArrayList<String> patchFiles = new ArrayList<>(Arrays.stream(new String[]{
                "1_cards.sql",
                "2_collection.sql",
                "3_notes.sql",
                "4_graves.sql",
                "5_revlog.sql",
                "6_indexes.sql",
        }).toList());

        for (String patchFile : patchFiles) {
            try {
                InputStream inputStream = getClass().getResourceAsStream("/sql/" + patchFile);
                String sql = new String(inputStream.readAllBytes());
                try (PreparedStatement ps = getDataSource().getConnection().prepareStatement(sql)) {
                    ps.executeUpdate();
                    System.out.printf("Executed patch %s%n", patchFile);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
