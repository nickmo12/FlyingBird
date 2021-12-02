import javax.swing.*;

public class GameStart extends JFrame {
    static MyGame game_panel = new MyGame();

    //-------------------------------------------------------------------------

    public static void main(String[] args)
    {
        new GameStart();     // Instantiate the JFrame

        game_panel.init();   // Hand off to the GamePanel
    }

    //-------------------------------------------------------------------------
    // GameStart constructor sets up the JFrame for hand-off to the GamePanel
    //-------------------------------------------------------------------------

    public GameStart()
    {
        setTitle("Flying Bird");

        setSize(708, 1000);

        getContentPane().add(game_panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        setVisible(true);
    }
}
