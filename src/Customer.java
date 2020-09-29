
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

class Customer implements Observer{
    String nume;
    ShoppingCart sh;
    WishList wl;
    Vector<Notification> notifications;
    
    // creez constructor pentru a initializa un Customer
    public Customer(String nume, int buget){
        this.nume = nume;
        wl = new WishList();
        sh = new ShoppingCart(buget);
        notifications = new Vector<>();
    }
  
    void update(Notification notification) {
        Iterator it = notifications.iterator();
        while(it.hasNext()){
            notifications.add(notification);
        }
    }
    
    @Override
    public String toString(){
        return nume + ";" + sh.buget;
    }
    
    // metoda neimplementata
    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}