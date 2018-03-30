
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyConnection {
    
    private Connection conn;
    
    public Connection getConnection() {
        return this.conn;
    }
    
    public MyConnection() {
        InitializeConnection();
    }
    
    public void InitializeConnection() {
        String connectionURL = "jdbc:mysql://localhost/db_kelas";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "admin");
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
            System.out.println("Exception: " + cnfe.getMessage());
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public void insertMahasiswa(Mahasiswa mhs) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO mahasiswa(NIM, Nama) VALUES (?,?)");
            ps.setString(1, mhs.getNIM());
            ps.setString(2, mhs.getNama());
            if(ps.executeUpdate() > 0) {
                //Sukses
                System.out.println("Data telah tersimpan!");
                ps.close();
            } else {
                //Gagal
                System.out.println("Terjadi kesalahan.");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    public void updateMahasiswa(Mahasiswa mhs) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE mahasiswa SET Nama=? WHERE NIM=?");
            ps.setString(1, mhs.getNama());
            ps.setString(2, mhs.getNIM());
            if(ps.executeUpdate() > 0) {
                //Sukses
                System.out.println("Data telah tersimpan!");
                ps.close();
            } else {
                //Gagal
                System.out.println("Terjadi kesalahan.");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    public void deleteMahasiswa(String NIM) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM mahasiswa WHERE NIM=?");
            ps.setString(1, NIM);
            if(ps.executeUpdate() > 0) {
                //Sukses
                System.out.println("Data telah tersimpan!");
                ps.close();
            } else {
                //Gagal
                System.out.println("Terjadi kesalahan.");
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }
    
    public ArrayList<Mahasiswa> selectAllMahasiswa() {
        ArrayList<Mahasiswa> allMahasiswa = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try{
            ps = conn.prepareStatement("SELECT * FROM mahasiswa");
            rs = ps.executeQuery();
            while (rs.next()) {
                String NIM = rs.getString(1);
                String Nama = rs.getString(2);
                Mahasiswa mhs = new Mahasiswa(NIM, Nama);
                allMahasiswa.add(mhs);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return allMahasiswa;
    }
}
