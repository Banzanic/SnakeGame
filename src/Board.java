public class Board {
    private final Field[][] board;
    final int xSize;
    final int ySize;
    private Field apple;
    public Board(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        board = new Field[xSize][ySize];
        for (int i = 0; i < xSize; i++) {
            for (int j = 0; j < ySize; j++) {
                board[i][j] = new Field(i, j);
            }
        }
    }

    public void generateFood(int fieldSize)
    {
        int i=(int)(Math.random() * xSize/fieldSize)*fieldSize;
        int j=(int)(Math.random() * ySize/fieldSize)*fieldSize;
        while(board[i][j].getFieldType()==FieldType.SNAKE){
            i=(int)(Math.random() * xSize/fieldSize)*fieldSize;
            j=(int)(Math.random() * ySize/fieldSize)*fieldSize;
        }
        board[i][j].setFieldType(FieldType.FOOD);
        apple=board[i][j];
    }
    public Field getApple(){
        return apple;
    }
}
