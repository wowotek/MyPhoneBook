package db;

public class Main
{

    private static DBHandler db = new DBHandler();
    private static console.ConsoleUtility cu = new console.ConsoleUtility(false, false);

    public static void main(String[] args)
    {
        try
        {
            switch (args[0])
            {
                case "-g":
                case "--gui":
                    runGui();
                    break;
                case "-c":
                case "--console":
                    runConsole();
                    break;
                case "-h":
                case "--help":
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
        final console.ConsoleApp ca = new console.ConsoleApp(db);
        ca.run();
    }
}
