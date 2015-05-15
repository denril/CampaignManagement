package sepe.domain;

import javax.persistence.*;

/**
 * Created by giouri-adm on 14/5/2015.
 */
@Entity
@Table(name = "results", schema = "androidcampaigns", catalog = "androidcampaigns")
public class ResultsEntity {
    private int id;
    private int experimentId;
    private int deviceId;
    private String message;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "experimentID")
    public int getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(int experimentId) {
        this.experimentId = experimentId;
    }

    @Basic
    @Column(name = "deviceID")
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultsEntity that = (ResultsEntity) o;

        if (deviceId != that.deviceId) return false;
        if (experimentId != that.experimentId) return false;
        if (id != that.id) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + experimentId;
        result = 31 * result + deviceId;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
