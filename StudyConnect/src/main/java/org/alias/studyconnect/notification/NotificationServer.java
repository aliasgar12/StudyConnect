package org.alias.studyconnect.notification;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Notification;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class NotificationServer {

	private static String fromUser;
	private static String moduleName;
	private static int request_type;
	private static String subjectName;
    final String serverKey = "AAAAogNSrr0:APA91bEsGN-sqcB58YdJklZR56Y8HcsV5bD-Bqc1qDHSyN7Pzf9u7_vwutExkZTSDffwNLPC5IwrI6CAM7LPJFTWmpIkwvPMMGnbJeMftgus96lpIW1FOIln5XniWSpDh4cbBqUAQY_z";
    private static String app_token;
    private static NotificationServer mInstance;
    private static String body;
    
    private NotificationServer(String fromUser, String moduleName,String subjectName,String app_token, int request_type){
    	NotificationServer.fromUser = fromUser;
    	NotificationServer.moduleName = moduleName;
    	NotificationServer.subjectName = subjectName;
    	NotificationServer.app_token = app_token;
    	NotificationServer.request_type = request_type;
    }
    
    public static synchronized NotificationServer getInstance(String fromUser, String moduleName,String subjectName,String app_token, int request_type){
    		mInstance = new NotificationServer(fromUser, moduleName, subjectName, app_token, request_type);
    	return mInstance;
    }
    
    public void sendRequest(){
		
    	if(request_type==0)
			body = fromUser + " requested you to help with the " + moduleName + " in " +subjectName;
		else
			body = "Request to " + fromUser + " for subject " + subjectName + " with " + moduleName + " has been accepted";
    	
		Thread t = new Thread() {
	        public void run(){ 
	            try {
	                Sender sender = new FCMSender(serverKey);
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
	                                  .addData("request_type", String.valueOf(request_type))
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
