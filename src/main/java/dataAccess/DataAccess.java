package dataAccess;

 
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.UtilDate;
import modelo.domain.*;
import exceptions.QuestionAlreadyExist;
import modelo.HibernateUtil;
import org.hibernate.Session;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess implements DataAccessInterface {
	
	private static DataAccess instance;
	
	public DataAccess() {
		initializeDB();
	}
	
	public static DataAccess getInstance() {
		if(instance==null) instance = new DataAccess();
		return instance;
	}

	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		 session.beginTransaction();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
				Event ev1=new Event(1, "Atleti-Athlet", UtilDate.newDate(year,month,17));
				Event ev2=new Event(2, "Eibar-Barcel", UtilDate.newDate(year,month,17));
				Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
				Event ev4=new Event(4, "Alavs-Depor", UtilDate.newDate(year,month,17));
				Event ev5=new Event(5, "Espaol-Villar", UtilDate.newDate(year,month,17));
				Event ev6=new Event(6, "Palmas-Sevilla", UtilDate.newDate(year,month,17));
				Event ev7=new Event(7, "Malaga-Valen", UtilDate.newDate(year,month,17));
				Event ev8=new Event(8, "Giro-Legans", UtilDate.newDate(year,month,17));
				Event ev9=new Event(9, "Real-Levante", UtilDate.newDate(year,month,17));
				Event ev10=new Event(10, "Betis-Madrid", UtilDate.newDate(year,month,17));
	
				Event ev11=new Event(11, "Atlet-Athle", UtilDate.newDate(year,month,1));
				Event ev12=new Event(12, "Eibar-Barce", UtilDate.newDate(year,month,1));
				Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
				Event ev14=new Event(14, "Alavs-Depor", UtilDate.newDate(year,month,1));
				Event ev15=new Event(15, "Espaol-Villa", UtilDate.newDate(year,month,1));
				Event ev16=new Event(16, "Palmas-Sevi", UtilDate.newDate(year,month,1));
				
	
				Event ev17=new Event(17, "Malaga-Valencia", UtilDate.newDate(year,month,28));
				Event ev18=new Event(18, "Girona-Legans", UtilDate.newDate(year,month,28));
				Event ev19=new Event(19, "Sociedad-Levante", UtilDate.newDate(year,month,28));
				Event ev20=new Event(20, "Betis-Madrid", UtilDate.newDate(year,month,28));
				
				Question q1;
				Question q2;
				Question q3;
				Question q4;
				Question q5;
				Question q6;
					
		
				q1=ev1.addQuestion("Quien gana?",1);
				q2=ev1.addQuestion("Quien mete gol?",2);
				q3=ev11.addQuestion("Quien gana?",1);
				q4=ev11.addQuestion("Cuantos goles?",2);
				q5=ev17.addQuestion("Quien gana?",1);
				q6=ev17.addQuestion("Habra gol?",2);
				
				System.out.println("llega");
				
				System.out.println(q1);
				
				session.save(ev1);
				session.save(ev2);
				session.save(ev3);
				session.save(ev4);
				session.save(ev5);
				session.save(ev6);
				session.save(ev7);
				session.save(ev8);
				session.save(ev9);
				session.save(ev10);
				session.save(ev11);
				session.save(ev12);
				session.save(ev13);
				session.save(ev14);
				session.save(ev15);
				session.save(ev16);
				session.save(ev17);
				session.save(ev18);
				session.save(ev19);
				session.save(ev20);
				
				session.save(q1);
				session.save(q2);
				session.save(q3);
				session.save(q4);
				session.save(q5);
				session.save(q6);
				
				/*
				System.out.println(q1);
			
				session.persist(ev1);
				session.persist(ev2);
				session.persist(ev3);
				session.persist(ev4);
				session.persist(ev5);
				session.persist(ev6);
				session.persist(ev7);
				session.persist(ev8);
				session.persist(ev9);
				session.persist(ev10);
				session.persist(ev11);
				session.persist(ev12);
				session.persist(ev13);
				session.persist(ev14);
				session.persist(ev15);
				session.persist(ev16);
				session.persist(ev17);
				session.persist(ev18);
				session.persist(ev19);
				session.persist(ev20);
				
				session.persist(q1);
				session.persist(q2);
				session.persist(q3);
				session.persist(q4);
				session.persist(q5);
				session.persist(q6);
		
	        */
						
			
			session.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		System.out.println(session + " " + event);
		
		session.beginTransaction(); 
		Event ev= getEvent(event);
			
		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist();
		
		Question q = ev.addQuestion(question, betMinimum);
		//db.persist(q);
		session.save(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
		session.save(q);		// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		session.getTransaction().commit();
		return q;
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println(">> DataAccess: getEvents");	
		
		List<Event> res = new ArrayList<Event>();
		
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate= :fecha");   
		query.setParameter("fecha", date);
		
		List<Event> events = query.list();
		session.getTransaction().commit();
		
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		session.beginTransaction();
		org.hibernate.Query query = session.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :uno and dos");   
		query.setParameter("uno", firstDayMonthDate);
		query.setParameter("dos", lastDayMonthDate);
		List<Date> dates = query.list();
		session.getTransaction().commit();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = getEvent(event);
		return ev.DoesQuestionExists(question);
	}
	
	public Event getEvent(Event evento) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		System.out.println(">> DataAccess: getEvent");	
		
		org.hibernate.Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventNumber= :ev");
		query.setParameter("ev", evento.getEventNumber());
		
		List<Event> events = query.list();
		Event ev=events.get(0);
		return ev;
	}
}

