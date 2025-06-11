import javax.swing.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

public class Game{
    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel titleNameLabel;
    JButton startButton, opt1, opt2, opt3, opt4;
    JTextArea mainTextArea;

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    
    public static void main(String[] args){
        new Game();
    }
    
    public Game(){
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        
        con = window.getContentPane();
       
        // painel do título
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        
        // label do título
        titleNameLabel = new JLabel("BOMBA!!!");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        
        // painel do botão
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);
        
        // botão iniciar
        startButton = new JButton("Iniciar");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener((e)-> createGameScreen()); // lambda pra nao precisar de um handler

        // adicionar componentes aos painéis
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        
        // adicionar painéis ao container
        con.add(titleNamePanel);
        con.add(startButtonPanel);
        
        // tornar a janela visível APÓS adicionar todos os componentes
        window.setVisible(true);
    }

    public void createGameScreen(){
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);

        mainTextArea = new JTextArea("Essa é a área principal de texto do jogo. Teasmlmlsdm klsdm kasrmk lkrm ldmkldrm lrasdmlkdm krs mlkdmm aslksdmkl");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);
        
        con.add(mainTextPanel);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));

        con.add(choiceButtonPanel);

        // botoes
        opt1 = new JButton("Opção 1");
        opt1.setBackground(Color.black);
        opt1.setForeground(Color.white);
        opt1.setFont(normalFont);
        choiceButtonPanel.add(opt1);

        opt2 = new JButton("Opção 1");
        opt2.setBackground(Color.black);
        opt2.setForeground(Color.white);
        opt2.setFont(normalFont);
        choiceButtonPanel.add(opt2);
        
        opt3 = new JButton("Opção 1");
        opt3.setBackground(Color.black);
        opt3.setForeground(Color.white);
        opt3.setFont(normalFont);
        choiceButtonPanel.add(opt3);
        
        opt4 = new JButton("Opção 1");
        opt4.setBackground(Color.black);
        opt4.setForeground(Color.white);
        opt4.setFont(normalFont);
        choiceButtonPanel.add(opt4);

        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.blue);
        playerPanel.setLayout(new GridLayout(1, 4));

        con.add(playerPanel);
    }
}