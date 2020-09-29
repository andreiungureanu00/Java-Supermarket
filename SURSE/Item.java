class Item {
    private String nume;
    private int ID;
    private double pret;
    String department;
    
    // constructor 
    public Item(String nume, double pret, int id){
        this.ID = id;
        this.nume = nume;
        this.pret = pret;
    }
    
    public String getNume(){
        return nume;
    }
    
    public void setNume(String nume){
        this.nume = nume;
    }
    
    public int getID(){
        return ID;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public double getPret(){
        return pret;
    }
    
    public void setPret(double pret){
        this.pret = pret;
    }
    
    public void setItemDepartament(String department){
        this.department = department;
    }
    
    public String getItemDepartament(){
        return department;
    }
    
    @Override
    public String toString(){
        return nume + ";" + ID + ";" + pret; 
    }
}