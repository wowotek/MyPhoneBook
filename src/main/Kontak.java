package main;

import java.sql.*;

public class Kontak
{

    private static final Alat a = new Alat();

    public void lihatKontak()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/phonebook?useSSL=false", "root", "root");
            Statement stm = conn.createStatement();
            String strSelect = "SELECT * from contact";

            a.err("Executing Query: " + strSelect);

            ResultSet rs = stm.executeQuery(strSelect);
            while (rs.next())
            {   // Move the cursor to the next row, return false if no more row
                String nama = rs.getString("Nama");
                String hp = rs.getString("Hp");
                String kat = rs.getString("Kategori");
                System.out.println("Nama            : " + nama);
                System.out.println("Nomor Handphone : " + hp);
                System.out.println("Kategori        : " + kat);
            }

        }
        catch (SQLException ex)
        {
            a.err(ex.getMessage(), "fungsi lihatKontak()");
        }
    }
}
