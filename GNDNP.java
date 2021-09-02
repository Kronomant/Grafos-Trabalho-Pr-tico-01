import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class GNDNP {
    public static void main(String[] args) {


        System.out.println("*********************************************************************");
        System.out.println("                 Grafo GNDNP Não Direcionado Não Ponderado                     ");
        System.out.println("*********************************************************************");
    
        try{
            FileReader arq = new FileReader("./entradaGNDNP.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine(); 
            int matrizAdjacencia[][] = new int[Integer.parseInt(linha)][Integer.parseInt(linha)]; // pega quantidade de vertices
            linha = lerArq.readLine(); 
            while (linha != null) {
            
                String[] dados = linha.split(",");   
                matrizAdjacencia[Integer.parseInt(dados[0])-1][Integer.parseInt(dados[1])-1] = 1;
                matrizAdjacencia[Integer.parseInt(dados[1])-1][Integer.parseInt(dados[0])-1] = 1;
                linha = lerArq.readLine();
            }
            arq.close();

            for(int i = 0; i< 4; i++){
                for(int j =0; j< 4; j++)
                {
                    System.out.print(matrizAdjacencia[i][j] + " ");
                }
                System.out.println(" ");
            }


            for(int i = 0; i< 4; i++){
                for(int j =0; j< 4; j++)
                {
                    if(matrizAdjacencia[i][j] == 1){
                        System.out.println("("+(i+1)+") ---- ("+ (j+1) + ")");
                    }
                  
                }
            }


        } catch(IOException erro){
            System.err.printf("Erro na abertura do arquivo: %s.\n", erro.getMessage());
        }
        
    }
}
