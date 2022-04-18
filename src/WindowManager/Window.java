package WindowManager;

import Controller.PlayerController;
import EventListener.KeyWatch;

import javax.swing.*;
import java.awt.*;

import INIT.Constants;
import INIT.Time;
import Renderer.Rect;
import Renderer.Circ;

public class Window extends JFrame implements Runnable {

    public Graphics2D g2;
    public KeyWatch keyWatcher = new KeyWatch();
    public Rect playerOne, ai;
    public Circ ball;
    public PlayerController playerController;

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITEL);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(keyWatcher);

        Constants.TOOLBAR_HEIGHT = this.getInsets().top;
        Constants.INSETS_BOTTOM = this.getInsets().bottom;
        g2 = (Graphics2D) this.getGraphics();

        playerOne = new Rect(Constants.HOR_PADDING, Constants.VERT_PADDING, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.PLAYER_PADDLE_COLOR);
        playerController = new PlayerController(playerOne, keyWatcher);

        ai = new Rect(Constants.SCREEN_WIDTH - Constants.PADDLE_WIDTH - Constants.HOR_PADDING,
                Constants.VERT_PADDING, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT, Constants.AI_PADDLE_COLOR);
        ball = new Circ(Constants.SCREEN_WIDTH / 2.0f, Constants.SCREEN_HEIGHT / 2.0f, Constants.BALL_RADIUS, Constants.BALL_COLOR);
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.drawDBImage(dbg);
        g2.drawImage(dbImage, 0, 0, this);

        playerController.update(dt);
    }

    public void drawDBImage(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        playerOne.draw(g2);
        ai.draw(g2);
        ball.draw(g2);
    }

    public void run() {
        double lastFrameTime = 0.0;
        while (true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);
        }
    }

}
