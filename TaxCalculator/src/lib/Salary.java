package lib;

public class Salary extends Person {
    private int monthWorkingInYear;
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;


	public int getMonthWorkingInYear() {
        return monthWorkingInYear;
    }
    public void setMonthWorkingInYear(int monthWorkingInYear) {
        this.monthWorkingInYear = monthWorkingInYear;
    }
	public int getMonthlySalary() {
        return monthlySalary;
    }
    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
	public int getOtherMonthlyIncome() {
        return otherMonthlyIncome;
    }
    public void setOtherMonthlyIncome(int otherMonthlyIncome) {
        this.otherMonthlyIncome = otherMonthlyIncome;
    }
    public int getAnnualDeductible() {
        return annualDeductible;
    }
    public void setAnnualDeductible(int annualDeductible) {
        this.annualDeductible = annualDeductible;
    }
}
