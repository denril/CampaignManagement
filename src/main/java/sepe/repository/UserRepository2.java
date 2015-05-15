package sepe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sepe.domain.UserEntity;

import javax.annotation.Nonnull;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface UserRepository2 extends CrudRepository<UserEntity, Long> {



    @RestResource(exported = true)
    public UserEntity findByEmailEquals(@Nonnull String email);

    @RestResource(exported = true)
    public UserEntity findByUsernameEquals(@Param("username") @Nonnull String username);

    @Override
    @RestResource(exported = true)
    public UserEntity findOne(Long id);

    @Override
    @RestResource(exported = true)
    public List<UserEntity> findAll();

    @Override
    @RestResource(exported = true)
    public long count();

    @Override
    @RestResource(exported = true)
    public void delete(Long id);



    @Override
    @RestResource(exported = false)
    public void deleteAll();

    @Override
    @RestResource(exported = false)
    public boolean exists(Long id);



    @Override
    @RestResource(exported = false)
    public UserEntity save(UserEntity entity);

}
