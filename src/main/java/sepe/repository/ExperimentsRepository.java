/**
 * Created by giouri-adm on 15/5/2015.
 */

package sepe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sepe.domain.ExperimentsEntity;

import javax.annotation.Nonnull;
import java.util.List;

@Repository
public interface ExperimentsRepository extends CrudRepository<ExperimentsEntity, Integer> {


    @Override
    @RestResource(exported = false)
    public ExperimentsEntity findOne(Integer id);

    @Override
    @RestResource(exported = false)
    public List<ExperimentsEntity> findAll();

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
    public ExperimentsEntity save(ExperimentsEntity entity);

}

