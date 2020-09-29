
import java.util.*;

 class ComparePrice implements Comparator<Item>{
    
    @Override
    // metoda care compara dupa pret 2 item-uri
    public int compare(Item a, Item b){
        double pret1 = a.getPret();
        double pret2 = b.getPret();
        if( pret1 == pret2 ) // daca pretul e egal, compara dupa nume
            return compare_name(a, b);
        else
            return (int) (pret1 - pret2);
    }
    
    public int compare_name(Item a, Item b){
        String nume1 = a.getNume();
        String nume2 = b.getNume();
        return (int) nume1.compareTo(nume2);
    }
 }

// comparator pentru compararea dupa nume a 2 item-uri
class CompareName implements Comparator<Item>{
  
    @Override
    public int compare(Item a, Item b){
        String nume1 = a.getNume();
        String nume2 = b.getNume();
        return (int) nume1.compareTo(nume2);
    }
 }
 