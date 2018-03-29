package console;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ConsoleUtility
{

    private boolean log;
    private boolean debug;
    
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
            try
            {
                this.out.write("\n"+fstring);
            }
            catch (IOException ex)
            {
                if(this.debug == true)
                    System.err.println("Error Writing Log");
            }
        }
        
        if (this.debug == true)
            System.err.println(fstring);
    }
    
    public void err(String str)
    {
        String fstring = "[  Logs  ]" + str + " ";
        
        if (this.log == false)
        {
            try
            {
                this.out.write("\n"+fstring);
            }
            catch (IOException ex)
            {
                if(this.debug == true)
                    System.err.println("Error Writing Log");
            }
        }
        
        if (this.debug == true)
            System.err.println(fstring);
    }
    
    ConsoleUtility(boolean log, boolean debug)
    {
        this.log = log;
        this.debug = debug;
        
        try
        {
            FileWriter fw = new FileWriter("out.txt", true); //true tells to append data.
            this.out = new BufferedWriter(fw);
        }
        catch (IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
