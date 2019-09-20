package com.company;
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

class Operations {
    static int count;
    static Scanner in = new Scanner(System.in);
    static double[] Mass;

    private static void Fill(int i){
        final Random random = new Random();
        if(i == 1){
            for(int a = 0; a < count; a++){
                System.out.print("Введите "+(a+1)+" элемент массива: ");
                Mass[a] = in.nextDouble();
            }
        }
        else{
            for(int a = 0; a < count; a++){
                Mass[a] = random.nextInt(100);
            }
        }
    }
    private static void OutR(){
        System.out.print("Массив слева направо: ");
        System.out.println(Arrays.toString(Mass));
        System.out.println(" ");
    }
    private static void OutL(){
        System.out.println("Массив справа налево: ");
        for(int a = count-1; a >= 0; a--){
            System.out.print(Mass[a] + " ");
        }
        System.out.println(" ");
    }
    static double MiddleAr(){
        double Sum = 0;
        int k = 0;
        for(int i = 0; i < count; i++){
            if(i % 2 == 0){
                Sum += Mass[i];
                k++;
            }
        }
        return Sum / k;
    }
    static double MiddleGeo(){
        double Sum = 1;
        int k = 0;
        for(int i = 0; i < count; i++){
            if(i % 2 == 0){
                Sum *= Mass[i];
                k++;
            }
        }
        return Math.pow(Sum, 1.0 / k );
    }
    static void Sort(){
        int left = 1;
        int right = count - 1;
        while (left <= right)
        {
            for (int i = right; i >= left; i--)
                if (Mass[i - 1] < Mass[i]) Swap(i);
            left++;
            for (int i = left; i <= right; i++)
                if (Mass[i - 1] < Mass[i]) Swap(i);
            right--;
        }
    }
    private static void Swap(int i){
        double buff;
        buff = Mass[i];
        Mass[i] = Mass[i - 1];
        Mass[i - 1] = buff;
    }
    static void menu(){
        System.out.println("Введите кол-во элементов массива: ");
        count = in.nextInt();
        Mass = new double[count];
        System.out.println("Заполнение массива:\n0-Случайно\n1-С клавиатуры");
        int random = in.nextInt();
        Fill(random);
        System.out.println("Вывод массива:\n0-Слева направо\n1-Справа налево");
        int out_mass = in.nextInt();
        if(out_mass==1)
            OutL();
        else
            OutR();
    }
}
