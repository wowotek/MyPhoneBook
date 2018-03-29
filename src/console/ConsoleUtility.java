package console;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleUtility
{

    private final boolean log = true;
    private final boolean debug = true;
    
    private BufferedWriter out = null;

    public void err(String str, int severity)
    {
        String strseverity;
        
        switch(severity)
        {
            case 1:
                strseverity = "Caution ";
            case 2:
                strseverity = "Warning ";
            case 3:
                strseverity = " Severe ";
            case 4:
                strseverity = "Critical";
            default:
                strseverity = "  Logs  ";
        }
        
        String fstring = "[" + strseverity + "]" + str + " ";
        
        if (this.log == false)
        {

        }
        else
        {

        }
        
        if (this.debug == true)
        {
            System.err.println(fstring);
        }
    }
    
    ConsoleUtility()
    {
        try
        {
            FileWriter fs = new FileWriter("out.txt", true); //true tells to append data.
            out = new BufferedWriter(fs);
        }
        catch (IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }
}
