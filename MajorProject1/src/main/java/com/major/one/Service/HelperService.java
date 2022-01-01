package com.major.one.Service;
import com.major.one.model.Helper;
import java.util.List;

public interface HelperService {
	Helper addHelper(Helper helper);
	List<Helper> getAllHelper2();
	List<Helper> findByLocationProfession(String location, String profession);
	
	public Helper findByuniqueHID(int uniqueHID);
	
	public Helper registerHelper(Helper helper);
	
	public void deleteById(Helper h);
	
	int findByNameContactLocationProfession(String name, String contact, String location, String profession);
}
