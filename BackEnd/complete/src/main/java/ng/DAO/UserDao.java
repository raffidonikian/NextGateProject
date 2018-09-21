package ng.DAO;

import ng.Entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ThreadLocalRandom;



public class UserDao {

    public static int validateLogin(User user) {
        String username = user.getUserName();
        String passwordIn = user.getPassword();
        int hashed = passwordIn.hashCode();
        String password = Integer.toString(hashed);
        String DBpassword = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ng_music?serverTimezone=PST","ng_user","ng");
            Statement stmt=con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from ng_users where username = \"" + username + "\";");

            while (rs.next()) {
                DBpassword = rs.getString("password").trim();
            }


            if (DBpassword.equals(password) && password.length() > 0) {
                int random = ThreadLocalRandom.current().nextInt();
                stmt = con.createStatement();
                int sqlUpdate=stmt.executeUpdate("insert into sessions values(" + Integer.toString(random) + ")");
                return random;
            }

            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public static boolean validateToken(int token) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/ng_music?serverTimezone=PST","ng_user","ng");
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from sessions where session_id = " + Integer.toString(token) + ";");
            if (rs.next()) {
                return true;
            }
            con.close();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
