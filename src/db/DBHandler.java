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

    public Connection getConnection()
    {
        return this.conn;
    }

    public DBHandler()
    {
        cu.err("Connection Initialization...", 5);
        boolean failed = false;
        int i;
        for (i = 0; i < cu.ConnectionRetryTimes; i++)
        {
            if (this.InitializeConnection() == true)
            {
                cu.err("Connection Successful !", 0);
                break;
            }
            else
            {
                cu.err("Connection Failed !", 3);
                cu.err("Tries : " + (i + 1) + ", Retrying Connection...");
                failed = true;
            }
        }

        if (failed == true)
        {
            cu.err("Connection Failed After " + i + " Attempts", 4);
            cu.err("Exiting Program...", 4);
            System.exit(1);
        }
    }

    public boolean InitializeConnection()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/phonebook?" + "user=root&password=root");
            return true;
        }
        catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException cnfe)
        {
            // handle any errors
            cu.err("Exception: " + cnfe.getMessage(), 2);
            cu.err("Location: InitializeConnection()");
            return false;
        }
        catch (SQLException ex)
        {
            // handle any errors
            cu.err("SQLException: " + ex.getMessage(), 2);
            cu.err("SQLState: " + ex.getSQLState(), 2);
            cu.err("VendorError: " + ex.getErrorCode(), 2);
            cu.err("Location: InitializeConnection()");
            return false;
        }
    }

    public boolean addKontak(Kontak k)
    {
        try
        {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO contact(Hp, Nama, Kategori) VALUES (?, ?, ?)");

            ps.setString(1, k.getNoHP());
            ps.setString(2, k.getNama());
            ps.setString(3, k.getKategori());

            if (ps.executeUpdate() > 0)
            {
                //Sukses
                cu.err("Data telah tersimpan!", 0);
                ps.close();
                return true;
            }
            else
            {
                //Gagal
                cu.err("Terjadi kesalahan.", 2);
                cu.err("Location: addKontak()");
                return false;
            }
        }
        catch (SQLException ex)
        {
            cu.err("SQLException: " + ex.getMessage(), 2);
            cu.err("Location: addKontak()");
            return false;
        }
    }

    public boolean updateKontak(Kontak k)
    {
        try
        {
            cu.err("Memperbarui Data...");
            PreparedStatement ps = conn.prepareStatement("UPDATE contact SET Nama=?, Kategori=? WHERE Hp=?");

            ps.setString(1, k.getNama());
            ps.setString(2, k.getKategori());
            ps.setString(3, k.getNoHP());

            if (ps.executeUpdate() > 0)
            {
                //Sukses
                cu.err("Data telah tersimpan!", 0);
                ps.close();
                return true;
            }
            else
            {
                //Gagal
                cu.err("Terjadi kesalahan.", 2);
                cu.err("Location: updateKontak()");
                return false;
            }
        }
        catch (SQLException ex)
        {
            cu.err("SQLException: " + ex.getMessage(), 2);
            cu.err("Location: updateKontak()");
            return false;
        }
    }

    public boolean deleteKontak(String NoHP)
    {
        try
        {
            cu.err("Menghapus Data...");
            PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM contact WHERE Hp=?");

            ps.setString(1, NoHP);

            if (ps.executeUpdate() > 0)
            {
                //Sukses
                cu.err("Data telah berhasil terhapus!", 0);
                ps.close();
                return true;
            }
            else
            {
                //Gagal
                cu.err("Terjadi kesalahan.", 2);
                cu.err("Location: deleteKontak()");
                return false;
            }
        }
        catch (SQLException ex)
        {
            cu.err("SQLException: " + ex.getMessage(), 2);
            cu.err("Location: updateKontak()");
            return false;
        }
    }

    public ArrayList<Kontak> selectAllKontak()
    {
        ArrayList<Kontak> lk = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;

        try
        {
            cu.err("Meminta Data...");
            ps = conn.prepareStatement("SELECT * FROM contact");
            rs = ps.executeQuery();

            while (rs.next())
            {
                String Hp = rs.getString(1);
                String Nama = rs.getString(2);
                String Kategori = rs.getString(3);

                Kontak k = new Kontak(Nama, Hp, Kategori);
                lk.add(k);
            }

            rs.close();
            ps.close();
            cu.err("Data Berhasil Didapatkan", 0);
        }
        catch (SQLException ex)
        {
            cu.err("SQLException: " + ex.getMessage(), 2);
            cu.err("Location: selectAllKontak()");
        }
        return lk;
    }
    
    public boolean closeConnection()
    {
        String e = "";
        for (int i = 0; i < cu.ConnectionRetryTimes; i++)
        {
            try
            {
                cu.err("Closing Connection...");
                cu.delay(50);
                conn.close();
                cu.err("Connection Succesfuly Closed!", 5);
                return true;
            }
            catch(SQLException ex)
            {
                cu.err("Failed to close Connection !", 2);
                cu.err("Retrying to close...");
                e = ex.getMessage();
            }
        }
        cu.err("Connection Cannot be Closed ! please handle your data Carefully !", 4);
        cu.err(e);
        return false;
    }
}
