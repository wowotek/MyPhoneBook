package console;

import db.DBHandler;
import db.Kontak;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import java.util.Scanner;

public class ConsoleUI
{

    private final DBHandler dbh = new DBHandler();
    private final ConsoleUtility cu = new ConsoleUtility(true, true);

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
            System.out.println("     No HP    : " + i.getNoHP());
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
                System.out.println("     No HP    : " + i.getNoHP());
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
        while (true)
        {
            System.out.print("Pilih Kategori >> ");
            is = s.nextInt();
            System.out.flush();
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
        String ik = this.inputKategori();
        if (ik.equals("Semua"))
        {
            this.printKontak();
        }
        else
        {
            this.printKontak(ik);
        }
    }
    //--------------------------- Selesai --------------------------------------

    private void tambahKontakUI()
    {
        Scanner scn = new Scanner(System.in);

        while (true)
        {
            String nama, hp, kat;

            System.out.flush();
            System.out.println("------------ Tambah Kontak ------------");
            System.out.println("    Silahkan Isi Data Kontak: ");
            System.out.print("        Nama     : ");
            nama = this.cu.titleCase(scn.nextLine());
            System.out.flush();

            System.out.print("        No HP    : ");
            hp = scn.nextLine();
            System.out.flush();

            System.out.print("        Kategori : ");
            kat = this.cu.titleCase(scn.nextLine());
            System.out.flush();

            this.cu.delay(100);
            System.out.println("Informasi Kontak :");
            System.out.println("    Nama     : " + nama);
            System.out.println("    No HP    : " + hp);
            System.out.println("    Kategori : " + kat);
            System.out.print("Konfirmasi ? (y/n)");
            System.out.flush();
            
            switch(scn.nextLine())
            {
                case "1":
                case "Y":
                case "y":
                case "yes":
                    addthisk();
                    return;
                case "0":
                case "2":
                case "n":
                case "N":
                case "no":
                    break;
            }
        }
    }
    
    private boolean addthisk()
    {
        return true;
    }
}
