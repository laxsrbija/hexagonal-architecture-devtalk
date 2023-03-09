package net.lazars.greeting.mongo.mapper;

import net.lazars.greeting.core.model.User;
import net.lazars.greeting.mongo.document.UserDocument;
import org.mapstruct.Mapper;

@Mapper
public interface UserDocumentMapper {

  User toUser(UserDocument userDocument);

  UserDocument toUserDocument(User user);
}
