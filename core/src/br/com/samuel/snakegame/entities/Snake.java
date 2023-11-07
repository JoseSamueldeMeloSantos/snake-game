package br.com.samuel.snakegame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Snake {

    public final Rectangle head;
    public Array<Rectangle> body;
    private Direction direction;
    private final int SPEED;
    private Direction auxD;
    private boolean outOfScreen;

    public Snake(int x, int y, int width, int height) {
        head = new Rectangle(x, y * 3, width, height);
        body = new Array<>();
        direction = Direction.STOPPED;
        SPEED = 16;
        auxD = direction;
        outOfScreen = false;
        body.add(head);
        body.add(new Rectangle(head.x, head.y - 16, 16, 16));
    }

    private void moveHead() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)
                && direction != Direction.DOWN) {
            auxD = Direction.UP;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)
                && direction != Direction.UP) {
            auxD = Direction.DOWN;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                && direction != Direction.LEFT) {
            auxD = Direction.RIGHT;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
                && direction != Direction.RIGHT) {
            auxD = Direction.LEFT;
        }

        if (head.x % 16 == 0 && head.y % 16 == 0 && !outOfScreen) {
            direction = auxD;
        }

        if (direction == Direction.UP) head.y += SPEED;
        else if (direction == Direction.DOWN) head.y -= SPEED;
        else if (direction == Direction.RIGHT) head.x += SPEED;
        else if (direction == Direction.LEFT) head.x -= SPEED;
    }

    private void moveBody() {
        Rectangle lastMember = copyRect(body.get(0));
        for (int i = 1; i < body.size; i++) {
            Rectangle currentMember = body.get(i);
            Rectangle temp = copyRect(currentMember);

            currentMember.x = lastMember.x;
            currentMember.y = lastMember.y;

            lastMember = temp;
        }
    }

    private void reverseDirection() {
        float width = 400;
        float height = 240;
        int sprDimension = 16;

        if (head.x < 0) {
            outOfScreen = true;
            head.x += width + sprDimension;
        }
        else if (head.x > width - sprDimension) {
            outOfScreen = true;
            head.x -= width + sprDimension;
        }
        else if (head.y < 0) {
            outOfScreen = true;
            head.y += height + sprDimension;
        }
        else if (head.y > height - sprDimension) {
            outOfScreen = true;
            head.y -= height + sprDimension;
        }
        else {
            outOfScreen = false;
        }
    }

    public void moveSnake() {
        if (direction != Direction.STOPPED) moveBody();
        moveHead();
        reverseDirection();
    }

    public static Rectangle copyRect(Rectangle rect) {
        return new Rectangle(rect.x, rect.y, rect.width, rect.height);
    }
}