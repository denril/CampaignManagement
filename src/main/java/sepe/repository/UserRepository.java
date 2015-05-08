package sepe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sepe.domain.UserEntity;

import javax.annotation.Nonnull;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {



    @RestResource(exported = false)
    public UserEntity findByEmailEquals(@Nonnull String email);

    @RestResource(exported = false)
    public UserEntity findByUsernameEquals(@Nonnull String username);

    @Override
    @RestResource(exported = false)
    public UserEntity findOne(Long id);

    @Override
    @RestResource(exported = false)
    public List<UserEntity> findAll();

    @Override
    @RestResource(exported = false)
    public long count();

    @Override
    @RestResource(exported = false)
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
