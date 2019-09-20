package com.company;
import java.util.Scanner;
import java.util.Random;
import com.company.Operations;

public class Main {
    public static void main(String[] args) {
	    Operations.menu();
        System.out.println("Срееднее арифметическое чисел с нечетными индексами: " + Operations.MiddleAr());
        System.out.println("Срееднее геометрическое чисел с нечетными индексами: " + Operations.MiddleGeo());
        System.out.print("Отсортированный массив: ");
        Operations.Sort();
        for(int a = 0; a < Operations.count; a++){
            System.out.print(Operations.Mass[a] + " ");
        }
    }
}


