package com.pf.sa.dal.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pf.sa.bll.services.ICommonService;
import com.pf.sa.dal.dao.IPersonDAO;
import com.pf.sa.dal.entities.Person;
import com.pf.sa.dal.entities.PersonContact;

@Repository
public class PersonDAO implements IPersonDAO {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private ICommonService commonService;

	private static final Logger log = LoggerFactory.getLogger(PersonDAO.class);

	public PersonDAO() {

	}

	private EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAllPersons() {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		// List<Person> persons = entityManager.createQuery("FROM Person
		// ").getResultList();
		List<Person> persons = entityManager.createQuery("FROM Person ").setMaxResults(2).setFirstResult(0)
				.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();

		return persons;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getActivePersons() {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		List<Person> persons = entityManager.createQuery(" FROM Person ").getResultList();

		entityManager.getTransaction().commit();
		entityManager.close();

		return persons;
	}

	@Override
	public String addNewPerson(Person dto) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			Person person = createPerson(dto);
			person.setDteCreatedDate(commonService.getCurrentTimeStamp_new());
			entityManager.merge(person);
			entityManager.getTransaction().commit();
			entityManager.close();
			return "Success";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "Failure";
		}
	}

	@Override
	public String deletePerson(int personsId) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			if (personsId > 0) {
				Person person = entityManager.find(Person.class, personsId);
				if (person != null) {

					entityManager.remove(person);

				}
			}
			entityManager.getTransaction().commit();
			entityManager.close();

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return "Failure";
		}
		return "Success";
	}

	@Override
	public String updatePerson(Person person) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();

			person.setDteModifiedDate(commonService.getCurrentTimeStamp_new());
			entityManager.merge(person);
			entityManager.getTransaction().commit();
			entityManager.close();
			return "Success";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return "Failure";
		}
	}

	public static boolean isNullOrEmpty(String myString) {
		return myString == null || "".equals(myString);
	}

	private Person createPerson(Person dto) {

		Person person = new Person();
		person.setTxtPersonName(dto.getTxtPersonName());
		person.setTxtSurName(dto.getTxtSurName());
		person.setTxtEmail(dto.getTxtEmail());
		person.setTxtPhoneNo(dto.getTxtPhoneNo());
		person.setTxtSex(dto.getTxtSex());
		person.setNumAge(dto.getNumAge());
		person.setDteDob(dto.getDteDob());

		if (dto.getPersonContacts() != null && dto.getPersonContacts().size() > 0) {
			PersonContact pc;
			for (int i = 0; i < dto.getPersonContacts().size(); i++) {
				if (dto.getPersonContacts().get(i) != null) {
					pc = (PersonContact) dto.getPersonContacts().get(i);
					pc.setDteCreatedDate(commonService.getCurrentTimeStamp_new());
					person.addDetails(pc);
				}

			}
		}

		return person;
	}

	@Override
	public List<Person> getAllPersons(Integer pageNo, Integer pageSize, String sortBy) {

		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		List<Person> persons = entityManager.createQuery("FROM Person ").setMaxResults(pageSize).setFirstResult(pageNo)
				.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();

		return persons;

	}

	@Override
	public Person getPersonByPK(int personId) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			String query = "FROM Person where serPersonId=" + personId;

			Person Person = (Person) entityManager.createQuery(query).getSingleResult();

			entityManager.close();

			return Person;

		} catch (NoResultException e) {
			log.error("Person not found");
			return null;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}
