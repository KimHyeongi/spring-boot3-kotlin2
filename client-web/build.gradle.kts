plugins {
}

dependencies {
    implementation(project(":core"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_reflect}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${Versions.kotlinx_coroutines}")

    implementation("org.springframework.boot:spring-boot-starter-web:${Versions.spring_boot}")
    implementation("org.springframework.boot:spring-boot-starter-webflux:${Versions.spring_boot}")
    implementation("org.springframework.boot:spring-boot-starter-security:${Versions.spring_boot}")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:${Versions.spring_boot}")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${Versions.openfeign}")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client:${Versions.spring_boot}")
    implementation("org.springframework.session:spring-session-data-redis:${Versions.spring_session}")

    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6:${Versions.thymeleaf_extras}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:${Versions.reactor_kotlin}")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:${Versions.thymeleaf_layout}")
    // firebase
    implementation("com.google.firebase:firebase-admin:${Versions.firebase_admin}")

    implementation("com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Versions.jackson}")
    implementation("org.dhatim:fastexcel:${Versions.fastexcel}")
    implementation("org.dhatim:fastexcel-reader:${Versions.fastexcel}")
    implementation("org.javers:javers-spring-boot-starter-sql:${Versions.javers}")
    implementation("org.jsoup:jsoup:${Versions.jsoup}")
    implementation("org.apache.commons:commons-text:${Versions.commons_text}")
    implementation("org.apache.commons:commons-imaging:${Versions.commons_imaging}")
    
    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.spring_boot}")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation(testFixtures(project(":core")))
}

tasks.register("prepareKotlinBuildScriptModel") {}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    enabled = true
    mainClass.set("com.tistory.eclipse4j.client.ClientApplicationKt")
    archiveFileName.set("${archiveBaseName.get()}.${archiveExtension.get()}")
}
