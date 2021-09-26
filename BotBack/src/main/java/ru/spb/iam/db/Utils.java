package ru.spb.iam.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.spb.iam.telebot.NonCommand;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {
    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    public static boolean maybeUserExists(Integer Id) throws SQLException {
        boolean user_exists;
        DbConnection dbCon = DbConnection.getInstance();
        ConnectionPool connectionPool = dbCon.getPool();
        Connection conn = connectionPool.getConnection();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT count(*) FROM subscribers where user_id = %s", Id);
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        if (rs.getInt(1) == 0) {
            user_exists = false;
        } else {
            user_exists = true;
        }
        conn.close();
        return user_exists;

    }

    public static boolean maybeLLExists(Integer Id) throws SQLException {
        boolean ll_exists;
        DbConnection dbCon = DbConnection.getInstance();
        ConnectionPool connectionPool = dbCon.getPool();
        Connection conn = connectionPool.getConnection();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT latutude,longtitide FROM subscribers where user_id = %s", Id);
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        if (rs.getObject(1) != null  && rs.getObject(2) != null) {
            logger.debug("Координаты в бвзе присутствуют");
            ll_exists = true;
        } else {
            logger.debug("Координаты в бвзе отсутствуют");
            ll_exists = false;
        }
        conn.close();
        return ll_exists;

    }


    public static void addLocation(Message msg) throws SQLException {
        DbConnection dbCon = DbConnection.getInstance();
        ConnectionPool connectionPool = dbCon.getPool();
        Connection conn = connectionPool.getConnection();
        Statement stmt = conn.createStatement();

        if (maybeUserExists(msg.getFrom().getId())) {
            logger.debug("Пользоваьель существует, попробуем обновить координаты");

            String sql = String.format("UPDATE public.subscribers  SET username='%s', firstname='%s', lastname='%s', latutude='%s', longtitide='%s' where user_id=%s",
                    (msg.getFrom().getUserName() != null) ? msg.getFrom().getUserName() : msg.getFrom().getFirstName() + " " + msg.getFrom().getLastName()
                    , msg.getFrom().getFirstName()
                    , msg.getFrom().getLastName()
                    , msg.getLocation().getLatitude()
                    , msg.getLocation().getLongitude()
                    , msg.getFrom().getId());
            logger.debug("SQL: " + sql);

         //   ResultSet rs = stmt.executeQuery(sql);
            conn.close();

        } else {
            logger.debug("Пользоваьель НЕ существует)");
        }


    }
}