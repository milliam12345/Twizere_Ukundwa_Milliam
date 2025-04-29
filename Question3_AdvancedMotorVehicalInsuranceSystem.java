/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q3;

/**
 *
 * @author Mimi
 */
// Java implementation for Exercise 3: Advanced Motor Vehicle Insurance System
// All classes are included in one file but structured separately

import java.time.LocalDate;
import java.util.*;

// Abstract class InsurancePolicy
abstract class InsurancePolicy {
    private String policyId;
    private Vehicle vehicle;
    private Person policyHolder;
    private double coverageAmount;
    private double premiumAmount;
    private LocalDate policyStartDate;
    private LocalDate policyEndDate;

    public InsurancePolicy(String policyId, Vehicle vehicle, Person policyHolder, double coverageAmount,
                           LocalDate policyStartDate, LocalDate policyEndDate) {
        this.policyId = policyId;
        this.vehicle = vehicle;
        this.policyHolder = policyHolder;
        this.coverageAmount = coverageAmount;
        this.policyStartDate = policyStartDate;
        this.policyEndDate = policyEndDate;
    }

    public String getPolicyId() { return policyId; }
    public Vehicle getVehicle() { return vehicle; }
    public Person getPolicyHolder() { return policyHolder; }
    public double getCoverageAmount() { return coverageAmount; }
    public double getPremiumAmount() { return premiumAmount; }
    public LocalDate getPolicyStartDate() { return policyStartDate; }
    public LocalDate getPolicyEndDate() { return policyEndDate; }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public abstract void calculatePremium();
    public abstract void processClaim(double claimAmount);
    public abstract void generatePolicyReport();
    public abstract void validatePolicy();
}

// Vehicle class (Encapsulation)
class Vehicle {
    private String vehicleId;
    private String vehicleMake;
    private String vehicleModel;
    private int vehicleYear;
    private String vehicleType;

    public Vehicle(String vehicleId, String vehicleMake, String vehicleModel, int vehicleYear, String vehicleType) {
        this.vehicleId = vehicleId;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.vehicleType = vehicleType;
    }

    public String getVehicleId() { return vehicleId; }
    public String getVehicleMake() { return vehicleMake; }
    public String getVehicleModel() { return vehicleModel; }
    public int getVehicleYear() { return vehicleYear; }
    public String getVehicleType() { return vehicleType; }
}

// Person class (Encapsulation)
class Person {
    private String personId;
    private String fullName;
    private String dob;
    private String email;
    private String phone;

