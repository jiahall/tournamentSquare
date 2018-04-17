package com.jiahall.jsonBuilder;


import com.google.gson.Gson;

public class JsonReply {
	
	   public String action;
	   public boolean success;
	   public String err;
	   public JsonReply(String message, boolean success, String err)
	   {
	      this.action = message;
	      this.success = success;
	      this.err = err;
	   }
	   public JsonReply(String message, boolean success)
	   {
	      this.action = message;
	      this.success = success;
	      
	   }
	   
	   public boolean count(String word) {
		    boolean count = true;
		   if(word.length() < 4)
			   count = false;
		return count;
		}
	   
	   public static String reply(String message, boolean success) {
			 JsonReply obj = new JsonReply(message, success);  
			 Gson gson = new Gson();
			 String json = gson.toJson(obj);
		   return json;
	   }
	   
	   public static String reply(String message, boolean success, String error) {
			 JsonReply obj = new JsonReply(message, success, error);  
			 Gson gson = new Gson();
			 String json = gson.toJson(obj);
		   return json;
	   }
	   

	}
	  
