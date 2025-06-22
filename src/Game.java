import javax.swing.*;

import gameui.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game{
    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, continueButtonPanel, choiceButtonPanel, inventoryButtonPanel, inventoryPanel, inventoryClosePanel;
    JLabel titleNameLabel;
    JButton startButton, continueButton, choice1, choice2, choice3, choice4, inventoryButton, item1, item2, item3, item4, item5, inventoryCloseButton;
    JTextArea mainTextArea;

    private ArrayList<String> inventory = new ArrayList<>();
    private final int MAX_INVENTORY_SIZE = 5;

    private Runnable currentScreenMethod;

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
        startButton.addActionListener((e)-> navigateTo(this::introScreen));

        // adicionar componentes aos painéis
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        
        // adicionar painéis ao container
        con.add(titleNamePanel);
        con.add(startButtonPanel);
        
        // tornar a janela visível APÓS adicionar todos os componentes
        window.setVisible(true);
    }

    // Método auxiliar para navegação (aqui eu usei IA, tava quebrando muito a cabeça pra saber como implementar isso)
    private void navigateTo(Runnable screenMethod) {
        currentScreenMethod = screenMethod;
        screenMethod.run();
    }

    private void returnToPreviousScreen() {
        if (currentScreenMethod != null) {
            currentScreenMethod.run();
        }
    }

    public void introScreen(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("O ano é 1997. Um terrorista conhecido por cometer crimes hediondos cercados por charadas, plantou uma bomba no 6º andar de um prédio comercial no centro da cidade de Porto Alegre.\n\nVocê, um renomado sargento aposentado do esquadrão antibombas do BOPE, foi a única pessoa confiada para resolver este caso.\n\nAja com cautela. Suas ações terão consequências.");
        createContinueButton();
    }

    public void createGameScreen(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Você está no saguão principal. A única informação concedida a você, é de que a bomba está trancada no acervo do escritório, ao lado da sala de reuniões.\n\nÀ sua esquerda e à sua direita, estende-se um corredor com diversas salas, e aos seus pés, você encontra um gravador. O que você faz?");
        createChoiceButtons();

        choice1.setText("Pegar gravador");
        choice1.addActionListener((e) -> navigateTo(this::gravador));
        
        choice2.setText("Corredor esquerdo");
        choice2.addActionListener((e) -> navigateTo(this::corredorEsquerdo));
        
        choice3.setText("Corredor direito");
        choice3.addActionListener((e) -> System.out.println("Foi ao corredor"));

        choice4.setText("Sair");
        choice4.addActionListener((e) -> navigateTo(this::sair));
        
        createInventoryButton();
    }

    public void gravador(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("*(clique)* ...\n\n ...Sabe, Sargento... Tem gente que só anda sozinho... Não se mistura, não se divide.\n\nPense ao contrário para talvez salvá-los de meu joguinho. Heheheh...");
        createCloseInventoryButton((e)-> navigateTo(this::createGameScreen));
    }

    public void corredorEsquerdo(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Você virou à esquerda no corredor, e consegue agora enxergar a porta para o refeitório, um banheiro, e ao fim do corredor, a sala do CEO.\n\nPara onde você vai?");
        createChoiceButtons();

        choice1.setText("Refeitório");
        choice1.addActionListener((e) -> navigateTo(this::refeitorio));
        
        choice2.setText("Sala do CEO");
        choice2.addActionListener((e) -> navigateTo(this::ceo));
        
        choice3.setText("Banheiro");
        choice3.addActionListener((e) -> navigateTo(this::banheiro));

        choice4.setText("Voltar");
        choice4.addActionListener((e) -> navigateTo(this::createGameScreen));
        
        createInventoryButton();
    }

    public void refeitorio(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Os copos de plástico e restos de comida espalhados pelo chão revelam uma reação desesperada dos funcionários... Algo interrompeu a pequena festa de escritório — e quem estava ali, fugiu como se a próxima explosão fosse no segundo seguinte...\n\n O que você faz?");
        createChoiceButtons();

        choice1.setText("Abrir geladeira");
        choice1.addActionListener((e) -> navigateTo(this::geladeira));
        
        choice2.setText("Abrir microondas");
        choice2.addActionListener((e) -> navigateTo(this::microondas));
        
        choice3.setText("Voltar");
        choice3.addActionListener((e) -> navigateTo(this::corredorEsquerdo));
        
        createInventoryButton();
    }

    public void geladeira(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Você vê um monte de comida gostosa...\n\nMas agora não é hora pra isso Sargento! Foco na missão!");
        createCloseInventoryButton((e)-> navigateTo(this::refeitorio));
    }

    public void microondas(){
        clearPanels();
        createTextPanel();
        
        if (hasItem("Bilhete")) {
            mainTextArea.setText("O microondas está vazio agora. Você já pegou o bilhete.");
            createCloseInventoryButton((e)-> navigateTo(this::refeitorio));
        } else {
            mainTextArea.setText("Estranhamente, você encontra um papel com nomes de cores escritas:\n\nVERDE, VERMELHO, AZUL, VERMELHO, VERDE");
            createChoiceButtons();

            choice1.setText("Pegar bilhete");
            choice1.addActionListener((e)-> navigateTo(this::pegarBilhete));
            
            choice2.setText("Ignorar bilhete");
            choice2.addActionListener((e)-> navigateTo(this::refeitorio));
            
            createInventoryButton();
        }
    }
    
    public void pegarBilhete(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Você pegou um bilhete com cores.");
        
        addToInventory("Bilhete");
        createCloseInventoryButton((e)-> navigateTo(this::refeitorio));
    }

        public void ceo(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("O contraste entre a bagunça caótica nos corredores da empresa e a limpeza esplêndida na sala do CEO, fazem você dar uma risadinha discreta.\n\nÉ cômico... É como se o dono da empresa nunca tivesse sequer pisado naquela sala.");
        createChoiceButtons();

        choice1.setText("Olhar armario");
        choice1.addActionListener((e) -> navigateTo(this::armario));
        
        choice2.setText("Olhar mesa grande");
        choice2.addActionListener((e)-> navigateTo(this::mesa));
        
        choice3.setText("Voltar");
        choice3.addActionListener((e) -> navigateTo(this::corredorEsquerdo));
        
        createInventoryButton();
    }

    public void armario(){
        clearPanels();
        createTextPanel();
        createChoiceButtons();
        
        if (hasItem("Tesoura")) {
            mainTextArea.setText("O armário está vazio agora. Você já pegou a tesoura.");
            createCloseInventoryButton((e)-> navigateTo(this::ceo));
        } else {
            mainTextArea.setText("Dentro, você encontra uma tesoura. Deseja pegá-la?\n\nPode ser útil para cortar os fios da bomba igual aos filmes...");
            createChoiceButtons();

            choice1.setText("Pegar tesoura");
            choice1.addActionListener((e)-> navigateTo(this::pegarTesoura));
            
            choice2.setText("Voltar");
            choice2.addActionListener((e)-> navigateTo(this::ceo));
            
            createInventoryButton();
        }
    }

    public void mesa(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Você encontra alguns papéis desinteressantes, e uma coleção de canetas que aparenta custar mais do que seu antigo salário.");
        createCloseInventoryButton((e)-> navigateTo(this::ceo));
    }

    public void pegarTesoura(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Você pegou uma tesoura.");
        
        addToInventory("Tesoura");
        createCloseInventoryButton((e)-> navigateTo(this::ceo));
    }

    public void banheiro(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Está trancado.");
        createCloseInventoryButton((e)-> navigateTo(this::corredorEsquerdo));
    }
    
    public void corredorDireito(){
        
    }

    public void sair(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Se você sair do prédio, irá fracassar na missão, e a bomba irá explodir...\n\n Você tem certeza?");
        createChoiceButtons();
        
        choice1.setText("Sim");
        choice1.addActionListener((e) -> navigateTo(this::lose));

        choice2.setText("Não");
        choice2.addActionListener((e) -> navigateTo(this::createGameScreen));
    }

    public void lose(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Por sua culpa, a bomba explodiu e causou danos irreparáveis para a cidade. Parabéns! >:(\n\nVocê perdeu.\n\nDeseja tentar novamente?");
        createChoiceButtons();
        choice1.setText("Sim");
        choice1.addActionListener((e) -> navigateTo(this::introScreen));
        
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
        createCloseInventoryButton((e)-> returnToPreviousScreen());
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
        continueButton.addActionListener((e) -> navigateTo(this::createGameScreen));

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

        ItemButton[] itemButtons = new ItemButton[5];
        
        for (int i = 0; i < 5; i++) {
            String itemText = (i < inventory.size()) ? inventory.get(i) : "-";
            itemButtons[i] = new ItemButton(itemText, normalFont);
            
            if (i < inventory.size()) {
                final int index = i;
                itemButtons[i].addActionListener((e) -> useItem(index));
            }
            
            inventoryPanel.add(itemButtons[i]);
        }

        con.add(inventoryPanel);
        inventoryPanel.setVisible(true);
    }
        
    private void createCloseInventoryButton(ActionListener action) {
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
        inventoryCloseButton.addActionListener(action);

        inventoryClosePanel.add(inventoryCloseButton);
        con.add(inventoryClosePanel);
        inventoryClosePanel.setVisible(true);
    }

    // metodos de inventario:

    private void addToInventory(String item) {
        inventory.add(item);
    }

    private boolean hasItem(String item) {
        return inventory.contains(item);
    }

    private String getItemAt(int index) {
        if (index >= 0 && index < inventory.size()) {
            return inventory.get(index);
        }
        return null;
    }

    public void useItem(int index) {
        String item = getItemAt(index);
        if (item == null) return;
        
        switch (item) {
            case "Bilhete":
                usarBilhete();
                break;

            case "Tesoura":
                usarTesoura();
                break;
            // adicionar mais itens aquiiiiiiiiii
            default:
                examinarItem(item);
                break;
        }
    }


    public void usarBilhete(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Você examina o bilhete mais de perto:\n\nVERDE, VERMELHO, AZUL, VERMELHO, VERDE\n\nParece ser uma sequência de cores... Talvez seja um código para algo?");
        createCloseInventoryButton((e)-> createInventoryScreen());
    }

    public void usarTesoura(){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("É, de fato, uma tesoura.");
        createCloseInventoryButton((e)-> createInventoryScreen());
    }

    public void examinarItem(String item){
        clearPanels();
        createTextPanel();
        mainTextArea.setText("Você examina o item: " + item + "\n\nNão parece ter nada especial sobre ele no momento.");
        createCloseInventoryButton((e)-> createInventoryScreen());
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