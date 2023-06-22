import java.util.Scanner;
import java.util.HashMap;
import java.util.Random;
public class Game{
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    static HashMap<String,Integer> col_dict = new HashMap<String,Integer>();
    public static void main(String[] args) {
        col_dict.put("A", 0); col_dict.put("B", 1); col_dict.put("C", 2);
        col_dict.put("D", 3); col_dict.put("E", 4); col_dict.put("F", 5);
        col_dict.put("G", 6); col_dict.put("H", 7); col_dict.put("I", 8);
        col_dict.put("J", 9); 
        Logic game_start = new Logic();
        boolean running = true; // Used for each of the keypoints
        int players = 2; // Later introduce more players and can be chosen at the beginning
        String [] player_arr = new String[players];
        // Create the Player Array to iterate throught turns
        for (int i =1;i <= players; i++){
            player_arr[i-1] = "Player "+i;
        }
        // Setting up the field for each player
        players = 1; // Keep using the variable to save memory (counter)
        while(players <= player_arr.length){
            int count = 0;
            int ship_num = 0;  
            System.out.println(player_arr[players-1]);  
            fieldSetup(count, ship_num, game_start,players);
            players++;
        }
        // Make a move by imputting
        players = 1; // Keep using variable to save memory (turn)
        // Playing the game
        sc.nextLine();
        while(running){
            int row = 0;
            int col = 0;
            System.out.println(player_arr[players-1]+" to attack (Type the number only): ");
            displayPlayers(player_arr, players-1);
            System.out.println("");
            // Scan the player that is being attacked
            int attack = 0;
            try {
                attack = sc.nextInt();
              // In case user puts wrong input  
            } catch (Exception e) {
                // TODO: handle exception
                attack = random.nextInt(player_arr.length-1);
            }
            // checks if player being attacked is different than player attacking
            attack = checkAttack(players, attack, player_arr); 
            System.out.println(player_arr[players-1]+" make a move against "+ player_arr[attack-1]+": \nHint* (A-J) for Col & (1-9) for Row (ex. B6)");
            sc.nextLine();
            try {
                // Scan the coordinate to be attacked
                String input = sc.nextLine();
                System.out.println("(2) Row: "+input.charAt(input.length()-1)+"\nCol: "+input.substring(0,1));
                // Split the input into the row and col in order to perform the attack
                if (input.length() == 2) {
                    row = Integer.valueOf(input.substring(1)); 
                    col = col_dict.get(input.substring(0,1));
                }
                // If an error occurs pick a random square to be attacked
            } catch (Exception e) {
                // TODO: handle exception
                row = random.nextInt(9);
                col = random.nextInt(9);
            }
            System.out.println("(2) Row: "+(row-1)+"\nCol: "+(col));
            System.out.println(game_start.makeMove(row-1, col, attack));
            // Print for debuggin purposes
            System.out.println("This is the attackers field: "+player_arr[attack-1]);
            game_start.printField(Integer.valueOf(attack));
            boolean check = game_start.checkWinner(attack);
            if (check){
                System.out.println("Winner is: "+player_arr[players-1]);
                running = false;
            }
            players = nextTurn(players, player_arr.length);
        } 
    }

    // Takes care of setting up the field
    public static void fieldSetup(int count, int ship_num, Logic game_start,int players){
        while(count < 1){
            int row =0;
            int col = 0;
            int size = 0;
            boolean direction = true; // True is vertical and False is horizontal 
            game_start.printField(players); // Print the field for 
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
                size = sc.nextInt();
                // Random Generator in case wrong input
                if (size < 2 || size > 5){
                    size = (int)Math.floor(Math.random() * (5 - 2 + 1) + 2);
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
              
                size = (int)Math.floor(Math.random() * (5 - 2 + 1) + 2);

                direction = random.nextBoolean();
            }
            int temp = game_start.checkSetShips(row,col,size-1,String.valueOf(ship_num),players,direction); 
            count += temp;
            ship_num += temp;
        }
    }

    // Make the player turns
    public static int nextTurn(int current_player,int max_players){
        if (current_player == max_players){
            return 1;
        }
        else {
            return current_player+=1;
        }
    }
    
    // Display the players currently playing
    public static void displayPlayers(String [] player_arr, int current_player){
        for (String str : player_arr){
            if (!player_arr[current_player].equals(str)){
                System.out.print(str+" ");
            }
        }
    }

    // Check if the player being attacked is valid
    public static int checkAttack(int current_player, int attack_player, String [] player_arr){
        if (attack_player == current_player || attack_player > player_arr.length || attack_player <= 0){
            // In case the player failed the condition
            for (int i=0;i<player_arr.length;i++){
                if (i+1 != current_player){
                    attack_player = i+1;
                    break;
                }
            }
        }
        else {
            return attack_player;
        }
        return attack_player;
    }

    
}