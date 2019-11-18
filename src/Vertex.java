public class Vertex implements Comparable<Vertex> {
    static int NEX_ID=0;
    private int mId;				// id of the vertex
    private String mName;			// name of the vertex
    private int mDegree;			// degree of vertex
    private int mDegreePos;			// positive degree of vertex
    private int mDegreeNeg;			// negative degree of vertex
    private int mColor;        	    // color of vertex
    private int mNbValues;		    // number of values associated to the vertex
    private double mValues[];		// values associated to the vertex
    private int parentId = NEX_ID++;
    public Vertex() {}

    /**
     * Constructor
     *
     * @param pId
     * @param pName
     * @param pDegree
     * @param pDegreePos
     * @param pDegreeNeg
     * @param pColor
     * @param pNbValues
     * @param pValues
     */
    public Vertex(int pId, String pName, int pDegree, int pDegreePos, int pDegreeNeg,
                  int pColor, int pNbValues, double[] pValues) {
        mId = pId;
        mName = pName;
        mDegree = pDegree;
        mDegreePos = pDegreePos;
        mDegreeNeg = pDegreeNeg;
        mColor = pColor;
        mNbValues = pNbValues;
        mValues = pValues;
    }

    // Getters and Setters
    public int getId() { return mId; }
    public void setId(int pId) { mId = pId; }
    public String getName() { return mName; }
    public void setName(String pName) { mName = pName; }
    public int getDegree() { return mDegree; }
    public void setDegree(int pDegree) { mDegree = pDegree; }
    public int getDegreePos() { return mDegreePos; }
    public void setDegreePos(int pDegreePos) { mDegreePos = pDegreePos; }
    public int getDegreeNeg() { return mDegreeNeg; }
    public void setDegreeNeg(int pDegreeNeg) { mDegreeNeg = pDegreeNeg; }
    public int getColor() { return mColor; }
    public void setColor(int pColor) { mColor = pColor; }
    public int getNbValues() { return mNbValues; }
    public void setNbValues(int pNbValues) { mNbValues = pNbValues; }
    public double[] getValues() { return mValues; }
    public void setValues(double[] pValues) { mValues = pValues; }
    public int getParentId(){
        return parentId;
    }
    public void setParentId(int id){
        parentId=id;
    }
    /**
     * Get the value at position pIndex
     * @param pIndex
     * @return the value mValues[pIndex]
     */
    public double getValueAt(int pIndex) {
        if ((pIndex >= 0) && (pIndex < mNbValues)) {
            return mValues[pIndex];
        }
        else return 0.0;
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

    public void incrementDegree() { mDegree = mDegree + 1; }
    public void incrementDegreePos() { mDegreePos = mDegreePos + 1; }
    public void incrementDegreeNeg() { mDegreeNeg = mDegreeNeg + 1; }

    /**
     * Compare this to pVertex according to mValues[0]
     *
     * @param pVertex
     * @return Return -1, 0 or 1
     */
    public int compareTo(Vertex pVertex) {
        if (this.mValues[0] < pVertex.getValueAt(0)) return -1;
        else {
            if (this.mValues[0] == pVertex.getValueAt(0)) return 0;
            else return 1;
        }
    }

    /**
     * Display the vertex
     */
    @Override
    public String toString() {
        StringBuilder lResult = new StringBuilder();
        String lNewLine = System.getProperty("line.separator");
        boolean lDisplayDegrees = false;

        lResult.append(mName);
        if (lDisplayDegrees) {
            lResult.append(" (" + mDegree + "=" + mDegreePos + "+" + mDegreeNeg + ")");
        }
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

    /**
     * Display the vertex color
     */
    public String toStringColor() {
        StringBuilder lResult = new StringBuilder();
        String lNewLine = System.getProperty("line.separator");

        lResult.append(mName + " [" + mColor + "]");
        lResult.append(lNewLine);
        return lResult.toString();
    }

}
