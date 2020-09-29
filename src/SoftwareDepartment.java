



class SoftwareDepartment extends Department{

    public SoftwareDepartment(String numeDepartament, int id_magazin){
        super(numeDepartament, id_magazin);
    }
    
    @Override
    public void accept(Visitor visitor) {
       visitor.visit(this);
    }

    
       
    
}