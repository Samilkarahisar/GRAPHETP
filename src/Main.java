public class Main {
    public static void main(String[] args) throws Exception {
        
        /**
         * Affiche un graphe
         */
        Graph lGraph1 = new Graph("data/GrapheStructureNonOriente.gsb");
        System.out.println(lGraph1.toString());

        Graph lGraph2 = new Graph("data/GrapheStructureOriente.gsb");
        System.out.println(lGraph2.toString());

        /**
         * Parcours en largeur et en profondeur d'un graphe
         */
        System.out.println("- PARCOURS LARGEUR -----");
        ListVertices lListVertices1 = Tools.parcoursLargeur(lGraph2,0);
        System.out.print(lListVertices1.toString());
        
        System.out.println("- PARCOURS PROFONDEUR --");
        ListVertices lListVertices2 = Tools.parcoursProfondeur(lGraph2,0);
        System.out.print(lListVertices2.toString());
        
        System.out.println("- KRUSKAL 1 --");
        Tools.Kruskal(lGraph2);
        
        
        
    }
}
