package phonenetworkapp;
public class KruskalAlg extends MSTAlgorithm{
    private PhoneNetworkApp graph;
    private int cost;
    
    public KruskalAlg(PhoneNetworkApp graph) {
        this.graph = graph;
    }

  
    
    public void displayResultingMST() {
        graph.kruskalMST();
    }
    
    public int getCost() {
        return cost;
}
    
}
