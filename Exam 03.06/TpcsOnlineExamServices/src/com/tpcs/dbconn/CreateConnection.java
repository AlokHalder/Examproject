package com.tpcs.dbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.tpcs.impl.constant.ExamineeApplicationConstant;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 *
 * @author TPCS_PL Chandan 109
 */
public class CreateConnection implements ExamineeApplicationConstant {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(PROP_DB);
    private static Connection con = null;

    public static Connection getConnection() {
        try {
            Class.forName(resourceBundle.getString(DRIVER_NAME));
            con = DriverManager.getConnection(resourceBundle.getString(DRIVER_URL));
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
        return con;
    }

    public static boolean connectionRelise(ResultSet rs, PreparedStatement pstmt) {
        boolean ok = false;
        if (rs != null) {
            try {
                rs.close();
                rs = null;
                pstmt.close();
                pstmt = null;
                ok = true;
            } catch (SQLException e) {
            }
        }
        return ok;
    }

    public static boolean connectionRelise(ResultSet rs, Connection conn, PreparedStatement pstmt) {
        boolean ok = false;
        if (rs != null) {
            try {
                rs.close();
                rs = null;
                pstmt.close();
                pstmt = null;
                conn.close();
                conn = null;
                ok = true;
            } catch (SQLException e) {
            }
        }
        return ok;
    }
}