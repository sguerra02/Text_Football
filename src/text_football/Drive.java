package text_football;

import java.util.Random;

public class Drive {

    int down = 1;
    int toGo = 10;
    int fieldPos;
    int yardsGained = 0;
    int playcount = 0;
    int score;
    int totalplays;
    int maxplays;
    boolean ball = true; // used to tell who has the ball

    Drive(int fieldPos, int playnum, int maxplays) {
        this.fieldPos = fieldPos; 
        this.totalplays = playnum;
        this.maxplays = maxplays;
    }

    public void driveStart(boolean simulate) {
        int passMid;//used for letting user know they are passed midfeild.

        if (fieldPos > 50) {
            passMid = 100 - fieldPos;
            System.out.println("\t**  First and Ten, ball at the  opponent's " + passMid);
        } else {
            System.out.println("\t**  First and Ten, ball at the " + fieldPos + "   **");
        }
        pause(1500);

        if (!simulate) {
            while (ball &&(maxplays>totalplays)) {

                Play p = new Play();
                status();
                p.selectPlay();
                playcount++;
                totalplays++;
                yardsGained = p.getYards();
                updateDrive();
                if (p.getfieldGoal()) {
                    pause(2000);
                    boolean good = fieldGoalKick();
                    if (!good) {
                        System.out.println("*** The Kick is away, aaaand he missed it. just wide on that one***\n");
                    }
                    break;
                }
                if (p.getPunt()) {
                    ball = !ball;
                    fieldPos = (100 - fieldPos);
                    if(fieldPos < 0){
                        System.out.println("\n*** Touchback ***\n");
                        fieldPos = 20;
                    }
                    break;
                }
                pause(2000);
                p.commentary();
                checkDown();
            }
        } else {
            while (ball&&(maxplays>totalplays)) {
                status();
                Play p = new Play(down, fieldPos);
                pause(2000);
                p.commentary();
                playcount++;
                totalplays++;
                yardsGained = p.getYards();
                updateDrive();
                if (p.getfieldGoal()) {
                    pause(2000);
                    boolean good = fieldGoalKick();
                    if (!good) {fieldPos = (100 - fieldPos);
                        System.out.println(" aaaand he missed it. just wide on that one\n\n");
                    }
                    break;
                }
                if (p.getPunt()) {
                    ball = !ball;
                    fieldPos = (100 - fieldPos);
                    if(fieldPos < 0){
                        System.out.println("\n*** Touchback ***\n");
                        fieldPos = 20;
                    }
                    break;
                }
                pause(2000);
                checkDown();
                
            }
        }

    }

    public void pause(int pause) {
        try {
            Thread.sleep(pause);
        } catch (InterruptedException e) {

        }
    }

    public int getFieldPos() {
        return fieldPos;
    }

    public boolean fieldGoalKick() {
        boolean kickisGood = true;
        Random rand = new Random();
        ball = !ball;
        int wind = rand.nextInt(99) + 1;
        System.out.println("\n*** And the Feild Goal unit looks ready. Here's the snap.");
        pause(2000);
        System.out.println("The Kick is up");
        pause(2000);
        if (fieldPos <= 60 && wind > 20) {
            return !kickisGood;//57  20%
        } else if (fieldPos <= 65 && wind > 30) {
            return !kickisGood;//53  30%
        } else if (fieldPos <= 70 && wind > 60) {
            return !kickisGood;//48  60%
        } else if (fieldPos <= 75 && wind > 70) {
            return !kickisGood;//43  70%
        } else if (fieldPos <= 85 && wind > 90) {
            return !kickisGood;//33  90%
        }
        System.out.println("It has the distance, And it is Good!!\n");
        score += 3;
        fieldPos = 20;
        return kickisGood;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getplayCount() {
        return playcount;
    }

    public void checkDown() {
        if (fieldPos >= 100) {
            down = 1;
            toGo = 10;
            System.out.println("\t****************\n\t** TouchDown! **\n\t****************");
            pause(2000);
            ball = !ball;
            setScore(7);
            fieldPos = 20;
        } else if (toGo <= 0) {
            down = 1;
            toGo = 10;
            System.out.println("\t*** First Down! ***");
            pause(2000);
        } else if (down >= 4) {
            System.out.println("******\n Turnover!\n*******");
            pause(2000);
            fieldPos = (100 - fieldPos);
            down = 1;
            toGo = 10;
            ball = !ball;
        } else {
            down++;
        }
    }

    public void updateDrive() {
        toGo -= yardsGained;
        fieldPos += yardsGained;

    }

    public void status() {
        //System.out.println("Total plays this drive: " + playcount);
        int passMid;
        if (fieldPos > 50) {
            passMid = 100 - fieldPos;
            System.out.println(down + " and " + toGo + ", ball at the opponents " + passMid);
        } else {
            System.out.println(down + " and " + toGo + ", ball at the " + fieldPos);
        }

    }

}
