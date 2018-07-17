package GUI;

public class People {
	private int id;
	private String name;
	private String type;
	private String email;
	private int contact;
	private String password;
	private String address;
	private int salary;
	private String secret;
	
	public void setID(int id){
		this.id = id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setType(String type){
		this.type = type;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setContact(int contact){
		this.contact=contact;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setSalary(int salary){
		this.salary = salary;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public int getID(){
		return id;
	}
	public int getContact(){
		return contact;
	}
	public int getSalary(){
		return salary;
	}
	public String getName(){
		return name;
	}
	public String getType(){
		return type;
	}
	public String getPassword(){
		return password;
	}
	public String getAddress(){
		return address;
	}
	public String getEmail(){
		return email;
	}
	public String getSecret(){
		return secret;
	}
}
