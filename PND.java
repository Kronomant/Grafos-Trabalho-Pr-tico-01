import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class GrafoPND {

    public GrafoPND prox;
    public int valor;
    public int pesoAresta;

    public GrafoPND() {
        valor = 0;
        pesoAresta = 0;
    }

    public GrafoPND(int v, int peso) {
        valor = v;
        prox = null;
        pesoAresta = peso;
    }
}

class DiagramaPND {

    public int arestas;
    public int vertices;
    public GrafoPND[] vetGrafos;

    public DiagramaPND() {
        this(0);
    }

    public DiagramaPND(int vert) {
        this.vetGrafos = new GrafoPND[vert];
        for (int i = 0; i < vert; i++) {
            vetGrafos[i] = new GrafoPND(i + 1, 0);
        }
        this.arestas = 0;
        this.vertices = vert;
    }

    public void gerarArestas(GrafoPND origem, int destino, int peso) {
        boolean conexao = false;
        GrafoPND aux = new GrafoPND(destino, peso);

        for (GrafoPND i = origem; conexao != true; i = i.prox) {
            if (i.prox == null) {
                i.prox = aux;
                conexao = true;
            }
        }
    }

    public void criarNovaAresta(GrafoPND origem, int destino, int peso) {
        boolean conexao = false;
        GrafoPND aux = new GrafoPND(destino, peso);

        for (GrafoPND i = origem; conexao != true; i = i.prox) {
            if (i.prox == null) {
                i.prox = aux;
                arestas++;
                conexao = true;
                if (origem.valor != destino) {
                    gerarArestas(vetGrafos[destino - 1], origem.valor, peso);
                }
            }
        }
    }

    public void imprimeMatrizAdjacencia() {
        int[][] matriz = new int[this.vertices][this.vertices];
        for (int i = 0; i < this.vertices; i++) {
            for (int j = 0; j < this.vertices; j++) {
                matriz[i][j] = 0;
            }
        }

        for (int i = 0; i < this.vertices; i++) {
            for (GrafoPND j = vetGrafos[i].prox; j != null; j = j.prox) {
                matriz[vetGrafos[i].valor - 1][j.valor - 1] = j.pesoAresta;
            }
        }

        for (int i = 0; i < this.vertices; i++) {
            for (int j = 0; j < this.vertices; j++) {
                System.out.print(matriz[i][j]);
                if (Integer.toString(matriz[i][j]).length() == 1) {
                    System.out.print("  ");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
}

public class PND {
    public static void main(String[] args) {
        DiagramaPND diagrama = new DiagramaPND();
        try {
            FileReader arq = new FileReader("entradaPND.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            System.out.println("*********************************************************************");
            System.out.println("                 GrafoPND Direcionado Não Ponderado                     ");
            System.out.println("*********************************************************************");
            int qtde_vertices = Integer.parseInt(lerArq.readLine());
            diagrama = new DiagramaPND(qtde_vertices);

            String linha = lerArq.readLine();
            while (linha != null) {

                String[] dados = linha.split(",");
                diagrama.criarNovaAresta(diagrama.vetGrafos[Integer.parseInt(dados[0]) - 1], Integer.parseInt(dados[1]),
                        Integer.parseInt(dados[2]));
                linha = lerArq.readLine();
            }
            arq.close();

        } catch (IOException erro) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", erro.getMessage());
        }
        System.out.println("Matriz de Adjacência\n");
        diagrama.imprimeMatrizAdjacencia();
        System.out.println("*********************************************************************");
    }
}
