package edu.escuelaing.arem.services;

public class User {

    private String name;
    private String email;
    private String description;

    public User(String name, String email, String description) {
        this.setName(name);
        this.setEmail(email);
        this.setDescription(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", description=" + description + "]";
	}

}