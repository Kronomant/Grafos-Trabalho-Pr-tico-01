import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

class Celula {
    public int rotulo;
	public int elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.
	/**
	 * Construtor da classe.
	 */
	public Celula() {
		this(0);
	}

	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public Celula(int rotulo) {
      this.rotulo = rotulo;
      this.elemento = rotulo;
      this.prox = null;
	}

    public Celula(int rotulo, int elemento) {
        this.rotulo = rotulo;
        this.elemento = elemento;
        this.prox = null;
      }
}



class Diagrama { 
    public int aresta;
    public int vertices;
    public Celula[] diagrama;
    private int index =0;

    public Diagrama(int numero_vertices){
        this.diagrama = new Celula[numero_vertices];
        vertices = numero_vertices;
    }

    public void addVertice(int rotulo){
        diagrama[index]= new Celula(rotulo);
        index++;
    }

    public void addAresta (int rotulo, int valor){
      boolean conexao = false;
      Celula aux = new Celula(rotulo, valor);

		for (Celula i = diagrama[index-1]; conexao != true; i = i.prox) {
			if (i.prox == null) {
				i.prox = aux;
				conexao = true;
			}
		}
    }

    public void imprimeVertices(){

        for(int j = 0; j < vertices; j++){
            System.out.print("("+diagrama[j].rotulo+") ");
            if(diagrama[j].prox != null){
                for (Celula g = diagrama[j].prox; g != null; g = g.prox) {
                    System.out.print("---"+g.elemento+"--> "+"("+g.rotulo+")");
					if (g.prox == null) {
						System.out.print("\n");
					}
					else {
						System.out.print(" ");
					}	
            }
        }  else {
            System.out.println("_");
        }   
     
    }

}
}

public class GDP{
    public static void lerEntrada(){
        try{
            FileReader arq = new FileReader("./entradaGDP.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine(); 
            Diagrama diagrama = new Diagrama(Integer.parseInt(linha));


            while (linha != null) {
            
                String[] dados = linha.split(",");
                 if(dados[0].equals("V")){
                     diagrama.addVertice(Integer.parseInt(dados[1]));
                 } else if(dados[0].equals("A")){
                    diagrama.addAresta(Integer.parseInt(dados[1]), Integer.parseInt(dados[2]));
                 } 
                linha = lerArq.readLine();
            }
            arq.close();
            diagrama.imprimeVertices();

        } catch(IOException erro){
            System.err.printf("Erro na abertura do arquivo: %s.\n", erro.getMessage());
        }
    }
    public static void main(String[] args){

        System.out.println("*********************************************************************");
        System.out.println("                 Grafo GDP Direcionado  Ponderado                     ");
        System.out.println("*********************************************************************");
        lerEntrada();
       
    }

}
