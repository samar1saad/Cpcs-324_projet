
package phonenetworkapp;
import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private static int nextId = 0;
    
    private int id;
    private String name;
    private List<Edge> edges;
    
    public Vertex(String name) {
        this.id = nextId++;
        this.name = name;
        this.edges = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Edge> getEdges() {
        return edges;
    }
    
    public void addEdge(Edge e) {
        edges.add(e);
    }
    
    public String toString() {
        return name;
    }
    
}
