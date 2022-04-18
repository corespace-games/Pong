package Controller;

import EventListener.KeyWatch;
import INIT.Constants;
import Renderer.Rect;

import java.awt.event.KeyEvent;

public class PlayerController {
    public Rect rect;
    public KeyWatch keyWatch;

    public PlayerController(Rect rect, KeyWatch keyWatch) {
        this.rect = rect;
        this.keyWatch = keyWatch;
    }

    public void update(double dt) {
        if (keyWatch.isKeyPressed(KeyEvent.VK_W)) {
            if (rect.y - Constants.PADDLE_SPEED * dt > Constants.TOOLBAR_HEIGHT)
                this.rect.y -= (Constants.PADDLE_SPEED * dt);
        } else if (keyWatch.isKeyPressed(KeyEvent.VK_S)) {
            if ((rect.y + Constants.PADDLE_SPEED * dt) + rect.height < Constants.SCREEN_HEIGHT - Constants.INSETS_BOTTOM)
                this.rect.y += (Constants.PADDLE_SPEED * dt);
        }
    }

}
