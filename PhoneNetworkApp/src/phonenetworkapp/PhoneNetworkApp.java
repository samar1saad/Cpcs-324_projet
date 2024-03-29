package phonenetworkapp;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class PhoneNetworkApp {
    
    private Map<String, Vertex> vertices;
    private List<Edge> edges;
    
    public PhoneNetworkApp() {
        vertices = new HashMap<>();
        edges = new ArrayList<>();
    }
    
    public void createVertex(String name) {
        Vertex v = new Vertex(name);
        vertices.put(name, v);
    }
    
    public void createEdge(Vertex source, Vertex dest, int weight) {
        Edge e = new Edge(source, dest, weight);
        edges.add(e);
    }
    
    public void readGraphFromFile(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            // Skip the first two lines
            bufferedReader.readLine();
            bufferedReader.readLine();
            
            // Create the vertices
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] parts = line.split("\\s+");
                String sourceName = parts[0];
                String destName = parts[1];
                int weight = Integer.parseInt(parts[2]);
                if (!vertices.containsKey(sourceName)) {
                    createVertex(sourceName);
                }
                if (!vertices.containsKey(destName)) {
                    createVertex(destName);
                }
                Vertex source = vertices.get(sourceName);
                Vertex dest = vertices.get(destName);
                createEdge(source, dest, weight);
                line = bufferedReader.readLine();
            }
            
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void make_Graph(int n, int m) {
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
        createVertex(String.valueOf(i));
    }
    for (int i = 0; i < m; i++) {
        int u = rand.nextInt(n);
        int v = rand.nextInt(n);
        int weight = rand.nextInt(1000) + 1;
        Vertex source = vertices.get(String.valueOf(u));
        Vertex dest = vertices.get(String.valueOf(v));
        createEdge(source, dest, weight);
    }
}
    public void kruskalMST() {
        // Sort the edges by weight
        Collections.sort(edges);
        
        // Initialize the union-find data structure
        UnionFind uf = new UnionFind(vertices.size());
        
        // Initialize the resulting MST
        List<Edge> mst = new ArrayList<>();
        
        // Iterate over the sorted edges
        for (Edge e : edges) {
            Vertex source = e.getSource();
            Vertex dest = e.getDest();
            int sourceId = source.getId();
            int destId = dest.getId();
            
            // Check if the source and dest are in different components
            if (uf.find(sourceId) != uf.find(destId)) {
                // Add the edge to the MST and merge the components
                mst.add(e);
                uf.union(sourceId, destId);
            }
        }
        
        // Print the resulting MST
        System.out.println("The phone network (minimum spanning tree) generated by Kruskal algorithm is as follows:");
        for (Edge e : mst) {
            System.out.println("Office No."+e.getSource().getName() + " - " +"Office No."+ e.getDest().getName() + " : line length: " + e.getWeight());
        }
        
        // Compute and print the cost of the designed phone network
        int cost = 0;
        for (Edge e : mst) {
            cost += e.getWeight();
        }
        System.out.println("The cost of designed phone network: " + cost);
    }
    
    public void primMST() {
        // Initialize the resulting MST
        List<Edge> mst = new ArrayList<>();
        
        // Initialize the visited set and the priority queue
        boolean[] visited = new boolean[vertices.size()];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        // Add the first vertex to the visited set and add its edges to the priority queue
        Vertex v = vertices.values().iterator().next();
        visited[v.getId()] = true;
        for (Edge e : v.getEdges()) {
            pq.offer(e);
        }
        
        // Iterate until the priority queue is empty
        while (!pq.isEmpty()) {
            // Get the edge with the minimum weight
            Edge e = pq.poll();
            Vertex source = e.getSource();
            Vertex dest = e.getDest();
            
            // Check if the dest is already visited
            if (visited[dest.getId()]) {
                continue;
            }
            
            // Add the edge to the MST and mark the dest as visited
            mst.add(e);
            visited[dest.getId()] = true;
            
            // Add the dest's edges to the priority queue
            for (Edge next : dest.getEdges()) {
                if (!visited[next.getDest().getId()]) {
                    pq.offer(next);
                }
            }
        }
        
        // Print the resulting MST
        System.out.println("The phone network (minimum spanning tree) generated by min-heap based Prim algorithm is as follows:");
        for (Edge e : mst) {
            System.out.println("Office No."+e.getSource().getName() + " - " +"Office No."+ e.getDest().getName() + " : line length: " + e.getWeight());
        }
        
        // Compute and print the cost of the designed phone network
        int cost = 0;
        for (Edge e : mst) {
            cost += e.getWeight();
        }
        System.out.println("The cost of designed phone network: " + cost);
    }
    
    public static void main(String[] args) {
        PhoneNetworkApp mst = new PhoneNetworkApp();
        mst.readGraphFromFile("graph.txt");
        mst.kruskalMST();
        mst.primMST();
         System.out.println("Testing Kruskal algorithm...");
    for (int m : new int[]{10000, 15000, 25000}) {
        mst.make_Graph(1000, m);
        long start = System.currentTimeMillis();
        KruskalAlg kruskal = new KruskalAlg(mst);
        
        kruskal.displayResultingMST();
        long end = System.currentTimeMillis();
        System.out.printf("n=1000, m=%d: %d ms\n", m, end - start);
        mst = new PhoneNetworkApp();
    }
    for (int m : new int[]{15000, 25000}) {
        mst.make_Graph(5000, m);
        long start = System.currentTimeMillis();
        KruskalAlg kruskal = new KruskalAlg(mst);
        kruskal.displayResultingMST();
        long end = System.currentTimeMillis();
        System.out.printf("n=5000, m=%d: %d ms\n", m, end - start);
        mst = new PhoneNetworkApp();
    }
    for (int m : new int[]{15000, 25000}) {
        mst.make_Graph(10000, m);
        long start = System.currentTimeMillis();
        KruskalAlg kruskal = new KruskalAlg(mst);
        kruskal.displayResultingMST();
        long end = System.currentTimeMillis();
        System.out.printf("n=10000, m=%d: %d ms\n", m, end - start);
        mst = new PhoneNetworkApp();
    }
    
    // Generate random graphs and test Prim algorithm
    System.out.println("Testing Prim algorithm...");
    for (int m : new int[]{10000, 15000, 25000}) {
        mst.make_Graph(1000, m);
        long start = System.currentTimeMillis();
        MHPrimAlg prim = new MHPrimAlg(mst);
        prim.displayResultingMST();
        long end = System.currentTimeMillis();
        System.out.printf("n=1000, m=%d: %d ms\n", m, end - start);
        mst = new PhoneNetworkApp();
    }
    for (int m : new int[]{15000, 25000}) {
        mst.make_Graph(5000, m);
        long start = System.currentTimeMillis();
        MHPrimAlg prim = new MHPrimAlg(mst);
        prim.displayResultingMST();
        long end = System.currentTimeMillis();
        System.out.printf("n=5000, m=%d: %d ms\n", m, end - start);
        mst = new PhoneNetworkApp();
    }
    for (int m : new int[]{15000, 25000}) {
        mst.make_Graph(10000, m);
        long start = System.currentTimeMillis();
        MHPrimAlg prim = new MHPrimAlg(mst);
        prim.displayResultingMST();
        long end = System.currentTimeMillis();
        System.out.printf("n=10000, m=%d: %d ms\n", m, end - start);
        mst = new PhoneNetworkApp();
    }
    }
    
}




class UnionFind {
    
    private int[] parent;
    
    public UnionFind(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        int root = find(parent[x]);
        parent[x] = root;
        return root;
    }
    
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        parent[rootX] = rootY;
    }
    
}



  
