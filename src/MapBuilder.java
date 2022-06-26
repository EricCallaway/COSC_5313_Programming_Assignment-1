// import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MapBuilder{

    public static void sort(ArrayList<node> nodeList){

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

        Collections.sort(nodeList, new Comparator<node>() {
            public int compare(node n1, node n2){
                return Integer.valueOf(n1.ID).compareTo(n2.ID);
            }
        });

        for(int i=0;i<map.length;i++){
            // nodeList.get(i);
            for(int j=0;j<map.length;j++){
                for(int k=0;k<nodeList.get(i).neighbors.length;k++){
                    if(j == nodeList.get(i).neighbors[k]){
                        map[i][j] = 1;
                    }
                }
            }
        }
        
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map.length;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}