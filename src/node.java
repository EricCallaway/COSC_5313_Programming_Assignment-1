public class node {
    String name;
    int ID;
    int[] neighbors;

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
