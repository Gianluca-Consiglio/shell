/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatoreshell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 70669130
 */
public class Net {
    private Folder root;
    private Posizionatore posizionatore;
    
    public Net(){
        root = new Folder("c:", null);
        try {
            carica();
        } catch (IOException ex) {
            System.err.println("errore");;
        }
        posizionatore = new Posizionatore(root);
    }

    public Posizionatore getPosizionatore() {
        return posizionatore;
    }
    
    public String help(){
        String s = "";
        try {
            Scanner inFile = new Scanner(new FileReader("help.txt"));
            while(inFile.hasNext())
                s+= inFile.nextLine() + '\n'; 
        } catch (FileNotFoundException ex) {
        }
        return s;
    }
    
    public void salva() throws java.io.IOException{
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("fileSys.bin"));
        stream.writeObject(root);
        stream.close();
    }
    
  
    public void carica() throws FileNotFoundException, IOException{
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream("fileSys.bin"));
        try {
            root = (Folder)stream.readObject();
            //System.out.println(root.getSottocartella().getFratello());
            //System.out.println(root.getSottocartella().getFratello().getFratello());
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Net.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stream.close();
        
    }
    

    

    
    
}
