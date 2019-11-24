import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Graph {
    protected String mName;             // name of the graph
    protected boolean mDirected;        // oriented or not
    protected int mNbVertices;          // number of vertices of the graph
    protected int mNbEdges;             // number of edges of the graph
    protected int mNbVertexValues;      // number of values associated to each vertex
    protected int mNbEdgeValues;        // number of values associated to each edge
    protected ListVertices mListVertices = new ListVertices();   // list of vertices
    protected ListEdges mListEdges = new ListEdges();            // list of edges
    protected ArrayList<LinkedList<Integer>> mListAdjacent = new ArrayList<LinkedList<Integer>>(); // adjacent list

    public Graph() {}

    public Graph(String pFilename) throws Exception {
        if (pFilename.endsWith("gsb")) { readGSBfile(pFilename); }
        else System.exit(0);
    }

    // Getters and Setters
    public String getName() { return mName; }
    public void setName(String pName) { mName = pName; }
    public boolean isDirected() { return mDirected; }
    public void setDirected(boolean pDirected) { mDirected = pDirected; }
    public int getNbVertices() { return mNbVertices; }
    public void setNbVertices(int pNbVertices) { mNbVertices = pNbVertices; }
    public int getNbEdges() { return mNbEdges; }
    public void setNbEdges(int pNbEdges) { mNbEdges = pNbEdges; }
    public int getNbVertexValues() { return mNbVertexValues; }
    public void setNbVertexValues(int pNbVertexValues) { mNbVertexValues = pNbVertexValues; }
    public int getNbEdgeValues() { return mNbEdgeValues; }
    public void setNbEdgeValues(int pNbEdgeValues) { mNbEdgeValues = pNbEdgeValues; }
    public ListVertices getListVertices() { return mListVertices; }
    public void setListVertices(ListVertices pListVertices) { mListVertices = pListVertices.clone(); }
    public ListEdges getListEdges() { return mListEdges; }
    public void setListEdges(ListEdges pListEdges) { mListEdges = pListEdges.clone(); }
    public ArrayList<LinkedList<Integer>> getListAdjacent() { return mListAdjacent; }

    /**
     * Clear color of vertices
     */
    public void clearColor() {
        for (int i = 0; i < mNbVertices; i++) { mListVertices.getVertexAt(i).setColor(0); }
    }

    /**
     * Read GSB graph
     *
     * @param pFileName
     */
    public void readGSBfile(String pFileName) throws Exception {
        int id1,id2;
        String lString;
        String lNameEdge = "";
        double lValue[];
        ArrayList<String> lAString;
        LinkedList<Integer> lLList = null;
        FileReader lFile = new FileReader(pFileName);
        BufferedReader lBuffer = new BufferedReader(lFile);
        Vertex lVertex = null;
        LinkedList<Integer> lVectin = null;
        Edge lEdge = null;

        lString = lBuffer.readLine();
        lAString = StringToArrayList(lString);
        mName = lAString.get(1).toString();
        lString = lBuffer.readLine();
        lAString = StringToArrayList(lString);
        if (lAString.get(1).toString().equals("oui")) { mDirected = true; }
        else mDirected = false;
        lString = lBuffer.readLine();
        lAString = StringToArrayList(lString);
        mNbVertices = Integer.parseInt((lAString.get(1)).toString());
        lString = lBuffer.readLine();
        lAString = StringToArrayList(lString);
        mNbVertexValues = Integer.parseInt((lAString.get(1)).toString());
        lString = lBuffer.readLine();
        lAString = StringToArrayList(lString);
        mNbEdges = Integer.parseInt((lAString.get(1)).toString());
        lString = lBuffer.readLine();
        lAString = StringToArrayList(lString);
        mNbEdgeValues = Integer.parseInt((lAString.get(1)).toString());
        lString = lBuffer.readLine();      // ligne de separation

        // lecture des sommets
        for (int i = 0; i < mNbVertices; i++)
        {
            lValue = new double[mNbVertexValues];
            // lecture de la ligne correspondant au sommet
            lString = lBuffer.readLine();
            lAString = StringToArrayList(lString);
            id1 = Integer.parseInt((lAString.get(0)).toString());
            lAString.remove(0);
            lNameEdge = lAString.get(0).toString();
            lAString.remove(0);
            for (int j = 0; j < mNbVertexValues; j++) {
                lValue[j] = Double.parseDouble((lAString.get(j)).toString());
            }
            // creation du sommet
            lVertex = new Vertex(id1,lNameEdge,0,0,0,0,mNbVertexValues,lValue);
            mListVertices.addVertex(lVertex);
            // creation de la liste d'adjacence correspondant au sommet
            lVectin = new LinkedList<Integer>();
            mListAdjacent.add(i,lVectin);
        }
        lString = lBuffer.readLine();      // ligne de separation
        // lecture des arcs/aretes
        for (int i = 0; i < mNbEdges; i++) {
            // lecture de la ligne correspondant a l'arc
            lString = lBuffer.readLine();
            lAString = StringToArrayList(lString);
            id1 = Integer.parseInt((lAString.get(0)).toString());
            lAString.remove(0);
            id2 = Integer.parseInt((lAString.get(0)).toString());
            lAString.remove(0);
            lValue = new double[mNbEdgeValues];
            for (int j = 0; j < mNbEdgeValues; j++) {
                lValue[j] = Double.parseDouble((lAString.get(j)).toString());
            }
            // creation de l'arc/arete
            lEdge = new Edge(i,id1,id2,mListVertices.getVertexAt(id1).getName(),mListVertices.getVertexAt(id2).getName(),mNbEdgeValues,lValue);
            mListEdges.addEdge(lEdge);
            mListVertices.getVertexAt(id1).incrementDegree();
            mListVertices.getVertexAt(id2).incrementDegree();
            // ajout de l'arc/arete a la liste d'adjacence
            lLList = (LinkedList<Integer>) mListAdjacent.get(id1);
            lLList.addLast(i);
            if (mDirected) {
                mListVertices.getVertexAt(id1).incrementDegreePos();
                mListVertices.getVertexAt(id2).incrementDegreeNeg();
            }
            else {
                if (id1 != id2) {
                    lLList = (LinkedList<Integer>) mListAdjacent.get(id2);
                    lLList.addLast(i);
                }
            }
        }
        lBuffer.close();
    }

    /**
     * Create a GSB graph
     * @return Chaine de caracteres contenant le graph GSB
     */
    public String createGSBfile() {
        StringBuilder lResult = new StringBuilder();
        String lNewLine = System.getProperty("line.separator");
        Vertex lVertex = null;
        Edge lEdge = null;
        Iterator<Integer> lIt = null;
        ArrayList<Iterator<Integer>> lListIt = new ArrayList<Iterator<Integer>>();

        // Creation de la liste des iterator
        for (int i = 0; i < mNbVertices; i++)
        {
            lIt = (mListAdjacent.get(i)).iterator();
            lListIt.add(i,lIt);
        }

        lResult.append("-------------------");
        lResult.append(lNewLine);
        lResult.append("Nom: " + mName);
        lResult.append(lNewLine);
        if (mDirected) lResult.append("Oriente: oui");
        else lResult.append("Oriente: non");
        lResult.append(lNewLine);
        lResult.append("NbSommets: " + mNbVertices);
        lResult.append(lNewLine);
        lResult.append("NbValSommet: " + mNbVertexValues);
        lResult.append(lNewLine);
        lResult.append("NbArcs: " + mNbEdges);
        lResult.append(lNewLine);
        lResult.append("NbValArc: " + mNbEdgeValues);
        lResult.append(lNewLine);
        lResult.append("---");
        lResult.append(lNewLine);
        for (int i = 0; i < mNbVertices; i++)
        {
            lVertex = (Vertex) mListVertices.getVertexAt(i);
            lResult.append(lVertex.getId() + " " + lVertex.getName());
            for (int j=0; j<lVertex.getNbValues(); j++) {
                lResult.append(" " + lVertex.getValueAt(j));
            }
            lResult.append(lNewLine);
        }
        lResult.append("---");
        lResult.append(lNewLine);
        for (int i = 0; i < mNbEdges; i++)
        {
            lEdge = (Edge) mListEdges.getEdgeAt(i);
            lResult.append(lEdge.getIndexInitialVertex() + " " + lEdge.getIndexFinalVertex());
            for (int j = 0; j < lEdge.getNbValues(); j++) {
                lResult.append(" " + lEdge.getValueAt(j));
            }
            lResult.append(lNewLine);
        }
        return lResult.toString();
    }

    /**
     * Display the graph
     */
    @Override
    public String toString() {
        StringBuilder lResult = new StringBuilder();
        String lNewLine = System.getProperty("line.separator");
        int lNumEdge;
        Vertex lVertex = null;
        Edge lEdge = null;
        Iterator<Integer> lIt = null;
        ArrayList<Iterator<Integer>> lListIt = new ArrayList<Iterator<Integer>>();

        // Creation de la liste des iterator
        for (int i = 0; i < mNbVertices; i++) {
            lIt = (mListAdjacent.get(i)).iterator();
            lListIt.add(i,lIt);
        }

        lResult.append("- GRAPH ----------------");
        lResult.append(lNewLine);
        lResult.append("Nom du graphe = " + mName);
        lResult.append(lNewLine);
        if (this.isDirected()) lResult.append("Le graphe est oriente");
        else lResult.append("Le graphe est non-oriente");
        lResult.append(lNewLine);
        lResult.append("Nombre de sommets = " + mNbVertices);
        lResult.append(lNewLine);
        lResult.append("Nombre de valeurs aux sommets = " + mNbVertexValues);
        lResult.append(lNewLine);
        if (this.isDirected()) lResult.append("Nombre d'arcs = " + mNbEdges);
        else lResult.append("Nombre d'aretes = " + mNbEdges);
        lResult.append(lNewLine);
        if (this.isDirected()) lResult.append("Nombre de valeurs aux arcs = " + mNbEdgeValues);
        else lResult.append("Nombre de valeurs aux aretes = " + mNbEdgeValues);
        lResult.append(lNewLine);
        lResult.append("---");
        lResult.append(lNewLine);
        lResult.append("Liste des sommets :");
        lResult.append(lNewLine);
        for (int i = 0; i < mNbVertices; i++) {
            lVertex = (Vertex) mListVertices.getVertexAt(i);
            lResult.append(lVertex.toString());
        }
        lResult.append("---");
        lResult.append(lNewLine);
        if (this.isDirected()) lResult.append("Liste des arcs :");
        else lResult.append("Liste des aretes :");
        lResult.append(lNewLine);
        for (int i = 0; i < mNbEdges; i++) {
            lEdge = (Edge) mListEdges.getEdgeAt(i);
            lResult.append(lEdge.toString());
        }
        lResult.append("---");
        lResult.append(lNewLine);
        if (this.isDirected()) lResult.append("Liste d'adjacence (sommet - liste sommets (num arcs)) :");
        else lResult.append("Liste d'adjacence (sommet - liste sommets (num aretes)) :");
        lResult.append(lNewLine);
        for (int i = 0; i < mNbVertices; i++) {
            lResult.append(mListVertices.getVertexAt(i).getName()+" -");
            lIt = lListIt.get(i);
            while (lIt.hasNext()) {
                lNumEdge = ((Integer) lIt.next()).intValue();
                lEdge = (Edge) mListEdges.getEdgeAt(lNumEdge);
                if (mListVertices.getVertexAt(i).getName() != lEdge.getNameFinalVertex())
                    lResult.append(" " + lEdge.getNameFinalVertex() + "(" + lNumEdge + ")");
                else lResult.append(" " + lEdge.getNameInitialVertex() + "(" + lNumEdge + ")");
            }
            lResult.append(lNewLine);
        }
        lResult.append("---------------- GRAPH -");
        lResult.append(lNewLine);
        return lResult.toString();
    }

    /**
     * Fonction interne de decomposition d'une ligne de texte en ArrayList
     * A chaque espace, un nouvel element est ajoute a l'ArrayList
     * @param pString chaine de caracteres
     * @return La liste des mots de la chaine de caracteres dans une ArrayList
     */
    protected ArrayList<String> StringToArrayList(String pString) {
        ArrayList<String> lV = new ArrayList<String>();
        StringTokenizer lSt = new StringTokenizer(pString);

        while (lSt.hasMoreTokens()) lV.add(lSt.nextToken());
        return lV ;
    }

    
}