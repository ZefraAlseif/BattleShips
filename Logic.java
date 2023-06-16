import java.util.ArrayList;
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
                p1_field[row][col] = "--";
                p2_field[row][col] = "--";

            }  
        }
        System.out.println(p1_field);
        player_map = new HashMap<Integer, String [][]>();
        player_map.put(1, p1_field);
        player_map.put(2, p2_field);
    }

    public void setShips(int row, int col, int size, String ship_num, int player_num) {
        while (size > 0){
            player_map.get(player_num) [row][col+size] = ship_num;
            size--;
        }
        for (String[] strings : p1_field) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println("");    
        }
    }

    public int checkSetShips(int row, int col, int size, String ship_num, int player_num){
        if (col + size < 10){
            setShips(row, col, size, ship_num, player_num);
            return 1;
        }
        else{
            System.out.println("Location chosen does not work");
            return 0;
        }
    }
}
