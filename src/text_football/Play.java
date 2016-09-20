package text_football;

import java.util.Random;
import java.util.Scanner;

public class Play {

    int yardsGained;
    boolean fieldGoal = false;
    boolean punt = false;

    Play() {
        this.yardsGained = yardsGained;
        this.fieldGoal = fieldGoal;
    }

    Play(int down, int fieldPos) {
        Random rand = new Random();

        if (down < 4) {
            int sim = rand.nextInt(10) + 4;
            runPlay(sim);
        } else if (fieldPos >= 60) {
            fieldGoal();
        } else {
            punt();
        }

    }

    public int selectPlay() {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();// why did i put this here? do I need it??
        System.out.println("** Choose a play type or choose the number of a play **\n\n\t 1.Run, 2.Pass, or 3.Special");
        int type = scan.nextInt();
        int playId = 0;

        if (type == 1) {
            System.out.println("4.Inside, 5.Outside, 6.Draw, 7.Toss, 8.Goaline");
            playId = scan.nextInt();
        } else if (type == 2) {
            System.out.println("9.Screen, 10.Shallow, 11.Middle, 12.Deep, 13.Hail Mary");
            playId = (scan.nextInt());
        } else if (type == 3) {
            System.out.println("14.Field Goal, 15.Punt, 16.Kneel");
            playId = scan.nextInt();
        } else if (type <= 16) {
            return runPlay(type);
        }
        return runPlay(playId);
    }

    public int runPlay(int playId) {
        Random def = new Random();
        int defence = def.nextInt(100) + 1; // pass percent
        int n = 0;

        switch (playId) {
            case 4:   //("4.Inside, 5.Outside, 6.Draw, 7.Toss, 8.Goaline");
            case 5:   // this should be 60% of the runs 0-5 yrd, 30% will be 5-10 yrd, 14% 10-20yrd 6%20+
            case 6:
            case 7:
                if (defence <= 60) {
                    n = def.nextInt(8) - 2;
                } else {
                    n = checkdefence(defence);
                }
                break;
            case 8://goaline run -1 - 2 yards
                n = def.nextInt(3) - 1;
                break;
            case 9: //("9.Screen, 10.Shallow, 11.Middle, 12.Deep, 13.Hail Mary");
                if (defence <= 85) {
                    System.out.println("The pass is complete.");
                    n = def.nextInt(5) + 1;
                } else {
                    n = checkdefence(-1);
                }
                break;
            case 10:
                if (defence <= 65) {
                    System.out.println("The pass is complete.");
                    n = def.nextInt(5) + 1;
                } else {
                    n = checkdefence(-1);
                }
                break;
            case 11:
                if (defence <= 40) {
                    System.out.println("The pass is complete.");
                    n = def.nextInt(9) + 6;
                } else {
                    n = checkdefence(-1);
                }
                break;
            case 12:
                if (defence <= 30) {
                    System.out.println("The pass is complete.");
                    n = def.nextInt(10) + 15;
                } else {
                    n = checkdefence(-1);
                }
                break;
            case 13:
                if (defence <= 30) {
                    System.out.println("The pass is complete.");
                    n = def.nextInt(74) + 25;
                } else {
                    n = checkdefence(-1);
                }
                break;
            case 14:
                fieldGoal();
                break;
            case 15:
                punt();
                n = yardsGained;
                break;
            case 16:
                n = -3;
                break;
            default:
                selectPlay();
        }

        yardsGained = n;  //this is not working;
        return yardsGained;  

    }

    public int checkdefence(int defence) {
        if (defence == -1) {
            System.out.println("The pass falls incomplete");
            return 0;
        }

        Random rand = new Random();
        int yardage;
        yardage = rand.nextInt(5) + 5;  //5-10yrds on the run

        if (defence > 84) {

            if (defence > 94) {
                yardage = rand.nextInt(79) + 20;  // 20-80yrds on the run
            } else {
                yardage = rand.nextInt(10) + 10; //10-20yrds on the run
            }
        }

        return yardage;
    }

    public void punt() {
        Random rand = new Random();
        int puntYards = (rand.nextInt(20) + 35);
        yardsGained += puntYards;
        System.out.println(" They line up to punt the ball here. Its a high kick, no return on this one. " + "\n net punt of " + puntYards);
        punt = true;
    }

    public boolean getPunt() {
        return punt;
    }

    public int getYards() {

        return yardsGained;

        /*pass comp % around 60-65%
        9 ints per 450 attempts
        
        Short (5 yards or less): 80.5 percent
Mid (6-15 yards): 65.0 percent
Deep (16-25 yards): 52.0 percent
Bomb (26+ yards): 33.6 percent
         */
 /*rush stats
        average 4-5yrds, for inside and outside 2.46-6.92 inside 1.95-7 outside hi/low averages of all rb
        6-fumbles per 500 runs
         */
    }

    public void fieldGoal() {
        fieldGoal = true;
    }

    public boolean getfieldGoal() {
        return fieldGoal;
    }

    public void commentary() {// put this in with getYards?
        System.out.println("\n */* A gain of " + yardsGained + " on the play */*\n");
    }

}
