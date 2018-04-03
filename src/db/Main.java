package db;

public class Main
{

    private static DBHandler db;
    private static console.ConsoleUtility cu = new console.ConsoleUtility(true, true);

    public static void main(String[] args)
    {
        boolean log = false;
        boolean debug = false;
        
        if(args.length < 2)
        {
            log = false;
            debug = false;
        }

        try
        {
            switch (args[2])
            {
                case "-d":
                case "--debug":
                    debug = true;
                    break;
                case "-l":
                case "--log":
                    log = true;
                    break;
                default:
                    log = false;
                    debug = false;
                    break;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            log = false;
            debug = false;
        }

        try
        {

            switch (args[1])
            {
                case "-d":
                case "--debug":
                    debug = true;
                    break;
                case "-l":
                case "--log":
                    log = true;
                    break;
                default:
                    log = false;
                    debug = false;
                    break;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            log = false;
            debug = false;
        }

        db = new DBHandler(log, debug);
        
        try
        {

            switch (args[0])
            {
                case "-g":
                case "--gui":
                    cu.err("Running GUI with :\n"
                            + "    -log   = " + log + "\n"
                            + "    -debug = " + debug, 2);
                    runGui(log, debug);
                    break;
                case "-c":
                case "--console":
                    cu.err("Running Console with :\n"
                            + "    -log   = " + log + "\n"
                            + "    -debug = " + debug, 2);
                    runConsole(log, debug);
                    break;
                case "-h":
                case "--help":
                default:
                    printHelp();
                    break;
            }
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            cu.err("Arguments is not exist... printing Help... ", 2);
            printHelp();
        }
    }

    private static void printHelp()
    {
        System.out.println("Usage : ");
        System.out.println("     MyPhoneBook.jar -g");
        System.out.println("     MyPhoneBook.jar --gui");
        System.out.println("   - Using GUI Version of This Application\n");

        System.out.println("     MyPhoneBook.jar -c");
        System.out.println("     MyPhoneBook.jar --console");
        System.out.println("   - Using Console Version of This Application\n");

        System.out.println("     MyPhoneBook.jar -h");
        System.out.println("     MyPhoneBook.jar --help");
        System.out.println("   - Print This Information\n");
    }

    private static void runGui(boolean l, boolean d)
    {
        
        final gui.FrontMain fm = new gui.FrontMain(db, l, d);
        fm.setVisible(true);
//        
//        SwingUtilities.invokeLater(new Thread (){
//            public void run() {
//                fm.setVisible(true);
//            }
//        });
    }

    private static void runConsole(boolean l, boolean d)
    {
        final console.ConsoleApp ca = new console.ConsoleApp(db, l, d);
        ca.run();
    }
}
