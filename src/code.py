#Ce code contient l'algo qu'on peut appliquer pour toruver un arbre de poids min avec contrainte de degré.0

# -*- coding: utf-8 -*-
import sys
# This is the framework for graphs we use on this work
import networkx as nx
# Tool to determine wether elements are on the same set
from networkx.utils import UnionFind
# We need this in python to "clone" objects
import copy


## BIG TODO - refazer o algoritmo para retornar o MST como um grafo

# debugar a função KruskalMST, ter certeza de que ela retorna um grafo e não uma lista!

class agmrg:
    '''
    Esta classe implementa um método exato para encontrar a árvore geradora
    mínima restrita a um grau máximo.
    Ela pode ser facilmente expandida também para encontrar agms restritas por
    capacidade ou agm com restrição de hop.

    Uma Partição do conjunto de árvores geradoras é representado por dois
    Conjuntos, um de arestas incluidas e um de arestas excluidas. A partição é
    composta pelas AGM's que tem todas as arestas incluidas e não tem nenhuma
    aresta exclida.

    '''

    @classmethod
    def KruskalMST(self, G, P):
        """
        Retorna o MST de um grafo contido na particao P.
        Returns None se nao existir nenhum.
        P[0] -> arestas incluidas; P[1] -> arestas excluidas
        """
        subtrees = UnionFind()
        # adicionamos as arestas incluidas dentro do conjunto solução

        #tree = copy.deepcopy(P[0])
        tree = nx.Graph(P[0])


        edges = sorted(tree.edges(data=True),key=lambda t: t[2].get(weight,1))
        for u,v,d in edges:
            subtrees.union(u,v)

        #for u,v in tree:
        #    subtrees.union(u,v)
        #edges = [(G[u][v].get('weight',1),u,v) for u in G for v in G[u]]

        # remove as arestas que já foram adicionadas ao conjunto solucao
        edges = [e for e in edges if (e[1],e[2]) not in P[0]]
        edges = [e for e in edges if (e[2],e[1]) not in P[0]]
        # remove as arestas que não fazem parte da particao
        edges = [e for e in edges if (e[1],e[2]) not in P[1]]
        edges = [e for e in edges if (e[2],e[1]) not in P[1]]
        #ordena as arestas do grato pelo peso
        edges = sorted(edges)
        # adiciona arestas unindo vértices ainda não conectados, até chegarmos à
        # uma solução, ou descobrirmos que não é possível conectar todos os
        # vértices
        for u,v,W in edges:
            if subtrees[u] != subtrees[v]:
                tree.add_node((u,v))
                subtrees.union(u,v)
        if tree.order() == G.order() - 1:
            return tree
        else:
            return None

    @classmethod
    def gera_lst(self, grafo, lp):
        '''
        Dado um grafo e uma lista de partições do grafo, encontra as
        árvores geradoras mínimas para cada partição da lista, e retorna menor
        árvore geradora encontrada e uma nova lista de partições que não contém
        esta árvore geradora mínima.
        '''
        # Lista de AGMs candidatas à Menor AGM da lista de partições
        candidatos_st = []
        for p in lp:
            nst = self.KruskalMST(grafo, p)
            if nst != None:
                w_nst = sum(grafo[u][v].get('weight',1) for u, v in nst)
                candidatos_st.append((w_nst, nst, p))
            else:
                lp.remove(p) # removendo partições que não contem arvore geradora

        if candidatos_st == []:
            return [], []

        # Ordena lista de AGMs por custo
        candidatos_st = sorted(candidatos_st, key=lambda c: c[0])
        # Remove a partição que contém a menor AGM de todas
        lp.remove(candidatos_st[0][2])
        # Adiciona novas partições que equivalem à partição removida
        # sem a menor AGM de todas
        lp += self.partition(candidatos_st[0][1], candidatos_st[0][2])

        # Retorna a lista de partições novas ( que não contém a AGM mínima ) e
        # a AGM mínima encontrada
        return candidatos_st[0][1], lp

    @classmethod
    def partition(self, mst, p):
        '''
        Dado um MST e uma particao P, particiona o P nos vertices de MST.
        A união do conjunto de partições geradas aqui com o MST passado
        é equivalente à partição passada como entrada.
        '''

        p1 = copy.deepcopy(p)
        p2 = copy.deepcopy(p)

        partitions = []

        open_edges = [(u,v) for (u,v) in mst if (u,v) not in p1[0] and (u,v) not in p1[1] and (v,u) not in p1[0] and (v,u) not in p1[1]]

        open_edges = sorted(open_edges, key=lambda e: e[0])

        for e in open_edges:
            p1[1].append(e)
            p2[0].append(e)
            partitions.append(p1)
            p1 = copy.deepcopy(p2)

        return partitions


    @classmethod
    def sat_grau(self, grafo, x, k):
        '''
        Retorna True se um caminho x respeita a restrição de grau máximo k,
        False caso contrário
        '''

        count = {}
        for u,v in x:
            try:
                count[u] += 1
            except KeyError:
                count[u] = 1 # u ainda não foi adicionado ao Dicionario
            try:
                count[v] += 1
            except KeyError:
                count[v] = 1

        # algum nó tem grau maior que k -> false
        for n, g in count.iteritems():
            if g > k:
                return False
        return True


def find_dcmst(grafo, k):

    lp = [([], [])]
    i = 0
    st = None
    while lp != [] :
        i += 1
        st, lp = agmrg.gera_lst(grafo, lp)
        if agmrg.sat_grau(grafo, st, k):
            break
        else:
            st = None

    if st != None:
        print "%d AG's analisadas."% i
        print "Peso : %d" % sum(grafo[u][v].get('weight',1) for u, v in st)
        print st
    else:
        print "Não existem soluções"


if __name__ == "__main__":
    # This library is being run as a script
    try:
        k = int(sys.argv[2])
        g = nx.read_weighted_edgelist(sys.argv[1])
    except IndexError:
       print "Uso %d <edgelist> <k>"
       print "edgelist é o grafo de entrada ( em lista de vértices)"
       print "k é a restrição de grau usada"
       exit(1)

    find_dcmst(g, k)



