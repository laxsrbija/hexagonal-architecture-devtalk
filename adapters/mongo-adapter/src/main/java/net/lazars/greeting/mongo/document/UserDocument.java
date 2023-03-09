package net.lazars.greeting.mongo.document;

import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserDocument {

  @Id private String id;

  private String name;

  private List<String> nicknames;
}
