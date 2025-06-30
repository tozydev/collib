plugins {
    alias(libs.plugins.paper.infra.kotlin)
    alias(libs.plugins.maven.publish)
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly(libs.configurate.core)

    api(libs.hikari.cp)
}
