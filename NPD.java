import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class GrafoNPD {

	public GrafoNPD prox;
	public int valor;

	public GrafoNPD() {
		this(0);
	}

	public GrafoNPD(int v) {
		valor = v;
		prox = null;
	}
}

class DiagramaNPD {

	public int arestas;
	public int vertices;
	public GrafoNPD[] vetGrafos;

	public DiagramaNPD() {
		this(0);
	}

	public DiagramaNPD(int vert) {
		this.vetGrafos = new GrafoNPD[vert];

		for (int i = 0; i < vert; i++) {
			vetGrafos[i] = new GrafoNPD(i + 1);
		}

		this.arestas = 0;
		this.vertices = vert;
	}

	public void criarNovaAresta(GrafoNPD origem, int destino) {
		boolean conexao = false;
		GrafoNPD aux = new GrafoNPD(destino);

		for (GrafoNPD i = origem; conexao != true; i = i.prox) {
			if (i.prox == null) {
				i.prox = aux;
				arestas++;
				conexao = true;
			}
		}
	}

	public void imprimeListaAdjacencia() {
		System.out.println("Origem  Destino");

		for (int i = 0; i < this.vertices; i++) {
			System.out.print(vetGrafos[i].valor + "     >>  ");

			if (vetGrafos[i].prox != null) {
				for (GrafoNPD g = vetGrafos[i].prox; g != null; g = g.prox) {

					System.out.print(g.valor);
					if (g.prox == null) {
						System.out.print("\n");
					} else {
						System.out.print(" ");
					}
				}
			} else {
				System.out.println("_");
			}
		}
	}

}

public class NPD {

	public static void main(String[] args) {
		DiagramaNPD diagrama = new DiagramaNPD();
		try {
			FileReader arq = new FileReader("entradaNPD.txt");
			BufferedReader lerArq = new BufferedReader(arq);

			System.out.println("*********************************************************************");
			System.out.println("                 GrafoNPD Direcionado Não Ponderado                     ");
			System.out.println("*********************************************************************");
			int qtde_vertices = Integer.parseInt(lerArq.readLine());
			diagrama = new DiagramaNPD(qtde_vertices);

			String linha = lerArq.readLine(); 
            while (linha != null) {

                String[] dados = linha.split(",");
				diagrama.criarNovaAresta(diagrama.vetGrafos[Integer.parseInt(dados[0])-1], Integer.parseInt(dados[1]));
				linha = lerArq.readLine(); 
            }
            arq.close();

		} catch (IOException erro) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", erro.getMessage());
		}
		System.out.println("Lista de Adjacência");
		diagrama.imprimeListaAdjacencia();
		System.out.println("*********************************************************************");
	}
}