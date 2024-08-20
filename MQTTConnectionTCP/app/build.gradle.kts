plugins {
    alias(libs.plugins.android.application)

}

android {
    namespace = "com.example.mqttconnection"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mqttconnection"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        resources {
            excludes += ("META-INF/INDEX.LIST")
            excludes += ("META-INF/io.netty.versions.properties")
        }
    }
}

dependencies {
//    implementation ("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")
//    implementation(files("libs/serviceLibrary-release.aar"))
    implementation ("com.hivemq:hivemq-mqtt-client:1.2.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}