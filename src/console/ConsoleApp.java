package console;

import db.DBHandler;
import java.util.Scanner;

public class ConsoleApp
{

    private ConsoleUI cui;
    private ConsoleUtility cu;
    private DBHandler cadb;

    public ConsoleApp(DBHandler db, boolean log, boolean debug)
    {
        cui = new ConsoleUI(db);
        cu = new ConsoleUtility(log, debug);
        cadb = db;
    }

    public void run()
    {
        this.menuUtama();
    }

    private boolean menuUtama()
    {
        while (true)
        {
            Scanner scn = new Scanner(System.in);
            System.out.println("------- Main Menu -------");
            System.out.println(" 1. Lihat Kontak");
            System.out.println(" 2. Tambahkan Kontak");
            System.out.println(" 3. Ubah Kontak");
            System.out.println(" 4. Hapus Kontak");
            System.out.println(" 0. Exit");
            System.out.println("");
            System.out.print("Menu >> ");
            System.out.flush();
            switch (scn.nextInt())
            {
                case 1:
                    cui.lihatKontakUI();
                    break;
                case 2:
                    cui.tambahKontakUI();
                    break;
                case 3:
                    cui.ubahKontak();
                    break;
                case 4:
                    cui.hapusKontak();
                    break;
                case 0:
                    if (this.exitConfirmation() == true)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
            }
        }
    }
    
        private boolean exitConfirmation()
    {
        if (this.cadb.closeConnection() == true)
        {
            return true;
        } else
        {
            return false;
        }
    }
}
