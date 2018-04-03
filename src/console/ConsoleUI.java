package console;

import db.DBHandler;
import db.Kontak;
import java.util.ArrayList;

import java.util.Scanner;

public class ConsoleUI
{

    private DBHandler dbh;
    private final ConsoleUtility cu = new ConsoleUtility(true, true);
    
    ConsoleUI(DBHandler dbh)
    {
        this.dbh = dbh;
    }

    //------------------------- Lihat Kontak -----------------------------------
    private void printKontak()
    {
        ArrayList<Kontak> k = this.dbh.selectAllKontak();

        this.cu.cls();
        System.out.println("------------- SEMUA KONTAK -------------");
        System.out.println("");
        for (Kontak i : k)
        {
            System.out.println("    <---------->");
            System.out.println("     Nama     : " + i.getNama());
            System.out.println("     No HP    : " + cu.parseHP(i.getNoHP()));
            System.out.println("     Kategori : " + i.getKategori());
            System.out.println("    <---------->");
            System.out.println("");

            this.cu.delay(10);
        }
        this.cu.delay(10);
        System.out.println("  total kontak : " + k.size());
        System.out.println("----------------------------------------");
        this.cu.delay(10);
        this.cu.pause();
        System.out.println("");
    }

    private void printKontak(String Kategori)
    {
        ArrayList<Kontak> k = this.dbh.selectAllKontak();

        this.cu.cls();
        System.out.println("------------- " + Kategori + " -------------");
        System.out.println("");
        int x = 0;
        for (Kontak i : k)
        {
            if (i.getKategori().equals(Kategori))
            {
                System.out.println("    <---------->");
                System.out.println("     Nama     : " + i.getNama());
                System.out.println("     No HP    : " + cu.parseHP(i.getNoHP()));
                System.out.println("     Kategori : " + i.getKategori());
                System.out.println("    <---------->");
                System.out.println("");

                this.cu.delay(10);
                x++;
            }
        }
        this.cu.delay(10);
        System.out.println("  total kontak " + Kategori + " : " + x);
        System.out.println("----------------------------------------");
        this.cu.delay(10);
        this.cu.pause();
        System.out.println("");
    }

    private String inputKategori()
    {
        Scanner s = new Scanner(System.in);
        ArrayList<Kontak> kontak = this.dbh.selectAllKontak();
        ArrayList<String> _kat = new ArrayList<>();

        for (Kontak k : kontak)
        {
            _kat.add(k.getKategori());
        }

        ArrayList<String> kat = cu.removeDuplicates(_kat);
        int i, is;

        System.out.println("------- Pilih Kategori -------");
        for (i = 0; i < kat.size(); i++)
        {
            System.out.println("    " + (i + 1) + ". " + kat.get(i));
        }
        System.out.println("    " + (kat.size() + 1) + ". Semua");
        System.out.println("    0. Kembali");
        while (true)
        {
            System.out.print("Pilih Kategori >> ");
            is = s.nextInt();
            System.out.flush();

            if (is == 0)
            {
                return "b";
            }

            if (is == kat.size() + 1)
            {
                return "Semua";
            }
            else if (is < 0 || is > kat.size() + 1)
            {
                System.out.println("    Kategori Tidak Ditemukan!");
                System.out.println("    Pastikan Pilihan Anda !");
            }
            else
            {
                return (String) (kat.get(is - 1));
            }
        }
    }

    public void lihatKontakUI()
    {
        while (true)
        {
            String ik = this.inputKategori();
            if (ik.equals("Semua"))
            {
                this.printKontak();
            }
            else if (ik.equals("b"))
            {
                System.out.println("");
                System.out.flush();
                return;
            }
            else
            {
                this.printKontak(ik);
            }
        }
    }
    //--------------------------- Selesai --------------------------------------

    public void tambahKontakUI()
    {
        Scanner scn = new Scanner(System.in);

        while (true)
        {
            String nama = "";
            String hp = "";
            String kat = "";

            System.out.flush();
            System.out.println("------------ Tambah Kontak ------------");
            System.out.println("                           c = karakter");
            System.out.println("    Silahkan Isi Data Kontak: ");
            System.out.print("            Nama (45 c) : ");
            nama = this.cu.titleCase(scn.nextLine());
            System.out.flush();

            System.out.print("          No. HP (12 c) : ");
            hp = scn.nextLine();
            System.out.flush();

            System.out.print("        Kategori (20 c) : ");
            kat = this.cu.titleCase(scn.nextLine());
            System.out.flush();

            this.cu.delay(100);

            //Filter Out the Input
            if (nama.equals("") || hp.equals("") || kat.equals(""))
            {
                System.out.println("Nama/HP/Kategori Tidak Boleh kosong");
                continue;
            }

            if (nama.length() > 45 || hp.length() > 12 || kat.length() > 25)
            {
                System.out.println("Nama/HP/Kategori Melebihi Batas Maksimal !");
                continue;
            }

            System.out.println("Informasi Kontak :");
            System.out.println("    Nama     : " + nama);
            System.out.println("    No HP    : " + cu.parseHP(hp));
            System.out.println("    Kategori : " + kat);
            System.out.print("Konfirmasi ? (y/n/b(batal)) ");
            System.out.flush();

            switch (scn.nextLine())
            {
                case "1":
                case "Y":
                case "y":
                case "yes":
                    Kontak k = new Kontak(nama, hp, kat);

                    if (this.dbh.addKontak(k) == true)
                    {
                        System.out.println("Data Berhasil Ditambahkan !");
                        return;
                    }
                    else
                    {
                        System.out.println("Terjadi Kesalahan Saat Menambahkan Data!");
                        return;
                    }
                case "2":
                case "n":
                case "N":
                case "no":
                    break;
                case "0":
                case "b":
                    return;
            }
        }
    }

