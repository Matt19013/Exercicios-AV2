import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class q4 {
    public static void main(String[] args) {
        try {
            int[][] matrizA = lerMatriz("caminho/para/matrizA.txt");
            int[][] matrizB = lerMatriz("caminho/para/matrizB.txt");

            if (matrizA[0].length != matrizB.length) {
                System.out.println("As matrizes não são multiplicáveis.");
                return;
            }
            int[][] resultado = multiplicarMatrizes(matrizA, matrizB);
            System.out.println("Matriz Resultante:");
            exibirMatriz(resultado);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int[][] lerMatriz(String caminhoArquivo) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        int linhas = 0;
        int colunas = 0;

        while ((linha = leitor.readLine()) != null) {
            linhas++;
            String[] valores = linha.split(" ");
            colunas = valores.length;
        }

        leitor.close();

        int[][] matriz = new int[linhas][colunas];
        leitor = new BufferedReader(new FileReader(caminhoArquivo));

        for (int i = 0; i < linhas; i++) {
            linha = leitor.readLine();
            String[] valores = linha.split(" ");
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = Integer.parseInt(valores[j]);
            }
        }

        leitor.close();

        return matriz;
    }

    private static int[][] multiplicarMatrizes(int[][] matrizA, int[][] matrizB) {
        int linhasA = matrizA.length;
        int colunasA = matrizA[0].length;
        int colunasB = matrizB[0].length;

        int[][] resultado = new int[linhasA][colunasB];

        for (int i = 0; i < linhasA; i++) {
            for (int j = 0; j < colunasB; j++) {
                for (int k = 0; k < colunasA; k++) {
                    resultado[i][j] += matrizA[i][k] * matrizB[k][j];
                }
            }
        }

        return resultado;
    }

    private static void exibirMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
