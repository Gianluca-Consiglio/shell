/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatoreshell;

import com.sun.org.apache.xpath.internal.operations.Equals;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gian
 */
public class GestoreComandi {

    private Net fileSys = new Net();
    private Posizionatore pos;

    public GestoreComandi() {
        /*try {
            fileSys.carica();
        } catch (IOException ex) {
            
        }*/
        pos = fileSys.getPosizionatore();

    }

    public String getPercorso() {
        return pos.getPercorso();
    }

    public String lancia(String s) {
        if(s.equals(""))
            return"";
        String[] comando = s.split(" ");
        if (comando.length == 0) {
            return "Comando non riconosciuto";
        }
        comando[0] = comando[0].toLowerCase();
        if (comando.length == 1) {
            switch (comando[0]) {
                case "dir":
                    return pos.dir();
                case "tree":
                    return pos.tree();
                case "cd..":
                    if (pos.cd("..")) {
                        return "ok";
                    }
                    return "Impossibile eseguire il comando";
                case "cls":
                    for(int i = 0; i < 30; i++)
                        System.out.println("");
                    return "ok";
                case "cd":
                    return pos.getPercorso();
                case "help":
                    return fileSys.help();
                case "exit":
                    try {
                        fileSys.salva();
                    } catch (IOException ex) { 
                        System.out.println("Errore di caricamento");
                    }
                    return "exit";
            }
        }
        if (comando.length == 2) {
            switch (comando[0]) {
                case "cd":
                    if (pos.cd(comando[1])) {
                        return "ok";
                    }
                    return "Impossibile eseguire il comando";
                case "mkdir":
                    if (pos.mkdir(comando[1])) {
                        return "ok";
                    }
                    return "Impossibile eseguire il comando";
                case "touch":
                    String[] t = comando[1].split("[.]");
                    if (t.length == 1) {
                        pos.touch(t[0]);
                        return "ok";
                    }
                    if (t.length == 2) {
                        pos.touch(t[0], t[1]);
                        return "ok";
                    }
                    return "Impossibile eseguire il comando";
                case "rd":
                    if (pos.rd(comando[1])) {
                        return "ok";
                    }
                    return "Cartella non trovata";
                case "del":
                    if (pos.del(comando[1])) {
                        return "ok";
                    }
                    return "File non trovato";
            }
        }
        if (comando.length == 3) {
            switch (comando[0]) {
                case "ren":
                    String[] temp = comando[1].split(".");
                    if (temp.length != 2) {
                        return "Impossibile eseguire il comando";
                    }
                    if (pos.ren(temp[0], temp[1], comando[2])) {
                        return "ok";
                    }
                    return "Impossibile rinominare il file";
            }
        }
        return "Comando non riconosciuto";
    }
}
