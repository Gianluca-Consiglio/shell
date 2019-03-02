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
public class Folder implements Serializable{
    private String nome;
    private Folder sottocartella;
    private Folder fratello;
    private Folder padre;
    private File file;
    private String data;
    private String ora;
    
    public Folder(String nome, Folder padre){
        this.nome = nome;
        this.padre = padre;
        this.setTime();
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

    public Folder getSottocartella() {
        return sottocartella;
    }

    public void setSottocartella(Folder sottocartella) {
        this.sottocartella = sottocartella;
    }

    public Folder getFratello() {
        return fratello;
    }

    public void setFratello(Folder fratello) {
        this.fratello = fratello;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getNome() {
        return nome;
    }

    public Folder getPadre() {
        return padre;
    }
    
    @Override
    public String toString(){
        return data + " " + ora + '\t' + "<DIR>       " + nome;
    }
    
}
