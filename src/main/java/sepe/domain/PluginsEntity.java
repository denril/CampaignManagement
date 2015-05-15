package sepe.domain;

import javax.persistence.*;

/**
 * Created by giouri-adm on 14/5/2015.
 */
@Entity
@Table(name = "plugins", schema = "androidcampaigns", catalog = "androidcampaigns")
public class PluginsEntity {
    private int id;
    private String name;
    private String contextType;
    private String runtimeFactoryClass;
    private String description;
    private String installUrl;
    private String filename;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "contextType")
    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    @Basic
    @Column(name = "runtimeFactoryClass")
    public String getRuntimeFactoryClass() {
        return runtimeFactoryClass;
    }

    public void setRuntimeFactoryClass(String runtimeFactoryClass) {
        this.runtimeFactoryClass = runtimeFactoryClass;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "installUrl")
    public String getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    @Basic
    @Column(name = "filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PluginsEntity that = (PluginsEntity) o;

        if (id != that.id) return false;
        if (contextType != null ? !contextType.equals(that.contextType) : that.contextType != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (filename != null ? !filename.equals(that.filename) : that.filename != null) return false;
        if (installUrl != null ? !installUrl.equals(that.installUrl) : that.installUrl != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (runtimeFactoryClass != null ? !runtimeFactoryClass.equals(that.runtimeFactoryClass) : that.runtimeFactoryClass != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (contextType != null ? contextType.hashCode() : 0);
        result = 31 * result + (runtimeFactoryClass != null ? runtimeFactoryClass.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (installUrl != null ? installUrl.hashCode() : 0);
        result = 31 * result + (filename != null ? filename.hashCode() : 0);
        return result;
    }
}
