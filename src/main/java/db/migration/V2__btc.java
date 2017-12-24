package db.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;

public class V2__btc implements JdbcMigration {
    public void migrate(Connection connection) throws Exception {
        connection.prepareStatement("CREATE TABLE btc (" +
                                    "code VARCHAR(6)," +
                                    "redeemed TINYINT," +
                                    "value INT" +
                                    ");").execute();

        ArrayList<BTCGift> names = new ArrayList<>();
        names.add(new BTCGift("mikee", 100));
        names.add(new BTCGift("mazi", 50));
        names.add(new BTCGift("chrissy", 50));
        names.add(new BTCGift("alia", 50));
        names.add(new BTCGift("yolanda", 50));
        names.add(new BTCGift("chrissy", 50));
        names.add(new BTCGift("don", 50));
        names.add(new BTCGift("siavash", 50));
        names.add(new BTCGift("father", 100));

        for (BTCGift gift : names) {
            Random rnd = new Random();
            int n = 100000 + rnd.nextInt(900000);
            connection.prepareStatement("INSERT INTO btc(code, value) VALUES(" + n + ", " + gift.value + ")").execute();
        }
    }
}

class BTCGift {
    String name;
    int value;

    public BTCGift(String name, int value) {
        this.name = name;
        this.value = value;
    }
}