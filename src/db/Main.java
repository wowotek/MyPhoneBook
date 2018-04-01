package db;

import console.ConsoleApp;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main
{
    private static ConsoleApp ca;
    private static DBHandler db = new DBHandler();
    
    public static void main(String[] args)
    {      
        //ca = new ConsoleApp();
        
        //runConsole();
        runGui();
        
//        switch(args[0])
//        {
//            case "-g":
//            case "--gui":
//                runGui();
//                break;
//            case "-c":
//            case "--console":
//                runConsole();
//                break;
//        }
    }
    
    private static void runGui()
    {
        final gui.FrontMain fm = new gui.FrontMain(db);
        fm.setVisible(true);
//        
//        SwingUtilities.invokeLater(new Thread (){
//            public void run() {
//                fm.setVisible(true);
//            }
//        });
    }
    
    private static void runConsole()
    {
        ca.run();
    }
}
