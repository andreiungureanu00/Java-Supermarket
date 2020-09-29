
import java.util.Iterator;
import java.util.ListIterator;

class ShoppingCart extends ItemList implements Visitor{
 
    double buget;
    
    Node<Item> current;
    
    public ShoppingCart(double buget){
        super(new ComparePrice()); // initializez cu Comparatorul de pret 
        this.buget = buget;
    }
    
    // metoda pentru a intoarce totalul tuturor preturilor produselor
   public double getTotalPrice() {
        double s = 0;
        // folosesc listiterator pentru a parcurge fiecare element
        ListIterator<Item> list = this.listIterator(); 
        while(list.hasNext()){
            Item produs = list.next();
            s += produs.getPret(); // obtin totalul preturilor
        }
        return s;
    }
    
    @Override
    public boolean add(Item element) { // metoda add in functie de buget
        if(buget > element.getPret()){
            super.add(element);
            buget = buget - element.getPret();
            return true;
        }
        else 
            return false;
    }

   
    @Override
    // suprascrie metoda remove , aplicand modificari asupra bugetului
    public Item remove(int index) {
        Item tmp = super.remove(index);
        buget = buget + tmp.getPret();
        return tmp;
    }

    @Override
    // se aplica accept pentru BookDepartment
    public void visit(BookDepartment bookDepartment) {
        double pret = 0;
        Iterator<Item> list = this.listIterator();
        while(list.hasNext()){
            // daca apartine departamentului ce incepe cu b sau B
            if(list.next().getItemDepartament().startsWith("b") ||
                    list.next().getItemDepartament().startsWith("B")){
                list.next().setPret(list.next().getPret() * 0.9); // schimba pretul
            }
        }
    }
    
    
    @Override
     // accept opentru SoftwareDepartment
    public void visit(SoftwareDepartment softwareDepartment) {
           double pret = 0;
           int index = 0;
           double minim = (int)getItem(0).getPret();
           for(ListIterator<Item> list = this.listIterator(); list.hasNext();){
            if(minim > list.next().getPret()) // aflu minimul elementelor 
                minim = list.next().getPret();
           }
           
           // adaug doar daca buget-ul permite cumpararea unui produs
           if(buget < minim){
               for(ListIterator<Item> iterator = listIterator();iterator.hasNext();){
                   if(iterator.next().getItemDepartament().startsWith("S") ||
                           iterator.next().getItemDepartament().startsWith("s")){
                        pret = iterator.next().getPret();
                        pret -= pret * 0.2; // aplic reducerea de 20%
                        iterator.next().setPret(pret); // setez noul pret
                   }
                    
               }
           }
       }

    @Override
    public void visit(VideoDepartment videoDepartment) {
        double maxPrice = 0; 
        for(ListIterator<Item> it = this.listIterator();it.hasNext();){// iterator pentru maxPrice
            if(it.next().getPret() > maxPrice){
                maxPrice = it.next().getPret(); // aflu cel mai scump element
            }
        }
        
        double total = this.getTotalPrice();
                
        for(ListIterator<Item> list = this.listIterator();list.hasNext();){
            if(total > maxPrice){
                if(list.next().getItemDepartament().startsWith("v") ||
                    list.next().getItemDepartament().startsWith("V")){
                    double pret = list.next().getPret();
                    pret -= pret * 0.15; // aplic reducerea de 15%
                    list.next().setPret(pret);
                }        
            }
        }
        buget += 0.05 * total;
    }

    @Override
    public void visit(MusicDepartment musicDepartment) {
        double total = 0;
        for(ListIterator<Item> it = this.listIterator(); it.hasNext();){
            if(it.next().getItemDepartament().startsWith("M") || 
                    it.next().getItemDepartament().startsWith("m")){
                total += it.next().getPret();
            }
        }
        buget += 0.1 * total; // adauga la buget 10%
    }   
        
}