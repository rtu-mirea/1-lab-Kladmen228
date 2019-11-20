package com.company;
import java.io.File;
import java.util.Arrays;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) {
        try{
            print("\nИсследовать возможности класса File по созданию файлов (пустых) и папок программой. Применение конструктора и метода");
            createFile(".", "MyFile1.txt");
            createFile("F:\\", "MyFile2.txt");
            createFile("C:\\Steam\\", "MyFile3.txt");
            createDirectory("Первая\\Вторая\\Третья");

            print("\nПолучить параметры файлов методами класса File, в пунктах задания использовать объекты, созданные в задании 1");
            info();

            print("\nМодификация файловой структуры приложения средствами класса File");
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
        catch (Exception ignored){

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

    private static void info() {
        File file = new File("C:\\Steam\\", "MyFile4.txt");
        File file1 = new File("MyFile1.txt");
        String exist = "";
        if (file1.exists()) {
            exist = "Файл MyFile1.txt существует";
        }
        print("Существует ли файл MyFile1.txt: " + exist + "\nИмя файла: " + file.getName() + "\nПуть к файлу: " +
                file.getAbsolutePath() + "\nВес: " + file.length() + " bytes\nТип файла: " + (file.isDirectory() ? "Папка" : "Файл"));
    }

    private static void print(String txt){
        System.out.println(txt);
    }
}
