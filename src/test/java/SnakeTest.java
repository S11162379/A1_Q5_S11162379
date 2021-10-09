    /*
     * This file contains the test cases for the snake game in Q5
     *
     * Compiled by Niheet N. Prasad // S11162379
     *
     * More possible test cases (not implemented due to time constraints):
     *
     * 1. Check default location of snake
     * 2. Check if food spawns
     * 3. Check if food despawns when eaten
     * 4. Check if snake gets longer when eating food
     * 5. Coordinates change when the snake moves every frame
     * 6. Check if game stops when snake hits itself
     *
     *
     * */

    import org.junit.jupiter.api.Assertions;
    import org.junit.jupiter.api.BeforeAll;
    import org.junit.jupiter.api.DisplayName;
    import org.junit.jupiter.api.Test;

    import java.awt.event.KeyEvent;

    class SnakeTest {

        //Instantiate a window to allow for testing
        Window instance = new Window();
        KeyboardListener keyboardListener = new KeyboardListener();

        //Defining keyEvents to simulate keyboard input by the user
        KeyEvent up = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, 'W');
        KeyEvent down = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, 'S');
        KeyEvent left = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, 'A');
        KeyEvent right = new KeyEvent(instance, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, 'D');


        @BeforeAll
        public static void setupAll() {
            System.out.println("\nInitiating testing sequence...");
        }

        //Checking if an invalid value for the color parameter is handled (less than 0)
        @Test
        @DisplayName("Checking if col is less than 0")
        public void ThrowRuntimeExceptionIfColIsLessThanZero() {

            Assertions.assertThrows(RuntimeException.class, () -> {
                DataOfSquare dataOfSquare = new DataOfSquare(-1);
            });
        }

        //Checking if an invalid value for the color parameter is handled (more than 2)
        @Test
        @DisplayName("Checking if col is more than 2")
        public void ThrowRuntimeExceptionIfColIsMoreThanTwo() {

            Assertions.assertThrows(RuntimeException.class, () -> {
                DataOfSquare dataOfSquare = new DataOfSquare(3);
            });
        }

        //Checking for invalid movement, i.e trying to go the direction that is opposite to the snake's path
        //Check if going backwards from North
        @Test
        @DisplayName("Check if heading backwards from North")
        public void ThrowRuntimeExceptionIfHeadingBackwardsFromNorth() {

            instance.getKeyListeners()[0].keyPressed(up);
            keyboardListener.keyPressed(up);
            instance.getKeyListeners()[0].keyPressed(down);
            keyboardListener.keyPressed(down);

            Assertions.assertEquals(3, ThreadsController.directionSnake);
        }

        //Checking for invalid movement, i.e trying to go the direction that is opposite to the snake's path
        //Check if going backwards from South
        @Test
        @DisplayName("Check if heading backwards from South")
        public void ThrowRuntimeExceptionIfHeadingBackwardsFromSouth() {

            instance.getKeyListeners()[0].keyPressed(down);
            keyboardListener.keyPressed(down);
            instance.getKeyListeners()[0].keyPressed(up);
            keyboardListener.keyPressed(up);

            Assertions.assertEquals(4, ThreadsController.directionSnake);
        }

        //Checking for invalid movement, i.e trying to go the direction that is opposite to the snake's path
        //Check if going backwards from East
        @Test
        @DisplayName("Check if heading backwards from East")
        public void ThrowRuntimeExceptionIfHeadingBackwardsFromEast() {

            instance.getKeyListeners()[0].keyPressed(right);
            keyboardListener.keyPressed(right);
            instance.getKeyListeners()[0].keyPressed(left);
            keyboardListener.keyPressed(left);

            Assertions.assertEquals(1, ThreadsController.directionSnake);
        }

        //Checking for invalid movement, i.e trying to go the direction that is opposite to the snake's path
        //Check if going backwards from West
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
    }
