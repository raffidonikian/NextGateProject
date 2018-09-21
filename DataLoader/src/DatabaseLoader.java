import java.sql.*;
import java.io.File;
import java.net.URL;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DatabaseLoader{
    public static void main(String args[]) {
        populateUsers();
        populateSingers();
        populateAlbums();
    }

    public static void populateUsers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ng_music?serverTimezone=PST","ng_user","ng");
            Statement stmt=con.createStatement();
            File usersFile = new File("src/Data/ng_users.txt");
            try {
                Scanner sc = new Scanner(usersFile);
                sc.nextLine();
                while (sc.hasNextLine()) {
                    String[] splitUp = sc.nextLine().split("\\|");
                    splitUp[0] = splitUp[0].trim();
                    splitUp[1] = splitUp[1].trim();
                    //can add salt but not going to because ng_users table has no salt column
                    int hashed = splitUp[1].hashCode();
                    stmt=con.createStatement();
                    String query = "INSERT into ng_users (username,password) VALUES (" + "\"" + splitUp[0] + "\", \"" + Integer.toString(hashed) + "\")";
                    int sqlUpdate=stmt.executeUpdate(query);
                }
                sc.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void populateSingers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ng_music?serverTimezone=PST","ng_user","ng");
            Statement stmt=con.createStatement();
            File singersFile = new File("src/Data/ng_singers.txt");
            try {
                Scanner sc = new Scanner(singersFile);
                sc.nextLine();
                while (sc.hasNextLine()) {
                    String[] splitUp = sc.nextLine().split("\\|");
                    splitUp[0] = splitUp[0].trim();
                    stmt=con.createStatement();
                    String query = "INSERT into ng_singers (name, dob, sex) VALUES ("
                                        + " \"" + splitUp[0] + "\","
                                        + splitUp[1] + ", "
                                        + " \"" + splitUp[2] + "\""
                                        + ")";
                    int sqlUpdate=stmt.executeUpdate(query);
                }
                sc.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void populateAlbums() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ng_music?serverTimezone=PST","ng_user","ng");
            Statement stmt=con.createStatement();
            File albumsFile = new File("src/Data/ng_albums.txt");
            try {

                Scanner sc = new Scanner(albumsFile);
                sc.nextLine();
                int count = 1;
                while (sc.hasNextLine()) {
                    String i = sc.nextLine();
                    String[] splitUp = i.split("\\|");
                    splitUp[0] = splitUp[0].trim();
                    splitUp[1] = splitUp[1].trim();
                    splitUp[2] = splitUp[2].trim();
                    splitUp[3] = splitUp[3].trim();
                    stmt=con.createStatement();
                    String query = "INSERT INTO ng_albums (ng_singers_id, album_name, release_year, record_company) " +
                                    "VALUES ((select ng_singers_id from ng_singers where name = " +
                            " \"" + splitUp[0] + "\")," +
                            " \"" + splitUp[1] + "\", " +
                            " \"" + splitUp[2] + "\", " +
                            " \"" + splitUp[3] + "\");";
                    int sqlUpdate=stmt.executeUpdate(query);
                    count++;
                }
                sc.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
