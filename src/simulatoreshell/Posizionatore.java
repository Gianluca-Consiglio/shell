/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatoreshell;

/**
 *
 * @author 70669130
 */
public class Posizionatore {

    private Folder attuale;
    private Percorso percorso;

    public Posizionatore(Folder root) {
        this.attuale = root;
        percorso = new Percorso(root.getNome());
    }

    public String getPercorso() {
        return percorso.toString();
    }

    public boolean mkdir(String nome) {
        if (nome == null) {
            return false;
        }
        if (nome.equals("")) {
            return false;
        }
        if (attuale.getSottocartella() == null) {
            attuale.setSottocartella(new Folder(nome, attuale));
            return true;
        }
        Folder temp = attuale.getSottocartella();
        if (temp.getNome().equals(nome)) {
            return false;
        }
        while (temp.getFratello() != null) {
            temp = temp.getFratello();
            if (temp.getNome().equals(nome)) {
                return false;
            }
        }
        temp.setFratello(new Folder(nome, attuale));
        return true;
    }

    public void touch(String nome) {
        if (attuale.getFile() == null) {
            attuale.setFile(new File(nome, attuale));
            return;
        }
        File temp = attuale.getFile();
        while (temp.getFratello() != null) {
            temp = temp.getFratello();
        }
        temp.setFratello(new File(nome, attuale));
    }

    public void touch(String nome, String type) {
        if (attuale.getFile() == null) {
            attuale.setFile(new File(nome, type, attuale));
            return;
        }
        File temp = attuale.getFile();
        while (temp.getFratello() != null) {
            temp = temp.getFratello();
        }
        temp.setFratello(new File(nome, type, attuale));
    }

    private boolean cdPrivate(String nome) {
        if (nome == null) {
            return false;
        }
        if (nome.equals("..")) {
            if (attuale.getPadre() != null) {
                attuale = attuale.getPadre();
                percorso.rmv();
            }
            return true;
        }
        Folder temp = attuale.getSottocartella();
        while (temp != null) {
            if (temp.getNome().equals(nome)) {
                attuale = temp;
                percorso.add(temp.getNome());
                return true;
            }
            temp = temp.getFratello();
        }
        return false;
    }
    
    
    
    public boolean cd(String nome) {
        nome = nome.replace("\\", " ");
        String[] t = nome.split(" ");
        if (t.length == 1) {
            return cdPrivate(nome);
        }
        for (int i = 0; i < t.length; i++) {
            if (!cdPrivate(t[i])) {
                for(int j = i; j > 0; j--)
                    this.cd("..");
                return false;
            }
        }
        return true;
    }

    public boolean rd(String nome) {
        Folder temp = attuale.getSottocartella();
        if (temp == null) {
            return false;
        }
        if (temp.getNome().equals(nome)) {
            attuale.setSottocartella(temp.getFratello());
            return true;
        }
        while (temp.getFratello() != null) {
            if (temp.getFratello().getNome().equals(nome)) {
                temp.setFratello(temp.getFratello().getFratello());
                return true;
            }
            temp = temp.getFratello();
        }
        return false;
    }

    public boolean del(String nome) {
        if (attuale.getFile() == null) {
            return false;
        }

        if (nome.contains("*")) {

            String t = nome.substring(1);
            if (nome.contains(".")) {
                t = t.substring(1);
            }

            System.out.println(t);
            File f = attuale.getFile();
            if (f == null) {
                return false;
            }
            while (f.getFratello() != null) {
                if (f.getFratello().getType().equals(t) || f.getFratello().getNome().contains(t)) {
                    f.setFratello(f.getFratello().getFratello());
                }
                f = f.getFratello();
            }
            if (attuale.getFile().getType().equals(t) || attuale.getFile().getNome().contains(t)) {
                attuale.setFile(attuale.getFile().getFratello());
            }
            return true;
        }

        //rimozione di file
        String[] s;
        if (nome.contains(".")) {
            s = nome.split("[.]");
        } else {
            s = new String[2];
            s[0] = nome;
            s[1] = "emp";
        }
        if (attuale.getFile().getNome().equals(s[0]) && attuale.getFile().getType().equals(s[1])) {
            attuale.setFile(attuale.getFile().getFratello());
            return true;
        }
        File f = attuale.getFile();
        while (f.getFratello() != null) {
            if (f.getFratello().getNome().equals(s[0]) && f.getFratello().getType().equals(s[1])) {
                f.setFratello(f.getFratello().getFratello());
                return true;
            }
            f = f.getFratello();
        }
        return false;
    }

    public String dir() {
        String s = "";
        int file = 0, cartelle = 0;
        Folder temp = attuale.getSottocartella();
        if (temp != null) {
            s += "" + temp.toString() + '\n';
            cartelle++;
            temp = temp.getFratello();
            while (temp != null) {
                s += "" + temp.toString() + '\n';
                temp = temp.getFratello();
                cartelle++;
            }
        }
        File f = attuale.getFile();
        if (f != null) {
            s += "" + f.toString() + '\n';
            f = f.getFratello();
            file++;
            while (f != null) {
                s += "" + f.toString() + '\n';
                f = f.getFratello();
                file++;
            }
        }
        return s + "\t\t" + file + " File" + "\n\t\t" + cartelle + " Directory";
    }

    public boolean ren(String fileName, String fileType, String newName) {
        File temp = attuale.getFile();
        while (temp != null) {
            if (temp.getNome().equals(fileName) && temp.getType().equals(fileType)) {
                temp.setNome(newName);
                return true;
            }
            temp = temp.getFratello();
        }
        return false;
    }

    private String treeR(Folder f, int livello) {
        if (f == null) {
            return "";
        }
        String s = "";
        if (livello == 0) {
            s += f.getNome() + '\n';
        } else {
            for (int i = livello - 1; i > 0; i--) {
                Folder temp = f;
                for (int j = 0; j < i; j++) {
                    temp = temp.getPadre();
                }
                if (temp.getFratello() == null) {
                    s += "    ";
                } else {
                    s += "│   ";
                }
            }
            if (f.getFratello() == null) {
                s += "└───";
            } else {
                s += "├───";
            }

            s += f.getNome() + "\n";
        }
        if (f.getSottocartella() != null) {
            s += treeR(f.getSottocartella(), livello + 1);
        }
        if (f.getFratello() != null && livello != 0) {
            s += treeR(f.getFratello(), livello);
        }
        return s;

    }

    public String tree() {
        return treeR(attuale, 0);
    }

    @Override
    public String toString() {
        String s = percorso.toString();
        Folder temp = attuale.getSottocartella();
        while (temp != null) {
            s += temp.getNome() + "; ";
            temp = temp.getFratello();
        }
        File t = attuale.getFile();
        while (t != null) {
            s += t.getNome() + '.' + t.getType() + "; ";
            t = t.getFratello();
        }
        return s;
    }

}
