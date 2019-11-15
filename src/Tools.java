import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
    	ListEdges SortList=pGraph.getListEdges();
    	
    	
    	SortList.getList().sort(null);
    	
    	pGraph.getListEdges().RemoveAllEdges();
    	pGraph.getListVertices().ResetDegrees();
    	
    	int NbVertices = pGraph.getListVertices().getNbVertices();
    	while (pGraph.getListEdges().getList().size() < NbVertices -1)
    	{
    		Edge courant = SortList.getEdgeAt(i);
    		//Rajouter un atribut "reseau de sommet" dans sommet 
    		//Tous les sommets d'un m�me r�seau ont la m�me valeur dans "R�seau de sommet"
    	}
    	for(int i=0;i< SortList.getNbEdges();i++)
    	{
    		Vertex VertexPointe = pGraph.getListVertices().getVertexAt(SortList.getEdgeAt(i).getIndexFinalVertex());//Sommet vers lequel pointe notre arc courant
    		//if(VertexPointe)>0)//Si il a deja des arcs entrant
    		{
    			
    		}
    		pGraph.mListEdges.addEdge(SortList.getEdgeAt(i));//J'ajoute mon ar�te
    		pGraph.mListVertices.getVertexAt(SortList.getEdgeAt(i).getIndexInitialVertex()).incrementDegree();// Je met a jour le degr�e du sommet initial de l'ar�te
    		pGraph.mListVertices.getVertexAt(SortList.getEdgeAt(i).getIndexInitialVertex()).incrementDegreePos();// Je met a jour le degr�e Positif du sommet Initial de l'ar�te
    		pGraph.mListVertices.getVertexAt(SortList.getEdgeAt(i).getIndexFinalVertex()).incrementDegreeNeg();// Je met a jour le degr�e N�gatif du sommet Final de l'ar�te
    		for (int j=0;j<NbVertices;j++)
    		{
    			if(pGraph.mListVertices.getVertexAt(j).getDegree()>1) {
    				
    			}
    		}
    		
    	}
    	//Trier la liste

    	//Comment g�rer les cycles ?
		return null;
    }
    
    
}
