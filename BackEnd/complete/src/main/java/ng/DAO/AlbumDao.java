package ng.DAO;
import ng.Entity.Album;
import java.sql.*;

public class AlbumDao {


    public static Album getAlbumByName(String name) {
        Album returnee = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ng_music?serverTimezone=PST","ng_user","ng");
            Statement stmt=con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from ng_albums where album_name LIKE \"" + name + "\";");

            while (rs.next()) {
                returnee = new Album(rs.getInt("ng_albums_id"), rs.getInt("ng_singers_id"), rs.getString("album_name"), rs.getString("release_year"), rs.getString("record_company"));
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
