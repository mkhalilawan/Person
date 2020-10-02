package com.pf.sa.bll.services;

import java.sql.Timestamp;
import java.text.DateFormat;

public interface ICommonService {

	DateFormat getDateFormater();

	String getCurrentTimeStamp();

	Timestamp getCurrentTimeStamp_new();

}
