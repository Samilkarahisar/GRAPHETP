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
        
        for (int i=0; i<pGraph.getNbVertices() ;i++) //Je marque tous les sommets à faux
        {
        	marquage[i]=false;
        }
        
        marquage[pStartVertex]=true; // Je marque le premier sommet à true 
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
        			
        			if(marquage[idVoisin]== false) // Si ces successeurs n'ont jamais été visité 
        			{
        				marquage[idVoisin] = true;
        				pile.push(pGraph.getListVertices().getList().get(idVoisin));//On met les successeur dans la pile 
        				i = 0;
        			}
        			
        			// Si le successeur est déjà marqué, on incrémente juste 
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

        while(dGraph.getNbVertices()<(pGraph.getNbVertices()-1)){
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
            p = p + 1;
            }
        }

        int i=0;
        while(dGraph.getListVertices().getList().size()!=0){

            dGraph.getListVertices().getVertexAt(i);
            i++;
            int k=0;
            do{
                k++;
                int index=dGraph.getListAdjacent().get(i).get(k);
                Vertex V=new Vertex();
                V=pGraph.getListVertices().getVertexAt(index);
                V.setDegreeNeg(V.getDegreeNeg()-1);
                if(V.getDegreeNeg()==0){
                    dGraph.getListVertices().addVertex(V);
                    p=p+1;
                }
            }while(dGraph.getListAdjacent().get(i).get(k)!=null);
        }
        if(p==pGraph.getNbVertices())return false;
        else return true;
    }
    
}
