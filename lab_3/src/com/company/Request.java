package com.company;

public class Request {
    private String place;
    private String discipline;
    private int group;
    private int pairsInWeek;

    public Request(Professor requester, String disc, int group, int hours){

    }

    public String getPlace(){ return place; }
    public String getDiscipline(){ return discipline; }
    public int getGroup(){ return group; }
    public int getPairs(){ return pairsInWeek; }
}