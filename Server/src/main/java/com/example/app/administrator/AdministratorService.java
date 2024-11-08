package com.example.app.administrator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.exception.IncorrectPasswordException;
import com.example.app.exception.UserAlreadyExistsException;
import com.example.app.exception.UserNotFoundException;
import com.example.app.exception.UserUpdateException;
import com.example.app.mail.MailAPI;

@Service
public class AdministratorService {
	
	private final AdministratorRepository ar;
	@Autowired
	public AdministratorService(AdministratorRepository ar)
	{
		this.ar = ar;
	}

	public Administrator getAccount(String username, String password)
	{
		Optional<Administrator> administrator = ar.findById(username);
		if (administrator.isPresent()) {
            if (administrator.get().getPassword().equals(digest(password))) {
                return administrator.get();
            } else {
                throw new IncorrectPasswordException("Incorrect password!");
            }
        } else {
            throw new UserNotFoundException("Admin with the given username does not exist!");
        }
	}

	public Administrator updatePassword(String username, String oldPassword, String newPassword) {
		Optional<Administrator> administrator = ar.findById(username);
		if (administrator.isPresent()) {
			Administrator admin = administrator.get();
            if (admin.getPassword().equals(digest(oldPassword))) {
            	admin.setPassword(digest(newPassword));
                ar.save(admin);
                return admin;
            } else {
                throw new IncorrectPasswordException("Old password incorrect!");
            }
        } else {
            throw new UserNotFoundException("Admin with the given username does not exist!");
        }
	}

	public Administrator update(String username, String newName, String newSurname, String newEmail) {
		Optional<Administrator> administrator = ar.findById(username);
		if (administrator.isPresent()) {
			Administrator admin = administrator.get();
			admin.setName(newName);
			admin.setSurname(newSurname);
            admin.setEmail(newEmail);
            ar.save(admin);
            return admin;
        } else {
            throw new UserNotFoundException("Admin with the given username does not exist!");
        }
	}

	public Administrator createNewAdministrator(Administrator admin) {
		Optional<Administrator> ad = ar.findById(admin.getUsername());
		if (ad.isPresent())
			throw new UserAlreadyExistsException("Admin with that username already exists!");
		else
		{
			List<Administrator>	list = ar.findByEmail(admin.getEmail());
			if(!list.isEmpty())
				throw new UserAlreadyExistsException("Admin with that email already exists!");
			else {
				admin.setPassword(digest(admin.getPassword()));
				return ar.save(admin);
			}
		}
	}
	
	private String digest(String s)
	{
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hashBytes = digest.digest(s.getBytes());
	        String hashHex = bytesToHex(hashBytes);
	        return hashHex;
		} catch (NoSuchAlgorithmException ignored) {}
		return null;
	}
	
	private static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
		    sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	public void sendPassword(String email) {
		List<Administrator>	list = ar.findByEmail(email);
		
		if (list.isEmpty())
			throw new UserNotFoundException("User with that email address doesn't exist!");
		else {
			Administrator administrator = list.get(0);
			String generatedPassword = PasswordGenerator.generatePassword();
			String result = digest(generatedPassword);
			if (result == null)
				throw new UserUpdateException("Diggest algorithm problem on server!");
			administrator.setPassword(result);
			ar.save(administrator);
			MailAPI.sendMail(email, "Your new password!", generatedPassword + "\nWe recommend to change the password as soon as possible.");
		}
	}
}
