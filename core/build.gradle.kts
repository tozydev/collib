plugins {
    alias(libs.plugins.paper.infra.kotlin)
    alias(libs.plugins.paperweight.userdev)
    alias(libs.plugins.maven.publish)
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper.api)

    api(platform(libs.kotlin.bom))
    api(libs.kotlin.stdlib)
    api(libs.kotlin.reflect)
    api(libs.kotlinx.datetime)

    api(platform(libs.kotlinx.coroutines.bom))
    api(libs.kotlinx.coroutines.core)
    api(libs.mccoroutine.folia.api) {
        exclude(group = "org.jetbrains.kotlin")
    }
    api(libs.mccoroutine.folia.core) {
        exclude(group = "org.jetbrains.kotlin")
    }

    api(libs.configurate.extra.kotlin) {
        exclude(group = "org.spongepowered")
        exclude(group = "org.jetbrains.kotlin")
        exclude(group = "com.google.errorprone")
    }
}
