package com.joe.bookstore.entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.joe.bookstore.commom.Constant;

import javax.persistence.*;


@Entity
public class User {
	 @Id
	    @GeneratedValue
	    private Integer id;
	    private String name;
	    private String gender;
	    private String emailId;
	    private String phoneNumber;
	    private String userType = Constant.USER_TYPE.NORMAL;
	    private String password;
	    private Boolean isActive = true;
	    private Integer loginCount = 0;
	    private String ssoType;
	    private DateTime loginAt;
	    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	    private DateTime createdAt;
	    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	    private DateTime updatedAt;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Boolean getIsActive() {
			return isActive;
		}
		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}
		public Integer getLoginCount() {
			return loginCount;
		}
		public void setLoginCount(Integer loginCount) {
			this.loginCount = loginCount;
		}
		public String getSsoType() {
			return ssoType;
		}
		public void setSsoType(String ssoType) {
			this.ssoType = ssoType;
		}
		public DateTime getLoginAt() {
			return loginAt;
		}
		public void setLoginAt(DateTime loginAt) {
			this.loginAt = loginAt;
		}
		public DateTime getCreatedAt() {
			return createdAt;
		}
		public void setCreatedAt(DateTime createdAt) {
			this.createdAt = createdAt;
		}
		public DateTime getUpdatedAt() {
			return updatedAt;
		}
		public void setUpdatedAt(DateTime updatedAt) {
			this.updatedAt = updatedAt;
		}
	   
	    @PrePersist
	    public void before()
	    {
	    	//created at
	    	DateTime dateTime = new DateTime();
	    	
	    	if (createdAt==null) {
				this.createdAt=dateTime;
			}
	    	
	    	//updated at
	    	this.updatedAt=dateTime;
	    }
	    
	    
}
