package console;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleUtility
{

    public String MyPhoneBookVersion = "0.1.20";
    public int ConnectionRetryTimes = 3;

    private boolean log;
    private boolean debug;

    private BufferedWriter out = null;

    public String getDevInfo()
    {
        String Dev1 = "Anderson";
        String Dev2 = "Aurelia Gabriele";
        String Dev3 = "Erlangga Ibrahim";

        String WD1 = "GUI Design, Documentation, Give Us lot of Coffee !";
        String WD2 = "Console Design & Dev, Give Us lot of Food and Love :)";
        String WD3 = "GUI Design & Dev, Console Dev, SQL";

        String fin
                = Dev1 + "\n  - " + WD1 + "\n"
                + Dev2 + "\n  - " + WD2 + "\n"
                + Dev3 + "\n  - " + WD3 + "\n\n"
                + "GUI Version : " + this.MyPhoneBookVersion;

        return fin;
    }

    public ConsoleUtility(boolean log, boolean debug)
    {
        this.log = log;
        this.debug = debug;

        try
        {
            FileWriter fw = new FileWriter("out.txt", true); //true tells to append data.
            this.out = new BufferedWriter(fw);
        } catch (IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void err(String str, int severity)
    {
        String strseverity;

        switch (severity)
        {
            case 0:
                strseverity = "  Pass  ";
                break;
            case 1:
                strseverity = "Caution ";
                break;
            case 2:
                strseverity = "Warning ";
                break;
            case 3:
                strseverity = " Severe ";
                break;
            case 4:
                strseverity = "Critical";
                break;
            default:
                strseverity = "  Logs  ";
                break;
        }

        String fstring = "[" + strseverity + "]" + str + " ";

        if (this.log == true)
        {
            try
            {
                this.out.write("\n" + fstring);
            } catch (IOException ex)
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

        if (this.log == true)
        {
            try
            {
                this.out.write("\n" + fstring);
            } catch (IOException ex)
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

    public void cls()
    {
        try
        {
            if (System.getProperty("os.name").contains("Windows"))
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else //untuk linux, ga sempurna
            {
                System.out.print("\033[2J\033[1;1H");
                System.out.print('\u000C');
                System.out.print("\u001b[2J");
                Runtime.getRuntime().exec("clear");
                System.out.println("\f");
            }
        } catch (IOException | InterruptedException ex)
        {
        }

        System.out.flush();
    }

    public String titleCase(String input_string)
    {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input_string.toLowerCase().toCharArray())
        {
            if (Character.isSpaceChar(c))
            {
                nextTitleCase = true;
            } else if (nextTitleCase)
            {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

    public ArrayList<String> removeDuplicates(ArrayList<String> list)
    {

        // Store unique items in result.
        ArrayList<String> result = new ArrayList<>();

        // Record encountered Strings in HashSet.
        HashSet<String> set = new HashSet<>();

        // Loop over argument list.
        for (String item : list)
        {

            // If String is not in set, add it to the list an0d the set.
            if (!set.contains(item))
            {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }

    public void delay()
    {
        try
        {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex)
        {
        }
    }

    public void delay(int Multiplier)
    {
        try
        {
            TimeUnit.MILLISECONDS.sleep(Multiplier * 10);
        } catch (InterruptedException ex)
        {
        }
    }

    public String parseHP(String HP)
    {
        char[] hp = HP.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < hp.length; i++)
        {
            sb.append(hp[i]);
            if ((i + 1) % 3 == 0 && i < hp.length - 1)
            {
                sb.append("-");
            }
        }

        return sb.toString();
    }

    public void pause()
    {
        Scanner s = new Scanner(System.in);

        System.out.println("Tekan Enter Untuk Melanjukan");
        s.nextLine();
    }
}
