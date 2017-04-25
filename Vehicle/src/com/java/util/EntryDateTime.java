package com.java.util;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@SuppressWarnings("serial")
public class EntryDateTime implements Serializable{
	
	static DecimalFormat format = new DecimalFormat("#00");
	
	private String datetime;
	private LocalDateTime local;
	private long epochTime; // to get current time in seconds from epoch
	
	//constructor
	public EntryDateTime(){
		datetime = init();
	}
	
	// creating the date and time
	private String init(){
		local = LocalDateTime.now();
		epochTime = local.toEpochSecond(ZoneOffset.UTC); // current time in seconds from epoch using time zone
		
		StringBuilder sb = new StringBuilder(64);
        sb.append(local.getYear());
        sb.append("-");
        sb.append(format.format(local.getMonthValue()));
        sb.append("-");
        sb.append(format.format(local.getDayOfMonth()));
        sb.append(" ");
        sb.append(format.format(local.getHour()));
        sb.append(":");
        sb.append(format.format(local.getMinute()));
        sb.append(":");
        sb.append(format.format(local.getSecond()));
        
        return(sb.toString());
    }
	
	public long getEpochTimeHours() {
		return epochTime / 3600L;
	}
	
	//getter for date and time
	public String getDateTime(){
		return datetime;
	}
	
	//to split the date
	public String getDate(){
		return datetime.split(" ")[0];
	}
	
	//to split the time
	public String getTime(){
		return datetime.split(" ")[1];
	}
	
	//getter for local date
	public LocalDateTime getLocaldate() {
		return local;
	}
	
}
