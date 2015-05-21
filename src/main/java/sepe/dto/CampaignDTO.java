package sepe.dto;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by giouri-adm on 15/5/2015.
 */

public class CampaignDTO {

    private String name;
    private String fromTime;
    private String toTime;
    private int experimentsUsedId;
    private int status;
    private String measurements;
    private String area;

    public CampaignDTO(String name, String fromTime, String toTime, int experimentsUsedId, int status, String measurements, String area) {
        this.name = name;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.experimentsUsedId = experimentsUsedId;
        this.status = status;
        this.measurements = measurements;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public int getExperimentsUsedId() {
        return experimentsUsedId;
    }

    public void setExperimentsUsedId(int experimentsUsedId) {
        this.experimentsUsedId = experimentsUsedId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMeasurements() {
        return measurements;
    }

    public void setMeasurements(String measurements) {
        this.measurements = measurements;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
