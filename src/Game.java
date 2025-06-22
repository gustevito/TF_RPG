import javax.swing.*;

import gameui.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

public class Game{
    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, continueButtonPanel, choiceButtonPanel, inventoryButtonPanel, inventoryPanel, inventoryClosePanel;
    JLabel titleNameLabel;
    JButton startButton, continueButton, opt1, opt2, opt3, voltar, inventoryButton, item1, item2, item3, item4, item5, inventoryCloseButton;
    JTextArea mainTextArea;

    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 26);
    
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
        titleNameLabel = new JLabel("BOMBA");
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
        startButton.addActionListener((e)-> introScreen()); // lambda pra nao precisar de um handler

        // adicionar componentes aos painéis
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        
        // adicionar painéis ao container
        con.add(titleNamePanel);
        con.add(startButtonPanel);
        
        // tornar a janela visível APÓS adicionar todos os componentes
        window.setVisible(true);
    }

    public void introScreen(){
        // limpa o menu principal
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);

        mainTextArea = new JTextArea("O ano é 1997. Dois terroristas plantaram uma bomba no 6º andar de um prédio comercial no centro da cidade de Porto Alegre, e você, sargento aposentado do esquadrão antibombas do BOPE, foi a única pessoa confiada para resolver este caso.\n\nAja com cautela. Suas ações terão consequências.");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setEditable(false);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextPanel.add(mainTextArea);
        
        con.add(mainTextPanel);

        // botao de continuar
        continueButtonPanel = new JPanel();
        continueButtonPanel.setBounds(50, 470, 150, 50);
        continueButtonPanel.setBackground(Color.black);

        con.add(continueButtonPanel);

        continueButton = new JButton("Continuar");
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.setFont(normalFont);
        continueButton.addActionListener((e)-> createGameScreen());

        continueButtonPanel.add(continueButton);
    }

    public void createGameScreen(){
        // limpa o botao da intro
        continueButtonPanel.setVisible(false);
        
        // limpa a tela de inventário se existir
        if(inventoryPanel != null) {
            inventoryPanel.setVisible(false);
        }
        if(inventoryClosePanel != null) {
            inventoryClosePanel.setVisible(false);
        }
        
        mainTextPanel.setVisible(true);
        mainTextArea.setText("Você está no saguão principal. A única informação concedida a você, é de que a bomba está trancada no acervo do escritório, ao lado da sala de reuniões.\n\nAos seus pés, você encontra um Walkie-talkie.\nO que você faz?");

        // botoes de seleçao
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(450, 375, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        con.add(choiceButtonPanel);

        opt1 = new ChoiceButton("Pegar Walkie-talkie", normalFont);
        choiceButtonPanel.add(opt1);

        opt2 = new ChoiceButton("Ir ao corredor principal", normalFont);
        choiceButtonPanel.add(opt2);

        opt3 = new ChoiceButton("Sair", normalFont);
        choiceButtonPanel.add(opt3);

        voltar = new ChoiceButton(" ", normalFont);
        choiceButtonPanel.add(voltar);

        
        // botao de inventario
        inventoryButtonPanel = new JPanel();
        inventoryButtonPanel.setBounds(50, 470, 150, 50);
        inventoryButtonPanel.setBackground(Color.black);

        con.add(inventoryButtonPanel);

        inventoryButton = new JButton("Inventário");
        inventoryButton.setBackground(Color.black);
        inventoryButton.setForeground(Color.white);
        inventoryButton.setFont(normalFont);
        inventoryButton.addActionListener((e)-> createInventoryScreen());

        inventoryButtonPanel.add(inventoryButton);
    }

    public void createInventoryScreen(){
        mainTextPanel.setVisible(false);
        choiceButtonPanel.setVisible(false);
        inventoryButtonPanel.setVisible(false);

        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(100, 100, 600, 150);
        inventoryPanel.setBackground(Color.blue);
        inventoryPanel.setLayout(new GridLayout(1,5));

        item1 = new ItemButton("Item", normalFont);
        inventoryPanel.add(item1);
        
        item2 = new ItemButton("Item", normalFont);
        inventoryPanel.add(item2);

        item3 = new ItemButton("Item", normalFont);
        inventoryPanel.add(item3);

        item4 = new ItemButton("Item", normalFont);
        inventoryPanel.add(item4);

        item5 = new ItemButton("Item", normalFont);
        inventoryPanel.add(item5);

        con.add(inventoryPanel);


        // fechar inventario
        inventoryClosePanel = new JPanel();
        inventoryClosePanel.setBounds(50, 470, 150, 50);
        inventoryClosePanel.setBackground(Color.black);

        con.add(inventoryClosePanel);

        inventoryCloseButton = new JButton("X");
        inventoryCloseButton.setBackground(Color.black);
        inventoryCloseButton.setForeground(Color.white);
        inventoryCloseButton.setFont(normalFont);
        inventoryCloseButton.addActionListener((e)-> createGameScreen());

        inventoryClosePanel.add(inventoryCloseButton);
    }


}