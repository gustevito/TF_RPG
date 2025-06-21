import javax.swing.*;
import java.awt.*;

// Classe para o botão
class ItemButton extends JButton {
    public ItemButton(String text, Font font) {
        super(text);
        setBackground(Color.black);
        setForeground(Color.white);
        setFont(font);
    }
}