    public Person(String personId, String fullName, String dob, String email, String phone) {
        this.personId = personId;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    public String getPersonId() { return personId; }
    public String getFullName() { return fullName; }
    public String getDob() { return dob; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}

// Claim class (Encapsulation)
class Claim {
    private String claimId;
    private double claimAmount;
    private LocalDate claimDate;
    private String claimStatus;

    public Claim(String claimId, double claimAmount, LocalDate claimDate, String claimStatus) {
        this.claimId = claimId;
        this.claimAmount = claimAmount;
        this.claimDate = claimDate;
        this.claimStatus = claimStatus;
    }

    public String getClaimId() { return claimId; }
    public double getClaimAmount() { return claimAmount; }
    public LocalDate getClaimDate() { return claimDate; }
    public String getClaimStatus() { return claimStatus; }
}

// Concrete Policies:
class ComprehensivePolicy extends InsurancePolicy {
    public ComprehensivePolicy(String id, Vehicle v, Person p, double coverage, LocalDate start, LocalDate end) {
        super(id, v, p, coverage, start, end);
    }
    public void calculatePremium() {
        int age = LocalDate.now().getYear() - getVehicle().getVehicleYear();
        double premium = getCoverageAmount() * (0.02 + (age * 0.001));
        setPremiumAmount(premium);
    }
    public void processClaim(double claimAmount) {
        if (claimAmount <= getCoverageAmount()) {
            System.out.println("Claim approved for amount: " + claimAmount);
        } else {
            System.out.println("Claim denied. Exceeds coverage amount.");
        }
    }
    public void generatePolicyReport() {
        System.out.println("Comprehensive Policy for: " + getPolicyHolder().getFullName());
    }
    public void validatePolicy() {
        System.out.println("Validating vehicle type and year...");
    }
}

class ThirdPartyPolicy extends InsurancePolicy {
    public ThirdPartyPolicy(String id, Vehicle v, Person p, double coverage, LocalDate start, LocalDate end) {
        super(id, v, p, coverage, start, end);
    }
    public void calculatePremium() {
        double baseRate = getVehicle().getVehicleType().equalsIgnoreCase("SUV") ? 0.03 : 0.02;
        setPremiumAmount(getCoverageAmount() * baseRate);
    }
    public void processClaim(double claimAmount) {
        System.out.println("Processing third party claim...");
    }
    public void generatePolicyReport() {
        System.out.println("Third Party Policy Report for: " + getPolicyHolder().getFullName());
    }
    public void validatePolicy() {
        System.out.println("Validating third party policy requirements...");
    }
}

class CollisionPolicy extends InsurancePolicy {
    public CollisionPolicy(String id, Vehicle v, Person p, double coverage, LocalDate start, LocalDate end) {
        super(id, v, p, coverage, start, end);
    }
    public void calculatePremium() {
        setPremiumAmount(getCoverageAmount() * 0.025);
    }
    public void processClaim(double claimAmount) {
        System.out.println("Collision claim processed: " + claimAmount);
    }
    public void generatePolicyReport() {
        System.out.println("Collision Policy Report for: " + getPolicyHolder().getFullName());
    }
    public void validatePolicy() {
        System.out.println("Vehicle safety check required...");
    }
}

class LiabilityPolicy extends InsurancePolicy {
    public LiabilityPolicy(String id, Vehicle v, Person p, double coverage, LocalDate start, LocalDate end) {
        super(id, v, p, coverage, start, end);
    }
    public void calculatePremium() {
        setPremiumAmount(getCoverageAmount() * 0.018);
    }
    public void processClaim(double claimAmount) {
        System.out.println("Liability claim handled: " + claimAmount);
    }
    public void generatePolicyReport() {
        System.out.println("Liability Policy Report for: " + getPolicyHolder().getFullName());
    }
    public void validatePolicy() {
        System.out.println("Medical check required for policyholder...");
    }
}

class RoadsideAssistancePolicy extends InsurancePolicy {
    public RoadsideAssistancePolicy(String id, Vehicle v, Person p, double coverage, LocalDate start, LocalDate end) {
        super(id, v, p, coverage, start, end);
    }
    public void calculatePremium() {
        setPremiumAmount(getCoverageAmount() * 0.015);
    }
    public void processClaim(double claimAmount) {
        System.out.println("Roadside assistance claim accepted: " + claimAmount);
    }
    public void generatePolicyReport() {
        System.out.println("Roadside Assistance Report for: " + getPolicyHolder().getFullName());
    }
    public void validatePolicy() {
        System.out.println("Verifying registration and inspection...");
    }
}

// Main class
public class AdvancedMotorVehicleInsuranceSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Policy ID: ");
        String policyId = scanner.nextLine();
        System.out.print("Enter Person Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Vehicle ID: ");
        String vid = scanner.nextLine();
        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline
        System.out.print("Enter Type (e.g., SUV, Sedan): ");
        String type = scanner.nextLine();

        System.out.print("Enter Coverage Amount: ");
        double coverage = scanner.nextDouble();
        scanner.nextLine();
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusYears(1);

        Person person = new Person(UUID.randomUUID().toString(), name, dob, email, phone);
        Vehicle vehicle = new Vehicle(vid, make, model, year, type);

        InsurancePolicy policy = new ComprehensivePolicy(policyId, vehicle, person, coverage, startDate, endDate);
        policy.validatePolicy();
        policy.calculatePremium();
        policy.generatePolicyReport();
        System.out.println("Premium: " + policy.getPremiumAmount());

        System.out.print("Enter Claim Amount: ");
        double claim = scanner.nextDouble();
        policy.processClaim(claim);
    }
}

