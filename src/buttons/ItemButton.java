package buttons;
import javax.swing.*;
import java.awt.*;

// Classe para o botão
public class ItemButton extends JButton {
    public ItemButton(String text, Font font) {
        super(text);
        setBackground(Color.black);
        setForeground(Color.white);
        setFont(font);
    }
}