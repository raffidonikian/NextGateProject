package ng.DAO;
import java.sql.*;
import ng.Entity.Singer;
import org.springframework.expression.spel.ast.NullLiteral;

import java.util.Collection;
import java.util.ArrayList;
public class SingerDao {

    public static Collection<Singer> getAllSingers() {
        ArrayList<Singer> returnee = new ArrayList<Singer>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ng_music?serverTimezone=PST","ng_user","ng");
            Statement stmt=con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from ng_singers;");

            while (rs.next()) {
                returnee.add(new Singer(rs.getInt("ng_singers_id"), rs.getString("name"), rs.getString("dob"), rs.getString("sex")));
            }

            con.close();
            return returnee;
        }

        catch(Exception e) {
            System.out.println(e);
        }

        return returnee;
    }



    public static Singer getSingerByName(String name) {
        Singer returnee = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ng_music?serverTimezone=PST","ng_user","ng");
            Statement stmt=con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from ng_singers where name LIKE \"" + name + "\";");

            while (rs.next()) {
                returnee = new Singer(rs.getInt("ng_singers_id"), rs.getString("name"), rs.getString("dob"), rs.getString("sex"));
            }

            con.close();
            return returnee;
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return returnee;
    }




    public static String getSingerNameById(int id) {
        String returnee = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ng_music?serverTimezone=PST","ng_user","ng");
            Statement stmt=con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from ng_singers where ng_singers_id = " + Integer.toString(id) + ";");

            while (rs.next()) {
                returnee = rs.getString("name");
            }

            con.close();
            return returnee;
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return returnee;
    }

}
