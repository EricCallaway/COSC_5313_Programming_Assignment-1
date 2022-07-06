import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;


public class MapBuilder{

    private int V;                  // Number of nodes in the Graph
    private LinkedList<Integer> adj[]; // Adjacency list
    private Queue<Integer> queue;      // Maintaining a Queue

    MapBuilder(int v){
        V = v;
        adj = new LinkedList[v];
        for(int i=0;i<v;i++){
            adj[i] = new LinkedList<>();
        }
        queue = new LinkedList<>();
    }

    node find_node (int id, ArrayList<node> nodeList){
        node n = new node("test", -1, new int[]{-1});
        for (int i = 0; i < nodeList.size(); i++){
            if (id == nodeList.get(i).ID){
                n = nodeList.get(i);
            }
        }
        return n;
    }
    
    int calc_path_cost(int w, int num_vertices){
        int cost = (num_vertices - 1) * w;
        return cost;

    }




    void addEdge(int v,int w)
    {
        adj[v].add(w);                          //adding an edge to the adjacency list (edges are bidirectional in this example)
    }

    void breadthFirst(int n, ArrayList<node> nodeList, node destination_node)
    {
        node dest_node = destination_node;
        System.out.println("Destination City: " + dest_node.name);
        boolean nodes[] = new boolean[V];       //initialize boolean array for holding the data
        int current = 0;
        String [] bfs_list = new String[20];
        LinkedList<Integer> visited = new LinkedList<Integer>();
        bfs_list[n] = nodeList.get(n).name;
        System.out.println("Root Node Selected: " + bfs_list[n]);
        System.out.println("------------------------------------------");


 
        nodes[n]=true;                  
        queue.add(n);                   //root node is added to the top of the queue
 
        while (queue.size() != 0)
        {
            n = queue.poll(); 
            visited.add(n);
            
                                    //remove the top element of the queue
            bfs_list[n] = nodeList.get(n).name;
            System.out.print(bfs_list[n]);
            System.out.println("\t\t\tCity ID: " + n + " \n"); //print the top element of the queue

            
                            
            for (int i = 0; i < adj[n].size(); i++)  //iterate through the linked list and push all neighbors into queue
            {
                
                current = adj[n].get(i);
                if (!nodes[current])                    //only insert nodes into queue if they have not been explored already
                {
                    node temp = nodeList.get(i);
                    nodes[current] = true;
                    temp = find_node(current, nodeList);
                    
                    node parent = find_node(n, nodeList);
                    for (int j = 0; j < parent.path_to_node.size(); j++){
                            temp.path_to_node.add(parent.path_to_node.get(j));
                        }
                        
                    if (!temp.path_to_node.contains(n)){
                        temp.path_to_node.add(n);
                    }
                    temp.path_to_node.add(temp.ID);
                    System.out.println("Path to node: " + temp.name + " - "  + temp.path_to_node.toString());
                    
                    queue.add(current);
                }
            }  
            if (n == dest_node.ID){
                System.out.println("**********************************************");
                System.out.println("Destination node has been found!");
                System.out.println("Destination node: " + dest_node.name);
                System.out.println("The path to the destination node is : " + dest_node.path_to_node.toString());
                int cost = calc_path_cost(1, dest_node.path_to_node.size());
                System.out.println("The total cost of the path to " + dest_node.name  +  " is " + cost);
                System.out.println("The order of the total amount of visited nodes is: " + visited);
                System.out.println("**********************************************");
                break;
            }

        }
    }
    public static void main(String[] args){
        int [][] map = new int[20][20];
        ArrayList<node> nodeList = new ArrayList<>();
        

        node c0 = new node("Oradea", 0, new int[]{1,7});
        nodeList.add(c0);

        node c1 = new node("Zerind", 1, new int[]{0,2});
        nodeList.add(c1);

        node c2 = new node("Arad", 2, new int[]{1,7,3});
        nodeList.add(c2);

        node c3 = new node("Timisoara", 3, new int[]{2,4});
        nodeList.add(c3);

        node c4 = new node("Lugoj", 4, new int[]{3,5});
        nodeList.add(c4);

        node c5 = new node("Mehadia", 5, new int[]{4,6});
        nodeList.add(c5);

        node c6 = new node("Drobeta", 6, new int[]{5, 9});
        nodeList.add(c6);

        node c7 = new node("Sibiu", 7, new int[]{0, 2, 8, 11});
        nodeList.add(c7);

        node c8 = new node("Rimnicu Vilcea", 8, new int[]{7, 9, 10});
        nodeList.add(c8);

        node c9 = new node("Craiova", 9, new int[]{6, 8, 10});
        nodeList.add(c9);

        node c10 = new node("Pitesti", 10, new int[]{8, 9, 12});
        nodeList.add(c10);

        node c11 = new node("Fagaras", 11, new int[]{7, 12});
        nodeList.add(c11);

        node c12 = new node("Bucharest", 12, new int[]{11, 10, 13, 14});
        nodeList.add(c12);

        node c13 = new node("Giurgiu", 13, new int[]{12});
        nodeList.add(c13);

        node c14 = new node("Urziceni", 14, new int[]{12, 15, 17});
        nodeList.add(c14);

        node c15 = new node("Hirsova", 15, new int[]{14, 16});
        nodeList.add(c15);

        node c16 = new node("Eforie", 16, new int[]{15});
        nodeList.add(c16);

        node c17 = new node("Vaslui", 17, new int[]{18, 14});
        nodeList.add(c17);

        node c18 = new node("Iasi", 18, new int[]{17, 19});
        nodeList.add(c18);

        node c19 = new node("Neamt", 19, new int[]{18});
        nodeList.add(c19);


        node dest_node = c12;
        MapBuilder mb = new MapBuilder(nodeList.size());

        Collections.sort(nodeList, new Comparator<node>() {
            public int compare(node n1, node n2){
                return Integer.valueOf(n1.ID).compareTo(n2.ID);
            }
        });

        
        System.out.println("------------------------------------------");
        System.out.println("Enter the name of the starting City: ");
        Scanner sc = new Scanner(System.in);
        String s_name = sc.nextLine();
        node root_node = nodeList.get(0);
        int root;        
        boolean found = false;                                               // ID of root node

        //Search for root node
        for(int i = 0; i < nodeList.size();i++){
            if (nodeList.get(i).name.equals(s_name)){
                root_node = nodeList.get(i);
                found = true;
                break;
            }
        }
        if (!found){
            System.out.println("Warning:" + s_name + " is an invalid city name.\nAlgorithm will continue with 'Oradea' as the root node.");
        }
        System.out.println("The root node is: " + root_node.name);

        for (int i = 0; i < nodeList.size(); i++){
            for (int j = 0; j < nodeList.get(i).neighbors.length; j++){
                mb.addEdge(i, nodeList.get(i).neighbors[j]);
            }
        }
        



        System.out.println("Printing out Breadth First Search Traversal");
        mb.breadthFirst(root_node.ID, nodeList, dest_node);
        
    }
}