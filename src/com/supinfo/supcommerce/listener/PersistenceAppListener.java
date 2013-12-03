package com.supinfo.supcommerce.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.supinfo.supcommerce.util.PersistenceManager;

/**
 * Application Lifecycle Listener implementation class PersistenceAppListener
 * 
 * @author Elka
 * @version %I% , %G%
 * @since SupCommerce 4.4
 */
@WebListener
public class PersistenceAppListener implements ServletContextListener {
	
	/**
	 * Default constructor.
	 */
	public PersistenceAppListener() {
	}
	
	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}
	
	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		PersistenceManager.closeEntityManagerFactory();
	}
	
}
