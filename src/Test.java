
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Vector;



public class Test {
    public static void main(String args[]) throws FileNotFoundException{
        
        // deschid toate fisierele necesare
        File st = new File("D:/LABORATOARE POO/TEMA_POO/Tema/test/PROIECT/store.txt");
        File cust = new File("D:/LABORATOARE POO/TEMA_POO/Tema/test/PROIECT/customers.txt");
        File ev = new File("D:/LABORATOARE POO/TEMA_POO/Tema/test/PROIECT/events.txt");
        File out = new File("D:/LABORATOARE POO/TEMA_POO/Tema/PROIECT/test/output.txt");
        try (PrintWriter outFile = new PrintWriter(out)) {
            Date date = new Date(); // intializez data
            Scanner scan = new Scanner(st); //fac citire cu scanner in fisie-ul store.txt
            String numeMagazin = scan.nextLine(); // memorez intr-un string numele magazinului
            System.out.println(numeMagazin);
            outFile.println(numeMagazin);
            
            
            while(scan.hasNextLine()){
                String line_content = scan.nextLine(); // citesc continutul liniei
                String[] delim = line_content.split(";");
                String numeDepartament = delim[0]; // memorez numele departamentului
                int id_magazin = Integer.parseInt(delim[1]);
                
                // verific daca e bookDepartment si adaug departament
                if(numeDepartament.startsWith("B")){
                    Store.getInstance(numeMagazin).addDepartment(new BookDepartment(numeDepartament, id_magazin));
                }
                
                // verific daca e MusicDepartament si adaug departament
                if(numeDepartament.startsWith("M")){
                    Store.getInstance(numeMagazin).addDepartment(new MusicDepartment(numeDepartament, id_magazin));
                }
                
                // verific daca e SoftwareDepartment si adaug departamentul
                if(numeDepartament.startsWith("S")){
                    Store.getInstance(numeMagazin).addDepartment(new SoftwareDepartment(numeDepartament, id_magazin));
                }
                
                // verific daca e VideoDepartment si adaug departamentul
                if(numeDepartament.startsWith("V")){
                    Store.getInstance(numeMagazin).addDepartment(new VideoDepartment(numeDepartament, id_magazin));
                }
                
                // citesc numarul de produse
                int num = scan.nextInt();
                scan.nextLine();
                // citesc produsele
                for(int i=0; i < num; i++){
                    String readLine = scan.nextLine();
                    String[] delimit = readLine.split(";");
                    String numeItem = delimit[0];
                    
                    int idItem = Integer.parseInt(delimit[1]); // citesc id Item
                    double pretItem = Double.parseDouble(delimit[2]);
                    // initializez fiecare item cu nume, pret, id
                    Item item = new Item(numeItem, pretItem, idItem);
                    item.setNume(numeItem);
                    item.setID(idItem);
                    item.setPret(pretItem);
                    item.setItemDepartament(numeDepartament);
                    Store.getInstance(numeMagazin).getDepartment(id_magazin).addItem(item);
                    System.out.println(item);
                    outFile.println(item);
                }
            }
            
            // citire Customers.txt
            Scanner scanCust = new Scanner(cust);
            int totalClients = scanCust.nextInt(); // citesc nr de clienti
            scanCust.nextLine();
            // initializez toti clientii
            for(int i=0; i < totalClients; i++){
                String line_content = scanCust.nextLine();
                String[] delimit = line_content.split(";");
                String numeClient = delimit[0];
                int buget = Integer.parseInt(delimit[1]);
                String strategy = delimit[2];
                
                // setez toti clientii
                Customer targetCustomer = new Customer(numeClient, buget);
                Store.getInstance("s").enter(targetCustomer);
                System.out.println(targetCustomer);
                outFile.println(targetCustomer);
                
            }
            
            
            // citire events.txt
            Scanner scanEv = new Scanner(ev);
            int nrEvents = scanEv.nextInt();
            while(scanEv.hasNextLine()){
                String line_content = scanEv.nextLine();
                String[] delimit = line_content.split(";");
                String event  = delimit[0];
                
                // aplic pentru fiecare eveniment
                switch (event) {
                    case "addItem":
                    {
                        int ItemId = Integer.parseInt(delimit[1]);
                        String toAdd = delimit[2];
                        String CustomerName = delimit[3];
                        Item itm = Store.getInstance(numeMagazin).getItem(ItemId);
                        Department targetDepartment = Store.getInstance("s").getDepartment(itm.getItemDepartament());
                        if(toAdd.startsWith("S")){ // daca adaug in SoftWareDepartament
                            // obtin client dupa nume
                            Customer targetCustomer = Store.getInstance("s").getCustomer(CustomerName);
                            targetCustomer.sh.add(itm); // adaug
                            targetDepartment.enter(targetCustomer);
                            System.out.println(targetCustomer);
                            outFile.println(targetCustomer);
                            
                        }
                        else{ // aplic aceeasi pentru wishlist
                            Customer targetCustomer = Store.getInstance("s").getCustomer(CustomerName);
                            targetCustomer.wl.add(itm);
                            targetDepartment.addObserver(targetCustomer);
                            targetDepartment.enter(targetCustomer);
                            System.out.println(targetCustomer);
                            outFile.println(targetCustomer);
                            
                        }       break;
                    }
                    case "delItem": // stergerea unui Item
                    {
                        int ItemId = Integer.parseInt(delimit[1]);
                        String toAdd = delimit[2]; // memorez unde trebuie sa sterg
                        String CustomerName = delimit[3];
                        Item itm = Store.getInstance(numeMagazin).getItem(ItemId);
                        Department targetDepartment = Store.getInstance(numeMagazin).getDepartment(itm.getItemDepartament());
                        if(toAdd.startsWith("S")){ // daca trebuie sa sterg in ShoppingCart
                            // obtin clientul 
                            Customer targetCustomer = Store.getInstance(numeMagazin).getCustomer(CustomerName);
                            // sterg din shoppingCart
                            targetCustomer.sh.remove(targetCustomer.sh.indexOf(itm));
                            targetDepartment.exit(targetCustomer);
                            System.out.println(targetCustomer); // afisez date despre clienti
                            outFile.println(targetCustomer);
                            System.out.println(itm);
                            outFile.println(itm);
                        }
                        else{ // sterg din wishlist
                            Customer targetCustomer = Store.getInstance(numeMagazin).getCustomer(CustomerName);
                            targetCustomer.wl.remove(targetCustomer.wl.indexOf(itm));
                            targetDepartment.exit(targetCustomer);
                            System.out.println(targetCustomer); // afisez date despre clienti
                            outFile.println(targetCustomer);
                            System.out.println(itm);
                            outFile.println(itm);
                            
                        }       
                        break;
                    }
                    case "addProduct":
                    {
                        int DepId = Integer.parseInt(delimit[1]);
                        int ItemId = Integer.parseInt(delimit[2]);
                        double Price = Double.parseDouble(delimit[3]);
                        String Name = delimit[4];
                        Department depart = Store.getInstance(numeMagazin).getDepartment(DepId);
                        Item itm = new Item(Name, Price, ItemId);
                        
                        
                        // obtin numele departamentului
                        String department = Store.getInstance(numeMagazin).getDepartment(DepId).nume;
                        // adaug item in departament
                        Store.getInstance(Name).getDepartment(DepId).addItem(itm);
                        // trimit notificare pentru ca s-a adaugat un produs
                        depart.notifyAllObservers(new Notification(NotificationType.ADD,
                                date, DepId, DepId));
                        
                        System.out.println(itm);
                        outFile.println(itm);
                        
                        break;
                    }
                    case "modifyProduct":
                    {
                        int DepId = Integer.parseInt(delimit[1]);
                        int ItemId = Integer.parseInt(delimit[2]);
                        double Price = Double.parseDouble(delimit[3]);
                        // obtin departamentul
                        Department departament = Store.getInstance(numeMagazin).getDepartment(DepId);
                        // obtin item-ul
                        Item item = Store.getInstance(numeMagazin).getItem(DepId);
                        item.setPret(Price); // schimb pretul
                        // trimit notificarea de modificare a pretului
                        departament.notifyAllObservers(new Notification(NotificationType.MODIFY, date, DepId, DepId));
                        System.out.println(item); // afisez item-urile dupa modificari
                        outFile.println(item);
                        break;
                    }
                    case "delProduct":
                    {
                        int ItemId = Integer.parseInt(delimit[1]);
                        Item itm = Store.getInstance(numeMagazin).getItem(ItemId);
                        Department depart = Store.getInstance(numeMagazin).getDepartment(ItemId);
                        System.out.println(itm);
                        outFile.println(itm);
                        
                        // iterez in produse pentru a sterge dupa id
                        for(ListIterator<Department> departament = Store.getInstance(numeMagazin).getDepartments().listIterator(); departament.hasNext();){
                            Department dep = departament.next();
                            java.util.Enumeration<Item> e = dep.getItems().elements();
                            while(e.hasMoreElements()){
                                // daca s-a ajung la elementul cu acelasi id 
                                if(e.nextElement().getID() == ItemId){
                                    dep.getItems().remove(dep.getItems().indexOf(itm));
                                }
                            }
                            // trimit notificare ca a fost eliminat un produs
                            //depart.notifyAllObservers( new Notification(NotificationType.REMOVE, date, ItemId, ItemId));
                        }
                       
                        System.out.println(itm);
                        outFile.println(itm);
                        
                        break;
                    }
                    case "getItem":
                    {
                        String CustomerName = delimit[1];
                        
                        break;
                    }
                    case "getItems":
                    {
                        String toAdd = delimit[1];
                        String CustomerName = delimit[2];
                        Customer customer = Store.getInstance(numeMagazin).getCustomer(CustomerName);                      
                        break;
                    }
                    case "getTotal": 
                    {
                        String toAdd = delimit[1];
                        String CustomerName = delimit[2];
                        Customer targetCustomer = Store.getInstance(numeMagazin).getCustomer(CustomerName);
                        if(toAdd.startsWith("S")){
                            double totalPriceSc = targetCustomer.sh.getTotalPrice();
                            System.out.println(totalPriceSc);
                            outFile.println(totalPriceSc);
                        }
                        else {
                            double totalPriceWl = targetCustomer.wl.getTotalPrice();
                            System.out.println(totalPriceWl);
                            outFile.println(totalPriceWl);
                        }
                        break;
                    }
                    case "accept": // accept nu lucreaza
                    {
                        int DepID = Integer.parseInt(delimit[1]);
                        String CustomerName = delimit[2];
                        Customer customer = Store.getInstance(numeMagazin).getCustomer(CustomerName);
                        Department department = Store.getInstance(numeMagazin).getDepartment(DepID);
                        break;
                    }
                    case "getObservers":
                    {
                        int DepId = Integer.parseInt(delimit[1]);
                        Department targetDepartament = Store.getInstance("s").getDepartment(DepId);
                        Vector<Customer>  v = targetDepartament.getObservers();
                        java.util.Enumeration<Customer> e = v.elements();
                        while(e.hasMoreElements()){
                            System.out.println(e.nextElement().nume);
                            
                        }
                        break;
                    }
                    case "getNotifications": // neimplementat
                    {
                        String CustomerName = delimit[1];
                        break;
                    }
                    default:
                        break;
                }
            }
        } 
    }
}

