package com.utype;

import java.util.Scanner;

public class AntonGame {
    class Room {
        public String name_;
        public Room north_;
        public Room east_;
        public Room south_;
        public Room west_;

        Room(String name) {
            name_ = name;
        }
    }
    public void run() {
        Room r1 = new Room("Room 1");
        Room r2 = new Room("Room 2");
        Room r3 = new Room("Room 3");
        Room r4 = new Room("Room 4");
        r1.east_ = r2;
        r2.west_ = r1;
        r2.south_ = r3;
        r3.north_ = r2;
        r3.west_ = r4;
        r4.east_ = r3;
        r4.north_ = r1;
        r1.south_ = r4;

        Room cur_room = r1;

        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("You are in " + cur_room.name_);
            System.out.print("> ");
            String a = s.nextLine();
            if (a.equals("q")) {
                break;
            }
            else if (a.equals("n")) {
                if (cur_room.north_ != null) {
                    cur_room = cur_room.north_;
                }
            }
            else if (a.equals("e")) {
                if (cur_room.east_ != null) {
                    cur_room = cur_room.east_;
                }
            }
            else if (a.equals("s")) {
                if (cur_room.south_ != null) {
                    cur_room = cur_room.south_;
                }
            }
            else if (a.equals("w")) {
                if (cur_room.west_ != null) {
                    cur_room = cur_room.west_;
                }
            }
            else {
                System.out.println("This doesn't sound like something meaningful.");
            }
        }
    }
}
