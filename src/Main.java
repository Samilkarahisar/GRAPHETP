public class Main {
    public static void main(String[] args) throws Exception {
        /**
         * Affiche un graphe
         */
        Graph lGraph1 = new Graph("data/GrapheStructureNonOriente.gsb");
        System.out.println(lGraph1.toString());
        Graph lGraph12 = new Graph("data/GrapheStructureOriente.gsb");
        System.out.println(lGraph12.toString());
        Graph lGraph2 = new Graph("data/Deneme.gsb");

        /**
         * Parcours en largeur et en profondeur d'un graphe
         * */

        System.out.println("- PARCOURS LARGEUR -----");
        ListVertices lListVertices1 = Tools.parcoursLargeur(lGraph12,0);
        System.out.print(lListVertices1.toString());
        System.out.println("- PARCOURS PROFONDEUR --");
        ListVertices lListVertices2 = Tools.parcoursProfondeur(lGraph12,0);
        System.out.print(lListVertices2.toString());

        /*
        *   Pour éviter toute erreur, on recharge le graphe avant chaque éxecution de nos algorithme.
        *   On a des valeurs très petites donc on fait le choix de lancer 100 000 fois nos algorithme et de prendre la moyenne
        */
        String file = "data/str1009.gsb";
        Graph lGraph21 = new Graph(file);
        System.out.println(lGraph21.toString());
        System.out.println("- KRUSKAL 1 --");
        ListEdges lListeEdges1 = Tools.Kruskal1(lGraph21);
        //System.out.println(lListeEdges1.toString());

        System.out.println("- KRUSKAL 2 --");
        Graph lGraph22 = new Graph(file);
        ListEdges lListeEdges2 = Tools.Kruskal2(lGraph22);
        //System.out.println(lListeEdges2.toString());

        System.out.println("- PRIM --");
        Graph lGraph23 = new Graph(file);
        ListEdges lListeEdges4 = Tools.Prim(lGraph23,0);
        //System.out.println(lListeEdges4.toString());

        /*System.out.println("- PRIM --");
        ListEdges lListeEdges3 = Tools.DCMST(lGraph22,2);
        System.out.println(lListeEdges3.toString());*/

        
    }
}
