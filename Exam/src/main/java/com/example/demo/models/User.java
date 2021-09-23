package com.example.demo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Entity
@Table(name="users", uniqueConstraints={@UniqueConstraint(columnNames ={"email"})})
public class User {

	
	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
	    @Size(min=3, message="Nombre debe tener 3 caracteres")
	    private String name;
	   
	    
	    @Email(message="Email invalido")
	    private String email;
	    @Size(min=8, message="contraseña debe tener 8 caracteres")
	    private String password;
	    @Transient
	    private String passwordConfirmation;
	    @Column(updatable=false)
	    private Date createdAt;
	    private Date updatedAt;

	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(
	        name = "likedIdeas", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "idea_id")
	    )     
	    private List<Idea> ideas;


		public User() {
	    }
	    
	    
	    public List<Idea> getIdeas() {
			return ideas;
		}


		public void setIdeas(List<Idea> ideas) {
			this.ideas = ideas;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getPasswordConfirmation() {
			return passwordConfirmation;
		}


		public void setPasswordConfirmation(String passwordConfirmation) {
			this.passwordConfirmation = passwordConfirmation;
		}


		public Date getCreatedAt() {
			return createdAt;
		}


		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}


		public Date getUpdatedAt() {
			return updatedAt;
		}


		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}


		// other getters and setters removed for brevity
	    @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    @PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
}
