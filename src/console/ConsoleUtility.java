package console;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleUtility
{

    private static String os;

    private boolean log;
    private boolean debug;

    private BufferedWriter out = null;

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

        this.os = checkOS();
    }

    private String checkOS()
    {
        String _os = System.getProperty("os.name");
        String ret_os = "null";

        String[] l_os_unix =
        {
            "Linux", "Solaris", "SunOS", "FreeBSD", "Irix",
            "Digital Unix", "NetWare 4.11", "OSF1", "OpenVMS"
        };

        String[] l_os_macs =
        {
            "Mac OS", "Mac OS X"
        };

        String[] l_os_wint =
        {
            "Windows 95", "Windows 98", "Windows Me", "Windows NT",
            "Windows 2000", "Windows XP", "Windows 2003", "Windows CE"
        };

        for (String i : l_os_unix)
        {
            if (_os.equals(i))
            {
                ret_os = "*nix";
                break;
            }
        }

        for (String i : l_os_macs)
        {
            if (_os.equals(i))
            {
                ret_os = "macs";
                break;
            }
        }

        for (String i : l_os_wint)
        {
            if (_os.equals(i))
            {
                ret_os = "wint";
                break;
            }
        }

        return ret_os;
    }

    public void err(String str, int severity)
    {
        String strseverity;

        switch (severity)
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
                this.out.write("\n" + fstring);
            }
            catch (IOException ex)
            {
                if (this.debug == true)
                {
                    System.err.println("Error Writing Log");
                }
            }
        }

        if (this.debug == true)
        {
            System.err.println(fstring);
        }
    }

    public void err(String str)
    {
        String fstring = "[  Logs  ]" + str + " ";

        if (this.log == false)
        {
            try
            {
                this.out.write("\n" + fstring);
            }
            catch (IOException ex)
            {
                if (this.debug == true)
                {
                    System.err.println("Error Writing Log");
                }
            }
        }

        if (this.debug == true)
        {
            System.err.println(fstring);
        }
    }

    public static void cls()
    {
        if ("*nix".equals(os))
        {
            System.out.print("\033[H\033[2J");
        }
        else if (os.equals("wint"))
        {
            try
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            catch (IOException | InterruptedException ex){}
        }
        else
        {
            try
            {
                Runtime.getRuntime().exec("clear");
            }
            catch (IOException ex){}
        }
        
        System.out.flush();
    }

    public static void pause()
    {
        Scanner s = new Scanner(System.in);

        s.nextLine();
    }
}
