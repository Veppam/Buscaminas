import javax.swing.*;

public class Casilla extends JButton {

    private boolean clicked;
    private boolean esUnaBomba;
    private int numBombasAlRededor;
    public static ImageIcon[] digitCasillas= new ImageIcon[9];


    public Casilla(boolean esBomba){
        clicked = false;
        setIcon(new ImageIcon("images/secret.png"));
        digitCasillas[0]= new ImageIcon("images/0.png");
        digitCasillas[1]= new ImageIcon("images/1.png");
        digitCasillas[2]= new ImageIcon("images/2.png");
        digitCasillas[3]= new ImageIcon("images/3.png");
        digitCasillas[4]= new ImageIcon("images/4.png");
        digitCasillas[5]= new ImageIcon("images/5.png");
        digitCasillas[6]= new ImageIcon("images/6.png");
        digitCasillas[7]= new ImageIcon("images/7.png");
        digitCasillas[8]= new ImageIcon("images/8.png");
        this.esUnaBomba= esBomba;
        this.digitCasillas[0]= new ImageIcon();
    }

    public void theSecretHasBeenShown(){
        if(esUnaBomba){
            setIcon(new ImageIcon("images/bomb.png"));
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
