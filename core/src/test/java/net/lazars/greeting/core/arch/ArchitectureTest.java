package net.lazars.greeting.core.arch;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

class ArchitectureTest {

  @Test
  void validateArchitecture() {
    classes()
        .that()
        .resideInAPackage("..port..")
        .should()
        .beInterfaces()
        .check(new ClassFileImporter().importPackages("net.lazars.greeting.core"));
  }
}
