
import java.util.ListIterator;
import java.util.Vector;

class Store{

    
    String nume;
    Department department;
    Vector<Customer> v = new Vector<>(); // retin clientii intr-un vector
    Vector<Department> d = new Vector<>(); // retin departamentele intr-un vector
    private static Store instance = null;
    
    public Store(){}
    
    public Store(String nume){
        this.nume = nume;
    }
    
    // foloesesc metoda getInstance pentru Singleton
    static Store getInstance(String nume) {
        if(instance == null){
            instance = new Store(nume);
        }
        return instance;
    }
    
    // clientul a intrat in magazin
    public void enter(Customer customer){
        v.add(customer);
    }
    
    // clientul a iesit din magazin
    public void exit(Customer customer){
        v.remove(customer);
    }
    
    // metoda folosita pentru a gasi un client dupa nume
    public Customer getCustomer(String name){
        // folosesc ListIterator<Customer> pentru a parcurge tot vectorul de customeri
        for(ListIterator<Customer> cust = getCustomers().listIterator(); cust.hasNext();){
            Customer searchedCustomer = cust.next();
            if(searchedCustomer.nume.equals(name)){
                return searchedCustomer;
            }
        }
    return null;
    }
    
    // metoda folosita pentru a gasi un Departament dupa ID 
    public Department getDepartment(int id){
        // folosesc un ListIterator pentru a parcurge fiecare departament in vectorul de departamente
        for(ListIterator<Department> dep = getDepartments().listIterator(); dep.hasNext();){
            Department searchedDepartment = dep.next();
            if(searchedDepartment.getId() == id){
                return searchedDepartment;
            }
        }
    return null;
    }
    
    // gasesc un departament dupa numele sau 
    public Department getDepartment(String name){
        for(ListIterator<Department> dep = getDepartments().listIterator(); dep.hasNext();){
            Department searchedDepartment = dep.next();
            if(searchedDepartment.nume .equals(name)){
                return searchedDepartment;
            }
        }
    return null;
    }
    
    // returnez un Item dupa id-ul cautat
    public Item getItem(int id){
        for(ListIterator<Department> departament = getDepartments().listIterator();
                departament.hasNext();){
            Vector<Item> next = departament.next().getItems();
            for(Item item : next){
                if(item.getID() == id){
                    return item;
                }
            }
        }
        return null;
    }
    
    // returnez vectorul de clienti
    public Vector getCustomers(){
        return v;
    }
    
    // metoda folosita pentru a adauga un departament
    public void addDepartment(Department depart){
        d.add(depart);
    }
    
    // returnez tot vectorul de departamente
    public Vector getDepartments(){
        return d;
    }
    
}
