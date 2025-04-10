package org.example;

import java.util.Scanner;

public class Client{
    static final String TOTIME = "asTime";
    static final String TOSECS = "asSeconds";
    static final String ADDTIME = "add";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String aString = sc.nextLine();
        String operator = sc.nextLine();
        String output="";
        T2time receiver;

        switch (operator) {
            case TOTIME:
                receiver = new T2time(Integer.parseInt(aString));
                output = receiver.toString();
                break;
            case TOSECS:
                receiver = new T2time(aString);
                output = "" + receiver.asSeconds();
                break;
            case ADDTIME:
                T2time argument;
                receiver = new T2time(aString);
                String bString = sc.nextLine();
                if (T2time.isTime(bString))
                    argument = new T2time(bString);
                else
                    argument = new T2time(Integer.parseInt(bString));
                output = receiver.add(argument).toString();
                break;
            default:
                output = "Invalid operator";
                break;
        }
        System.out.println(output);
        sc.close();
    }
}