package RecommendationSystem.RecommenderBackend.user;

import RecommendationSystem.RecommenderBackend.interesting.Interesting;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="age")
    @Transient // calculate age
    private Integer age;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="dob")
    private LocalDate dob;

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    @Column(name="role")
    private String role;



    @Column(name="gender")
    private String gender;

    @Column(name="regd")
    private LocalDate regd;

    @Column(name="empStatus")
    private String empStatus;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
    @JoinTable(
            name="interesting_select",
            joinColumns =@JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="interesting_id")
    )
   Set<Interesting> selectedInterestings;

    public User(){

    }


    public Set<Interesting> getInterestings() {
        return selectedInterestings;
    }

    public void setInterestings(Set<Interesting> interestings) {
        this.selectedInterestings = interestings;
    }

    public LocalDate getRegd() {
        return regd;
    }


    public void setRegd(LocalDate regd) {
        this.regd = regd;
    }

    public User(Long id, String email, String password, String firstname, String lastname, LocalDate dob, Integer age, String role, LocalDate regd) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.age = age;
        this.role = role;
        this.regd =regd;
        this.empStatus=empStatus;

    }

    public User(Set<Interesting> interestings) {
        this.selectedInterestings = interestings;
    }

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();//calculating age
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Interesting> getEnrolledInterestings() {
        return selectedInterestings;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public void enrollInteresting(Interesting insertInteresting) {

        selectedInterestings.add(insertInteresting);
    }

    public void removeInteresting(Interesting insertInteresting) {

        selectedInterestings.remove(insertInteresting);
    }





}
