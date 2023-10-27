
package q5
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GUI {
private JFrame frame;
private int numeroAleatorio;
private int tentativasRestantes = 5;
private JTextField tentativasField;

public GUI() {
frame = new JFrame("Adivinhação");
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLayout(new GridLayout(5, 4));

Random random = new Random();
numeroAleatorio = random.nextInt(20) + 1;

tentativasField = new JTextField(10);
tentativasField.setEditable(false);
frame.add(tentativasField);

for (int i = 1; i <= 20; i++) {
JButton button = new JButton(Integer.toString(i));
button.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
if (tentativasRestantes > 0) {
JButton sourceButton = (JButton) e.getSource();
int numeroEscolhido = Integer.parseInt(sourceButton.getText());
tentativasRestantes--;

System.out.println("Número escolhido pelo usuário: " + numeroEscolhido);
System.out.println("Número escolhido pelo programa: " + numeroAleatorio);

if (numeroEscolhido == numeroAleatorio) {
System.out.println("Parabéns! Você acertou!");
} else {
System.out.println("Tentativas restantes: " + tentativasRestantes);
if (tentativasRestantes == 0) {
System.out.println("Suas tentativas acabaram. O número correto era: " + numeroAleatorio);
}
}
tentativasField.setText("Tentativas restantes: " + tentativasRestantes);
}
}
});
frame.add(button);
}

frame.pack();
frame.setVisible(true);
}

public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
new GUI();
});
}
}