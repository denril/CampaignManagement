/**
 * Created by giouri-adm on 15/5/2015.
 */

package sepe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sepe.domain.ResultsEntity;

import javax.annotation.Nonnull;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "results", path = "/results")
public interface ResultsRepository extends CrudRepository<ResultsEntity, Integer> {


    @Override
    @RestResource(exported = false)
    public ResultsEntity findOne(Integer id);

    @Override
    @RestResource(exported = false)
    public List<ResultsEntity> findAll();

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
    public ResultsEntity save(ResultsEntity entity);

}

