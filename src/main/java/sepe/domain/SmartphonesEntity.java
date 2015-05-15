package sepe.domain;

import javax.persistence.*;

/**
 * Created by giouri-adm on 14/5/2015.
 */
@Entity
@Table(name = "smartphones", schema = "androidcampaigns", catalog = "androidcampaigns")
public class SmartphonesEntity {
    private int id;
    private int phoneId;
    private String deviceType;
    private String sensorRules;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "phoneID")
    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    @Basic
    @Column(name = "deviceType")
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Basic
    @Column(name = "sensorRules")
    public String getSensorRules() {
        return sensorRules;
    }

    public void setSensorRules(String sensorRules) {
        this.sensorRules = sensorRules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmartphonesEntity that = (SmartphonesEntity) o;

        if (id != that.id) return false;
        if (phoneId != that.phoneId) return false;
        if (deviceType != null ? !deviceType.equals(that.deviceType) : that.deviceType != null) return false;
        if (sensorRules != null ? !sensorRules.equals(that.sensorRules) : that.sensorRules != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + phoneId;
        result = 31 * result + (deviceType != null ? deviceType.hashCode() : 0);
        result = 31 * result + (sensorRules != null ? sensorRules.hashCode() : 0);
        return result;
    }
}
