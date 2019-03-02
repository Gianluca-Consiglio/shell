/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatoreshell;

/**
 *
 * @author Gian
 */
public class Percorso {
    private String[] percorso = new String[100];
    private int nodi;
    
    public Percorso(String root){
        percorso[0] = root;
        nodi = 1;
    }
    
    public void add(String folder){
        percorso[nodi] = folder;
        nodi++;
    }
    
    public void rmv(){
        percorso[nodi] = null;
        nodi--;
    }
    
    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < nodi; i++)
            s += "" + percorso[i] + "\\";
        return s.substring(0,s.length()-1) + ">";
    }
}
