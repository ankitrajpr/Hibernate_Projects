package helloworldexample;
/* =================================

author ankitrajprasad created on 10/04/20 
inside the package - helloworldexample 

=====================================*/


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class HelloWorldExample {

    //In order to use Hbernate
    private static SessionFactory factory;  //And each time, my program has ended, so I don't have a connection, so none of the messages are left in memory. It's obtaining all that data from the database, and that's how we persist the data from one session to the next.
    //As seen it will reprints all data from database stored previously without obtainng that data specifically from DB.
    private static ServiceRegistry registry;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String m = "";
        System.out.println("Enter a message: ");
        m = in.nextLine();
        try{
            Configuration conf = new Configuration().configure();
            registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            factory = conf.buildSessionFactory(registry);
        } catch (Throwable ex){
            System.err.println("Failed to create session factory object"+ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        Short msgId = null;
        try{
            tx=session.beginTransaction();
            Message msg = new Message(m);
            msgId = (Short) session.save(msg);
            List messages = session.createQuery("FROM Message").list(); //Used HQL query as Object/ simple java objects rather than POJO to interact with DB. Also, redundacny of ResultSet here otherwise we needed to extract data from result set and then add to it by getter /setter of it.
            for(Iterator iterator = messages.iterator(); iterator.hasNext();){
                Message message = (Message)iterator.next();
                System.out.println("message: "+message.getMessage());
            }
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
