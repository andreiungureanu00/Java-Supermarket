

class VideoDepartment extends Department{
    
    public VideoDepartment(String numeDepartament, int id_magazin){
        super(numeDepartament, id_magazin);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}