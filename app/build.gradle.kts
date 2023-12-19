plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.omaradev.cocktail"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.omaradev.cocktail"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.compose.runtime:runtime:1.5.4")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0-alpha02")

    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    androidTestImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    androidTestImplementation("org.mockito:mockito-android:3.11.2")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("android.arch.core:core-testing:1.1.1")

    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
}

tasks.register("addSpaceToEndOfLines") {
    description = "Add space to the end of each line in .kt files and commit changes to Git"

    doLast {
        println("Starting magic")

        project.exec {
            commandLine("git", "checkout", "-b" , "refactor/submitCode1")
        }

        println("Checked out new branch")

        // Set the directory containing Kotlin files
        val kotlinDir = file("src/main/")

        // Get all .kt files in the directory
        val kotlinFiles = fileTree(kotlinDir).matching { include("**/*.kt") }

        // Iterate over each Kotlin file
        println("processing to files ${kotlinFiles.files.size}")

        for (file in kotlinFiles) {
            // Read the content of the Kotlin file
            val originalContent = file.readText()

            // Add a space to the end of each line
            val modifiedContent = originalContent.replace(Regex("$", RegexOption.MULTILINE), " ")

            // Write the modified content back to the same file
            file.writeText(modifiedContent)
        }

        println("Adding files")

        project.exec {
            commandLine("git", "add", ".")
        }

        println("Committing")

        project.exec {
            commandLine("git", "commit", "-m", "refactor: prepare files for code review")
        }

        println("Push to remote")

        project.exec {
            commandLine("git", "push" , "fork")
        }

        println("Gradle task 'addSpaceToEndOfLines' completed successfully ")
    }
}