package text_football;

import java.util.Scanner;
import java.util.Random;

public class game {

    boolean simulate = false;
    public String homeTeam;
    public String awayTeam;
    public int homeScore = 0;
    public int awayScore = 0;
    // public Team homeTeam;
    // public Team awayTeam;
    public int playNum = 0;
    boolean ball = true; //used to see who has the ball.
    public int fieldPos = 20;

    game() {
        this.homeTeam = "Michigan";
        this.awayTeam = "Ohio State";
        System.out.println("*****\t Ann Arbor Michigan is the site of Todays Game \n We welcome you on a beautiful fall day for football\n Now lets get down on the feild for the Coin Toss");
    }

    game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        System.out.println("*****\t Welcome to Todays Game between " + homeTeam + " and " + awayTeam + "!\n We welcome you on a beautiful fall day for football\n Now lets get down on the feild for the KickOff");

    }

    public void start() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Simulate Defence\n Enter \"Y\" to simulate");
        if (scan.next().equalsIgnoreCase("Y")) {
            System.out.println("\n Simulating for defence\n");
            simulate = true;
        } else {
            System.out.println("\n Player two will play away team \n");
            simulate = false;
        }
        cointoss();

        //***********************      
        while (playNum < 70) { //average game is about 130 plays,

            while (ball) {
                System.out.println("\n" + homeTeam + " has the ball as they get ready to start this drive");
                Drive d = new Drive(fieldPos);//start new drive
                d.driveStart(false);
                fieldPos = d.getFieldPos();
                this.homeScore += d.getScore();
                playNum += d.getplayCount();
                endDrive();
                printScore();
            }

            simulate(simulate);

            while (!ball) {
                System.out.println("\n" + awayTeam + " has the ball as they get ready to start this drive");
                Drive d = new Drive(fieldPos);//start new drive
                d.driveStart(false);
                fieldPos = d.getFieldPos();
                this.awayScore += d.getScore();
                playNum += d.getplayCount();
                endDrive();
                printScore();
            }

        }
    }

    public void simulate(boolean answer) {
        if (answer) {
            Drive d = new Drive(fieldPos);
            d.driveStart(simulate);//code to simualte d
            this.awayScore += d.getScore();
            playNum += d.getplayCount();
            fieldPos = d.getFieldPos();
            ball = !ball;
            d.status();
            printScore();
        }
    }

    public void endDrive() {
        ball = !ball;
        System.out.println("Total plays so far: " + playNum);
    }

    public void printScore() {
        System.out.println(homeTeam + ": " + homeScore + "\n" + awayTeam + ": " + awayScore);
    }

    public void cointoss() { // fix this, current if guess is tails will equal a loss no matter what
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        boolean coin = rand.nextBoolean();
        String coinGuess = "";

        while (!coinGuess.equals("Heads") && !coinGuess.equals("Tails")) {
            System.out.println("\t\t" + homeTeam + ", Heads or Tails?");
            coinGuess = scan.next();
        }

        if (coinGuess.equals("Heads") && coin) {
            System.out.println("The coin is Heads.. " + homeTeam + " Has won the toss, \n Would you like to Kick or Recieve?");
            if (scan.next().equals("Recieve")) {
                ball = true;
            } else {
                ball = false;
            }
        } else { 
            System.out.println("The coin is Tails. " + awayTeam + " Wins the toss, \n Kick or Recieve?");
            if (scan.next().equals("Recieve")) {
                ball = false;
            } else {
                ball = true;
            }
        }
        System.out.println("They are all lined up ready for the Kickoff. \n"
                + "The kick is away and it will be downed in the Endzone for a Touchback.\n");

    }

}
