package org.alias.studyconnect.notification;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Notification;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class SendRequestNotification {
	private static String fromUser;
	private static String moduleName;
	private static String subjectName;
    final String serverKey = "AAAAogNSrr0:APA91bEsGN-sqcB58YdJklZR56Y8HcsV5bD-Bqc1qDHSyN7Pzf9u7_vwutExkZTSDffwNLPC5IwrI6CAM7LPJFTWmpIkwvPMMGnbJeMftgus96lpIW1FOIln5XniWSpDh4cbBqUAQY_z";
    private static String app_token;
    private static SendRequestNotification mInstance;
    
    private SendRequestNotification(String fromUser, String moduleName,String subjectName,String app_token){
    	SendRequestNotification.fromUser = fromUser;
    	SendRequestNotification.moduleName = moduleName;
    	SendRequestNotification.subjectName = subjectName;
    	SendRequestNotification.app_token = app_token;
    }
    
    public static synchronized SendRequestNotification getInstance(String fromUser, String moduleName,String subjectName,String app_token){
    	if(mInstance == null)
    		mInstance = new SendRequestNotification(fromUser, moduleName, subjectName, app_token);
    	return mInstance;
    }
    
    public void sendAddRequest(){
    	
    	
    	Thread t = new Thread() {
	        public void run(){ 
	            try {
	                Sender sender = new FCMSender(serverKey);
	                String body = fromUser + " requested you to help with the " + moduleName + " in " +subjectName;
	                Notification mNotification = new Notification.Builder("MyNotification")
							.title(fromUser)
							.body(body)
							.build();
	                
	                
	                Message message = new Message.Builder()
	                                  .collapseKey("message")
	                                  .timeToLive(3)
	                                  .delayWhileIdle(true)
	                                  .addData("fromUser", fromUser)
	                                  .addData("moduleName", moduleName)
	                                  .addData("subjectName", subjectName)
	                                  .notification(mNotification)
	                                  .build(); 
		          
	                Result result = sender.send(message,app_token,1);
	                System.out.println("Result: " + result.toString());
	            } catch (Exception e) 
	            {
	            	e.printStackTrace();
	            	}
	        };
	    };
	        t.start();
	        try {
	            t.join();
	        }
	        catch (InterruptedException iex) {
	            iex.printStackTrace();
	        }
    }
    
}
