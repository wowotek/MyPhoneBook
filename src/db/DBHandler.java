package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class DBHandler
{
    private Connection conn;
    
    public Connection getConnection() {
        return this.conn;
    }
    
    DBHandler()
    {
        String connectionURL = "jdbc:mysql://localhost/phonebook";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        properties.setProperty("useSSL", "false");
        properties.setProperty("autoReconnect", "true");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionURL, properties);
            // Do something with the MyConnection
            
        } 
        catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException cnfe) {
            // handle any errors
            System.err.println("Exception: " + cnfe.getMessage());
        }
        catch (SQLException ex) {
            // handle any errors
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public void addContact(Kontak k) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO contact(Hp, Nama, Kategori) VALUES (?, ?, ?)");
            
            ps.setString(1, k.getNoHP());
            ps.setString(2, k.getNama());
            ps.setString(3, k.getKategori());
            
            if(ps.executeUpdate() > 0) {
                //Sukses
                System.err.println("Data telah tersimpan!");
                ps.close();
            } else {
                //Gagal
                System.err.println("Terjadi kesalahan.");
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    public void updateMahasiswa(Kontak k) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE contact SET Nama=?, Kategori=? WHERE Hp=?");
            
            ps.setString(1, k.getNama());
            ps.setString(2, k.getKategori());
            ps.setString(3, k.getNoHP());
            
            if(ps.executeUpdate() > 0) {
                //Sukses
                System.err.println("Data telah tersimpan!");
                ps.close();
            } else {
                //Gagal
                System.err.println("Terjadi kesalahan.");
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    public void deleteContact(String NoHP) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM contact WHERE Hp=?");
            
            ps.setString(1, NoHP);
            
            if(ps.executeUpdate() > 0) {
                //Sukses
                System.err.println("Data telah tersimpan!");
                ps.close();
            } else {
                //Gagal
                System.err.println("Terjadi kesalahan.");
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }
    
    public ArrayList<Kontak> selectAllMahasiswa() {
        ArrayList<Kontak> lk = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            ps = conn.prepareStatement("SELECT * FROM contact");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String Hp = rs.getString(1);
                String Nama = rs.getString(2);
                String Kategori = rs.getString(3);
                
                Kontak k = new Kontak(Hp, Nama, Kategori);
                lk.add(k);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
        return lk;
    }
}
