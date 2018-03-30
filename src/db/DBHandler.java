package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import console.ConsoleUtility;

public final class DBHandler
{
    private ConsoleUtility cu = new ConsoleUtility(true, true);
    private Connection conn;
    
    public Connection getConnection() {
        return this.conn;
    }
    
    public DBHandler()
    {
        this.InitializeConnection();
    }
    
    public void InitializeConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/phonebook?" + "user=root&password=root");
        } 
        catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException cnfe) {
            // handle any errors
            cu.err("Exception: " + cnfe.getMessage());
            cu.err("Location: InitializeConnection()");
        }
        catch (SQLException ex) {
            // handle any errors
            cu.err("SQLException: " + ex.getMessage());
            cu.err("SQLState: " + ex.getSQLState());
            cu.err("VendorError: " + ex.getErrorCode());
            cu.err("Location: InitializeConnection()");
        }
    }
    
    public void addKontak(Kontak k) {
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO contact(Hp, Nama, Kategori) VALUES (?, ?, ?)");
            
            ps.setString(1, k.getNoHP());
            ps.setString(2, k.getNama());
            ps.setString(3, k.getKategori());
            
            if(ps.executeUpdate() > 0) {
                //Sukses
                cu.err("Data telah tersimpan!");
                ps.close();
            } else {
                //Gagal
                cu.err("Terjadi kesalahan.");
                cu.err("Location: addKontak()");
            }
        } catch (SQLException ex) {
            cu.err("SQLException: " + ex.getMessage());
            cu.err("Location: addKontak()");
        }
    }
    
    public void updateKontak(Kontak k) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE contact SET Nama=?, Kategori=? WHERE Hp=?");
            
            ps.setString(1, k.getNama());
            ps.setString(2, k.getKategori());
            ps.setString(3, k.getNoHP());
            
            if(ps.executeUpdate() > 0) {
                //Sukses
                cu.err("Data telah tersimpan!");
                ps.close();
            } else {
                //Gagal
                cu.err("Terjadi kesalahan.");
                cu.err("Location: updateKontak()");
            }
        } catch (SQLException ex) {
            cu.err("SQLException: " + ex.getMessage());
            cu.err("Location: updateKontak()");
        }
    }
    
    public void deleteKontak(String NoHP) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM contact WHERE Hp=?");
            
            ps.setString(1, NoHP);
            
            if(ps.executeUpdate() > 0) {
                //Sukses
                cu.err("Data telah tersimpan!");
                ps.close();
            } else {
                //Gagal
                cu.err("Terjadi kesalahan.");
                cu.err("Location: deleteKontak()");
            }
        } catch (SQLException ex) {
            cu.err("SQLException: " + ex.getMessage());
            cu.err("Location: updateKontak()");
        }
    }
    
    public ArrayList<Kontak> selectAllKontak() {
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
            cu.err("SQLException: " + ex.getMessage());
            cu.err("Location: selectAllKontak()");
        }
        return lk;
    }
}
