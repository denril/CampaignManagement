/**
 * Created by giouri-adm on 15/5/2015.
 */

package sepe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sepe.domain.PluginsEntity;

import javax.annotation.Nonnull;
import java.util.List;

@Repository
public interface PluginsRepository extends CrudRepository<PluginsEntity, Integer> {


    @Override
    @RestResource(exported = false)
    public PluginsEntity findOne(Integer id);

    @Override
    @RestResource(exported = false)
    public List<PluginsEntity> findAll();

    @Override
    @RestResource(exported = false)
    public long count();

    @Override
    @RestResource(exported = false)
    public void delete(Integer id);



    @Override
    @RestResource(exported = false)
    public void deleteAll();

    @Override
    @RestResource(exported = false)
    public boolean exists(Integer id);



    @Override
    @RestResource(exported = false)
    public PluginsEntity save(PluginsEntity entity);

}

