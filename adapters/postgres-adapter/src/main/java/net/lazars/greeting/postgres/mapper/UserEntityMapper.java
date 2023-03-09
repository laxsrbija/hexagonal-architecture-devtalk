package net.lazars.greeting.postgres.mapper;

import net.lazars.greeting.core.model.User;
import net.lazars.greeting.postgres.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserEntityMapper {

  User toUser(UserEntity userEntity);

  UserEntity toUserEntity(User user);
}
