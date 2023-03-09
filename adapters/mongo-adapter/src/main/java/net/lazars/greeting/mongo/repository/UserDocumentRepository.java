package net.lazars.greeting.mongo.repository;

import net.lazars.greeting.mongo.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentRepository extends MongoRepository<UserDocument, String> {}
