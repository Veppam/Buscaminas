import javax.swing.*;
import java.awt.*;

public class BuscaMinas extends JFrame {

    private boolean [][] matrix;
    private JPanel cuadricula;
    private JPanel data;
    private JLabel etBombas;
    private JButton theGame;
    private int numCasillas;
    private int numBombas;


    public int colocarMinasRand(int [] bombsRand){
        /*Función recursiva que se encarga de generar números aleatorios
        * sin repetición*/
        int random = (int) Math.floor(Math.random()*numCasillas);
        boolean sinRepeticion=true;
        for (int value : bombsRand) {
            if (value == random) {
                sinRepeticion = false;
            }
        }
        if(sinRepeticion){
            return random;
        }else{
            return colocarMinasRand( bombsRand);
        }
    }

    public int[] buscarMinasAlRededor(){
        int[] numBombasArre= new int[numCasillas];
        int index=0;
        for(int i=0; i<Math.sqrt(numCasillas);i++){
            for(int j=0; j<Math.sqrt(numCasillas);j++){
                int num=0;
                if(i==0 && j==0){
                    if(matrix[i+1][j])
                        num++;
                    if(matrix[i+1][j+1])
                        num++;
                    if(matrix[i][j+1])
                        num++;
                }else if(i==0 && j>0 && j!=(Math.sqrt(numCasillas)-1)){
                    if(matrix[i][j+1])
                        num++;
                    if(matrix[i+1][j+1])
                        num++;
                    if(matrix[i+1][j])
                        num++;
                    if(matrix[i+1][j-1])
                        num++;
                    if(matrix[i][j-1])
                        num++;

                }else if(i==0 && j==(Math.sqrt(numCasillas)-1)){
                    if(matrix[i][j-1])
                        num++;
                    if(matrix[i+1][j-1])
                        num++;
                    if(matrix[i+1][j])
                        num++;

                }else if(j==(Math.sqrt(numCasillas)-1) && i>0 && i!=(Math.sqrt(numCasillas)-1)){
                    if(matrix[i-1][j])
                        num++;
                    if(matrix[i-1][j-1])
                        num++;
                    if(matrix[i][j-1])
                        num++;
                    if(matrix[i+1][j-1])
                        num++;
                    if(matrix[i+1][j])
                        num++;

                }else if(j==(Math.sqrt(numCasillas)-1) && i==(Math.sqrt(numCasillas)-1)){
                    if(matrix[i-1][j])
                        num++;
                    if(matrix[i-1][j-1])
                        num++;
                    if(matrix[i][j-1])
                        num++;

                }else if(i==(Math.sqrt(numCasillas)-1) && j>0 && j!=(Math.sqrt(numCasillas)-1)){
                    if(matrix[i][j+1])
                        num++;
                    if(matrix[i-1][j+1])
                        num++;
                    if(matrix[i-1][j])
                        num++;
                    if(matrix[i-1][j-1])
                        num++;
                    if(matrix[i][j-1])
                        num++;

                }else if(i==(Math.sqrt(numCasillas)-1) && j==0){
                    if(matrix[i][j+1])
                        num++;
                    if(matrix[i-1][j+1])
                        num++;
                    if(matrix[i-1][j])
                        num++;

                }else if(j==0 && i>0 && i!=(Math.sqrt(numCasillas)-1)){
                    if(matrix[i-1][j])
                        num++;
                    if(matrix[i-1][j+1])
                        num++;
                    if(matrix[i][j+1])
                        num++;
                    if(matrix[i+1][j+1])
                        num++;
                    if(matrix[i+1][j])
                        num++;

                }else if(i>0 && j>0 && i<(Math.sqrt(numCasillas)-1) && j<(Math.sqrt(numCasillas)-1)){
                    if(matrix[i-1][j])
                        num++;
                    if(matrix[i-1][j+1])
                        num++;
                    if(matrix[i][j+1])
                        num++;
                    if(matrix[i+1][j+1])
                        num++;
                    if(matrix[i+1][j])
                        num++;
                    if(matrix[i+1][j-1])
                        num++;
                    if(matrix[i][j-1])
                        num++;
                    if(matrix[i-1][j-1])
                        num++;
                }
                numBombasArre[index]=num;
                index++;
            }
        }
        return numBombasArre;
    }

