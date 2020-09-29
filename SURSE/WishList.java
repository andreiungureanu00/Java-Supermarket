
import java.util.ListIterator;



class WishList extends ItemList{

    public WishList() {
        super(new CompareName());
    }
    
    
    @Override
    public boolean add(Item element){
        boolean flag = super.add(element);
        return flag;
    }
    
    
    @Override
    public Item remove(int index){
        Item removed = super.remove(index);
        return removed;
    }
    
    // returneaza pretul total 
    public double getTotalPrice() {
        double s = 0;
        ListIterator<Item> list = this.listIterator(); 
        while(list.hasNext()){
            Item produs = list.next();
            s += produs.getPret();
        }
        return s;
    }
    
}
