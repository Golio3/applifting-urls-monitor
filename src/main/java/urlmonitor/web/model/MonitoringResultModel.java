package urlmonitor.web.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "monitoring_result")
public class MonitoringResultModel {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @Column(name = "LAST_CHECK_DATE")
    private Date lastCheckDate;

    @Column(name = "HTTP_STATUS_CODE")
    private Integer httpStatusCode;

    @Column(name = "PAYLOAD")
    private String payload​;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MONITORED_ENDPOINT_ID")
    private MonitoredEndpointsModel monitoredEndpoint;

    public MonitoringResultModel() {
    }

    public MonitoringResultModel(MonitoredEndpointsModel monitoredEndpoint, Integer httpStatusCode, String payload​, Date lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
        this.httpStatusCode = httpStatusCode;
        this.payload​ = payload​;
        this.monitoredEndpoint = monitoredEndpoint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLastCheckDate() {
        return lastCheckDate;
    }

    public void setLastCheckDate(Date lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getPayload​() {
        return payload​;
    }

    public void setPayload​(String payload​) {
        this.payload​ = payload​;
    }

    public MonitoredEndpointsModel getMonitoredEndpoint() {
        return monitoredEndpoint;
    }

    public void setMonitoredEndpoint(MonitoredEndpointsModel monitoredEndpoint) {
        this.monitoredEndpoint = monitoredEndpoint;
    }
}
