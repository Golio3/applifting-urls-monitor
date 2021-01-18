package urlmonitor.web.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "monitored_endpoints")
public class MonitoredEndpointsModel {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @NotNull
    @Size(min=1, max=1000)
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Size(min=1, max=1000)
    @URL
    @Column(name = "URL")
    private String url;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @NotNull
    @Min(1)
    @Max(10000)
    @Column(name = "MONITORED_INTERVAL")
    private Integer monitoredInterval;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UsersModel user;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "MONITORED_ENDPOINT_ID")
    private List<MonitoringResultModel> monitoringResultList = new ArrayList<>();

    public MonitoredEndpointsModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getMonitoredInterval() {
        return monitoredInterval;
    }

    public void setMonitoredInterval(Integer monitoredInterval) {
        this.monitoredInterval = monitoredInterval;
    }

    public UsersModel getUser() {
        return user;
    }

    public void setUser(UsersModel user) {
        this.user = user;
    }

    public List<MonitoringResultModel> getMonitoringResultList() {
        return monitoringResultList;
    }

    public void setMonitoringResultList(List<MonitoringResultModel> monitoringResultList) {
        this.monitoringResultList = monitoringResultList;
    }
}
