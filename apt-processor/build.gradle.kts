plugins {
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":apt-annotation"))
    // for auto register
    implementation("com.google.auto.service:auto-service:1.0-rc6")
    implementation("com.squareup:javapoet:1.13.0")
    annotationProcessor("com.google.auto.service:auto-service:1.0-rc6")

}