package lib;

// import java.time.Month; 
import java.time.LocalDate;
import java.util.Date ;
import java.util.LinkedList;
import java.util.List;
import java.text.SimpleDateFormat;


public class Employee extends Salary{

	private enum Gender {
		Pria,
		Wanita
	}
	private String employeeId;
	private Date dateJoined;
	private boolean isForeigner;
	private String idNumber;
	private Gender gender; 
	
	private person personalData;
	private Salary SalaryData;

	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,Date dateJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		personalData = new person();
		personalData.setFirstName(firstName);
		personalData.setLastName(lastName);
		personalData.setAddress(address);
		this.idNumber = idNumber;
		this.dateJoined = dateJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;

		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
		
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			SalaryData.setMonthlySalary(3000000);
			if (isForeigner) {
			 SalaryData.setMonthlySalary((int) (3000000 * 1.5));				
			}
		}else if (grade == 2) {
			SalaryData.setMonthlySalary(5000000);
			if (isForeigner) {
				SalaryData.setMonthlySalary((int) (3000000 * 1.5));	
			}
		}else if (grade == 3) {
			SalaryData.setMonthlySalary(7000000);
			if (isForeigner) {
				SalaryData.setMonthlySalary((int) (3000000 * 1.5));	
			}
		}
	}
	
	public String getEmployeeId() {
		return employeeId;
	}
	public String getSpouseName() {
		return spouseName;
	}

	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
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
		LocalDate date = LocalDate.now();
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		String month = monthFormat.format(dateJoined);
		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		String year = yearFormat.format(dateJoined);

		
		if (date.getYear() == Integer.parseInt(year) ) {
			SalaryData.setMonthWorkingInYear(date.getMonthValue() - Integer.parseInt(month));
		}else {
			SalaryData.setMonthWorkingInYear(12);
			
		}
		
		return TaxFunction.calculateTax(SalaryData.getMonthlySalary(),SalaryData.getOtherMonthlyIncome(),SalaryData.getMonthWorkingInYear(),SalaryData.getAnnualDeductible(), spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
