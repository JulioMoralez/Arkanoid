package pack.Arkanoid;

import java.awt.*;

public enum BonusType {

    EXPAND("E",new Color(0,0,255)),
    DIVIDE("D",new Color(50,200,255)),
    LASER("L",new Color(255,0,0)),
    SLOW("S",new Color(255,0,255)),
    FASTER("F",new Color(255,0,255)),
    BREAK("B",new Color(150,0,150)),
    CATCH("C",new Color(0,255,0)),
    PLAYER("P",new Color(200,200,200)),
    QUATRO("Q",new Color(200,100,100)),
    MULTI("P",new Color(255,255,0));

    private String name;
    private Color color;

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    BonusType(String name, Color color) {
        this.name=name;
         this.color=color;
    }
}
