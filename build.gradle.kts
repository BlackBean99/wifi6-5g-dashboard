import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.script.experimental.api.ScriptCompilationConfiguration.Default.properties

plugins {
    id("org.springframework.boot") version "2.7.16" apply false
    id("io.spring.dependency-management") version "1.0.15.RELEASE" apply false
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
    id("com.diffplug.spotless") version  "6.18.0" // spotless
    id("org.sonarqube") version "3.5.0.2730" // 소나 클라우드
    id("jacoco")
}
java.sourceCompatibility = JavaVersion.VERSION_17

sonarqube {
    properties {
        property ("sonar.projectKey", "wifi6-api")
        property ("sonar.organization", "wifi6-1")
        property ("sonar.host.url", "https://sonarcloud.io")
        property ("sonar.sources", "src")
        property ("sonar.language", "Kotlin")
        property ("sonar.sourceEncoding", "UTF-8")
        property ("sonar.test.inclusions", "**/*Test.java")
        property ("sonar.exclusions", "**/test/**, **/Q*.kt, **/*Doc*.kt, **/resources/** ,**/*Application*.kt , **/*Config*.kt, **/*Dto*.kt, **/*Request*.kt, **/*Response*.kt ,**/*Exception*.kt ,**/*ErrorCode*.kt")
        property ("sonar.java.coveragePlugin", "jacoco")
    }
}

/*
allOpen{
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}*/

allprojects {
    group = "com.jnu"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "jacoco" )
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }

//    tasks.withType<JavaExec> {
//        doLast {
//            commandLine("bash", "load_env.sh")
//        }
//    }

    dependencies {
        // feign
        api ("io.github.openfeign:feign-httpclient:12.2")
        api ("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.4")
        //influx
//        implementation("com.fasterxml.jackson.core:jackson-databind:2.10.2")

        implementation("com.influxdb:influxdb-client-kotlin:6.6.0")

        api("com.mysql:mysql-connector-j:8.1.0")

        implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.8")
        // batch
        implementation("org.springframework.boot:spring-boot-starter-batch")
        testImplementation("org.springframework.batch:spring-batch-test")
        implementation("org.springframework.boot:spring-boot-starter-quartz")
        implementation("io.github.microutils:kotlin-logging:2.0.11")

        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-web-services")

        //    implementation("org.springframework.boot:spring-boot-starter-webflux")
        //    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
    }
    tasks.getByName<Jar>("jar") {
        enabled = false
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.test {
        finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test) // tests are required to run before generating the report
        reports {
            html.required.set(true) // html 설정
            csv.required.set(true) // csv 설정
            xml.required.set(true)
            xml.outputLocation.set(File("${buildDir}/reports/jacoco.xml"))
        }


        classDirectories.setFrom(
            files(classDirectories.files.map {
                fileTree(it) { // jacoco file 테스트 커버리지 측정안할 목록
                    exclude("**/*Application*",
                        "**/*Config*",
                        "**/*Dto*",
                        "**/*Request*",
                        "**/*Response*",
                        "**/*Interceptor*",
                        "**/*Exception*" ,
                        "**/Q*") // Query Dsl 용이긴하나 Q로 시작하는 다른 클래스를 뺄 위험이 있음.
                }
            })
        )
    }

    sonarqube {
        properties {
            property("sonar.java.binaries", "${buildDir}/classes")
            property("sonar.coverage.jacoco.xmlReportPaths", "${buildDir}/reports/jacoco.xml")
        }
    }
}
configure<com.diffplug.gradle.spotless.SpotlessExtension> {

    kotlin {
        // version, setUseExperimental, userData and editorConfigOverride are all optional
        target ("**/*.kt")
        ktlint("0.48.0")
        // kt lint 설정중에
        // no wild card import 의경우를 그대로 따라갑니다. 대신 ide 에서 설정을 해주셔야합니다.
        // https://blog.leocat.kr/notes/2020/12/14/intellij-avoid-wildcard-imports-in-kotlin-with-intellij
    }
}