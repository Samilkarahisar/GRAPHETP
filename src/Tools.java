import java.util.*;

public class Tools {

    static final int INFINI = 999999;

    /**
     * Parcours en largeur
     *
     * @param pGraph
     * @param pStartVertex
     * @return liste des sommets dans l'ordre de traitement
     */
    public static ListVertices parcoursLargeur(Graph pGraph, int pStartVertex) {
        ListVertices lListVertices = new ListVertices();
        Queue<Vertex> file = new LinkedList<Vertex>();
        
        boolean [] tabMarque = new boolean[pGraph.getNbVertices()];
        
        int p=1;
        for(int i=0; i<pGraph.getNbVertices(); i++)
        {
                tabMarque[i]=false;
        }
        
        tabMarque[pStartVertex]= true;
        file.add((pGraph.getListVertices().getVertexAt(pStartVertex)));
        
        while(!file.isEmpty())
        {
                Vertex pCurrentVertex = file.element();
                for(int i=0; i<pGraph.getListAdjacent().get(pCurrentVertex.getId()).size(); i++)
                {
                        if(tabMarque[pGraph.getListEdges().getEdgeAt(pGraph.getListAdjacent().get(pCurrentVertex.getId()).get(i)).getVoisin(pCurrentVertex.getId())]==false)

                            {

                                tabMarque[pGraph.getListEdges().getEdgeAt(pGraph.getListAdjacent().get(pCurrentVertex.getId()).get(i)).getVoisin(pCurrentVertex.getId())]=true;
                                file.add(pGraph.getListVertices().getVertexAt(pGraph.getListEdges().getEdgeAt(pGraph.getListAdjacent().get(pCurrentVertex.getId()).get(i)).getVoisin(pCurrentVertex.getId())));

                        }
                }
                lListVertices.addVertex(pGraph.getListVertices().getVertexAt(pCurrentVertex.getId()));
                file.remove();
                p++;
        }

        return lListVertices;
    }

    /**
     * Parcours en profondeur
     *
     * @param pGraph
     * @param pStartVertex
     * @return liste des sommets dans l'ordre de traitement
     */
    public static ListVertices parcoursProfondeur(Graph pGraph, int pStartVertex) 
    {
        ListVertices lListVertices = new ListVertices();
        Boolean marquage[]=new Boolean[pGraph.getNbVertices()];		
        Stack <Vertex>pile=new Stack<Vertex>();
        
        for (int i=0; i<pGraph.getNbVertices() ;i++) //Je marque tous les sommets � faux
        {
        	marquage[i]=false;
        }
        
        marquage[pStartVertex]=true; // Je marque le premier sommet � true 
        pile.push(pGraph.getListVertices().getVertexAt(pStartVertex)); // Je met le premier sommet dans la pile 
        
        while(!pile.isEmpty())
        {
        	Vertex pCurrentVertex=pile.peek();
        	
        	if(pGraph.getListAdjacent().get(pile.peek().getId()).size() >0 )//Si notre sommet en haut de pile a des adjacents
        	{
        		
        		int nbSuccesseurs= pGraph.getListAdjacent().get(pile.peek().getId()).size();
        		int i=0;
        		
        		while (i<nbSuccesseurs) // Tant que notre noeud courant a des successeurs
        		{
        			
        			int idVoisin = pGraph.getListEdges().getEdgeAt(pGraph.getListAdjacent().get(pile.peek().getId()).get(i)).getVoisin(pile.peek().getId());
        			
        			if(marquage[idVoisin]== false) // Si ces successeurs n'ont jamais �t� visit� 
        			{
        				marquage[idVoisin] = true;
        				pile.push(pGraph.getListVertices().getList().get(idVoisin));//On met les successeur dans la pile 
        				i = 0;
        			}
        			
        			// Si le successeur est d�j� marqu�, on incr�mente juste 
        			else { i++;} 
        			nbSuccesseurs = pGraph.getListAdjacent().get(pile.peek().getId()).size(); // On met a jour le nombre de successeur avec le nouveau noeud au sommet de la pile 	
        		}
        	}
        	lListVertices.addVertex(pile.peek());
        	pile.pop();
        }
        return lListVertices;
    }
    
    
    public static ListEdges Kruskal(Graph pGraph)
    {
    	Collections.sort(pGraph.getListEdges().getList(), new Comparator<Edge>(){
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.compareTo(e2);
            }
        });

