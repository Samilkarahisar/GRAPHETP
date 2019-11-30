public class Main {
    public static void main(String[] args) throws Exception {
        /**
         * Affiche un graphe
         */
        Graph lGraph1 = new Graph("data/GrapheStructureNonOriente.gsb");
        System.out.println(lGraph1.toString());
        Graph lGraph2 = new Graph("data/Deneme.gsb");
        System.out.println(lGraph2.toString());
        Graph lGraph22 = new Graph("data/GrapheDCMST.gsb");
        /**
         * Parcours en largeur et en profondeur d'un graphe
         * */

        System.out.println("- PARCOURS LARGEUR -----");
        ListVertices lListVertices1 = Tools.parcoursLargeur(lGraph1,0);
        System.out.print(lListVertices1.toString());
        System.out.println("- PARCOURS PROFONDEUR --");

       // ListVertices lListVertices2 = Tools.parcoursProfondeur(lGraph1,0);
        //System.out.print(lListVertices2.toString());
        System.out.println("- KRUSKAL 1 --");
        ListEdges lListeEdges1 = Tools.Kruskal1(lGraph22);
        System.out.println(lListeEdges1.toString());
        System.out.println("- KRUSKAL 2 --");
         ListEdges lListeEdges2 = Tools.Kruskal2(lGraph22);
        System.out.println(lListeEdges2.toString());

        System.out.println("- PRIM --");
        ListEdges lListeEdges3 = Tools.DCMST(lGraph2,3    );
        System.out.println(lListeEdges3.toString());

        
    }
}
