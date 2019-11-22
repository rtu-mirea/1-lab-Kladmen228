package com.company;
import java.io.File;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) {
        try{
            print("\n1.Исследовать возможности класса File по созданию файлов (пустых) и папок программой. Применение конструктора и метода");
            task1_1();
            print("\n2.Получить параметры файлов методами класса File, в пунктах задания использовать объекты, созданные в задании 1");
            task1_2();
            print("\n3.Модификация файловой структуры приложения средствами класса File");
            task1_3();

            print("\n1.Чтение из одного файла текстового файла и запись в другой");
            task3_1();
            print("\n2.Применение буферизированных потоков для чтения и записи текстовых файлов");
            task3_2();
            print("\n3.Настройка кодировки символов для входного и выходного потоков");
            task3_3();

            print("Применение классов ObjectOutputStream и ObjectInputStream для сериализации и десериализации объектов ");
            task4_1();
        }
        catch (Exception e){
            print(String.valueOf(e));
        }
    }

    private static void createFile(String filepath, String name) {
        File file = new File(filepath, name);
        file.getParentFile().mkdir();
        if (!file.exists()) {
            try {
                file.createNewFile();
                print("Файл создан");
                return;
            }
            catch (IOException e) {
                print(e.getMessage());
                return;
            }
        }
        print("Файл  уже создан");
    }

    private static void createDirectory(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            file.mkdirs();
            print("Файл создан");
            return;
        }
        print("Файл уже создан");
    }

    private static void deleteFile(String filepath, String name) {
        File file = new File(filepath, name);
        if (file.delete()) {
            print("Файл " + file.getName() + " удален");
            return;
        }
        print("Ошибка удаления");
    }

    private static void deleteDirectory(String filepath) {
        File file = new File(filepath);
        if (file.delete()) {
            print("Папка " + file.getName() + " удалена");
            return;
        }
        print("Ошибка удаления");
    }

    private static String[] getFiles() {
        File file = new File(".");
        return file.list();
    }

    private static File[] getFolders() {
        File file = new File(".");
        return file.listFiles();
    }

    private static void foldersCount(File[] files) {
        int count = 0;
        for (File file : files) {
            if (file.isDirectory()) {
                ++count;
            }
        }
        print(String.valueOf(count));
    }

    private static void task1_1(){
        createFile(".", "MyFile1.txt");
        createFile("F:\\", "MyFile2.txt");
        createFile("C:\\Steam\\", "MyFile3.txt");
        createDirectory("Первая\\Вторая\\Третья");
    }

    private static void task1_2() {
        File file = new File("C:\\Steam\\", "MyFile4.txt");
        File file1 = new File("MyFile1.txt");
        String exist = "";
        if (file1.exists()) {
            exist = "Файл MyFile1.txt существует";
        }
        print("Существует ли файл MyFile1.txt: " + exist + "\nИмя файла: " + file.getName() + "\nПуть к файлу: " +
                file.getAbsolutePath() + "\nВес: " + file.length() + " bytes\nТип файла: " + (file.isDirectory() ? "Папка" : "Файл"));
    }

    private static void task1_3(){
        createDirectory("one_more_folder");
        print(Arrays.toString(getFiles()));
        File[] files = getFolders();
        print(Arrays.toString(files));
        foldersCount(files);
        deleteFile(".", "MyFile1.txt");
        deleteFile("F:\\", "MyFile2.txt");
        deleteFile("C:\\Steam\\", "MyFile3.txt");
        deleteDirectory("Первая\\Вторая\\Третья");
        deleteDirectory("Первая\\Вторая");
        deleteDirectory("Первая");
        deleteDirectory("one_more_folder");
    }

    private static void task3_1(){
        try {
            Reader reader = new InputStreamReader(new FileInputStream("T1.txt"));
            Writer writer = new OutputStreamWriter(new FileOutputStream("T2.txt"));
            int x;
            while((x = reader.read()) != -1) {
                writer.write((char)x);
            }
            reader.close();
            writer.close();
            print("Перепись файлов завершена");
        }
        catch (Exception e){
            print(String.valueOf(e));
        }
    }

    private static void task3_2(){
        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream("A.txt"));
            for (int i = 40; i < 552; i++) {
                writer.write((char) i);
            }
            writer.close();

            BufferedReader inb = new BufferedReader(new InputStreamReader(new FileInputStream("A.txt")), 128);
            BufferedWriter outb = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("B.txt")), 128);
            char[] buf = new char[128];
            for(int i = 0; i < 4; i++){
                inb.read(buf);
                outb.write(buf);
                outb.newLine();
            }
            inb.close();
            outb.close();
            print("Данные переписаны");
        }
        catch (Exception e){
            print(String.valueOf(e));
        }
    }

    private static void task3_3(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("A.txt"), "Cp1251"));
            print(Charset.defaultCharset().name());
            String str = in.readLine();
            print(str);
            in = new BufferedReader(new InputStreamReader(new FileInputStream("B.txt"), "UTF-8"));
            str = in.readLine();
            print(str);
        }
        catch (Exception e){
            print(String.valueOf(e));
        }
    }

    private static void task4_1(){

    }

    private static void print(String txt){
        System.out.println(txt);
    }
}
