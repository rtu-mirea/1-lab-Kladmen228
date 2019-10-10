package com.company;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Task2 {
    private StringBuilder line;
    private StringBuilder line2;
    private ArrayList<Integer> Mass = new ArrayList<>();
    private ArrayList<Integer> Mass2 = new ArrayList<>();
    private String res = "";
    void Input(String str){
        try {
            if (str.charAt(str.length() - 1) != ' ')
                str += " ";
            line = new StringBuilder(str);
            res = str;
            line2 = new StringBuilder(' ');
        }
        catch(Exception e) {
            System.out.println("Error");
        }
    }
    void CountAbz(){
        try {
            int count = 0;
            int temp = 0;
            int temp2 = 0;
            int max = 0;
            String tmp = "";
            for (int i = 0; i < line.length() - 2; i++) {
                temp += 1;
                temp2 += 1;
                if (count == 1) {
                    tmp = String.valueOf(line.charAt(i));
                    line2.append(tmp);
                }
                if (line.charAt(i) == '\t') {
                    count += 1;
                    if (count != 1) {
                        Mass.add(temp);
                        Mass2.add(temp2);
                        if (temp > max)
                            max = temp;
                    }
                    temp = 0;
                }
            }
            if (temp > max)
                max = temp;
            Mass.add(temp);
            Mass2.add(temp2);
            int ind = Mass.indexOf(max);
            tmp = String.valueOf(line2);
            if (ind != 0) {
                line.delete(Mass2.get(ind - 1), Mass2.get(ind)-1);
                line.delete(0, Mass2.get(0));
                line.append("\t").append(tmp);
            } else {
                line.delete(0, Mass2.get(ind)-1);
                line.append("\t").append(tmp);
            }
            System.out.println(line);
            Update();
            line.delete(0,line.length());
            line.append(res);
            line2.delete(0,line2.length());
            line2.append(" ");
        }
        catch(Exception ignored) {

        }
    }
    private void Update(){
        double t = 0,b = 0;
        String str = String.valueOf(line);
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()) {
            t = Double.parseDouble(matcher.group());
            Modify(t,b);
        }
        System.out.print("\n");
    }
    private void Modify(double t, double b){
        int a = 0;
        b = t;
        while(true){
            if(t>1){
                t/=10;
                a+=1;
            }
            else
                break;
        }
        System.out.print("Число "+ b +" в форме с плавающей точкой выглядит: "+ t +"*exp*10^"+a+"\n");
    }
}
