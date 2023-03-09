package net.lazars.greeting.postgres.repository;

import net.lazars.greeting.postgres.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, String> {}
