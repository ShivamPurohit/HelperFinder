package com.major.one.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.major.one.Repository.HelperRepository;
import com.major.one.model.Helper;


import org.springframework.stereotype.Service;

@Service
public class HelperOperation implements HelperService{
	@Autowired
	private HelperRepository helperrepo;
	
	@Override
	public List<Helper> getAllHelper2(){
		return helperrepo.findAll();
	}
	
	public Helper addHelper(Helper helper) {
		
		helperrepo.save(helper);
	    return helper;
	}

	@Override
	public List<Helper> findByLocationProfession(String location, String profession) {
//		List<Helper> l = new ArrayList<Helper>();
//		for(Helper h:helperrepo.findAll()) {
//			if(h.getLocation().equals(location) && h.getProfession().equals(profession)) {
//				l.add(h);
//			}
//		}
//		if(!l.isEmpty()) return l;
//		return null;
		return helperrepo.findByLocationProfession(location, profession);
	}

	@Override
	public Helper findByuniqueHID(int uniqueHID) {
		return helperrepo.findByuniqueHID(uniqueHID);
		
	}

	@Override
	public Helper registerHelper(Helper helper) {
		return helperrepo.save(helper);
	}

	
	@Override
	public void deleteById(Helper h) {
		helperrepo.delete(h);
	}

	@Override
	public int findByNameContactLocationProfession(String name, String contact, String location, String profession) {
		return helperrepo.findIdHelper(name,contact,location,profession);
	}
	}

	

	

