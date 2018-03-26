package main;

import java.io.*;
import java.util.Scanner;

public class Alat
{
    public void err(String error_text, String location)
    {
        System.err.println("   [Console] "+ error_text + "| Location: " + location);
    }
    
    public void err(String error_text)
    {
        System.err.println("   [Console] "+ error_text);
    }
    
    // saya menggunakan cls dan pause seperti ini karena bisa digunakan di linux's Terminal/Bash dan Window's CMD
    public void cls()
    {
        System.out.flush();
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }
    
    public void pause()
    {
        Scanner s = new Scanner(System.in);
        System.out.flush();
        s.nextLine();
    }
}
