package net.lazars.greeting.postgres.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "user_table")
public class UserEntity {

  @Id
  @Column(name = "user_id")
  private String id;

  @Column(name = "name")
  private String name;

  @ElementCollection
  @Column(name = "nickname")
  @CollectionTable(
      name = "user_nicknames",
      joinColumns = @JoinColumn(name = "user_id"),
      indexes = @Index(name = "idx_nickname", columnList = "nickname"))
  private List<String> nicknames;
}