        Graph dGraph= new Graph();
        int i =0;

        while(dGraph.getListEdges().getNbEdges()<(pGraph.getNbVertices()-1)){
            dGraph.getListEdges().addEdge(pGraph.getListEdges().getEdgeAt(i));

            if(Circuit(dGraph)){
                dGraph.getListEdges().getEdgeAt(i).DeleteEdge();
                System.out.println("ok");
            }else{
                //dGraph.getListEdges().addEdge(pGraph.getListEdges().getEdgeAt(i));
                System.out.println("ok2");
            }
            i++;
        }


        return dGraph.getListEdges();
    }

    public static boolean Circuit(Graph pGraph){
        Graph dGraph= new Graph();
        int p=0;
        for(Vertex v:pGraph.getListVertices().getList()) {

            if (v.getDegreeNeg()==0){
                dGraph.getListVertices().addVertex(v);
                dGraph.getListAdjacent().add(pGraph.getListAdjacent().get(v.getId()));

                p = p + 1;
                System.out.println("foreach");
            }
        }

        int i=0;
        while(dGraph.getListVertices().getList().size()!=0){
            int k=0;
            do{

                if(pGraph.getListAdjacent().get(i).size()==0)
                {
                    break;
                }
                int idArc=pGraph.getListAdjacent().get(i).get(k).intValue();
                int idSuc=pGraph.getListEdges().getEdgeAt(idArc).getIndexFinalVertex();

                Vertex Successeur=new Vertex();
                Successeur=pGraph.getListVertices().getVertexAt(idSuc);
                Successeur.setDegreeNeg(Successeur.getDegreeNeg()-1);
                if(Successeur.getDegreeNeg()==0){
                    dGraph.getListVertices().addVertex(Successeur);
                    dGraph.getListAdjacent().add(pGraph.getListAdjacent().get(Successeur.getId()));
                    p=p+1;
                }
                k++;

            }while(k!=dGraph.getListAdjacent().get(i).size());

            dGraph.getListVertices().getList().remove(0);
            i++;
        }
        if(p==pGraph.getNbVertices())return false;
        else return true;
    }

    public static ListEdges Kruskal2(Graph pGraph)
    {

        Graph dGraph= new Graph();
    //il faut copier les arretes et sommets de pGraph ici

        int x=pGraph.getNbVertices();
        for(Vertex v:pGraph.getListVertices().getList()) {
                dGraph.getListVertices().addVertex(v);
                dGraph.getListAdjacent().add(pGraph.getListAdjacent().get(v.getId()));

        }
        for(Edge e:pGraph.getListEdges().getList()){
                dGraph.getListEdges().addEdge(e);
        }

        dGraph.setNbVertices(pGraph.getNbVertices());
        dGraph.setNbEdges(pGraph.getNbEdges());

        Collections.sort(dGraph.getListEdges().getList(), new Comparator<Edge>(){
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.compareTo(e2);
            }
        });
        Collections.sort(dGraph.getListEdges().getList(),Collections.reverseOrder()); //Tri dans l'ordre d�croissant

        int i = 0;
        while(dGraph.getListEdges().getNbEdges()>= pGraph.getNbVertices())
        {
            Edge temp=dGraph.getListEdges().getEdgeAt(i);
            dGraph.getListEdges().RemoveEdgeAt(i);
            dGraph.setNbEdges(dGraph.getListEdges().getNbEdges());
            dGraph.getListAdjacent().remove(temp.getIndexInitialVertex());
            dGraph.getListAdjacent().remove(temp.getIndexFinalVertex());
            dGraph.getListVertices().getVertexAt(temp.getIndexInitialVertex());
            dGraph.getListVertices().getVertexAt(temp.getIndexFinalVertex());

            if(!EstConnexe(dGraph,0))
            {
                dGraph.getListEdges().addEdge(temp);
            }
            i++;
        }
        return dGraph.getListEdges();
    }

    public static boolean EstConnexe(Graph pGraph,int i){
        int nb=0;
        nb=parcoursProfondeur(pGraph,i).getNbVertices();
        System.out.println(nb);
        if(pGraph.getNbVertices()==nb)return true;
        else return false;
    }
    //on a deux types de connexe, weakly connected et fortement connexe.
    //un graphe orient� est connexe si le graphe non orient� est connexe.

    /*
    Pour un graphe orient�, on parle de connexit� si en oubliant l'orientation des ar�tes, le graphe est connexe.
    On parle de forte connexit� s'il existe un chemin orient� depuis tout n�ud u vers tout n�ud v.
    */


    public static ListEdges Prim(Graph pGraph,int i){
        Vertex tamp = pGraph.getListVertices().getList().get(i);
        Graph R = new Graph();
        R.getListVertices().addVertex(tamp);
        R.getListAdjacent().add(pGraph.getListAdjacent().get(tamp.getId()));
        R.getListEdges().setList(pGraph.getListEdges().getList());

        ListEdges T = new ListEdges();

        ListVertices nonR = new ListVertices();
        nonR.setList(pGraph.getListVertices().getList());

        //Ici sa supprime aussi dans pGraph a cause du set avant apparament ...
        nonR.getList().remove(tamp.getId());

        int k = tamp.getId();
        while(pGraph.getNbVertices() != R.getListVertices().getList().size())
        {
            int nbP = pGraph.getNbVertices();
            int nbR = R.getListVertices().getList().size();
            ListEdges Ltamp = new ListEdges();
            int j;
            for (j=0;j<R.getListAdjacent().size();j++)//Pour parcourir toutes les ar�tes de R
            {
                for (int l = 0; l < R.getListAdjacent().get(j).size(); l++)//l est la liste des arc adjacent au noeud j de R
                {
                    for (int n = 0; n < nonR.getNbVertices(); n++)//n est la liste des noeuds de nonR
                    {
                        Edge eTamp= R.getListEdges().getEdgeByID(R.getListAdjacent().get(j).get(l).intValue());
                        if (R.getListEdges().getEdgeByID(R.getListAdjacent().get(j).get(l).intValue()).getIndexFinalVertex() == nonR.getList().get(n).getId()) //Si le noeud de Final dans l'ar�te qui part de R == un des noeuds dans nonR
                        {
                            //Je r�cup�re toutes les ar�tes qui vont de R � nonR
                            Ltamp.getList().add(eTamp);
                        }
                    }

                }
            }
            Collections.sort(Ltamp.getList(), new Comparator<Edge>() {
                @Override
                public int compare(Edge e1, Edge e2) {
                    return e1.compareTo(e2);
                }
            });

            //On ajoute l'ar�te de poids minimum � T
            T.getList().add(Ltamp.getList().get(0));
            int IdNoeudFinal = Ltamp.getEdgeAt(0).getIndexFinalVertex();

            //On ajoute le noeud final de cette ar�te � R avec sa liste d'adjacense
            //L'Id du vertex ne correspond pas a son indice dans la liste...

            R.getListVertices().getList().add(pGraph.getListVertices().getVertexById(IdNoeudFinal));
            R.getListAdjacent().add(pGraph.getListAdjacent().get(IdNoeudFinal));

            //Vu qu'on a ajouter dans R, on retire de nonR
            for(int g=0;g<nonR.getNbVertices();g++)
            {
                if(nonR.getList().get(g).getId()==IdNoeudFinal)
                {
                    nonR.getList().remove(g);
                }
            }
        }
        return T;
    }
}
