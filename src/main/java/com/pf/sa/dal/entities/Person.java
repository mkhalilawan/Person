package com.pf.sa.dal.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@Table(name="person")
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
@JsonIdentityInfo( generator = ObjectIdGenerators.PropertyGenerator.class, property = "serPersonId",scope = Person.class )
@JsonIdentityReference(alwaysAsId = false)
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ser_person_id")
	private Integer serPersonId;

	@Column(name="dte_created_date")
	private Timestamp dteCreatedDate;

	@Temporal(TemporalType.DATE)
	@Column(name="dte_dob")
	private Date dteDob;

	@Column(name="dte_modified_date")
	private Timestamp dteModifiedDate;

	@Column(name="num_age")
	private BigDecimal numAge;

	@Column(name="txt_email")
	private String txtEmail;

	@Column(name="txt_person_name", nullable = false)
	private String txtPersonName;

	@Column(name="txt_phone_no")
	private String txtPhoneNo;

	@Column(name="txt_sex")
	private String txtSex;

	@Column(name="txt_sur_name", nullable = false)
	private String txtSurName;

	
	@OneToMany(  mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER )
	private List<PersonContact> personContacts= new ArrayList<PersonContact>();

	public Person() {
	}

	public Integer getSerPersonId() {
		return this.serPersonId;
	}

	public void setSerPersonId(Integer serPersonId) {
		this.serPersonId = serPersonId;
	}

	public Timestamp getDteCreatedDate() {
		return this.dteCreatedDate;
	}

	public void setDteCreatedDate(Timestamp dteCreatedDate) {
		this.dteCreatedDate = dteCreatedDate;
	}

	public Date getDteDob() {
		return this.dteDob;
	}

	public void setDteDob(Date dteDob) {
		this.dteDob = dteDob;
	}

	public Timestamp getDteModifiedDate() {
		return this.dteModifiedDate;
	}

	public void setDteModifiedDate(Timestamp dteModifiedDate) {
		this.dteModifiedDate = dteModifiedDate;
	}

	public BigDecimal getNumAge() {
		return this.numAge;
	}

	public void setNumAge(BigDecimal numAge) {
		this.numAge = numAge;
	}

	public String getTxtEmail() {
		return this.txtEmail;
	}

	public void setTxtEmail(String txtEmail) {
		this.txtEmail = txtEmail;
	}

	public String getTxtPersonName() {
		return this.txtPersonName;
	}

	public void setTxtPersonName(String txtPersonName) {
		this.txtPersonName = txtPersonName;
	}

	public String getTxtPhoneNo() {
		return this.txtPhoneNo;
	}

	public void setTxtPhoneNo(String txtPhoneNo) {
		this.txtPhoneNo = txtPhoneNo;
	}

	public String getTxtSex() {
		return this.txtSex;
	}

	public void setTxtSex(String txtSex) {
		this.txtSex = txtSex;
	}

	public String getTxtSurName() {
		return this.txtSurName;
	}

	public void setTxtSurName(String txtSurName) {
		this.txtSurName = txtSurName;
	}

	public List<PersonContact> getPersonContacts() {
		return this.personContacts;
	}

	public void setPersonContacts(List<PersonContact> personContacts) {
		this.personContacts = personContacts;
	}

	/*public PersonContact addPersonContact(PersonContact personContact) {
		getPersonContacts().add(personContact);
		personContact.setPerson(this);
		return personContact;
	}

	public PersonContact removePersonContact(PersonContact personContact) {
		getPersonContacts().remove(personContact);
		personContact.setPerson(null);
		return personContact;
	}*/
	
	 public void addDetails(PersonContact detail) {
		   detail.setPerson(this);
		   personContacts.add(detail);
	   }
	   
	   public void removeDetails(PersonContact detail) {
		   detail.setPerson(null);
		   personContacts.remove(detail);
	   }

}