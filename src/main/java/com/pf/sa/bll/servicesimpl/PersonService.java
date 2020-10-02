package com.pf.sa.bll.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pf.sa.bll.services.IPersonService;
import com.pf.sa.dal.dao.IPersonDAO;
import com.pf.sa.dal.entities.Person;


@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonDAO personDAO;
	
	private Logger logger = LogManager.getLogger(PersonService.class);

	public PersonService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Person> getAllPersons() {
		logger.debug("getAllPerson()");
		List<Person> persons = personDAO.getAllPersons();
		return persons;
	}

	@Override
	public List<Person> getActivePersons() {
		logger.debug("getActivePerson()");
		List<Person> persons = personDAO.getActivePersons();
		return persons;
	}

	@Override
	public String addNewPerson(Person person) {

		return personDAO.addNewPerson(person);
	}

	@Override
	public String updatePerson(Person person) {

		return personDAO.updatePerson(person);
	}

	@Override
	public String deletePerson(int personsId) {
		// TODO Auto-generated method stub
		return personDAO.deletePerson(personsId);
	}
	
	@Override
	public List<Person> getAllPersons(Integer pageNo, Integer pageSize, String sortBy) {
		// TODO Auto-generated method stub
		return personDAO.getAllPersons(pageNo, pageSize, sortBy);
	}
	
	@Override
	public Person getPersonByPK(int personId) {
		// TODO Auto-generated method stub
		return personDAO.getPersonByPK(personId);
	}

}
