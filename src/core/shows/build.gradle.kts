plugins { kotlin("jvm") version "2.1.0" }

group = "com.daromi.playbook.core.shows"

version = "0.1.0-SNAPSHOT"

repositories { mavenCentral() }

dependencies { testImplementation(kotlin("test")) }

kotlin { jvmToolchain(21) }

tasks.test { useJUnitPlatform() }
