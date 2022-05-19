import java.util.LinkedList;

public class Snake {
    private final LinkedList<Field> snake;
    private Field first;

    public Snake(Field start) {
        snake = new LinkedList<>();
        first = start;
        first.setFieldType(FieldType.SNAKE);
        snake.add(first);
    }

    public void grow() {
        snake.add(first);
    }

    public void move(Field nextField) {
        first = nextField;
        first.setFieldType(FieldType.SNAKE);
        snake.addFirst(first);
        Field last = snake.removeLast();
        last.setFieldType(FieldType.EMPTY);
    }

    public Field getFirst() {
        return first;
    }


    public LinkedList<Field> getSnake() {
        return snake;
    }
}
