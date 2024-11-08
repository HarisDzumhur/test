package com.example.app.sharing.request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.example.app.client.RegisteredClient;
import com.example.app.client.RegisteredClientController;
import com.example.app.client.RegisteredClientRepository;
import com.example.app.client.RegisteredClientService;
import com.example.app.exception.UserNotFoundException;
import com.example.app.firebase.FirebaseAPI;


@Service
public class SharingRequestService {

	private final SharingRequestRepository rp;
	private final RegisteredClientRepository rpClient;
	
	@Autowired
	public SharingRequestService(SharingRequestRepository rp,RegisteredClientRepository rpClient)
	{
		this.rp=rp;
		this.rpClient = rpClient;
	}
	
	
	public List<SharingRequest> getMyRequests(Integer id2)
	{
		try {
			List<SharingRequest> list=rp.findByidUser2(id2);
			for(SharingRequest req:list)
			{
				if(req.getDate().isBefore(LocalDateTime.now().minusMinutes(5)))
				{
					rp.delete(req);
				}
			}
			
		return rp.findByidUser2(id2);
		}catch(Exception ex)
		{
			throw new UserNotFoundException("User doesn't exist!");
		}
	}
	
	
	public SharingRequest add(SharingRequest sh)
	{
		RegisteredClient rc=rpClient.findById(sh.getIdUser1()).get();
		String s=rc.getUsername();
		
		FirebaseAPI.sendNotification("New sharing request!", "Request from user:"+s, sh.getIdUser2());
		return rp.save(sh);
	}

	


	public void remove(SharingRequest sharing) {
		SharingRequest request=rp.findById(new SharingRequestId(sharing.getIdGroup(),sharing.getIdUser1(),sharing.getIdUser2())).get();
		rp.delete(request);
	}
	
}
