
import java.util.Collection;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

abstract class ItemList{
    Node<Item> head; // capul listei 
    int size; 
    Comparator<Item> comparator;
    
    public Comparator getComparator(){
            return comparator;
    }
    
    // comstructor care initializeaza lista cu un element si initializeaza comparatorul
    public ItemList(Comparator comparator){
        size = 0;
        head = new Node(null);
        this.comparator = comparator;
    }
    
    public ItemList(){
        size = 0;
        head = new Node(null);
    }
    
    // metoda pentru adaugarea unui nod in lista
    public boolean add(Item element) {
        if(isEmpty()){ // verific daca lista este vida si adauga un singur nod
            Node<Item> tmp = new Node(element);
            head = tmp; // pune nodul la inceput
            
            size++;
            return true;
        }
        
        Node<Item> current = head; // memorez capul listei 
        Node<Item> toAdd = new Node(element); // nodul care va fi adaugat
        
        if(current.getNext() != null){ // verific daca exista mai mult de un nod in lista
            // daca element-ul nodului de adaugat e mai mare , il adaug dupa current
            if(comparator.compare(element, current.getElement()) > 0){ 
                current.setNext(toAdd);
                toAdd.setPrev(current);
                
                size++; // creste dimensiunea listei 
                return true;
            }
        }
        else { // daca am doar un nod in lista , il adaug imediat dupa primul
            current.setNext(toAdd);
            toAdd.setPrev(current);
            
            size++;
            return true;
            }
        
        // verific cate 2 noduri si fac sortarea crescatoare a nodurilor 
        while(current.getNext() != null){
            // daca trebuie sa fie adaugat un nod intre cele 2 , se adauga si se stabilesc legaturile
            if (comparator.compare(element, current.getElement()) > 0){
                   if(comparator.compare(element, current.next.getElement()) < 0){
                        Node<Item> nou = new Node(element);
                        current.setNext(nou);
                        nou.setPrev(current);
                        current.getNext().setPrev(nou);
                        nou.setNext(current.getNext());
                        
                        size++;
                        return true;
                    }
                // parcurg lista mai departe 
                current = current.next;
            }  
        }
        return false;
        }
  
    // metoda folosita pentru a sterge un element de la un index dat
    public Item remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        // daca lista e vida, nu am ce sterge 
        if(isEmpty())
            return null;
        if(index == 0){ // daca index == 0 , sterg primul nod 
            Node<Item> temp = head;
            head = head.getNext();
            size--;
            return temp.getElement();
        }
        else{ // daca index != 0, sterg un nod care il obtin cu getNode(index)
            Node<Item> current = getNode(index);
            Node<Item> previous = current.getPrev();
            Node<Item> next = current.getNext();
            previous.setNext(next);
            next.setPrev(previous);
            return current.getElement();
        }
    }
    
    // metoda nefolosita
    public boolean addAll(Collection<? extends Item> c) {return true;};
    
     public Item getItem(int index) {
         Node current = head.getNext();
        if(index > 0 && index < this.size) {         
            for(int i = 0; i < index; i++) {
                if(current.getNext() == null) {
                    return null;
                }
                current = current.getNext();
            }
        }
        return (Item) current.getElement();
    }
     
     // metoda care intoarce nodul de la pozitia index
     public Node<Item> getNode(int index){
         Node<Item> current = head.getNext(); // memorez capul 
         if(index > 0 && index < this.size){ // daca index-ul e valid
            for(int i=0; i < index; i++){
                if(i == index) // daca se ajunge la pozitia necesara , intoarce nodul
                    return current;
                else{
                    current = current.next; // altfel merge mai departe 
                }
            }
         }
     return current; 
     }
       
     // metoda care verifica daca un nod se contine in o lista
     public boolean contains(Node<Item> node){
         Node<Item> current = head;
         while(current != null){
             if(current == node)
                 return true;
             current = current.next;
         }
         return false;
     }

     // metoda care verifica daca se contine un item
     public boolean contains(Item item){
         // folosesc un ListIterator pentru a parcurge toate nodurile
         ListIterator<Item> list = this.listIterator();
         Item checked = list.next();
         while(list.hasNext()){
             if(checked == item)
                 return true;
             checked = list.next();
         }
         return false;
     }
     
     // returneaza daca este vida lista 
     public boolean isEmpty(){
        return size == 0;
     }
     
     public int getSize() {
        return size;
    }
   
     // metoda folosita pentru a obtine un int index al unui item
     public int indexOf(Item item){
        int index;
        for(index=0; index < this.size; index++){
            if(this.getItem(index) == item)
                return index;
        }
        int INT_MIN = -9999999;
        return INT_MIN;
     }    

    @Override
     public String toString() {
        Node current = head.getNext();
        String result = "";
        while(current != null) {
            result += "[" + current.getElement() + "] ";
            current = current.getNext();
        }
        return result;
    }
     
    public static class Node<Item> {
        private Node<Item> next;
        private Node<Item> prev;
        private Item element;

        public Node(Item element){
            next = null;
            prev = null;
            this.element = element;
        }
        
        public Node(Node next, Node prev, Item element) {
            this.next = next;
            this.prev = prev;
            this.element = element;
        }

        Node() {             
        }
        
        public Item getElement(){
            return element;
        }
        
        public void setElement(Item element){
            this.element = element;
        }
        
        public Node<Item> getNext(){
            return next;
        }
        
        public void setNext(Node next){
            this.next = next;
        }
        
        public Node<Item> getPrev(){
            return prev;
        }
        
        public void setPrev(Node prev){
            this.prev = prev;
        }
    }

    class ItemIterator implements ListIterator<Item>{
      
        Node<Item> nextNode;
        Node<Item> previousNode;
        Node<Item> current;
        Node<Item> last = null;
        int index = 0;
        
        
        
        public ItemIterator(){
            current = head;
        }
        
        public int getIndex(int index){
            return index;
        }
        
        public void setIndex(int index){
            this.index = index;
        }
        
        @Override
        public boolean hasNext(){
            return index < size;
        }
        
        @Override
        public boolean hasPrevious(){
            return index > 0;
        }
        
        @Override
        
        // returneaza urmatorul element
        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            last = current;
            Item result = current.element;           
            current = current.next;
            index++;
            return result;
        }
                
        @Override
        // returneaza elementul precedent
        public Item previous(){
            if(!hasPrevious()) throw new NoSuchElementException();
            current = current.prev;
            index--;
            last = current;
            Item result = current.element;
            return result;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
           return index--;
        }

        

        @Override
        public void set(Item element) {
            if(last == null) throw new IllegalStateException();
            last.element = element; 
        }

        @Override
        public void add(Item e) {
            
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
       
    }
    
    public ListIterator<Item> listIterator(){
        return new ItemIterator();
    }
    
    // constructor pentru accesare indexata cu ajutorul unui listiterator
    public ListIterator<Item> listIterator(int index) {
        ListIterator list = new ItemIterator();
        ((ItemIterator) list).setIndex(index);
        return list;
    }
    
}
