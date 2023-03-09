package net.lazars.greeting.postgres.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.lazars.greeting.core.model.User;
import net.lazars.greeting.core.port.outbound.UserRepository;
import net.lazars.greeting.postgres.mapper.UserEntityMapper;
import net.lazars.greeting.postgres.repository.UserEntityRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "greeting", name = "database", havingValue = "POSTGRES")
public class UserRepositoryImpl implements UserRepository {

  private final UserEntityMapper userEntityMapper = Mappers.getMapper(UserEntityMapper.class);
  private final UserEntityRepository userEntityRepository;

  @Override
  public Optional<User> findById(final String id) {
    return userEntityRepository.findById(id).map(userEntityMapper::toUser);
  }

  @Override
  public void save(final User user) {
    userEntityRepository.save(userEntityMapper.toUserEntity(user));
  }
}
