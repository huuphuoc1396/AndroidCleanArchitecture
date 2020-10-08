// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
        maven {
            url = uri(Url.jitPack)
        }
        maven {
            url = uri(Url.fabric)
        }
    }
    dependencies {
        classpath(BuildPlugins.androidPlugin)
        classpath(BuildPlugins.kotlinPlugin)
        classpath(BuildPlugins.navigationPlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = uri(Url.jitPack)
        }
        maven {
            url = uri(Url.fabric)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
