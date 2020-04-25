package com.thalgrim.warhammer;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.thalgrim.warhammer");

        noClasses()
            .that()
            .resideInAnyPackage("com.thalgrim.warhammer.service..")
            .or()
            .resideInAnyPackage("com.thalgrim.warhammer.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.thalgrim.warhammer.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
