import java.util.LinkedList;

public class node {
    String name;
    int ID;
    int[] neighbors;
    int[] children;
    LinkedList<String> path_to_node = new LinkedList<String>();

    node(String name, int ID, int[] neighbors){
        this.name = name;
        this.ID = ID;
        this.neighbors = neighbors;
    }

    public String toString(){
        return "City name: " + this.name +
        ", ID: " + this.ID + ", Neighbors: " + this.neighbors;
    }
}
