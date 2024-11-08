package com.example.app.firebase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.example.app.ServerApplication;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

public class FirebaseAPI {
	private static HashMap<Integer, Set<String>> TOKENS = new HashMap<>();
	private static FirebaseMessaging fm;
	static
	{
		try {
			fm=ServerApplication.firebaseMessageing();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void addMapping(Integer idClient, String token)
	{
		
		if(token==null)
			return;

		if (TOKENS.containsKey(idClient))
		{
			TOKENS.get(idClient).add(token);
		}
		else
		{
			Set<String> set = new HashSet<String>();
			set.add(token);
			TOKENS.put(idClient, set);
		}
	}
	
	public static void removeMapping(Integer idClient, String token)
	{
		if(token==null)
			return;
		
		if (TOKENS.containsKey(idClient))
			TOKENS.get(idClient).remove(token);
	}
	
	public static void sendNotification(String title, String body, Integer idClient)
	{
		Notification notification = Notification.builder()
				.setTitle(title)
				.setBody(body)
				.build();
		
		Set<String> tokens = TOKENS.get(idClient);
		if(tokens!=null) {
		for(String token : tokens)
		{
			Message message = Message.builder()
					.setNotification(notification)
					.setToken(token)
					.build();
			
			try {
				
				fm.send(message);
			} catch(FirebaseMessagingException fme)
			{
				if (fme.getMessage().equals("Requested entity was not found."))
					TOKENS.get(idClient).remove(token);
			}
		}
	}
	}
}
