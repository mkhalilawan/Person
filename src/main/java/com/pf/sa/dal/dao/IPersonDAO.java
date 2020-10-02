package com.pf.sa.dal.dao;

import java.util.List;

import com.pf.sa.dal.entities.Person;

public interface IPersonDAO {

	List<Person> getAllPersons();

	List<Person> getActivePersons();

	String addNewPerson(Person person);

	String updatePerson(Person person);

	String deletePerson(int id);
	
	List<Person> getAllPersons(Integer pageNo, Integer pageSize, String sortBy);
	
	Person getPersonByPK(int personId);

}
