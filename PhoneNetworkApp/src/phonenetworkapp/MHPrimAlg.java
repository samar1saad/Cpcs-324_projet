
package phonenetworkapp;
public class MHPrimAlg extends MSTAlgorithm{
    private PhoneNetworkApp graph;
    private int cost;
    
    public MHPrimAlg(PhoneNetworkApp graph) {
        this.graph = graph;
    }

  
    
    public void displayResultingMST() {
        graph.primMST();
    }
    
    public int getCost() {
        return cost;
    }
    
}
