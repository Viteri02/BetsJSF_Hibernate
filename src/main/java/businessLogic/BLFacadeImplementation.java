package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import dataAccess.DataAccessInterface;
import modelo.domain.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
public class BLFacadeImplementation implements BLFacade {
	
	public DataAccessInterface dbManager;
    public BLFacadeImplementation(DataAccessInterface da)  {

		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
    public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
 	   
	    //The minimum bed must be greater than 0
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		
		return qry;
   }
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */	
	public List<Event> getEvents(Date date)  {
		List<Event>  events=dbManager.getEvents(date);
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		List<Date>  dates=dbManager.getEventsMonth(date);
		return dates;
	}


	
	public void initializeBD() {
		
	}
}

