import java.util.ArrayList;
import java.util.HashMap;

public class Logic {
    private ArrayList<ArrayList<String>> p1_field;
    private ArrayList<ArrayList<String>> p2_field;
    private ArrayList<String> empty_field;
    private HashMap<Integer, ArrayList<ArrayList<String>>> player_map;
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
        p1_field = new ArrayList<ArrayList<String>>();
        p2_field = new ArrayList<ArrayList<String>>();
        empty_field = new ArrayList<String>(10);
        for (int i = 0; i < 10; i++) {
            empty_field.add("--");
        }
        for (int i = 0; i < 10; i++) {
            p1_field.add(empty_field);
            p2_field.add(empty_field);
        }
        player_map = new HashMap<Integer, ArrayList<ArrayList<String>>>();
        player_map.put(1, p1_field);
        player_map.put(2, p2_field);
    }

    public void setShips(int row, int col, int size, String ship_num, int player_num) {
        player_map.get(player_num).get(row).set(col, ship_num);
        while (size > 0) {
            player_map.get(player_num).get(row).set(col, ship_num);
        }
    }
}
