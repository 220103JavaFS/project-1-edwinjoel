package com.revature.util;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static com.revature.JDBCPostgresSQLConnection.getConnection;

public class JDBCPostgresSQLConnectionTest {

    @Test
    public void testConnection() {
        try{
            getConnection();
            System.out.println("Connection successful");
        }catch (SQLException e){
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

}
