package com.major.one.Repository;

import com.major.one.model.Helper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HelperRepository extends JpaRepository<Helper, Integer>{
	
	@Query(value = "SELECT * FROM HELPER WHERE location = :location AND profession = :profession ORDER BY rating DESC", nativeQuery = true)
	List<Helper> findByLocationProfession(String location,String profession);

	Helper findByuniqueHID(int uniqueHID);

	@Query(value = "SELECT uniqueHID FROM HELPER WHERE location = :location AND profession = :profession AND name = :name AND contact = :contact", nativeQuery = true)
	int findIdHelper(String name, String contact, String location, String profession);

//	void delete(int uniqueHID);

//	@Query(value = "DELETE FROM HELPER WHERE uniqueHID = :uniqueHID", nativeQuery = true)
//	Helper remove(int uniqueHID);
}
