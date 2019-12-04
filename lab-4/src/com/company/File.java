package com.company;
import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;

class file1 {
    private String Name;
    private File file;

    public file1(String Name) throws IOException {
        this.Name = Name;
        file = new File(this.Name);
        file.createNewFile();
    }

    public boolean exist(){
        if(file.canRead()){
            return true;
        }
        else{
            return false;
        }
    }

    public void write(book book) throws IOException{
        if(exist()){
            String res = "";
            DataInputStream InpStrem = new DataInputStream(new FileInputStream(file.getAbsolutePath()));
            try{
                res = InpStrem.readUTF();
            }
            catch (Exception e){}
            res = stringBook(book) + "\n" + res;
            InpStrem.close();

            DataOutputStream OutStrem = new DataOutputStream(new FileOutputStream(file.getAbsolutePath()));
            OutStrem.writeUTF(res);
            OutStrem.flush();
            OutStrem.close();
        }
        else{
            throw new IOException("Файла не существует");
        }
    }

    public String stringBook(book book){
        String res = "";
        res += book.getAuthor() + ":" + book.getName() + ":" + book.getNumber() + ":" + book.getPublisher() + ":" + book.getCount() + ":" + book.getCost();
        return res;
    }
}
