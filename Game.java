import java.util.Scanner;

public class Game{
    public static void main(String[] args) {
        Logic game_start = new Logic();
        Scanner sc = new Scanner(System.in);
        boolean running = true; // Used for each of the keypoints
        int count = 0;
        int ship_num = 0; 
        while(count < 5){
            System.out.println("Input the row where your ship would go: ");
            int row = sc.nextInt();
            System.out.println("Input the col where your ship would go: ");
            int col = sc.nextInt();
            System.out.println("Input the size of the ship (between 2-9): ");
            int size = sc.nextInt();
            int temp = game_start.checkSetShips(row,col,size,String.valueOf(ship_num),1); 
            count += temp;
            ship_num += temp;
        }
        count = 0;
        ship_num = 0; 
        while(count < 5){
            System.out.println("Input the row where your ship would go: ");
            int row = sc.nextInt();
            System.out.println("Input the col where your ship would go: ");
            int col = sc.nextInt();
            System.out.println("Input the size of the ship (between 2-9): ");
            int size = sc.nextInt();
            int temp = game_start.checkSetShips(row-1,col-1,size,String.valueOf(ship_num),2); 
            count += temp;
            ship_num += temp;
        }
    }
}