    public boolean ubahKontak()
    {
        while (true)
        {
            Scanner s = new Scanner(System.in);
            Kontak ki = null;
            Kontak kp;

            String hpi, namai, kati;

            //get data
            ArrayList<Kontak> k = this.dbh.selectAllKontak();

            System.out.println("------------ Ubah Kontak ------------");
            System.out.print("    No HP               : ");
            hpi = s.nextLine();
            System.out.flush();

            //Filter the Input
            for (Kontak i : k)
            {
                if (i.getNoHP().equals(hpi))
                {
                    ki = i;
                    break;
                }
            }

            if (ki == null)
            {
                System.out.println("Kontak yang akan anda rubah tidak ditemukan !");
                continue;
            }

            System.out.println("    Nama Sebelumnya     : " + ki.getNama());
            System.out.print("           Ubah Menjadi : ");
            namai = this.cu.titleCase(s.nextLine());
            System.out.flush();
            System.out.println("    Kategori Sebelumnya : " + ki.getKategori());
            System.out.print("           Ubah Menjadi : ");
            kati = this.cu.titleCase(s.nextLine());
            System.out.flush();

            //print konfirmasi
            System.out.println("------------ Konfirmasi Ubah ------------");
            System.out.println("                  No HP : " + cu.parseHP(ki.getNoHP()));
            System.out.println("    Nama Sebelumnya     : " + ki.getNama());
            System.out.println("         Setelah Diubah : " + namai);
            System.out.println("    Kategori Sebelumnya : " + ki.getKategori());
            System.out.println("         Setelah Diubah : " + kati);
            System.out.print("Konfirmasi ? (y/n/b(batal)) ");
            System.out.flush();

            switch (s.nextLine())
            {
                case "1":
                case "Y":
                case "y":
                case "yes":
                    kp = new Kontak(namai, hpi, kati);

                    if (this.dbh.updateKontak(kp) == true)
                    {
                        System.out.println("Kontak Berhasil Diubah !");
                        return true;
                    }
                    else
                    {
                        System.out.println("Terjadi Kesalahan Saat Mengubah Kontak!");
                        return false;
                    }
                case "2":
                case "n":
                case "N":
                case "no":
                    break;
                case "0":
                case "b":
                    return false;
            }
        }
    }

    public void hapusKontak()
    {
        while (true)
        {
            Scanner s = new Scanner(System.in);
            Kontak ki = null;
            Kontak kp;

            String hpi, namai, kati;

            //get data
            ArrayList<Kontak> k = this.dbh.selectAllKontak();

            System.out.println("------------ Hapus Kontak ------------");
            System.out.print("    No HP : ");
            hpi = s.nextLine();
            System.out.flush();

            //Filter the Input
            for (Kontak i : k)
            {
                if (i.getNoHP().equals(hpi))
                {
                    ki = i;
                    break;
                }
            }

            if (ki == null)
            {
                System.out.println("Kontak yang akan anda hapus tidak ditemukan !");
                continue;
            }

            //print konfirmasi
            System.out.println("------------ Konfirmasi Hapus ------------");
            System.out.println("                  No HP : " + cu.parseHP(ki.getNoHP()));
            System.out.println("                   Nama : " + ki.getNama());
            System.out.println("               Kategori : " + ki.getKategori());
            System.out.print("Konfirmasi ? (y/n/b(batal)) ");
            System.out.flush();

            switch (s.nextLine())
            {
                case "1":
                case "Y":
                case "y":
                case "yes":
                    if (this.dbh.deleteKontak(ki.getNoHP()) == true)
                    {
                        System.out.println("Kontak Berhasil Dihapus !");
                        return;
                    }
                    else
                    {
                        System.out.println("Terjadi Kesalahan Saat Menghapus Kontak!");
                        return;
                    }
                case "2":
                case "n":
                case "N":
                case "no":
                    break;
                case "0":
                case "b":
                    return;
            }
        }
    }
}
