package pack.Arkanoid;

public class Menu {

    private int currentMenuItem;
    private String[] nameMenuItem = {"New game", "Help", "Exit"};
    private int maxMenuItem;
    private static boolean help;

    public static boolean isHelp() {
        return help;
    }

    public static void setHelp(boolean help) {
        Menu.help = help;
    }

    public Menu(){
        currentMenuItem=0;
        maxMenuItem=nameMenuItem.length;
    }



    public int getCurrentMenuItem() {
        return currentMenuItem;
    }

    public int getMaxMenuItem() {
        return maxMenuItem;
    }

    public String[] getNameMenuItem() {
        return nameMenuItem;
    }

    void keyMenu(int keyCode){
        switch (keyCode){
            case 38:
                currentMenuItem--;
                if (currentMenuItem<0){
                    currentMenuItem=maxMenuItem-1;
                }
                break;
            case 40:
                currentMenuItem++;
                if (currentMenuItem>=maxMenuItem){
                    currentMenuItem=0;
                }
                break;

        }
    }
}
