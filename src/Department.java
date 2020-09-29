
import java.util.Vector;

abstract class Department implements Subject{
    String nume; // numele departamentului
    Vector<Item> items = new Vector<>(); // vector pentru item-uri disponibile 
    Vector<Customer> clienti = new Vector<>(); // vector pentru clienti care au cumparat
    Vector<Customer> freeClienti = new Vector<>(); // vectori care isi doresc un produs
    int id;
    
    public Department(){}
    
    // constructor pentru a initializa un departament
    public Department(String nume, int id){
        this.id = id;
        this.nume = nume;
    }
    
    // clientul a cumparat un produs
     public void enter(Customer customer){
        clienti.add(customer);
         
    }
    
     // clientul nu mai vrea sa cumpere
    public void exit(Customer customer){
        clienti.remove(customer);
        
    }
    // se adauga un item in vectorul de items
    public void addItem(Item item){
        items.add(item);
    }
    
    // intoarce ID-ul departamentului
    public int getId(){
        return id;
    }
    
    // intoarce vectorul de items
    public Vector getItems(){
       return items;
    }
    
    // intoarce vectorul de clienti care au cumparat ceva
    public Vector getCustomers(){
        return clienti;
    }
    
    // intoarec vectorul de clienti care doresc sa cumpere ceva
    public Vector<Customer> getObservers(){
        return freeClienti;
    }
    
    // se apeleaza cand un observer vrea sa cumpere ceva
    @Override
    public void addObserver(Customer customer){
        freeClienti.add(customer);
    }
    
    // se apeleaza cand un observer nu mai vrea sa cumpere ceva
    @Override
    public void removeObserver(Customer customer){
        freeClienti.remove(customer);
    }
    
    @Override
    // trimite notificarea catre fiecare observer
    public void notifyAllObservers(Notification notification){
        for(Customer client: freeClienti){
            client.update(notification);
        }
    }
    
    @Override
    public String toString(){
        return nume + ";" + id;
    }
    
    abstract public void accept(Visitor visit);
    
}