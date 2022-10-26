plugins {
    kotlin("plugin.jpa") version "1.6.21"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

val asciidoctorExt: Configuration by configurations.creating

dependencies {
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")

    implementation(project(":daily-coffee-support:logging"))
    implementation(project(":daily-coffee-support:common"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    testImplementation("org.springframework.restdocs:spring-restdocs-restassured")
    testImplementation("io.rest-assured:kotlin-extensions:5.2.0")

    runtimeOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
}

val snippetsDir by extra { file("build/generated-snippets") }

tasks {
    asciidoctor {
        dependsOn(test)
        configurations("asciidoctorExt")
        baseDirFollowsSourceFile()
        inputs.dir(snippetsDir)
    }
    register<Copy>("copyDocument") {
        dependsOn(asciidoctor)
        from(file("build/docs/asciidoc/index.html"))
        into(file("src/main/resources/static/docs"))
    }
    bootJar {
        dependsOn("copyDocument")
        enabled = true
    }
    jar { enabled = false }
}
