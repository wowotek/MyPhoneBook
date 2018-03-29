package db;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main
{
    private static void runGui()
    {
        final gui.FrontMain fm = new gui.FrontMain();
        
        SwingUtilities.invokeLater(new Thread (){
            public void run() {
                fm.setVisible(true);
            }
        });
    }
    public static void main(String[] args)
    {
        switch(args[0])
        {
            case "-gui":
                runGui();
        }
    }
}
