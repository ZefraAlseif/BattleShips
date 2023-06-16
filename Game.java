import java.util.Scanner;
import java.util.Random;
public class Game{
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {
        Logic game_start = new Logic();
        boolean running = true; // Used for each of the keypoints
        int players = 2; // Later introduce more players and can be chosen at the beginning
        while(players > 0){
            int count = 0;
            int ship_num = 0;    
            fieldSetup(count, ship_num, game_start,players);
            players--;
        }
    }

    // Takes care of setting up the field
    public static void fieldSetup(int count, int ship_num, Logic game_start,int players){
        while(count < 5){
            int row =0;
            int col = 0;
            int size = 0;
            boolean direction = true; // True is vertical and False is horizontal 
            try {
                System.out.println("Input the row where your ship would go: ");
                // The row in the field to put the boat
                row = sc.nextInt()-1;
                // Random Generator in case wrong input
                if (row < 0 || row > 9){
                    row = random.nextInt(9);
                }
                System.out.println("Input the col where your ship would go: ");
                // The col in the field to put the boat
                col = sc.nextInt()-1;
                // Random Generator in case wrong input
                if (col < 0 || col > 9){
                    col = random.nextInt(9);
                }
                System.out.println("Input the size of the ship (between 2-5): ");
                // The size of the boat
                size = sc.nextInt()-1;
                // Random Generator in case wrong input
                if (size < 2 || size > 5){
                    size = random.nextInt(2,5); // Add Math.random instead
                }
                // Introduce the ability to scan the direction (vertical or horizontal)
                System.out.println("Input the direction of the boat (v or h): ");
                String temp_direction = sc.next();
                // Setup the conditions according to the input
                if (temp_direction.equalsIgnoreCase("v")){
                    direction = true;
                }
                else if (temp_direction.equalsIgnoreCase("h")){
                    direction = false;
                }
                else {
                    direction = random.nextBoolean();
                }  
            } catch (Exception e) { // Any exception occurs define all of the values randomly
                // TODO: handle exception
                row = random.nextInt(9);
                col = random.nextInt(9);
                size = random.nextInt(2,5); // Math.random instead
                direction = random.nextBoolean();
            }
            int temp = game_start.checkSetShips(row,col,size,String.valueOf(ship_num),players,direction); 
            count += temp;
            ship_num += temp;
        }
    }
}