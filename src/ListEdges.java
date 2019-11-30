import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public void sort()
    {
        Collections.sort(mList, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.compareTo(e2);
            }
        });
        ArrayList<Edge> edgeListCopy = new ArrayList<Edge>();
        for(int i=0; i<mList.size(); i++)
        {
            edgeListCopy.add(mList.get(mList.size()-i-1));
        }
        mList=edgeListCopy;
    }

    @Override
    public ListEdges clone() {
        ListEdges lCloneList = new ListEdges();

        for (int i = 0; i < this.mList.size(); i++) {
            lCloneList.mList.add(this.getEdgeAt(i).clone());
        }
        lCloneList.mValue = this.mValue;
        return lCloneList;
    }


    public double getPoids()
    {
        double somme=0;
        for(int i=0; i<this.getNbEdges(); i++)
        {
            somme+=this.getEdgeAt(i).getValueAt(0);
        }
        return somme;
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

    public Edge getEdgeByID(int IdEdge){
        for(int i=0;i<this.getNbEdges();i++)
        {
            Edge tamp = this.getList().get(i);
            if(tamp.getId()==IdEdge)
            {
                return tamp;
            }
        }
        return null;
    }
    /**
     * @param pEdge
     */
    public void addEdge(Edge pEdge) { mList.add(pEdge); }



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
        //mList.removeAll(Collection<Edge> c);
        /*
        for (Edge e: mList

             ) {
            mList.remove(e);
        }
        */

    }
}
