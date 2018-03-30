
import java.sql.SQLException;
import java.util.ArrayList;


public class Tester {
    public static void main(String[] args) {
        MyConnection connection = new MyConnection();
        //Mahasiswa mahasiswaBaru = new Mahasiswa("672017002", "Raisa");
        //connection.insertMahasiswa(mahasiswaBaru);
        //connection.updateMahasiswa(mahasiswaBaru);
        //connection.deleteMahasiswa("672017001");
        ArrayList<Mahasiswa> allMahasiswa = connection.selectAllMahasiswa();
        for (Mahasiswa mhs: allMahasiswa) {
            System.out.println("Nama: "+mhs.getNama());
            System.out.println("NIM: "+mhs.getNIM());
            System.out.println("");
        }
        
        try {
            connection.getConnection().close();
        } catch (SQLException ex) {
            System.out.println("SQLException: "+ex.getMessage());
        }
    }
}
