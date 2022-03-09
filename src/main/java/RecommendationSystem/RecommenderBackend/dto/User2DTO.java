package RecommendationSystem.RecommenderBackend.dto;

import RecommendationSystem.RecommenderBackend.user.User;

import java.time.LocalDate;

public class User2DTO {
    private Long id;
    private Integer age;
    private String email;
    private String password;
    private String firstname;

    public User2DTO(Long id, Integer age, String email, String password, String firstname, String lastname, String empStatus, String gender, LocalDate regd, String role) {
        this.id = id;
        this.age = age;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.empStatus = empStatus;
        this.gender = gender;
        this.regd = regd;
        this.role = role;
    }

    public User2DTO(User user) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getRegd() {
        return regd;
    }

    public void setRegd(LocalDate regd) {
        this.regd = regd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String lastname;
    private String empStatus;
    private String gender;
    private LocalDate regd;
    private String role;

}
