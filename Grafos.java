import java.util.*;

class Grafo {

	public Grafo prox;
	public int valor;

	public Grafo() {
		this(0);
	}

	public Grafo(int v) {
		valor = v;
		prox = null;
	}
}

class Diagrama {

	public int arestas;
	public int vertices;
	public Grafo[] vetGrafos;

	public Diagrama(int vert) {
		this.vetGrafos = new Grafo[vert];

		for (int i = 0; i < vert; i++) {
			vetGrafos[i] = new Grafo(i + 1);
		}

		this.arestas = 0;
		this.vertices = vert;
	}

	public void criarNovaAresta(Grafo origem, int destino) {
		boolean conexao = false;
		Grafo aux = new Grafo(destino);

		for (Grafo i = origem; conexao != true; i = i.prox) {
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
				for (Grafo g = vetGrafos[i].prox; g != null; g = g.prox) {

					System.out.print(g.valor);
					if (g.prox == null) {
						System.out.print("\n");
					}
					else {
						System.out.print(" ");
					}	
				}
			} else {
				System.out.println("_");
			}
		}
	}

}

public class Grafos {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("*********************************************************************");
		System.out.println("                 Grafo Direcionado Não Ponderado                     ");
		System.out.println("*********************************************************************");
		System.out.println("Digite a quantidade de vértices que o grafo possui:");
		int qtde_vertices = sc.nextInt();
		sc.nextLine(); // Limpar scanner

		Diagrama diagrama = new Diagrama(qtde_vertices);
		int vDestino = 0;

		System.out.println("Insira as conexões dos vértices \nPara finalizar digite -1");
		for (int i = 0; i < qtde_vertices; i++) {
			System.out.println("Insira o vértice de destino das arestas que partem do vértice " + diagrama.vetGrafos[i].valor);
			while (vDestino != -1) {
				System.out.print("Vértice de destino: ");
				vDestino = sc.nextInt();
				sc.nextLine(); // Limpar scanner

				if (vDestino != -1)
					diagrama.criarNovaAresta(diagrama.vetGrafos[i], vDestino);
			}
			vDestino = 0;
		}
		sc.close();

		System.out.println("*********************************************************************");
		System.out.println("Lista de Adjacência");
		diagrama.imprimeListaAdjacencia();
		System.out.println("*********************************************************************");
	}
}