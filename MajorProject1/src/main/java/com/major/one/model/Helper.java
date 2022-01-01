package com.major.one.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;

@Entity
public class Helper {

	@Id
    @Column
    @GeneratedValue
	private int uniqueHID;
	
	private String name;
	private String age;
	private String gender;
	private String contact;
	private String profession;
	private String address;
	private String location;
	private int rating;
	private String profile;
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Helper(String name, String age, String gender, String contact, String profession, String address,
			String location, int rating) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.contact = contact;
		this.profession = profession;
		this.address = address;
		this.location = location;
		this.rating = rating;
		
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	Helper(){};
	
	public int getUniqueHID() {
		return uniqueHID;
	}
	public void setUniqueHID(int uniqueHID) {
		this.uniqueHID = uniqueHID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Helper [uniqueHID=" + uniqueHID + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", contact=" + contact + ", profession=" + profession + ", address=" + address
				+ ", location=" + location + "]";
	}

		
	
}
