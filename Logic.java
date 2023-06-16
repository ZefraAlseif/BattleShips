import java.util.HashMap;

public class Logic {
    private String [][] p1_field;
    private String [][] p2_field;
    private HashMap<Integer, String [][]> player_map;
    /*
     * List<List<String>> = [
     * []
     * []
     * []
     * []
     *
     */
    // Create the Starting empty List
    Logic() {
        p1_field = new String[10][10];
        p2_field = new String[10][10];
        for (int row = 0; row < 10; row++) {
            for (int col =0; col < 10; col++){
                p1_field[row][col] = "---";
                p2_field[row][col] = "---";

            }  
        }
        
        player_map = new HashMap<Integer, String [][]>();
        player_map.put(1, p1_field);
        player_map.put(2, p2_field);
        // Printing out the Player field (Used for debugging)
        for (String[] strings : p1_field) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println("");    
        }
    }
    
    // Verifies that the user input can satisfy the conditions
    public int checkSetShips(int row, int col, int size, String ship_num, int player_num, boolean direction){
        // Checks that the size does not cause the ship to go out of bounds
        int count = size;
        if ((col + size < 10 && !direction) || (row + size < 10 && direction)){
            // Checks that there are no ships in the specified direction
            while(count>=0){
                if (player_map.get(player_num)[row][col+size] != "---" && !direction){
                    System.out.println("There is a ship at this location please try again!");
                    return 0;
                }
                else if (player_map.get(player_num)[row+size][col] != "---" && direction){
                    System.out.println("There is a ship at this location please try again!");
                    return 0;
                }
                count--; 
            }
            setShips(row, col, size, ship_num, player_num,direction);
            return 1;
        }
        else{
            System.out.println("Location chosen does not work");
            return 0;
        }
    }

    // Sets the ships according to the user input
    public void setShips(int row, int col, int size, String ship_num, int player_num, boolean direction) {
        if (!direction){
            while (size >= 0){
                player_map.get(player_num) [row][col+size] = ship_num+"-"+ship_num;
                size--;
            }
        }
        else {
            while (size >= 0){
                player_map.get(player_num) [row+size][col] = ship_num+"-"+ship_num;
                size--;
            }
        }
        // Printing out the Player field (Used for debugging)
        for (String[] strings : player_map.get(player_num)) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println("");    
        }
    }
}
