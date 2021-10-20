package com.alert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.database.TodayAlert;

public class AlertByThread {

	private ArrayList<Long> temp1 = new ArrayList<Long>();
	private ArrayList<ArrayList<String>> temp2 = new ArrayList<ArrayList<String>>();
	private int len = 0;
	
	public AlertByThread() throws InterruptedException {
		
		ArrayList<ArrayList<String>> arr = new TodayAlert().TodayAlert();
		for(int i = 0; i < arr.size(); i++) {
			String target_time = arr.get(i).get(3);
			if(target_time.substring(6).equals("PM")) {
				target_time = (Integer.valueOf(target_time.substring(0,2))+12)+":"+target_time.substring(3,5)+":00";
			} else {
				if(target_time.substring(0,2).equals("12")) {
					target_time = "00"+":"+target_time.substring(3,5)+":00";
				} else {
					target_time = Integer.valueOf(target_time.substring(0,2))+":"+target_time.substring(3,5)+":00";
				}
			}
			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");  
		    Date date = new Date();
		    String current_time = format.format(date);
		    System.out.println(current_time+"-"+target_time);
		    try {
				Date d1 = format.parse(target_time);
				Date d2 = format.parse(current_time);
				
				long f = d1.getTime()-d2.getTime();
				if(f >= 0) {
					temp1.add(f);
					temp2.add(arr.get(i));
					len++;
				}
				
				//System.out.println(temp1);
				//System.out.println(temp2);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
		}
		sort(len);
		System.out.println(temp1);
		System.out.println(temp2);
		for(int i = 0; i < len; i++) {
			Thread2 t = new Thread2(temp1.get(i),temp2.get(i));
			System.out.println(temp1.get(i)+" "+temp2.get(i));
			t.start();
			t.join();
		}
	}
	
	public void sort(int n) {
		
		boolean flag = true;
		for(int i = 0; i < n-1; i++) {
			flag = true;
			for(int j = 0; j < n-i-1; j++) {
				if(temp1.get(j) > temp1.get(j+1)) {
					
					flag = false;
					long t1 = temp1.get(j);
					long t2 = temp1.get(j+1);
					temp1.remove(j+1);
					temp1.add(j+1, t1);
					temp1.remove(j);
					temp1.add(j, t2);
					
					ArrayList<String> a1 = temp2.get(j);
					ArrayList<String> a2 = temp2.get(j+1);
					temp2.remove(j+1);
					temp2.add(j+1, a1);
					temp2.remove(j);
					temp2.add(j, a2);
				}
			}
			if(flag) {
				break;
			}
		}
	}
}
