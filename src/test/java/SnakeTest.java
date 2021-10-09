import org.junit.Ignore;
import org.junit.jupiter.api.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class SnakeTest {

    Window instance = new Window();
    KeyboardListener keyboardListener = new KeyboardListener();

    Tuple position = new Tuple(10, 10);
    ThreadsController threadsController = new ThreadsController(position);

    KeyEvent up = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'W');
    KeyEvent down = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'S');
    KeyEvent left = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'A');
    KeyEvent right = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'D');


    @BeforeAll
    public static void setupAll() {
        System.out.println("\nInitiating testing sequence...");
    }

    //Checking if an invalid value for the color parameter is handled
    @Test
    @DisplayName("Checking if col is less than 0")
    public void ThrowRuntimeExceptionIfColIsLessThanZero() {

        Assertions.assertThrows(RuntimeException.class, () -> {
            DataOfSquare dataOfSquare = new DataOfSquare(-1);
        });
    }

    @Test
    @DisplayName("Checking if col is more than 2")
    public void ThrowRuntimeExceptionIfColIsMoreThanTwo() {

        Assertions.assertThrows(RuntimeException.class, () -> {
            DataOfSquare dataOfSquare = new DataOfSquare(3);
        });
    }

    //Checking for invalid movement, i.e trying to go the direction that is opposite to the snake's path
    @Test
    @DisplayName("Check if heading backwards from North")
    public void ThrowRuntimeExceptionIfHeadingBackwardsFromNorth() {

        instance.getKeyListeners()[0].keyPressed(up);
        keyboardListener.keyPressed(up);
        instance.getKeyListeners()[0].keyPressed(down);
        keyboardListener.keyPressed(down);

        Assertions.assertEquals(3, ThreadsController.directionSnake);
    }

    @Test
    @DisplayName("Check if heading backwards from South")
    public void ThrowRuntimeExceptionIfHeadingBackwardsFromSouth() {

        instance.getKeyListeners()[0].keyPressed(down);
        keyboardListener.keyPressed(down);
        instance.getKeyListeners()[0].keyPressed(up);
        keyboardListener.keyPressed(up);

        Assertions.assertEquals(4, ThreadsController.directionSnake);
    }

    @Test
    @DisplayName("Check if heading backwards from East")
    public void ThrowRuntimeExceptionIfHeadingBackwardsFromEast() {

        instance.getKeyListeners()[0].keyPressed(right);
        keyboardListener.keyPressed(right);
        instance.getKeyListeners()[0].keyPressed(left);
        keyboardListener.keyPressed(left);

        Assertions.assertEquals(1, ThreadsController.directionSnake);
    }

    @Test
    @DisplayName("Check if heading backwards from West")
    public void ThrowRuntimeExceptionIfHeadingBackwardsFromWest() {

        //Extra keypress to go downward as the default direction of the snake is towards East,
        //thus the test always fails since directionSnake is always '1' and does not change.
        instance.getKeyListeners()[0].keyPressed(down);
        keyboardListener.keyPressed(down);
        instance.getKeyListeners()[0].keyPressed(left);
        keyboardListener.keyPressed(left);
        instance.getKeyListeners()[0].keyPressed(right);
        keyboardListener.keyPressed(right);

        Assertions.assertEquals(2, ThreadsController.directionSnake);
    }


    @Test
    @DisplayName("Check if pauser throws exception when thread is interrupted")
    public void ThrowRuntimeExceptionPauserThreadInterrupted() {

        Assertions.assertThrows(InterruptedException.class, () -> {
            threadsController.moveInterne(4);
        });
    }
}
