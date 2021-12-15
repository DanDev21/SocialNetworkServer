val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kmongo_version: String by project
val koin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
}

group = "com.example"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

dependencies {

    // ktor - core
    implementation("io.ktor:ktor-server-core:$ktor_version")

    // ktor - authentication
    implementation("io.ktor:ktor-auth:$ktor_version")
    implementation("io.ktor:ktor-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-server-host-common:$ktor_version")

    // ktor - gson
    implementation("io.ktor:ktor-gson:$ktor_version")

    // ktor - websockets
    implementation("io.ktor:ktor-websockets:$ktor_version")

    implementation("io.ktor:ktor-server-netty:$ktor_version")

    // ktor - logback
    implementation("ch.qos.logback:logback-classic:$logback_version")


    // kmongo
    implementation("org.litote.kmongo:kmongo:$kmongo_version")
    implementation("org.litote.kmongo:kmongo-coroutine:$kmongo_version")


    // koin
    implementation("io.insert-koin:koin-core:$koin_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")


//    ----------------------   testing   ----------------------


    // testing - junit
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    // testing - kotlin
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")

    // testing - ktor server
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")

    // testing - gson
    testImplementation("com.google.code.gson:gson:2.8.9")

    // testing - koin
    testImplementation("io.insert-koin:koin-test:$koin_version")

    // testing - truth
    testImplementation("com.google.truth:truth:1.1.3")
}