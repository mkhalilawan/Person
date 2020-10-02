package com.pf.sa.dal.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the person_contact database table.
 * 
 */
@Entity
@Table(name="person_contact")
@NamedQuery(name="PersonContact.findAll", query="SELECT p FROM PersonContact p")
public class PersonContact implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="ser_person_contact_id")
	private Integer serPersonContactId;

	@Column(name="dte_created_date")
	private Timestamp dteCreatedDate;

	@Column(name="dte_modified_date")
	private Timestamp dteModifiedDate;

	@Column(name="txt_contact_no")
	private String txtContactNo;

	@Column(name="txt_contact_person")
	private String txtContactPerson;

	private String txtemail;

	//bi-directional many-to-one association to Person
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Person.class)
	@JoinColumn(name="ser_person_id", nullable = false)
	private Person person;

	public PersonContact() {
	}

	public Integer getSerPersonContactId() {
		return this.serPersonContactId;
	}

	public void setSerPersonContactId(Integer serPersonContactId) {
		this.serPersonContactId = serPersonContactId;
	}

	public Timestamp getDteCreatedDate() {
		return this.dteCreatedDate;
	}

	public void setDteCreatedDate(Timestamp dteCreatedDate) {
		this.dteCreatedDate = dteCreatedDate;
	}

	public Timestamp getDteModifiedDate() {
		return this.dteModifiedDate;
	}

	public void setDteModifiedDate(Timestamp dteModifiedDate) {
		this.dteModifiedDate = dteModifiedDate;
	}

	public String getTxtContactNo() {
		return this.txtContactNo;
	}

	public void setTxtContactNo(String txtContactNo) {
		this.txtContactNo = txtContactNo;
	}

	public String getTxtContactPerson() {
		return this.txtContactPerson;
	}

	public void setTxtContactPerson(String txtContactPerson) {
		this.txtContactPerson = txtContactPerson;
	}

	public String getTxtemail() {
		return this.txtemail;
	}

	public void setTxtemail(String txtemail) {
		this.txtemail = txtemail;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}