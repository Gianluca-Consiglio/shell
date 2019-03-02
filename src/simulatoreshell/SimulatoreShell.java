/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatoreshell;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author 70669130
 */
public class SimulatoreShell {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GestoreComandi gestore = new GestoreComandi();
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            System.out.print(gestore.getPercorso());
            String ret = gestore.lancia(keyboard.nextLine());
            exit = ret.equals("exit");
            if(!ret.equalsIgnoreCase("ok") && !ret.equals("") && !ret.equals("exit"))
                System.out.println(ret);
        }

        
    }
    
}
