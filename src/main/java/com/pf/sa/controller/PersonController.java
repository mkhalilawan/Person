package com.pf.sa.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pf.sa.bll.services.IPersonService;
import com.pf.sa.dal.entities.Person;

@RestController
public class PersonController {

	private Logger logger = LogManager.getLogger(PersonController.class);

	@Autowired
	private IPersonService personService;

	@RequestMapping(value = "/getAllPersons", method = RequestMethod.GET)
	public List<Person> getAllPersons(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("getAllPersons()");
		response.addHeader("Cache-Control", "max-age=120, must-revalidate, no-transform");

		List<Person> persons = personService.getAllPersons();
		return persons;
	}

	@RequestMapping(value = "/getActivePersons", method = RequestMethod.GET)
	public List<Person> getActivePersons(HttpServletRequest request, HttpServletResponse response) {
		response.addHeader("Cache-Control", "max-age=120, must-revalidate, no-transform");
		logger.debug("getActivePersons()");

		List<Person> persons = personService.getActivePersons();
		return persons;
	}

	@RequestMapping(value = "/addNewPerson", method = RequestMethod.POST)
	public String addNewPerson(@RequestBody Person person, HttpServletRequest request, Model model)
			throws IllegalStateException, IOException {
		try {

			if (person != null && person.getTxtPersonName() != null && person.getTxtPersonName().trim().length() > 0) {
				if (person.getTxtSurName() != null && person.getTxtSurName().trim().length() > 0) {
					return personService.addNewPerson(person);
				} else {
					return "Sur Name not Found";
				}

			} else {
				return "Person Name not Found";
			}

		} catch (Exception ex) {
			return "{\"status\":\"Failure\"}";
		}
	}

	@RequestMapping(value = "/deletePerson/{id}")
	public String deletePersons(@PathVariable("id") int personsId, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			return personService.deletePerson(personsId);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			return "Failure";
		}
	}

	@RequestMapping(value = "/updatePerson", method = RequestMethod.POST, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateCandidate(@RequestBody Person person, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			return personService.updatePerson(person);
		} catch (Exception ex) {
			return "Failure";
		}
	}

	@RequestMapping(value = "/getPersonsbyPagiation", method = RequestMethod.GET)
	public ResponseEntity<List<Person>> getAllPersons(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "serPersonId") String sortBy) {
		if (pageNo > 0)
			pageNo = (pageNo - 1) * pageSize;
		else
			pageNo = 0;
		List<Person> list = personService.getAllPersons(pageNo, pageSize, sortBy);

		return new ResponseEntity<List<Person>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getPersonsByID/{id}")
	public Person getPersonsByID(@PathVariable("id") int personsId, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			return personService.getPersonByPK(personsId);
		} catch (Exception ex) {
			return new Person();
		}
	}

}
