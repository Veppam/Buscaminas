import javax.swing.*;

public class Casilla extends JButton {

    private boolean clicked;
    private boolean esUnaBomba;
    private int numBombasAlRededor;
    private String param;

    public static ImageIcon[] digitCasillas= new ImageIcon[9];


    public Casilla(boolean esBomba, String param) {
        clicked = false;
        setIcon(new ImageIcon("images/secret.png"));
        this.esUnaBomba = esBomba;
        this.param = param;
        switch (param){
            case "Fácil":
                for (int i = 0; i < 9; i++){
                    digitCasillas[i] = new ImageIcon("images/Facil/"+i+".png");
                }
                break;
            case "Medio":
                for (int i = 0; i < 9; i++){
                    digitCasillas[i] = new ImageIcon("images/Medio/"+i+".png");
                }
                break;
            case "Difícil":
                for (int i = 0; i < 9; i++){
                    digitCasillas[i] = new ImageIcon("images/Dificil/"+i+".png");
                }
                break;
        }
        this.digitCasillas[0] = new ImageIcon();
    }

    public void theSecretHasBeenShown(){
        if(esUnaBomba){
            switch (param){
                case "Fácil":
                    setIcon(new ImageIcon("images/Facil/bomb.png"));
                    break;
                case "Medio":
                    setIcon(new ImageIcon("images/Medio/bomb.png"));
                    break;
                case "Difícil":
                    setIcon(new ImageIcon("images/Dificil/bomb.png"));
                    break;
            }
        }else{
            setIcon(digitCasillas[numBombasAlRededor]);
        }
    }

    public boolean getIfBomb(){
        return esUnaBomba;
    }

    public void setNumBombasAlRededor(int nBombas){
        this.numBombasAlRededor=nBombas;
    }

    public void setClicked(){
        clicked = true;
    }

    public boolean getClicked(){
        return clicked;
    }
}
