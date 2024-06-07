package com.unicamp.mc322.lab13;
import com.unicamp.mc322.lab13.Exceptions.CrazyDSException;
import com.unicamp.mc322.lab13.ICrazyDS.*;
import com.unicamp.mc322.lab13.IOrder.*;
import com.unicamp.mc322.lab13.IOrderStrategy.*;
import com.unicamp.mc322.lab13.Person.*;
import java.time.LocalDate;
import java.time.Month;


public class Main {
    public static void main(String[] args) {
    	
        ICrazyDS crazyDS = new CrazyDS(new YourStrategy1());
        //ICrazyDS crazyDS = new CrazyDS(new YourStrategy2());
        IOrder order1 = new InternetOrder(new PersonPT(LocalDate.of( 1985 , Month.JANUARY , 1 ), "CPF1", "name1"));
        IOrder order2 = new InternetOrder(new PersonPT(LocalDate.of( 1986 , Month.JANUARY , 2 ), "CPF2", "name2"));
        IOrder order3 = new InternetOrder(new PersonPT(LocalDate.of( 1987 , Month.JANUARY , 3 ), "CPF3", "name3"));

        crazyDS.addElement(order1);
        crazyDS.addElement(order2);
        crazyDS.addElement(order3);
        System.out.println("---- A: Elements ----");
        crazyDS.printElements();

        System.out.println("---- B: Getting and removing the element with highest priority ----");
        IOrder p1;
        try {
            p1 = crazyDS.peekElement();
            System.out.println("-selected element");
            p1.printOwner();
            crazyDS.removeElement(p1);
            System.out.println("-elements");
            crazyDS.printElements();
        } catch (CrazyDSException e) {
            e.printStackTrace();
        }

        System.out.println("---- C: Adding an old person ----");
        IOrder order4 = new InternetOrder(new PersonPT(LocalDate.of( 1880 , Month.JUNE , 1 ), "CPF", "name3"));
        crazyDS.addElement(order4);
        crazyDS.printElements();
        System.out.println("-selected element");
        IOrder p2 = crazyDS.peekElement();
        p2.printOwnerFullData();

        System.out.println("---- D: Checking an exception ----");
        try {
            IOrder p3 = crazyDS.getElementAt(1000);
        } catch (CrazyDSException e) {
            //e.printStackTrace();
            System.out.println("-ok: Show error in logs");
        }
        
        // Como o runner disponibilizado pelo professor já mostra quase todas as funcionalidades cobradas, vou
        // adicionar só aquelas que faltaram:
        //-Um pedido deve ter uma operação para obter o seu código:
        IOrder order5 = new InternetOrder(new PersonPT(LocalDate.of( 1985 , Month.JANUARY , 1 ), "CPF5", "name5"));
        System.out.println("\nOrder 5 Id: "+order5.getId());
        //-Um pedido deve ter uma operação para obter a pessoa que o fez
        PersonPT person5 = order5.getPerson();
        System.out.println("Order 5 owner:"+person5);
        
        /*Teste de estresse:
        ICrazyDS crazyDs = new CrazyDS(new YourStrategy1());
        IOrder o1 = new InternetOrder(new PersonPT(LocalDate.of( 1990 , Month.JANUARY , 1 ), "CPF1", "name1"));
        IOrder o2 = new InternetOrder(new PersonPT(LocalDate.of( 1907 , Month.JANUARY , 2 ), "CPF2", "name2"));
        IOrder o3 = new InternetOrder(new PersonPT(LocalDate.of( 1997 , Month.JANUARY , 3 ), "CPF3", "name3"));
        IOrder o4 = new InternetOrder(new PersonPT(LocalDate.of( 2000 , Month.JANUARY , 1 ), "CPF4", "name4"));
        IOrder o5 = new InternetOrder(new PersonPT(LocalDate.of( 2010 , Month.JANUARY , 2 ), "CPF5", "name5"));
        IOrder o6 = new InternetOrder(new PersonPT(LocalDate.of( 1987 , Month.JANUARY , 3 ), "CPF6", "name6"));
        IOrder o7 = new InternetOrder(new PersonPT(LocalDate.of( 1985 , Month.JANUARY , 1 ), "CPF7", "name7"));
        IOrder o8 = new InternetOrder(new PersonPT(LocalDate.of( 1986 , Month.JANUARY , 2 ), "CPF8", "name8"));
        IOrder o9 = new InternetOrder(new PersonPT(LocalDate.of( 1965 , Month.JANUARY , 3 ), "CPF9", "name9"));
        
        crazyDs.addElement(o1);
        crazyDs.addElement(o2);
        crazyDs.addElement(o3);
        crazyDs.addElement(o4);
        crazyDs.addElement(o5);
        crazyDs.addElement(o6);
        crazyDs.addElement(o7);
        crazyDs.addElement(o8);
        crazyDs.addElement(o9);
        
        System.out.println("\n---Elements:---");
        crazyDs.printElements();
        
        try {
        	for (int i=0;i<9;i++){
        		crazyDs.removeElement(crazyDs.peekElement());
        		System.out.println("\nElements:");
        		crazyDs.printElements();
        	}
        } catch(CrazyDSException e) {
        	System.out.println(e);
        }
       	*/
        
        
        
    }
}
