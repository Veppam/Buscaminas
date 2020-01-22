import javax.swing.*;

public class Casilla extends JButton {

    private boolean esUnaBomba;
    private int numBombasAlRededor;


    public Casilla(boolean esBomba){
        this.esUnaBomba= esBomba;
    }

    public void theSecretHasBeenShown(){
        if(esUnaBomba){
            setIcon(new ImageIcon("images/logo.png"));
        }else{

        }
    }

    public boolean getIfBomb(){
        return esUnaBomba;
    }

}
