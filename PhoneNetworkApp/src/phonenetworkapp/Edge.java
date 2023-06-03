
package phonenetworkapp;

public class Edge implements Comparable<Edge>{
    private Vertex source;
    private Vertex dest;
    private int weight;
    
    public Edge(Vertex source, Vertex dest, int weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
        source.addEdge(this);
    }
    
    public Vertex getSource() {
        return source;
    }
    
    public Vertex getDest() {
        return dest;
    }
    
    public int getWeight() {
        return weight;
    }
    
    public int compareTo(Edge other) {
        return Integer.compare(weight, other.weight);
    }
    
}
