import java.util.ArrayList;

public class ListEdges {
    private ArrayList<Edge> mList;   // list of edges
    private double mValue;           // value associated to this list

    /**
     * Constructor
     */
    public ListEdges() {
        mList = new ArrayList<Edge>();
        mValue = 0.0;
    }

    /**
     * Constructor with parameters
     *
     * @param pList
     * @param pValue
     */
    public ListEdges(ArrayList<Edge> pList, double pValue) {
        mList = pList;
        mValue = pValue;
    }

    // Getters and Setters
    public double getValue() { return mValue; }
    public void setValue(double pValue) { mValue = pValue; }
    public ArrayList<Edge> getList() { return mList; }
    public void setList(ArrayList<Edge> pList) { mList = pList; }

    /**
     * @return the number of edges
     */
    public int getNbEdges() { return mList.size(); }

    /**
     * @param pIndex
     * @return the edge at index pIndex
     */
    public Edge getEdgeAt(int pIndex) { return mList.get(pIndex); }

    /**
     * @param pEdge
     */
    public void addEdge(Edge pEdge) { mList.add(pEdge); }

    /**
     * Clone the list
     */
    @Override
    public ListEdges clone() {
        ListEdges lCloneList = new ListEdges();

        for (int i = 0; i < this.mList.size(); i++) {
            lCloneList.mList.add(this.getEdgeAt(i));
        }
        lCloneList.mValue = this.mValue;
        return lCloneList;
    }

    /**
     * Display the list of edges
     */
    @Override
    public String toString() {
        StringBuilder lResult = new StringBuilder();
        String lNewLine = System.getProperty("line.separator");
        Edge lEdge;

        if (mList.size() == 0) {
            lResult.append("No edge in this list !");
            lResult.append(lNewLine);
        }
        else {
            for (int i = 0; i < mList.size(); i++) {
                lEdge = (Edge) mList.get(i);
                lResult.append(lEdge.toString());
            }
            lResult.append("Value = " + mValue);
            lResult.append(lNewLine);
        }
        return lResult.toString();
    }
    
    /**
     * Delete an Edge
     */
    public void RemoveEdgeAt(int val)
    {
    	this.mList.remove(val);
    }
    
    /**
     * Delete all Edges
     */
    public void RemoveAllEdges()
    {
    	for (int i=0;i<this.mList.size();i++)
    	{
    		this.mList.remove(i);
    	}
    }
}
