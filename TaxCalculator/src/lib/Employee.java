package lib;
 
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date ;
import java.util.LinkedList;
import java.util.List;


public class Employee extends Salary{

	private enum Gender {
		Pria,
		Wanita
	}
	private String employeeId;
	private Date dateJoined;
	private boolean isForeigner;
	private Gender gender; 
	
	private person personalData;
	private Salary SalaryData;

	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, person personalData,Date dateJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.personalData = personalData;
		this.dateJoined = dateJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;

		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
		setSpouse(personalData.getFirstName(), employeeId);
		addChild(personalData.getFirstName(), employeeId);
		

	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
		int monthlySalary = 0;
		switch (grade) {
			case 1:
				monthlySalary = 3000000;
				break;
			case 2:
				monthlySalary = 5000000;
				break;
			case 3:
				monthlySalary = 7000000;
				break;
			default:
				System.err.println("Invalid grade");
				break;
		}
		if (isForeigner) {
			monthlySalary = (int) (monthlySalary * 1.5);
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
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate joinDate = dateJoined.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		int monthsWorked;

		if (LocalDate.now().getYear() == joinDate.getYear()) {
			monthsWorked = LocalDate.now().getMonthValue() - joinDate.getMonthValue();
		} else {
			monthsWorked = 12;
		}

		SalaryData.setMonthWorkingInYear(monthsWorked);

		
		return TaxFunction.calculateTax(SalaryData, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
