package com.example.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;

@SpringBootApplication
@EnableTransactionManagement
public class ServerApplication {
	
	//@Bean
	public static FirebaseMessaging firebaseMessageing() throws IOException {
		GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource("serviceAccountKey.json").getInputStream());
		
		FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(googleCredentials)
				  .build();

		 FirebaseApp app;
		    if (FirebaseApp.getApps().isEmpty()) {
		        app = FirebaseApp.initializeApp(options);
		    } else {
		        app = FirebaseApp.getInstance();
		    }
		return FirebaseMessaging.getInstance(app);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
