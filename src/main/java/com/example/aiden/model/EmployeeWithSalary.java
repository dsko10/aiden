package com.example.aiden.model;

public class EmployeeWithSalary {
    private Long id;
    private String firstName;
    private String lastName;
    private WorkTime workTime;
    private Double grossSalary;
    private Double netSalary;
    private Double tax;
    private Double pensionInsurance;
    private Double disabilityInsurance;
    private Double medicalInsurance;
    private Double healthInsurance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    public Double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(Double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public Double getNetSalary() {
        return netSalary;
    }

    public void setNetSalary(Double netSalary) {
        this.netSalary = netSalary;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getPensionInsurance() {
        return pensionInsurance;
    }

    public void setPensionInsurance(Double pensionInsurance) {
        this.pensionInsurance = pensionInsurance;
    }

    public Double getDisabilityInsurance() {
        return disabilityInsurance;
    }

    public void setDisabilityInsurance(Double disabilityInsurance) {
        this.disabilityInsurance = disabilityInsurance;
    }

    public Double getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(Double medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public Double getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(Double healthInsurance) {
        this.healthInsurance = healthInsurance;
    }
}
