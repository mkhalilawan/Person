package com.pf.sa.bll.servicesimpl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import com.pf.sa.bll.services.ICommonService;

@Service
public class CommonService implements ICommonService{
	
	
	public DateFormat getDateFormater() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
		return df;
	}

	@Override
	public String getCurrentTimeStamp() {
		    return new Timestamp(new Date().getTime()).toString();
	}
	
	@Override
	public Timestamp getCurrentTimeStamp_new() {
		    return new Timestamp(new Date().getTime());
	}
	

}