    public static void main(String[] KbIn){
        BuscaMinas Juego = new BuscaMinas();
    }

    public BuscaMinas(){
        //------------------- BASIC GAME INTERFACE SETTINGS---------------------
        super("Busca Minas");
        setSize(600,600); //tamaño de la ventana
        setLocation(100,100); //Lugar donde va a estar
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Qué hacer al cerrar
        setLayout(new BorderLayout()); //Objeto forma del marco
        setIconImage(new ImageIcon("images/Logo.png").getImage());
        //----------------------------------------------------------------------

        //-------------------- GAME INTERFACE SEGMENTS--------------------------
        cuadricula= new JPanel();
        data= new JPanel();
        theGame= new JButton();
        //----------------------------------------------------------------------

        //------------------Dificultad------------------------------
        String[] dificultades={"Fácil","Medio","Difícil"};
        String dificultadOp = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione Dificultad: ",
                "Dificultad",
                JOptionPane.QUESTION_MESSAGE,
                null,
                dificultades,
                dificultades[1]
        );

        switch (dificultadOp) {
            case "Fácil":
                this.numCasillas = 10 * 10;
                this.numBombas = 10;
                break;
            case "Medio":
                this.numCasillas = 16 * 16;
                this.numBombas = 40;
                break;
            case "Difícil":
                this.numCasillas = 20 * 20;
                this.numBombas = 80;
                break;
        }
        //----------------------------------------------------------
        //------------------Generar Casillas------------------------

        //-------------->Obtener números de casillas de bombas
        int[] bombsRand= new int[numBombas]; //Guarda números de casillas aleatorios de bombas
        for(int i=0; i<bombsRand.length; i++){
            bombsRand[i]= colocarMinasRand(bombsRand);
        }
        //------------->Obtener casillas
        for(int i=0; i<numCasillas;i++){
            boolean esBomba=false;
            for(int j=0; j<bombsRand.length; j++){
                if(i==bombsRand[j]){
                    esBomba=true;
                }
            }
            Casilla celda= new Casilla(esBomba);
            cuadricula.add(celda);
        }

        //Casilla[] casillitas= (Casilla[]) cuadricula.getComponents();
        Casilla[] casillitas= new Casilla[numCasillas];
        int j=0;
        for(int i=0; i<cuadricula.getComponents().length;i++){
            if(cuadricula.getComponents()[i] instanceof Casilla) {
                casillitas[j] = (Casilla) cuadricula.getComponents()[i];
                j++;
            }
        }

        //------------------- THE MATRIX---------------------
        matrix= new boolean[(int)Math.sqrt(numCasillas)][(int) Math.sqrt(numCasillas)];
        int k=0;
        for(int i=0; i< (int) Math.sqrt(numCasillas); i++){
            for(int l=0; l< (int) Math.sqrt(numCasillas); l++){
                if(casillitas[k].getIfBomb()) {
                    matrix[i][l] = true;
                }else{
                    matrix[i][l] = false;
                }
                k++;
            }
        }
        //-------->Asignar número de bombas al rededor de la casilla
        int[] numBombasArre = buscarMinasAlRededor();
        for(int i=0; i<casillitas.length;i++){
            if(!casillitas[i].getIfBomb()) {
                casillitas[i].setNumBombasAlRededor(numBombasArre[i]);
            }
        }
        //----------------------------------------------------------

        /*for(int i=0; i<casillitas.length; i++){
            //if(casillitas[i].getIfBomb()){
                casillitas[i].theSecretHasBeenShown();
            //}
        }*/

        //----------------------------------------------------------
        //------------------- ADD GAME INTERFACE SEGMENTS---------------------

        theGame.setIcon(new ImageIcon("images/HappyFace.PNG"));
        etBombas= new JLabel("Minas: "+numBombas);
        data.add(etBombas, BorderLayout.EAST);
        data.add(theGame, BorderLayout.CENTER);
        cuadricula.setLayout(new GridLayout((int)Math.sqrt(numCasillas), (int)Math.sqrt(numCasillas))); //Hacer que se acomoden en cuadrado
        add(cuadricula,BorderLayout.CENTER);
        add(data, BorderLayout.NORTH);
        setVisible(true); //Si se ve o no la ventana
        System.out.println(numCasillas);
    }

}
