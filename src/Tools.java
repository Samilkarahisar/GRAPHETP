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

        			Edge etest=pGraph.getListEdges().getEdgeByID(pGraph.getListAdjacent().get(pile.peek().getId()).get(i));
        			int idVoisin =etest.getVoisin(pile.peek().getId());
        			
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



    public static ListEdges Kruskal1(Graph pGraph)
    {
        ArrayList<ArrayList<Integer>> Ensembles = CreerEnsemble(pGraph);


        ListEdges Croissant = pGraph.getListEdges().clone();

        Collections.sort(Croissant.getList(), new Comparator<Edge>(){
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.compareTo(e2);
            }
        });

        ListEdges T = new ListEdges();
        for(int i = 0 ;i<Croissant.getList().size();i++)
        {
            int IdEnsInitial = Find(Croissant.getList().get(i).getIndexInitialVertex(),Ensembles);
            int IdEnsFinal = Find(Croissant.getList().get(i).getIndexFinalVertex(),Ensembles);
            if( IdEnsInitial != IdEnsFinal)
            {
                T.addEdge(Croissant.getList().get(i));
                Union(IdEnsInitial,IdEnsFinal,Ensembles);
            }
        }
        System.out.println(T.getNbEdges());
        int sum=0;
        for(int z =0 ; z< T.getList().size();z++){
            sum += T.getList().get(z).getValues()[0];
        }
        System.out.println("Somme Kruskal1 :"+sum);
        return T;
    }

    private static ArrayList<ArrayList<Integer>>CreerEnsemble(Graph pGraph)
    {
        ArrayList<ArrayList<Integer>> EnsVertex = new ArrayList<ArrayList<Integer>>();
        for(Vertex ve:pGraph.getListVertices().getList())
        {
            ArrayList<Integer> Ltamp = new ArrayList<Integer>();
            Ltamp.add(ve.getId());
            EnsVertex.add(Ltamp);
        }
        return EnsVertex;
    }

    public static int Find(int IndexVertex,ArrayList<ArrayList<Integer>> Ens)
    {
        for(ArrayList<Integer> A:Ens)
        {
            if(A.contains(IndexVertex))
            {
                return Ens.indexOf(A);
            }
        }
        return -1;
    }

    public static void Union(int Id1,int Id2, ArrayList<ArrayList<Integer>> Ens)
    {
        for(int i=0;i<Ens.get(Id2).size();i++)
        {
            Ens.get(Id1).add(Ens.get(Id2).get(i));
        }
        Ens.remove(Id2);
    }


    public static ListEdges Kruskal2(Graph pGraph)
    {
        Graph dGraph= new Graph();
    //il faut copier les arretes et sommets de pGraph ici

       // int x=pGraph.getNbVertices();
        for(Vertex v:pGraph.getListVertices().getList()) {
                dGraph.getListVertices().addVertex(v);
                dGraph.getListAdjacent().add(pGraph.getListAdjacent().get(v.getId()));

        }

        for(Edge e:pGraph.getListEdges().getList()){
                dGraph.getListEdges().addEdge(e);
        }

        dGraph.setNbVertices(pGraph.getNbVertices());
        dGraph.setNbEdges(pGraph.getNbEdges());

        ListEdges COPIE = dGraph.getListEdges().clone();

        Collections.sort(COPIE.getList(), new Comparator<Edge>(){
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.compareTo(e2);
            }
        });
        Collections.sort(COPIE.getList(),Collections.reverseOrder()); //Tri dans l'ordre décroissant

        int i = 0;

        while(dGraph.getListEdges().getNbEdges()> pGraph.getNbVertices())//On s'arrête quand on a plus que n-1 arêtes
        {
            int nbEdgesd = dGraph.getListEdges().getNbEdges();
            int nbVertexp = pGraph.getNbVertices();
            Edge integrite = new Edge();
            Edge eTamp=COPIE.getEdgeAt(i);
            dGraph.getListEdges().RemoveEdgeAt(eTamp.getId());
           // dGraph.getListEdges().getList().add(eTamp.getId(),integrite);
            dGraph.setNbEdges(dGraph.getNbEdges()-1);

            int x=0;
           do{
                if (eTamp.getId() == dGraph.getListAdjacent().get(eTamp.getIndexInitialVertex()).get(x).intValue()){
                    dGraph.getListAdjacent().get(eTamp.getIndexInitialVertex()).remove(x);
                  break;
                }
                x++;
            }while(x<dGraph.getListAdjacent().get(eTamp.getIndexInitialVertex()).size());

            int y=0;
            do{
                if (eTamp.getId() == dGraph.getListAdjacent().get(eTamp.getIndexFinalVertex()).get(y).intValue()){
                    dGraph.getListAdjacent().get(eTamp.getIndexFinalVertex()).remove(y);
                    break;
                }
                y++;
            }while(y<dGraph.getListAdjacent().get(eTamp.getIndexFinalVertex()).size());

            dGraph.setNbEdges(dGraph.getListEdges().getNbEdges());
            dGraph.setNbVertices(dGraph.getListVertices().getNbVertices());

            if(!EstConnexe(dGraph,0))//Si le graphe sans l'arête n'est plus connexe
            {
                dGraph.getListEdges().addEdge(eTamp);
            }
            i++;
        }


        //Pour enlever les nulls
        Iterator<Edge> ed = dGraph.getListEdges().getList().iterator();
        /*while( ed.hasNext() ) {

            Edge edg = ed.next();

            if( edg.getId() == 0 ) { // une condition qui indique que l'on doit retirer l'élément
                ed.remove();
            }
        }*/

        System.out.println(dGraph.getListEdges().getNbEdges());

        int sum=0;
        for(int z =0 ; z< dGraph.getListEdges().getList().size();z++){
            sum += dGraph.getListEdges().getList().get(z).getValues()[0];
        }
        System.out.println("Somme Kruskal2 :"+sum);
        return dGraph.getListEdges();
    }

    public static boolean EstConnexe(Graph pGraph,int i){
        int nb=0;
        nb=parcoursProfondeur(pGraph,i).getNbVertices();
        if(pGraph.getNbVertices()==nb)return true;
        else return false;
    }
    //on a deux types de connexe, weakly connected et fortement connexe.
    //un graphe orienté est connexe si le graphe non orienté est connexe.

    /*
    Pour un graphe orienté, on parle de connexité si en oubliant l'orientation des arêtes, le graphe est connexe.
    On parle de forte connexité s'il existe un chemin orienté depuis tout nœud u vers tout nœud v.
    */


    public static ListEdges Prim(Graph pGraph,int i){
        Vertex tamp = pGraph.getListVertices().getList().get(i);
        Graph R = new Graph();
        R.getListVertices().addVertex(tamp);
        R.getListAdjacent().add(pGraph.getListAdjacent().get(tamp.getId()));
        R.getListEdges().setList(pGraph.getListEdges().getList());

        ListEdges T = new ListEdges();

        ListVertices nonR = pGraph.getListVertices().clone();

        //Ici sa supprime aussi dans pGraph a cause du set avant apparament ...
        nonR.getList().remove(tamp.getId());


        //int k = tamp.getId();
        while(pGraph.getNbVertices() != R.getListVertices().getList().size())
        {
            int nbP = pGraph.getNbVertices();
            int nbR = R.getListVertices().getList().size();
            ListEdges Ltamp = new ListEdges();
            int j;

            for (j=0;j<R.getListAdjacent().size();j++)//Pour parcourir toutes les arêtes de R
            {
                for (int l = 0; l < R.getListAdjacent().get(j).size(); l++)//l est la liste des arc adjacent au noeud j de R
                {
                    for (int n = 0; n < nonR.getNbVertices(); n++)//n est la liste des noeuds de nonR
                    {
                        Edge eTamp= R.getListEdges().getEdgeByID(R.getListAdjacent().get(j).get(l).intValue());
                        if(pGraph.mDirected == false)//Si le graph n'est pas orienté
                        {
                            if(eTamp.getIndexFinalVertex() == nonR.getList().get(n).getId())
                            {
                                //Je récupère toutes les arêtes qui vont de R à nonR
                                Ltamp.getList().add(eTamp);
                            }
                            if (eTamp.getIndexInitialVertex() == nonR.getList().get(n).getId())
                            {
                                //Je récupère les arêtes qui vont de nonR à R
                                Ltamp.getList().add(eTamp);
                            }
                        }else //Si le graph est orienté je vérifie si le noeud Final est dans nonR
                        {
                            if (eTamp.getIndexFinalVertex() == nonR.getList().get(n).getId()) //Si le noeud de Final dans l'arête qui part de R == un des noeuds dans nonR
                            {
                                //Je récupère toutes les arêtes qui vont de R à nonR
                                Ltamp.getList().add(eTamp);
                            }
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

            //On ajoute l'arête de poids minimum à T
            try{
                T.getList().add(Ltamp.getList().get(0));
            }catch(Exception e)
            {
                System.out.println("ERREUR : ");
                System.out.println("l'algorithme n'a pas trouvé d'arête pouvant être ajouté à l'arbre.");
                System.out.println("Il se peut que ce soit du au fait que votre graphe est orienté est que par conséquent, en partant de ce sommet, aucun chemin n'est possible");
                System.out.println("SOLUTION : ");
                System.out.println("Essayez de partir d'un autre noeud");
            }
            int IdNoeudFinal = Ltamp.getEdgeAt(0).getIndexFinalVertex();
            int IdNoeudInitial = Ltamp.getEdgeAt(0).getIndexInitialVertex();


            //On ajoute le noeud final de cette arête à R avec sa liste d'adjacense
            Vertex vFinal = pGraph.getListVertices().getVertexById(IdNoeudFinal);
            Vertex vInitial = pGraph.getListVertices().getVertexById(IdNoeudInitial);


            //Vu qu'on a ajouter dans R, on retire de nonR
            for(int g=0;g<nonR.getNbVertices();g++)
            {
                int idCourant = nonR.getList().get(g).getId();
                if(idCourant == IdNoeudFinal)//Si c'est le noeud Final qui est dans nonR
                {
                    R.getListVertices().getList().add(vFinal);
                    R.getListAdjacent().add(pGraph.getListAdjacent().get(vFinal.getId()));
                    nonR.getList().remove(g);
                }
                if(idCourant==IdNoeudInitial)//Si c'est le noeud Initial qui est dans nonR
                {
                    R.getListVertices().getList().add(vInitial);
                    R.getListAdjacent().add(pGraph.getListAdjacent().get(vInitial.getId()));
                    nonR.getList().remove(g);
                }
            }
        }


        System.out.println(T.getNbEdges());
        int sum=0;
        for(int z =0 ; z< T.getList().size();z++){
            sum += T.getList().get(z).getValues()[0];
        }
        System.out.println("Somme Prim :"+sum);
        return T;
    }

    public static ListEdges DCMSTKruskal(Graph pGraph,int degmax){


        List<ArrayList<Integer>> Test = new ArrayList<ArrayList<Integer>>();


            ArrayList<ArrayList<Integer>> Ensembles = CreerEnsemble(pGraph);

        ListEdges Croissant = pGraph.getListEdges().clone();

        Collections.sort(Croissant.getList(), new Comparator<Edge>(){
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.compareTo(e2);
            }
        });


        for(Vertex v:pGraph.getListVertices().getList()) {
            try {
                Test.get(v.getId());
            } catch ( IndexOutOfBoundsException e ) {
                ArrayList<Integer> g = new ArrayList<Integer>();
                g.add(1);
                Test.add(v.getId() , g);
            }
        }


        ListEdges T = new ListEdges();
        for(int i = 0 ;i<Croissant.getList().size();i++)
        {
            int ab=Croissant.getList().get(i).getIndexInitialVertex();

            int c=Test.get(ab).size();
            int ty=Croissant.getList().get(i).getIndexFinalVertex();
            int d=Test.get(ty).size();
            int o=9;
            if((c-1)==degmax||(d-1)==degmax){
                continue;
            }else {

                int IdEnsInitial = Find(Croissant.getList().get(i).getIndexInitialVertex(), Ensembles);
                int IdEnsFinal = Find(Croissant.getList().get(i).getIndexFinalVertex(), Ensembles);
                if (IdEnsInitial != IdEnsFinal) {
                    T.addEdge(Croissant.getList().get(i));
                    Test.get(ab).add(Croissant.getList().get(i).getId());
                    Test.get(ty).add(Croissant.getList().get(i).getId());

                    Union(IdEnsInitial, IdEnsFinal, Ensembles);
                }
            }
        }
        System.out.println(T.getNbEdges());
        int sum=0;
        for(int z =0 ; z< T.getList().size();z++){
            sum += T.getList().get(z).getValues()[0];
        }
        System.out.println("Somme Kruskal1 :"+sum);
        return T;

    }
}
