package com.company;
import java.io.Serializable;

public class TextSerializ implements Serializable {
    private String text;

    void inputText(String Text){
        text = Text;
    }
}