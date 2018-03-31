package console;

import java.util.Scanner;

public class ConsoleApp
{
    private final ConsoleUI cui = new ConsoleUI();
    private final ConsoleUtility cu = new ConsoleUtility(true, true);
    
    public void run()
    {
        System.out.println(this.cu.titleCase("hAnSeN sAmOsIr"));
        System.out.println(this.cu.titleCase("tEmAn"));
        //this.menuUtama();
    }
    
    private boolean menuUtama()
    {
        while(true)
        {
            Scanner scn = new Scanner(System.in);
            System.out.println("------- Main Menu -------");
            System.out.println(" 1. Lihat Kontak");
            System.out.println(" 2. Tambahkan Kontak");
            System.out.println(" 3. Ubah Kontak");
            System.out.println(" 0. Exit");
            System.out.println("");
            System.out.print("Menu >> ");
            System.out.flush();
            switch(scn.nextInt())
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
                case 0:
                    if(this.exitConfirmation() == true)
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
    
    private void ubahKontak()
    {
        
    }
    
    private boolean exitConfirmation()
    {
        return true;
    }
}
