package www.commice.com;

public class AccountsStringer {
	
	/*
	private String title;
	private String currency;
	private String salaryrangefrom;
	private String salaryrangeto;
	private String company;
	private String address;
	
	private String specialization;
	private String level;
	private String jobid;
	*/
	public String image; 
	public String username; 
	private String email;
	private String phone;
	
	private String status;
	
	private String fullname;
	private String gender;
	private String age;
	private String hobbies;
	private String address;
	private String specialization;
	private String spec_experience; 	
	private String services;
	private String serv_experience;
	private String website;
	private String facebook;
	private String twitter;
	private String linkedin;

	
	public AccountsStringer() {
		// TODO Auto-generated constructor stub
	}
	public AccountsStringer(String image,String username,String email,String phone,	
			String status,
			String fullname,
			String gender,			
		String age,
	String hobbies,String address,String specialization,String spec_experience,
			String services,
			String serv_experience,
			String website,
			String facebook,
			String twitter,
			String linkedin)
		
	 

	{
		super();
	
		
		
		this.image=image;
		
		this.username=username; 
		this.email=email;
		this.phone=phone;

		this.status=status;
		
		this.fullname=fullname;
		this.gender=gender;
		this.age=age;
		this.status=status;
		this.hobbies=hobbies;
		this.address=address;
		this.specialization=specialization;
		this.spec_experience=spec_experience; 	
		this.services=services;
		this.serv_experience=serv_experience;
		this.website=website;
		this.facebook=facebook;
		this.twitter=twitter;
		this.linkedin=linkedin;
			
		
		/*
		this.title = title;
		this.salaryrangefrom = salaryrangefrom;
		this.salaryrangeto = salaryrangeto;
		this.company = company;
		this.address = address;
		
		this.specialization = specialization;
		this.level = level;
		this.jobid = jobid;
		*/
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() 
	{
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getHobbies() {
		return hobbies;
	}
	
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public String getSpec_experience() {
		return spec_experience;
	}

	public void setSpec_experience(String spec_experience) {
		this.spec_experience = spec_experience;
	}


	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}
	
	public String getServ_experience() {
		return serv_experience;
	}

	public void setServ_experience(String serv_experience) {
		this.serv_experience = serv_experience;
	}


	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}


	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}


	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}


	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	
/*
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSalaryrangefrom() {
		return salaryrangefrom;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCurrency() {
		return currency;
	}
	public void setSalaryrangefrom(String salaryrangefrom) {
		this.salaryrangefrom = salaryrangefrom;
	}
	public String getSalaryrangeto() {
		return salaryrangeto;
	}
	public void setSalaryrangeto(String salaryrangeto) {
		this.salaryrangeto = salaryrangeto;
	}
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	
	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}


	public String getJobid() {
		return jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
*/
	

/*
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

	public String getQualificaton() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSlots() {
		return slots;
	}

	public void setSlots(String slots) {
		this.slots = slots;
	}
	
	
	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	
	public String getDescribtion() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}		

	
*/
	
	



}
