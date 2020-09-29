

public class BookDepartment extends Department{

    // creez constructor pentru BookDepartment
    public BookDepartment(String numeDepartament, int id_magazin) {
       super(numeDepartament, id_magazin);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    

}
