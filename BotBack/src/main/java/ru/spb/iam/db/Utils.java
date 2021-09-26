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
        logger.debug("Внутри maybeLLExists");
        DbConnection dbCon = DbConnection.getInstance();
        ConnectionPool connectionPool = dbCon.getPool();
        Connection conn = connectionPool.getConnection();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT latitude,longitude FROM subscribers where user_id = %s", Id);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next() && (rs.getObject(1) != null && rs.getObject(2) != null)) {
            logger.debug("Координаты в бвзе присутствуют");
            ll_exists = true;
        } else {
            logger.debug("Координаты в бвзе отсутствуют");
            ll_exists = false;
        }
        conn.close();
        return ll_exists;
    }

    public static void addLocation(Integer user_id, Double latitude, Double longitude) throws SQLException {
        DbConnection dbCon = DbConnection.getInstance();
        ConnectionPool connectionPool = dbCon.getPool();
        Connection conn = connectionPool.getConnection();
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE public.subscribers  SET latitude='%s', longitude='%s' where user_id=%s"
                , latitude
                , longitude
                , user_id);
        logger.debug("SQL: " + sql);
        stmt.executeUpdate(sql);
        conn.close();
    }

    public static void addUser(Integer user_id, String username, String firstname, String lastname) throws SQLException {
        DbConnection dbCon = DbConnection.getInstance();
        ConnectionPool connectionPool = dbCon.getPool();
        Connection conn = connectionPool.getConnection();
        Statement stmt = conn.createStatement();
        String sql = String.format("INSERT INTO public.subscribers (user_id, username, firstname, lastname, subscribed) VALUES(%s, '%s', '%s', '%s', false)"
                , user_id
                , username
                , firstname
                , lastname);
        logger.debug("SQL: " + sql);
        stmt.executeUpdate(sql);
        conn.close();
    }

    public static void updateSubscription(Integer user_id, Boolean subscribed) throws SQLException {
        DbConnection dbCon = DbConnection.getInstance();
        ConnectionPool connectionPool = dbCon.getPool();
        Connection conn = connectionPool.getConnection();
        Statement stmt = conn.createStatement();
        String sql = String.format("UPDATE public.subscribers  SET subscribed='%s' where user_id=%s"
                , subscribed
                , user_id
        );
        logger.debug("SQL: " + sql);
        Integer rs = stmt.executeUpdate(sql);
        logger.debug("После запроса: " + rs);
        conn.close();
    }

    public static boolean maybeUserSubscribed(Integer Id) throws SQLException {
        boolean user_subscribed;
        logger.debug("Внутри maybeUserSubscribed");
        DbConnection dbCon = DbConnection.getInstance();
        ConnectionPool connectionPool = dbCon.getPool();
        Connection conn = connectionPool.getConnection();
        Statement stmt = conn.createStatement();
        String sql = String.format("SELECT subscribed FROM subscribers where user_id = %s", Id);
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next() && rs.getBoolean(1)){
            logger.debug("Пользователь подписан");
            user_subscribed = true;
        } else{
            logger.debug("Пользователь еще не подписан");
            user_subscribed = false;
        }
        conn.close();
        return user_subscribed;
    }

}

