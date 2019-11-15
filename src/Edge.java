public class Edge implements Comparable<Edge> {
    private int mId;					// id of the edge
    private int mIndexInitialVertex;	// index of initial vertex in the list of vertices
    private int mIndexFinalVertex;		// index of final vertex in the list of vertices
    private String mNameInitialVertex;	// name of initial vertex in the list of vertices
    private String mNameFinalVertex;	// name of final vertex in the list of vertices
    private int mNbValues;				// number of values associated to the edge
    private double mValues[];			// values associated to the edge

    public Edge() {}

    /**
     * Constructor of the class with parameters
     *
     * @param pId
     * @param pIndexInitialVertex
     * @param pIndexFinalVertex
     * @param pNbValues
     * @param pValues
     */
    public Edge(int pId, int pIndexInitialVertex, int pIndexFinalVertex,
                String pNameInitialVertex, String pNameFinalVertex, int pNbValues, double[] pValues) {
        mId = pId;
        mIndexInitialVertex = pIndexInitialVertex;
        mIndexFinalVertex = pIndexFinalVertex;
        mNameInitialVertex = pNameInitialVertex;
        mNameFinalVertex = pNameFinalVertex;
        mNbValues = pNbValues;
        mValues = pValues;
    }

    // Getters and Setters
    public int getId() { return mId; }
    public void setId(int pId) { mId = pId; }
    public int getIndexInitialVertex() { return mIndexInitialVertex; }
    public void setIndexInitialVertex(int pIndexInitialVertex) { mIndexInitialVertex = pIndexInitialVertex; }
    public int getIndexFinalVertex() { return mIndexFinalVertex; }
    public void setIndexFinalVertex(int pIndexFinalVertex) { mIndexFinalVertex = pIndexFinalVertex; }
    public String getNameInitialVertex() { return mNameInitialVertex; }
    public void setNameInitialVertex(String pNameInitialVertex) { mNameInitialVertex = pNameInitialVertex; }
    public String getNameFinalVertex() { return mNameFinalVertex; }
    public void setNameFinalVertex(String pNameFinalVertex) { mNameFinalVertex = pNameFinalVertex; }
    public int getNbValues() { return mNbValues; }
    public void setNbValues(int pNbValues) { mNbValues = pNbValues; }
    public double[] getValues() { return mValues; }
    public void setValues(double[] pValues) { mValues = pValues; }

    /**
     * Get the value at position pIndex
     * @param pIndex
     * @return the value mValues[pIndex]
     */
    public double getValueAt(int pIndex) {
        if ((pIndex >= 0) && (pIndex < mNbValues)) {
            return mValues[pIndex];
        }
        return 0.0;
    }

    /**
     * Set a value, pValue, at position pIndex
     * @param pIndex
     * @param pValue
     */
    public void setValueAt(int pIndex, double pValue) {
        if ((pIndex >= 0) && (pIndex < mNbValues)) {
            mValues[pIndex] = pValue;
        }
    }

    /**
     * Get the neighbor of the vertex in undirected graph
     *
     * @param pId id of the vertex looking for its neighbor
     * @return Return the neighbor of pId
     */
    public int getVoisin(int pId) {
        if (mIndexInitialVertex == pId) return mIndexFinalVertex;
        else return mIndexInitialVertex;
    }

    /**
     * Compare this to pEdge according to mValues[0]
     *
     * @param pEdge
     * @return Return -1, 0 or 1
     */
    public int compareTo(Edge pEdge) {
        if (this.mValues[0] < pEdge.getValueAt(0)) return -1;
        else {
            if (this.mValues[0] == pEdge.getValueAt(0)) return 0;
            else return 1;
        }
    }

    /**
     * Display the edge
     */
    @Override
    public String toString() {
        StringBuilder lResult = new StringBuilder();
        String lNewLine = System.getProperty("line.separator");

        lResult.append(mId + " (" + mNameInitialVertex + "," + mNameFinalVertex + ")");
        if (mNbValues > 0) {
            lResult.append(" [");
            for (int i = 0; i < mNbValues; i++) {
                lResult.append(" " + mValues[i]);
            }
            lResult.append(" ]");
        }
        lResult.append(lNewLine);
        return lResult.toString();
    }
    public void DeleteEdge()
    {
    	try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /*@Override
	public int compareTo(Edge compare)
	{
		
	}*/
}
