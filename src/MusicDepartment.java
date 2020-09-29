
class MusicDepartment extends Department{

    public MusicDepartment(String numeDepartament, int id_magazin){
        super(numeDepartament,id_magazin);
    }
    
    @Override
    public void accept(Visitor visitor) { // implementeaza visit pentru fiecare visitor
        visitor.visit(this);
    }
}