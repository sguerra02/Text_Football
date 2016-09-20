package text_football;
import java.util.Scanner;

public class Text_Football {

   
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean again = false;
        
        //starting with just game, later start at menu to select season, start game, add teams, select team.
        do{
        game g = new game();
        g.start();
        System.out.println("Game over \n Enter \"Y\" to play again");
            if (scan.next() == "Y" || scan.next()== "y"){
            again = true;
        }}
        while(again);
        
        
        
        
        
    }
    
}
