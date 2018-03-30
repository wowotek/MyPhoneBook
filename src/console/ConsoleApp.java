package console;

import java.util.Scanner;

public class ConsoleApp
{
    public void run()
    {
        
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
            System.out.print("Menu >>");
            System.out.flush();
            switch(scn.nextInt())
            {
                case 1:
                    this.lihatKontakUI();
                    break;
                case 2:
                    this.tambahKontakUI();
                    break;
                case 3:
                    this.ubahKontakUI();
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
    
    private void lihatKontakUI()
    {
        
    }
    
    private void tambahKontakUI()
    {
        
    }
    
    private void ubahKontakUI()
    {
        
    }
    
    private boolean exitConfirmation()
    {
        return true;
    }
}
