package ru.spb.iam.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class DbConnection {
    private static Logger logger = LoggerFactory.getLogger(DbConnection.class);

    private DbConnection() throws SQLException {
        logger.debug("Запускаем конструктор DbConnection()");
    }

    private static class Holder {
        private static DbConnection INSTANCE = null;

        static {
            try {
                logger.debug("Пытаемся создать единственный экземпляр");
                INSTANCE = new DbConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DbConnection getInstance() {
        logger.debug("Отдаем ссылку на DbConnection()");
        return Holder.INSTANCE;
    }

    private ConnectionPool connectionPool =
            BasicConnectionPool.create(
                    System.getenv("BOT_DB_URL"),
                    System.getenv("BOT_DB_USERNAME"),
                    System.getenv("BOT_DB_PWD"));

    public ConnectionPool getPool() {
        return this.connectionPool;
    }
}

