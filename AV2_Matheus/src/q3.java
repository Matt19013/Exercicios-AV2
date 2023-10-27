import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class q3 {

    public static void main(String[] args) {
        String caminhoImagemEntrada = "https://www.example.com/exemplo.jpg";

        try {
            BufferedImage imagem = ImageIO.read(new File(caminhoImagemEntrada));
            ajustarBrilho(imagem, 1.5);
            String caminhoImagemSaida = "https://www.example.com/exemplo_saida.jpg";
            ImageIO.write(imagem, "jpg", new File(caminhoImagemSaida));
            System.out.println("Imagem com brilho ajustado salva com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ajustarBrilho(BufferedImage imagem, double fator) {
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                int rgb = imagem.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                r = (int) (r * fator);
                g = (int) (g * fator);
                b = (int) (b * fator);

                r = Math.min(255, Math.max(0, r));
                g = Math.min(255, Math.max(0, g));
                b = Math.min(255, Math.max(0, b));

                int novoRGB = (r << 16) | (g << 8) | b;
                imagem.setRGB(x, y, novoRGB);
            }
        }
    }
}