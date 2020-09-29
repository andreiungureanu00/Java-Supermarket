
public interface Subject {
    
    public void addObserver(Customer customer);
    public void removeObserver(Customer customer);
    public void notifyAllObservers(Notification notification);
    
}
