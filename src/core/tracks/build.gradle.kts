plugins { kotlin("jvm") version "2.1.0" }

group = "com.daromi.playbook.core.tracks"

version = "0.1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies {
    implementation(platform("com.google.cloud:libraries-bom:26.52.0"))
    implementation("com.google.cloud:google-cloud-bigtable")

    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.test { useJUnitPlatform() }
