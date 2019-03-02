/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatoreshell;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author 70669130
 */
public class File  implements Serializable{
    private String nome;
    private String type;
    private File fratello;
    private Folder padre;
    private String data;
    private String ora;
    private String dimensione;
    
    public File(String nome, Folder padre){
        this.nome = nome;
        type = "emp";
        fratello = null;
        this.setTime();
        dimensione = Integer.toString((int) (Math.random() * 100000));
        this.padre = padre;
    }
    
    private void setTime(){
        GregorianCalendar calendario = new GregorianCalendar();
        data = Integer.toString(calendario.get(Calendar.DAY_OF_MONTH)) + "/";
        if(calendario.get(Calendar.MONTH) < 9)
            data += '0';
        data += Integer.toString(calendario.get(Calendar.MONTH)+1) + "/" + Integer.toString(calendario.get(Calendar.YEAR));
        int hh = calendario.get(Calendar.HOUR);
        if(calendario.get(Calendar.AM_PM) == 1)
            hh += 12;
        if(hh < 10)
            ora = "0" + hh + ":";
        else
            ora = "" + hh + ":";
        int mm = calendario.get(Calendar.MINUTE);
        if(mm < 10)
            ora += "0" + mm;
        else
            ora += "" + mm;
    }
    
    public File(String nome, String type, Folder padre){
        this.nome = nome;
        this.type = type;
        fratello = null;
        this.setTime();
        dimensione = Integer.toString((int)(Math.random()*100000));
        this.padre = padre;
    }

    public Folder getPadre() {
        return padre;
    }

    public void setPadre(Folder padre) {
        this.padre = padre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public File getFratello() {
        return fratello;
    }

    public void setFratello(File fratello) {
        this.fratello = fratello;
    }
    @Override
    public String toString(){
        String s = data + " " + ora + '\t';
        int spazzi = 11 - dimensione.length();
        for(int i = 0; i < spazzi; i++)
            s += ' ';
        s += dimensione + ' ';
        s += nome + '.' + type;
        return s;
    }
    
}
