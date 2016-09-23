package text_football;

import java.util.Scanner;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public int maxplays = 35;

    game() {
        this.homeTeam = "Michigan";
        this.awayTeam = "Ohio State";
        System.out.println("\n\n*****\t Ann Arbor Michigan is the site of Todays Game \n We welcome you on a beautiful fall day for football");
        pause(1500);
        System.out.println("\nNow lets get down on the feild for the Coin Toss\n");
        pause(1500);
    }

    game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        System.out.println("\n\n*****\t Welcome to Todays Game between " + homeTeam + " and " + awayTeam + "!, in what should be an exciting matchup\n");
         pause(1500);
        System.out.println("\nNow lets get down on the feild for the KickOff");
        pause(1500);
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

        
        while (playNum < 70) { //average game is about 130 plays,

            while (ball) {
                System.out.println("---------------------------------------------------"
                        + "\n" + homeTeam + " has the ball as they get ready to start this drive");
                Drive d = new Drive(fieldPos, playNum, maxplays);//start new drive
                d.driveStart(false);
                fieldPos = d.getFieldPos();
                this.homeScore += d.getScore();
                playNum += d.getplayCount();
                endDrive();
                printScore();
                break;
            }

            simulate(simulate);

            while (!ball) {
                System.out.println("---------------------------------------------------"
                        + "\n" + awayTeam + " has the ball as they get ready to start this drive");
                Drive d = new Drive(fieldPos, playNum, maxplays);//start new drive
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
            Drive d = new Drive(fieldPos, playNum, maxplays);
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
        System.out.println("-----------------------");
        System.out.println("| "+homeTeam + ": " + homeScore + "\t|\n| " + awayTeam + ": " + awayScore +"\t|");
        System.out.println("-----------------------");
    }

    public void cointoss() { // fix this, current if guess is tails will equal a loss no matter what
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        boolean coin = rand.nextBoolean();
        String coinGuess = "";

        while (!coinGuess.equalsIgnoreCase("Heads") && !coinGuess.equalsIgnoreCase("Tails")) {
            System.out.println("\t\t" + homeTeam + ", Heads or Tails?");
            coinGuess = scan.next();
        }
        pause(800);

        if ((coinGuess.equalsIgnoreCase("Heads") && coin ) || (coinGuess.equalsIgnoreCase("Tails") && !coin)) {
           
            if(coin){
                System.out.println("The coin is Heads.. " + homeTeam + " Has won the toss, \n Would you like to Kick or Recieve?");
            }
            else{
            System.out.println("The coin is Tails.. " + homeTeam + " Has won the toss, \n Would you like to Kick or Recieve?");
            }
            if (scan.next().equals("Recieve")) {
                ball = true;
            } else {
                ball = false;
            }
        } 
        else { 
            System.out.println("The coin is Tails. " + awayTeam + " Wins the toss, \n Kick or Recieve?");
            boolean KR = rand.nextBoolean();
            if(KR){
                System.out.println( awayTeam + " Chooses to Recieve the ball");
                ball = false;
            }else {
                System.out.println( awayTeam + " Chooses to Kick to start the Game");
                ball = true;
            }
        }
        pause(1000);
        System.out.println("They are all lined up ready for the Kickoff.");
                pause(1500);
        System.out.println("The kick is away and it will be downed in the Endzone for a Touchback.\n");

    }
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
