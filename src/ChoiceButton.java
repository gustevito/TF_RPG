import javax.swing.*;
import java.awt.*;

// Classe para o botão
class ChoiceButton extends JButton {
    public ChoiceButton(String text, Font font) {
        super(text);
        setBackground(Color.black);
        setForeground(Color.white);
        setFont(font);
    }
}