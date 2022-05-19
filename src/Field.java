public class Field {
    private FieldType fieldtype;
    private final int row;
    private final int col;

    public Field(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public FieldType getFieldType() {
        return fieldtype;
    }

    public void setFieldType(FieldType fieldtype) {
        this.fieldtype = fieldtype;
    }
}
