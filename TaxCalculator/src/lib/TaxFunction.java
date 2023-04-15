package lib;

public class TaxFunction {
	
	public static int calculateTax(Salary salaryData , boolean isMarried, int numberOfChildren) {
		final double tax =  0.05;
		final int singlesAllowance = 54000000;
		final int marriageAllowance = 4500000;
		final int dependentAllowance = 1500000;
		final int maxNumberOfChildren = 3;
			
		int totalSalary = 0;
		int monthlyIncome = salaryData.getMonthlySalary() + salaryData.getOtherMonthlyIncome();
		int workingMonths = salaryData.getMonthWorkingInYear();
		int annualDeductible = salaryData.getAnnualDeductible();
		int taxableIncome;
		
		if (salaryData.getMonthWorkingInYear() > 12) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > maxNumberOfChildren) {
			numberOfChildren = maxNumberOfChildren;
		}

	
		if (isMarried) {
			taxableIncome = monthlyIncome * workingMonths - annualDeductible - (singlesAllowance + marriageAllowance +  (numberOfChildren * dependentAllowance));
		} else {
			taxableIncome = monthlyIncome * workingMonths - annualDeductible - singlesAllowance;
		}
	
		totalSalary = (int) Math.round(tax * taxableIncome);
	
		return Math.max(totalSalary, 0);
			 
	}
}
