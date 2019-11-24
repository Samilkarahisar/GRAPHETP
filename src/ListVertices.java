import java.util.ArrayList;

public class ListVertices {
    private ArrayList<Vertex> mList;    // list of vertices
    private double mValue;              // value associated to this list

    /**
     * Constructor
     */
    public ListVertices() {
        mList = new ArrayList<Vertex>();
        mValue = 0.0;
    }

    /**
     * Constructor with parameters
     *
     * @param pList
     * @param pValue
     */
    public ListVertices(ArrayList<Vertex> pList, double pValue) {
        mList = pList;
        mValue = pValue;
    }

    // Getters and Setters
    public double getValue() { return mValue; }
    public void setValue(double pValue) { mValue = pValue; }
    public ArrayList<Vertex> getList() { return mList; }
    public void setList(ArrayList<Vertex> pList) { mList = pList; }

    /**
     * @return the number of vertices
     */
    public int getNbVertices() {
        return mList.size();
    }

    /**
     * @param pIndex
     * @return the vertex at index pIndex
     */
    public Vertex getVertexAt(int pIndex) {
        return mList.get(pIndex);
    }

    /**
     * @param IdVertex
     * @return the vertex at index pIndex
     */
    public Vertex getVertexById(int IdVertex) {
        for(int i=0;i<getNbVertices();i++){
            Vertex tamp= this.getList().get(i);
            if(tamp.getId()==IdVertex)
            {
                return tamp;
            }
        }
        return null;
    }

    /**
     * @param pVertex
     */
    public void addVertex(Vertex pVertex) {
        mList.add(pVertex);
    }

    /**
     * Clone the list
     */
    @Override
    public ListVertices clone() {
        ListVertices lCloneList = new ListVertices();

        for (int i = 0; i < this.mList.size(); i++) {
            lCloneList.mList.add(this.getVertexAt(i));
        }
        lCloneList.mValue = this.mValue;
        return lCloneList;
    }

    /**
     * Display the list of vertices
     */
    @Override
    public String toString() {
        StringBuilder lResult = new StringBuilder();
        String lNewLine = System.getProperty("line.separator");
        Vertex lVertex;

        if (mList.size() == 0) {
            lResult.append("No vertex in this list !");
            lResult.append(lNewLine);
        }
        else {
            for (int i = 0; i < mList.size(); i++) {
                lVertex = (Vertex) mList.get(i);
                lResult.append(lVertex.toString());
            }
            lResult.append("Value = " + mValue);
            lResult.append(lNewLine);
        }
        return lResult.toString();
    }

    /**
     * Display the list of vertices with color
     */
    public String toStringColor() {
        StringBuilder lResult = new StringBuilder();
        String lNewLine = System.getProperty("line.separator");
        Vertex lVertex;


        if (mList.size() == 0) {
            lResult.append("No vertex in this list !");
            lResult.append(lNewLine);
        }
        else {
            for (int i = 0; i < mList.size(); i++) {
                lVertex = (Vertex) mList.get(i);
                lResult.append(lVertex.toStringColor());
            }
            lResult.append("Number of colors used = " + mValue);
            lResult.append(lNewLine);
        }
        return lResult.toString();
    }
    
    /**
     * Reset all Vertices degrees
     */
    public void ResetDegrees()
    {
    	for(int i=0; i< this.mList.size();i++)
    	{
    		this.mList.get(i).setDegree(0);
    		this.mList.get(i).setDegreeNeg(0);
    		this.mList.get(i).setDegreePos(0);
    	}
    }
}
