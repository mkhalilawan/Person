package com.pf.sa.dal.daoimpl;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pf.sa.dal.entities.Person;
 
@Repository
public interface PersonRepository
        extends PagingAndSortingRepository<Person, Long> {
 
}