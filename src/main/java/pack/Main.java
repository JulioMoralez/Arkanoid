package pack;

import pack.Arkanoid.Arkanoid;

import javax.swing.*;

public class Main{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }


    private Main() {
        new Arkanoid();
    }
}
