import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class App extends JPanel implements ActionListener {
    private final int xSize=900;
    private final int ySize=900;
    private final int fieldSize=30;
    private String direction;
    private Board board;
    private Snake snake;
    private boolean gameOver;
    private Timer timer;
    private int appleCounter=0;
    App() {
        this.setPreferredSize(new Dimension(xSize, ySize));
        this.setBackground(Color.black);
        this.setFocusable(true);
        addKeyListener(new Directions());
        startGame();
    }
    public void startGame() {
        board=new Board(xSize,ySize);
        snake = new Snake(new Field(board.xSize/2, board.xSize/2));
        board.generateFood(fieldSize);
        gameOver=false;
        timer = new Timer(80,this);
        timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if (!gameOver) {
            g.setColor(Color.red);
            g.fillOval(board.getApple().getRow(), board.getApple().getCol(), fieldSize, fieldSize);
            for(int i = 0; i< snake.getSnake().size();i++) {
                g.setColor(Color.green);
                g.fillRect(snake.getSnake().get(i).getRow(), snake.getSnake().get(i).getCol(), fieldSize, fieldSize);
            }
            g.setColor(Color.white);
            g.setFont( new Font("Score",Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+appleCounter, (xSize - metrics.stringWidth("Score: "+appleCounter))/2, g.getFont().getSize());
        } else {
            g.setColor(Color.white);
            g.setFont( new Font("Score",Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+appleCounter, (ySize - metrics.stringWidth("Score: "+appleCounter))/2, g.getFont().getSize());
            g.setFont( new Font("Score",Font.BOLD, 80));
            metrics = getFontMetrics(g.getFont());
            g.drawString("You lose", (xSize - metrics.stringWidth("You lose"))/2, ySize/2);

        }
    }
    public void isThereApple() {
        if((snake.getFirst().getRow()==board.getApple().getRow()) && (snake.getFirst().getCol()==board.getApple().getCol())) {
            appleCounter++;
            snake.grow();
            board.generateFood(fieldSize);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver){
            move();
            isCrashed();
            isThereApple();
        }
        repaint();
    }
    public void isCrashed() {
        for(int i=1;i<snake.getSnake().size();i++){
            if(snake.getSnake().get(i).getRow()==snake.getSnake().get(0).getRow() && snake.getSnake().get(i).getCol()==snake.getSnake().get(0).getCol()){
                gameOver=true;
                break;
            }
        }
        if(snake.getFirst().getRow()<0 || snake.getFirst().getRow()>xSize || snake.getFirst().getCol()<0 || snake.getFirst().getCol()>xSize){
            gameOver=true;
        }
        if(gameOver){
            timer.stop();
        }
    }
    private void move() {
        if (Objects.equals(direction, "Right")) {
            snake.move(new Field(snake.getFirst().getRow()+fieldSize, snake.getFirst().getCol()));
        }

        if (Objects.equals(direction, "Up")) {
            snake.move(new Field(snake.getFirst().getRow(), snake.getFirst().getCol()-fieldSize));
        }

        if (Objects.equals(direction, "Left")) {
            snake.move(new Field(snake.getFirst().getRow()-fieldSize, snake.getFirst().getCol()));
        }

        if (Objects.equals(direction, "Down")) {
            snake.move(new Field(snake.getFirst().getRow(), snake.getFirst().getCol()+fieldSize));
        }
    }
    private class Directions extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ((key == KeyEvent.VK_RIGHT) && (!Objects.equals(direction, "Left"))) {
                direction="Right";
            }
            if ((key == KeyEvent.VK_UP) && (!Objects.equals(direction, "Down"))) {
                direction="Up";
            }
            if ((key == KeyEvent.VK_LEFT) && (!Objects.equals(direction, "Right"))) {
                direction="Left";
            }
            if ((key == KeyEvent.VK_DOWN) && (!Objects.equals(direction, "Up"))) {
                direction="Down";
            }
        }
    }
}
