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
    JButton startButton, continueButton, choice1, choice2, choice3, choice4, inventoryButton, item1, item2, item3, item4, item5, inventoryCloseButton;
    JTextArea mainTextArea;

    String position;

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
        clearPanels();
        createTextPanel();
        mainTextArea.setText("O ano é 1997. Dois terroristas conhecidos por cometerem crimes hediondos cercados por charadas, plantaram uma bomba no 6º andar de um prédio comercial no centro da cidade de Porto Alegre.\n\nVocê, um renomado sargento aposentado do esquadrão antibombas do BOPE, foi a única pessoa confiada para resolver este caso.\n\nAja com cautela. Suas ações terão consequências.");
        createContinueButton();
    }

    public void createGameScreen(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Você está no saguão principal. A única informação concedida a você, é de que a bomba está trancada no acervo do escritório, ao lado da sala de reuniões.\n\nAos seus pés, você encontra um Walkie-talkie.\nO que você faz?");
        createChoiceButtons();

        choice1.setText("Pegar Walkie-talkie");
        choice1.addActionListener((e) -> System.out.println("Pegou walkie-talkie"));
        
        choice2.setText("Ir ao corredor principal");
        choice2.addActionListener((e) -> System.out.println("Foi ao corredor"));
        
        choice3.setText("Sair");
        choice3.addActionListener((e) -> sair());
        
        createInventoryButton();
    }

    public void sair(){
        clearPanels();
        position = "sair";
        createTextPanel();
        mainTextArea.setText("Se você sair do prédio, irá fracassar na missão, e a bomba irá explodir...\n\n Você tem certeza?");
        createChoiceButtons();
        
        choice1.setText("Sim");
        choice1.addActionListener((e) -> lose());

        choice2.setText("Não");
        choice2.addActionListener((e) -> createGameScreen());
    }

    public void lose(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Por sua culpa, a bomba explodiu e causou danos irreparáveis para a cidade. Parabéns!\n\nVocê perdeu.\n\n Deseja tentar novamente?");
        createChoiceButtons();
        choice1.setText("Sim");
        choice1.addActionListener((e) -> introScreen());
        
        choice2.setText("Não");
        choice2.addActionListener((e) -> System.exit(0));
    }

    public void createInventoryScreen(){
        // esconder painéis
        if (mainTextPanel != null) mainTextPanel.setVisible(false);
        if (choiceButtonPanel != null) choiceButtonPanel.setVisible(false);
        if (inventoryButtonPanel != null) inventoryButtonPanel.setVisible(false);
        if (continueButtonPanel != null) continueButtonPanel.setVisible(false);

        createInventoryPanel();
        createCloseInventoryButton();
    }

    // métodos auxiliares
    private void createTextPanel() {
        if (mainTextPanel != null) {
            con.remove(mainTextPanel);
        }
        
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        
        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setEditable(false);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        
        mainTextPanel.add(mainTextArea);
        con.add(mainTextPanel);
        mainTextPanel.setVisible(true);
    }
    
    private void createContinueButton() {
        if (continueButtonPanel != null) {
            con.remove(continueButtonPanel);
        }
        
        continueButtonPanel = new JPanel();
        continueButtonPanel.setBounds(50, 470, 150, 50);
        continueButtonPanel.setBackground(Color.black);

        continueButton = new JButton("Continuar");
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.setFont(normalFont);
        continueButton.addActionListener((e) -> createGameScreen());

        continueButtonPanel.add(continueButton);
        con.add(continueButtonPanel);
        continueButtonPanel.setVisible(true);
    }
    
    private void createChoiceButtons() {
        if (choiceButtonPanel != null) {
            con.remove(choiceButtonPanel);
        }
        
        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(450, 375, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));

        choice1 = new ChoiceButton("", normalFont);
        choice2 = new ChoiceButton("", normalFont);
        choice3 = new ChoiceButton("", normalFont);
        choice4 = new ChoiceButton("", normalFont);

        choiceButtonPanel.add(choice1);
        choiceButtonPanel.add(choice2);
        choiceButtonPanel.add(choice3);
        choiceButtonPanel.add(choice4);
        
        con.add(choiceButtonPanel);
        choiceButtonPanel.setVisible(true);
    }
    
    private void createInventoryButton() {
        if (inventoryButtonPanel != null) {
            con.remove(inventoryButtonPanel);
        }
        
        inventoryButtonPanel = new JPanel();
        inventoryButtonPanel.setBounds(50, 470, 150, 50);
        inventoryButtonPanel.setBackground(Color.black);

        inventoryButton = new JButton("Inventário");
        inventoryButton.setBackground(Color.black);
        inventoryButton.setForeground(Color.white);
        inventoryButton.setFont(normalFont);
        inventoryButton.addActionListener((e) -> createInventoryScreen());

        inventoryButtonPanel.add(inventoryButton);
        con.add(inventoryButtonPanel);
        inventoryButtonPanel.setVisible(true);
    }
    
    private void createInventoryPanel() {
        if (inventoryPanel != null) {
            con.remove(inventoryPanel);
        }
        
        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(100, 100, 600, 150);
        inventoryPanel.setBackground(Color.blue);
        inventoryPanel.setLayout(new GridLayout(1, 5));

        item1 = new ItemButton("Item", normalFont);
        item2 = new ItemButton("Item", normalFont);
        item3 = new ItemButton("Item", normalFont);
        item4 = new ItemButton("Item", normalFont);
        item5 = new ItemButton("Item", normalFont);
        
        inventoryPanel.add(item1);
        inventoryPanel.add(item2);
        inventoryPanel.add(item3);
        inventoryPanel.add(item4);
        inventoryPanel.add(item5);

        con.add(inventoryPanel);
        inventoryPanel.setVisible(true);
    }
    
    private void createCloseInventoryButton() {
        if (inventoryClosePanel != null) {
            con.remove(inventoryClosePanel);
        }
        
        inventoryClosePanel = new JPanel();
        inventoryClosePanel.setBounds(50, 470, 150, 50);
        inventoryClosePanel.setBackground(Color.black);

        inventoryCloseButton = new JButton("X");
        inventoryCloseButton.setBackground(Color.black);
        inventoryCloseButton.setForeground(Color.white);
        inventoryCloseButton.setFont(normalFont);
        inventoryCloseButton.addActionListener((e) -> createGameScreen());

        inventoryClosePanel.add(inventoryCloseButton);
        con.add(inventoryClosePanel);
        inventoryClosePanel.setVisible(true);
    }

    private void clearPanels() {
        // array com os paineis (dá pra melhorar mais eu acho)
        JPanel[] paineis = {
            titleNamePanel, startButtonPanel, mainTextPanel, continueButtonPanel,
            choiceButtonPanel, inventoryButtonPanel, inventoryPanel, inventoryClosePanel
        };
        
        // remove todos os paineis do container
        for (JPanel painel : paineis) {
            if (painel != null) {
                painel.setVisible(false);
                con.remove(painel);
            }
        }
        con.revalidate();
        con.repaint();
    }

    // versão inicial gambiarrenta:

    /*private void clearPanels() {
        if (titleNamePanel != null) {
            titleNamePanel.setVisible(false);
            con.remove(titleNamePanel);
        }
        if (startButtonPanel != null) {
            startButtonPanel.setVisible(false);
            con.remove(startButtonPanel);
        }
        if (mainTextPanel != null) {
            mainTextPanel.setVisible(false);
            con.remove(mainTextPanel);
        }
        if (continueButtonPanel != null) {
            continueButtonPanel.setVisible(false);
            con.remove(continueButtonPanel);
        }
        if (choiceButtonPanel != null) {
            choiceButtonPanel.setVisible(false);
            con.remove(choiceButtonPanel);
        }
        if (inventoryButtonPanel != null) {
            inventoryButtonPanel.setVisible(false);
            con.remove(inventoryButtonPanel);
        }
        if (inventoryPanel != null) {
            inventoryPanel.setVisible(false);
            con.remove(inventoryPanel);
        }
        if (inventoryClosePanel != null) {
            inventoryClosePanel.setVisible(false);
            con.remove(inventoryClosePanel);
        }
        con.revalidate();
        con.repaint();
    }*/
}