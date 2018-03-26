package main;

import java.util.*;
import java.sql.*;

public class MenuKontak
{
    private static Alat a = new Alat();
    private static Kontak k = new Kontak();
    
    public void menuUtama()
    {
        while(true)
        {
            Scanner scn = new Scanner(System.in);
            System.out.println("--------- Menu Utama ---------");
            System.out.println("1. Lihat Semua Kontak");
            System.out.println("2. Tambah Kontak");
            System.out.println("3. Edit Kontak");
            System.out.println("4. Hapus Kontak");
            System.out.println("5. Exit");
            System.out.print("Pilih Menu: ");

            switch(scn.nextInt())
            {
                case 1:
                    a.cls();
                    k.lihatKontak();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    if(_ExitCeremonies() == true)
                    {
                        return;
                    }
                    else
                    {
                        break;
                    }
            }
        }
    }
    
    private boolean _ExitCeremonies()
    {
        System.exit(0);
        return true;
    }
}