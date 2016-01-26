package com.utype;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Scanner;

/**
 * Created by Sakis on 26/01/2016.
 */
public class Pipuzzle {
    private int clue = 14159265;
    private int answer = 35;



    public void run(){
        System.out.println(clue + "_ _");
        System.out.println("YOU: Can you read this Ollie?");
        System.out.println("CM: I can see a sequence of numbers on the screen followed by two blank spaces.");
        System.out.println("It would seem that to proceed, the two digits missing should be entered");
    }


    public void check(int guess) {
        while (guess!=answer) {

            if ((guess > 99)||(guess < 10)) {
                System.out.println("Only enter TWO digits");
            } else if (guess!=this.answer) {
                System.out.println("That is not the correct guess, try again");
            } else if (guess==this.answer) {
                System.out.println("Yes! That's the number, 3 and 5 are the numbers that follow the sequence in Pi after the decimal");
                //the door is opened
            }

        }
    }



    }


