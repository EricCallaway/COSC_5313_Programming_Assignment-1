import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.List;

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

    void addEdge(int u,int v)
    {
        adj[u].add(v);
    }

    //This function removes duplicates from the path to a node
    void remove_dups(LinkedList<String> path){
        for(int i = 0; i < path.size(); i++){
            String dup = path.get(i);
            for (int j = 1; j < path.size(); j++){
                if(path.get(j).equals(dup) && j != i){
                    path.remove(j);
                }
            }
        }
    }

    // This method takes in an integer n, which is the ID of the root node, the list of cities, and the destination node.
    void breadthFirst(int n, ArrayList<node> nodeList, node destination_node)
    {
        node dest_node = destination_node;
        System.out.println("Destination City: " + dest_node.name);
        boolean nodes[] = new boolean[V];       //initialize boolean array for holding the data
        int current = 0;
        String [] bfs_list = new String[20];
        LinkedList<String> visited = new LinkedList<String>();
        bfs_list[n] = nodeList.get(n).name;
        System.out.println("Root Node Selected: " + bfs_list[n]);
        System.out.println("------------------------------------------");
 
        nodes[n]=true;                  
        queue.add(n);                   //root node is added to the top of the queue
 
        while (queue.size() != 0)
        {
            n = queue.poll(); 
            visited.add(nodeList.get(n).name);
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
                        temp.path_to_node.add(nodeList.get(n).name);
                    }
                    temp.path_to_node.add(temp.name);
                    remove_dups(temp.path_to_node);
                    System.out.println("Path to node: " + temp.name + " - "  + temp.path_to_node.toString());
                    queue.add(current);
                }
            }  

            if (n == dest_node.ID){
                //Remove duplicates
                remove_dups(dest_node.path_to_node);

                System.out.println("**********************************************");
                System.out.println("Destination node has been found!");
                System.out.println("Destination node: " + dest_node.name);
                System.out.println("The path to the destination node is : " + dest_node.path_to_node.toString());
                int cost = calc_path_cost(1, dest_node.path_to_node.size());
                System.out.println("The total cost of the path to " + dest_node.name  +  " is " + cost);
                System.out.println("The order of all visited nodes is: " + visited);
                System.out.println("**********************************************");
                break;
            }
        }
    }

    void depthFirst(int n, ArrayList<node> nodeList, node destination_node){
        boolean nodes[] = new boolean[20];
        List<String> visited = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(n); // Push root node onto stack
        int current = 0;

        while(!stack.empty()){
            n = stack.peek();
            stack.pop();
            node curr_n = find_node(n, nodeList);

            if(nodes[n] == false){
                System.out.println("We are currently at city: " + curr_n.name + ", ID: " + n);
                nodes[n] = true;
                visited.add(curr_n.name);
            }

            for (int i = 0; i < adj[n].size(); i++){
                current = adj[n].get(i);
                if(!nodes[current]){
                    stack.push(current);
                }
            if(curr_n.ID == destination_node.ID){
                System.out.println("**********************************************");
                    System.out.println("Destination node has been found!");
                    System.out.println("List of cities explored: " + visited);
                    System.out.println("Path from " + visited.get(0) + " to " + destination_node.name + " is: " + visited);
                    int cost = calc_path_cost(1, visited.size());
                    System.out.println("The path cost is: " + cost);
                    System.out.println("**********************************************");
                    return;
                }
            }
        }
    }

    //Iterative Deepening Search Implementation
    public void ids(int limit, int n, ArrayList<node> nodeList, node destination_node){
        for(int i = 0; i <= limit; i++){
            boolean nodes[] = new boolean[20];
            List<String> visited = new ArrayList<>();
            Stack<Integer> stack = new Stack<>();

            stack.push(n); // Push root node onto stack
            int current = 0;

            while(!stack.empty()){
                n = stack.peek();
                stack.pop();
                node curr_n = find_node(n, nodeList);
                

                if(nodes[n] == false){
                    nodes[n] = true;
                    visited.add(curr_n.name);
                }

                for (int k = 0; k < adj[n].size(); k++){
                    current = adj[n].get(k);
                    if(!nodes[current]){
                        stack.push(current);
                    }
                
                if(curr_n.ID == destination_node.ID){
                    System.out.println("**********************************************");
                        System.out.println("Destination node has been found!");
                        System.out.println("List of cities explored: " + visited);
                        System.out.println("Path from " + visited.get(0) + " to " + destination_node.name + " is: " + visited);
                        int cost = calc_path_cost(1, visited.size());
                        System.out.println("The path cost is: " + cost);
                        System.out.println("**********************************************");
                        return;
                    }
                }
                i++;
                if (i == limit){
                    System.out.println("Path is not found at level: " + limit);
                    int cost = calc_path_cost(1, visited.size());
                    System.out.println("The total path cost thus far is: " + cost);
                    System.out.println("The list of visited nodes: " + visited);
                    return;
            }
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

        mb.addEdge(0, 1);
        mb.addEdge(0, 7);

        mb.addEdge(1, 0);
        mb.addEdge(1, 2);

        mb.addEdge(2, 1);
        mb.addEdge(2, 7);
        mb.addEdge(2, 3);

        mb.addEdge(3, 2);
        mb.addEdge(3, 4);

        mb.addEdge(4, 3);
        mb.addEdge(4, 5);

        mb.addEdge(5, 4);
        mb.addEdge(5, 6);

        mb.addEdge(6, 5);
        mb.addEdge(6, 9);

        mb.addEdge(7, 0);
        mb.addEdge(7, 2);
        mb.addEdge(7, 8);
        mb.addEdge(7, 11);

        mb.addEdge(8, 7);
        mb.addEdge(8, 9);
        mb.addEdge(8, 10);

        mb.addEdge(9, 6);
        mb.addEdge(9, 8);
        mb.addEdge(9, 10);

        mb.addEdge(10, 8);
        mb.addEdge(10, 9);
        mb.addEdge(10, 12);

        mb.addEdge(11, 7);
        mb.addEdge(11, 12);

        mb.addEdge(12, 11);
        mb.addEdge(12, 10);
        mb.addEdge(12, 13);
        mb.addEdge(12, 14);

        mb.addEdge(13, 12);

        mb.addEdge(14, 12);
        mb.addEdge(14, 15);
        mb.addEdge(14, 17);

        mb.addEdge(15, 14);
        mb.addEdge(15, 16);

        mb.addEdge(16, 15);

        mb.addEdge(17, 18);
        mb.addEdge(17, 14);

        mb.addEdge(18, 17);
        mb.addEdge(18, 19);

        mb.addEdge(19, 18);
        
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
        System.out.println("The destination node is: " + dest_node.name);

        for (int i = 0; i < nodeList.size(); i++){
            for (int j = 0; j < nodeList.get(i).neighbors.length; j++){
                mb.addEdge(i, nodeList.get(i).neighbors[j]);
            }
        }

        System.out.println("Printing out Breadth First Search Traversal");
        mb.breadthFirst(root_node.ID, nodeList, dest_node);
        System.out.println("------------------------------------------");
        System.out.println("Printing out Depth First Search Traversal.");
        mb.depthFirst(root_node.ID, nodeList, dest_node);
        System.out.println("------------------------------------------");
        System.out.println("Printing out iterative deepening search\nPlease enter a limit");
        int limit = sc.nextInt();
        mb.ids(limit, root_node.ID, nodeList, dest_node);
    }
}