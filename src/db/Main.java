package db;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main
{
    public static void main(String[] args)
    {      
        switch(args[0])
        {
            case "-g":
            case "--gui":
                runGui();
                break;
            case "-c":
            case "--console":
                runConsole();
                break;
        }
    }
    
    private static void runGui()
    {
        final gui.FrontMain fm = new gui.FrontMain();
        
        SwingUtilities.invokeLater(new Thread (){
            public void run() {
                fm.setVisible(true);
            }
        });
    }
    
    private static void runConsole()
    {
        
    }
}
