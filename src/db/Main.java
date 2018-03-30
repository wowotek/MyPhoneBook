package db;

import console.ConsoleApp;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main
{
    private static final ConsoleApp ca = new ConsoleApp();
    
    public static void main(String[] args)
    {      
        runConsole();
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
        final gui.FrontMain fm = new gui.FrontMain();
        
        SwingUtilities.invokeLater(new Thread (){
            public void run() {
                fm.setVisible(true);
            }
        });
    }
    
    private static void runConsole()
    {
        ca.run();
    }
}
