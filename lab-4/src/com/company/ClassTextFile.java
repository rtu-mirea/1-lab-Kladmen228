package com.company;
import java.io.File;
import java.util.Scanner;

public class ClassTextFile {
    private String fileName;

    public ClassTextFile(){
        fileName = "";
    }

    ClassTextFile(String name) throws Exception{
        if(new File(name).exists())
            fileName = name;
        else throw new Exception("Файл не существует");
    }
}