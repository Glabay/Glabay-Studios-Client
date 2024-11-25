import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.22"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.openjfx.javafxplugin")
}


buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.guardsquare:proguard-gradle:7.3.0")
    }
}

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "application")
    apply(plugin = "com.github.johnrengelman.shadow")

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    }

    java {
        setSourceCompatibility(JavaVersion.VERSION_11.toString())
        setTargetCompatibility(JavaVersion.VERSION_11.toString())
    }
}

dependencies {
    implementation(project(":game"))
}

tasks.withType<JavaCompile>().configureEach {
    options.isWarnings = false
    options.isDeprecation = false
    options.isIncremental = true
}

tasks {
    jar {
        destinationDirectory.set(file("${rootProject.buildDir}\\"))
    }
}

application {
    mainClass.set("Application")
}

val fatJar = task("fatJar", type = Jar::class) {
    this.archiveBaseName.set("OSBY - Alpha")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes(
            "Implementation-Title" to project.name,
            "Implementation-Version" to version,
            "Main-Class" to "Application"
        )
    }
    from(configurations.runtimeClasspath.get().map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks.jar.get() as CopySpec)
}

val fatJarL = task("fatJar - local", type = Jar::class) {
    this.archiveBaseName.set("OSBY - Local")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    manifest {
        attributes(
            "Implementation-Title" to project.name,
            "Implementation-Version" to version,
            "Main-Class" to "Application"
        )
    }
    from(configurations.runtimeClasspath.get().map({ if (it.isDirectory) it else zipTree(it) }))
    with(tasks.jar.get() as CopySpec)
}

tasks.jar {
    dependsOn(fatJar)
}