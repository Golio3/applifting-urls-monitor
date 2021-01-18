package urlmonitor.web.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.*;

@Entity(name = "users")
public class UsersModel {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @NotBlank
    @Column(name = "USERNAME")
    private String username;

    @NotBlank
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ACTIVE")
    private int active;

    @Column(name = "ROLES")
    private String roles = "";

    @Email
    @Column(name = "EMAIL")
    private String email;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "USER_ID")
    private List<MonitoredEndpointsModel> monitoredEndpointList = new ArrayList<>();

    @Transient
    private String errors;

    @Transient
    private String repeatPassword;

    public UsersModel() {
    }

    public UsersModel(String username, String password, int active, String roles, String email, String errors, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.active = 1;
        this.roles = roles;
        this.email = email;
        this.errors = errors;
        this.repeatPassword = repeatPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MonitoredEndpointsModel> getMonitoredEndpointList() {
        return monitoredEndpointList;
    }

    public void setMonitoredEndpointList(List<MonitoredEndpointsModel> monitoredEndpointList) {
        this.monitoredEndpointList = monitoredEndpointList;
    }
}
