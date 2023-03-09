package net.lazars.greeting.mongo.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.lazars.greeting.core.model.User;
import net.lazars.greeting.core.port.outbound.UserRepository;
import net.lazars.greeting.mongo.mapper.UserDocumentMapper;
import net.lazars.greeting.mongo.repository.UserDocumentRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "greeting", name = "database", havingValue = "MONGO")
public class UserRepositoryImpl implements UserRepository {

  private final UserDocumentMapper userDocumentMapper = Mappers.getMapper(UserDocumentMapper.class);
  private final UserDocumentRepository userDocumentRepository;

  @Override
  public Optional<User> findById(final String id) {
    return userDocumentRepository.findById(id).map(userDocumentMapper::toUser);
  }

  @Override
  public void save(final User user) {
    userDocumentRepository.save(userDocumentMapper.toUserDocument(user));
  }
}
