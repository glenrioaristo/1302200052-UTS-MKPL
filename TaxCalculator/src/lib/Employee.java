package lib;
 
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date ;
import java.util.LinkedList;
import java.util.List;


public class Employee extends Salary{

	private enum Gender {
		PRIA,
		WANITA
	}
	private String employeeId;
	private Date dateJoined;
	private boolean isForeigner;
	private Gender gender; 
	
	private Person personalData;
	private Salary SalaryData;

	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames = new LinkedList<String>();
	private List<String> childIdNumbers = new LinkedList<String>();
	
	public Employee(String employeeId, Person personalData,Date dateJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.personalData = personalData;
		this.dateJoined = dateJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;

		setSpouse(personalData.getFirstName(), employeeId);
		addChild(personalData.getFirstName(), employeeId);
		

	}
	
	
	private final int juniorEmployee = 3000000;
	private final int seniorEmployee = 3000000;
	private final int managerEmployee = 3000000;
	private final float foreignEmployee = (float) 1.5;


	public void setMonthlySalary(int grade) {	
		int monthlySalary = 0;
		switch (grade) {
			case 1:
				monthlySalary = juniorEmployee;
				break;
			case 2:
				monthlySalary = seniorEmployee;
				break;
			case 3:
				monthlySalary = managerEmployee;
				break;
			default:
				System.err.println("Invalid grade");
				break;
		}
		if (isForeigner) {
			monthlySalary = (int) (monthlySalary * foreignEmployee);
		}
	
		SalaryData.setMonthlySalary(monthlySalary);
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public String getSpouseName() {
		return spouseName;
	}

	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = personalData.getIdNumber();
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public Date getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	
	public int getAnnualIncomeTax() {
		
		LocalDate joinDate = dateJoined.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int lengthofWork =  joinDate.getMonthValue();
		int monthsWorked = 12;
		 
		if (LocalDate.now().getYear() == joinDate.getYear()) {
			monthsWorked = LocalDate.now().getMonthValue() - lengthofWork;
		} 

		SalaryData.setMonthWorkingInYear(monthsWorked);

		
		return TaxFunction.calculateTax(SalaryData, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
