package ua.lviv.lgs.hbm.xml;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Application {
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

//		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//				.applySettings(configuration.getProperties()).build();

		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();

		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		Cart cart = new Cart(1, "Cart 1");
		Item item1 = new Item(1234);
		Item item2 = new Item(2345);
		Item item3 = new Item(3456);
		Item item4 = new Item(4567);
		Item item5 = new Item(5678);
		cart.setItems(new HashSet<>(Arrays.asList(item1, item2, item3, item4, item5)));

		session.persist(cart);

		@SuppressWarnings("unchecked")
		List<Cart> listOfCards = session.createCriteria(Cart.class).list();
		listOfCards.forEach(System.out::println);

		transaction.commit();
		session.close();

	}

}