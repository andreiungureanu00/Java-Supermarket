
import java.util.Date;

enum NotificationType{
        ADD,REMOVE,MODIFY
    }

class Notification{
    
    NotificationType notification;
    int department_ID;
    int product_ID;
    Date date;

public Notification(NotificationType notification, Date date, int deparment_ID, int product_ID){
    this.notification = notification;
    this.date = date;
    this.department_ID = deparment_ID;
    this.product_ID = product_ID;
}
    
}

