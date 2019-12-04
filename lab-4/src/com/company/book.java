package com.company;

public class book {
    private String Author;
    private String Name;
    private String Number;
    private String Publisher;
    private String Count;
    private String Cost;

    public book(String author,  String name, String number, String publisher, String count, String cost){
        this.Author = author;
        this.Name = name;
        this.Number = number;
        this.Publisher = publisher;
        this.Count = count;
        this.Cost = cost;
    }

    public String getAuthor() {  return Author; }
    public String getName() {   return Name; }
    public String getNumber() {   return Number; }
    public String getPublisher() {    return Publisher; }
    public String getCount() {  return Count; }
    public String getCost() {   return Cost; }

    private String getNumber(String name, String author){
        return Number;
    }

    private String checkBooks(){
        if(true)
            return "Книги изданы в 1 издании";
        else
            return "Книги не изданы в 1 издании";
    }

    private void authorBooks(String author){
        print(" ");
    }

    private void changePublisher(String pub1, String pub2){
        print("");
    }

    private void print(String txt){
        System.out.println(txt);
    }
}

