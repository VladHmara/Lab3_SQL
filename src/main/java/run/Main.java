package main.java.run;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

import java.io.File;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws LiquibaseException, SQLException {
        MyConnection.connect();

        Statement stmt = MyConnection.connection.createStatement();
        String sql = "DROP TABLE IF EXISTS public.databasechangelog;";
        stmt.execute(sql);

        updateChangelog("2.0","1.0","3.0");

        MyConnection.disconnect();
    }


    public static void updateChangelog(String versionOfChangelog) throws LiquibaseException {
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(MyConnection.connection));
        for (File f:new File("src/main/resources/liquibase").listFiles()) {
            if(f.getName().contains(versionOfChangelog))
            {
                Liquibase liq = new Liquibase(f.getPath(), new FileSystemResourceAccessor(), database);
                liq.update(new Contexts(), new LabelExpression());
                break;
            }
        }
    }

    public static void updateChangelog(String ... versionOfChangelog) throws LiquibaseException {
        for (String version : versionOfChangelog)
                updateChangelog(version);
    }

}
