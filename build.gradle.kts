plugins {
    alias(libs.plugins.paper.infra.kotlin) apply false
    alias(libs.plugins.paper.infra.paper) apply false
    alias(libs.plugins.paper.infra.shadow) apply false
    alias(libs.plugins.maven.publish) apply false
}

subprojects {
    plugins.withType<MavenPublishPlugin> {
        extensions.configure<PublishingExtension> {
            repositories {
                maven {
                    val isSnapshot = version.toString().endsWith("-SNAPSHOT")
                    name =
                        if (isSnapshot) {
                            "VelaSnapshots"
                        } else {
                            "VelaReleases"
                        }
                    url =
                        if (isSnapshot) {
                            uri("https://vela.tozydev.id.vn/snapshots")
                        } else {
                            uri("https://vela.tozydev.id.vn/releases")
                        }
                    credentials {
                        username = propertyOrEnv("vela.username")
                        password = propertyOrEnv("vela.password")
                    }
                }
            }
        }
    }
}

fun Project.propertyOrEnv(name: String): String? = findProperty(name) as String? ?: System.getenv(name.uppercase().replace('.', '_'))
