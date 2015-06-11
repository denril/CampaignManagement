package sepe.domain;

import sepe.service.CurrentUserDetailsService;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by giouri-adm on 15/5/2015.
 */
@Entity
@Table(name = "campaign", schema = "androidcampaigns", catalog = "")
public class CampaignEntity implements Serializable {
    private int id;
    private String name;
    private String fromTime;
    private String toTime;
    private int experimentsUsedId;
    private int status;
    private String measurements;
    private String area;
    private String registeredUsers;

    public CampaignEntity(int id, String name, String fromTime, String toTime, int experimentsUsedId, int status, String measurements, String area) {
        this.id = id;
        this.name = name;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.experimentsUsedId = experimentsUsedId;
        this.status = status;
        this.measurements = measurements;
        this.area = area;
    }

    public CampaignEntity(String name, String fromTime, String toTime, int experimentsUsedId, int status, String measurements, String area) {
        this.name = name;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.experimentsUsedId = experimentsUsedId;
        this.status = status;
        this.measurements = measurements;
        this.area = area;
    }

    public CampaignEntity(String name, String fromTime, String toTime, int experimentsUsedId, int status, String measurements, String area, String registeredUsers) {
        this.name = name;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.experimentsUsedId = experimentsUsedId;
        this.status = status;
        this.measurements = measurements;
        this.area = area;
        this.registeredUsers = registeredUsers;
    }

    public CampaignEntity(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false, insertable = true, updatable = true, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name" , nullable = true, insertable = true, updatable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "from_time" , nullable = true, insertable = true, updatable = true, length = 50)
    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    @Basic
    @Column(name = "to_time" , nullable = true, insertable = true, updatable = true, length = 50)
    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    @Basic
    @Column(name = "experiments_used_id" , nullable = true, insertable = true, updatable = true, precision = 0)
    public int getExperimentsUsedId() {
        return experimentsUsedId;
    }

    public void setExperimentsUsedId(int experimentsUsedId) {
        this.experimentsUsedId = experimentsUsedId;
    }

    @Basic
    @Column(name = "status" , nullable = true, insertable = true, updatable = true, precision = 0)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "measurements" , nullable = true, insertable = true, updatable = true, length = 1000)
    public String getMeasurements() {
        return measurements;
    }

    public void setMeasurements(String measurements) {
        this.measurements = measurements;
    }

    @Basic
    @Column(name = "area" , nullable = true, insertable = true, updatable = true, length = 1000)
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "registered_users" , nullable = true, insertable = true, updatable = true, length = 1000)
    public String getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(String registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CampaignEntity that = (CampaignEntity) o;

        if (experimentsUsedId != that.experimentsUsedId) return false;
        if (id != that.id) return false;
        if (status != that.status) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (fromTime != null ? !fromTime.equals(that.fromTime) : that.fromTime != null) return false;
        if (measurements != null ? !measurements.equals(that.measurements) : that.measurements != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (toTime != null ? !toTime.equals(that.toTime) : that.toTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fromTime != null ? fromTime.hashCode() : 0);
        result = 31 * result + (toTime != null ? toTime.hashCode() : 0);
        result = 31 * result + experimentsUsedId;
        result = 31 * result + status;
        result = 31 * result + (measurements != null ? measurements.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        return result;
    }
}
