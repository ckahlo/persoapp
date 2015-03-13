The PersoApp for Android uses [Gradle](http://www.gradle.org/) as build system.

# Building the Android app #

  * navigate into the `/PersoApp-Android/PersoApp-Client` folder
  * run '`gradle build`' or '`gradlew build`' in a terminal

The build task fetches all dependencies, runs the jarjar tool in order to repackage some dependencies, compiles code and resources, runs proguard and finally creates a debug and release version of the app. You find the output in the `/PersoApp-Android/PersoApp-Client/app/build/apk` folder. The proguard output can be found under `/PersoApp-Android/PersoApp-Client/app/build/proguard`.


# Updating the PersoApp-Core and PersoApp-Lib-JAXB libraries #

The PersoApp Android client fetches these two dependencies from the local Maven repository, which can be found at `/PersoApp-Android/mvn-repo`. During development it is recommended to use a second repository for snapshot builds. Create next to the `mvn-repo` folder a folder called '`mvn-repo-snapshot`'.

Both libraries are already prepared for publishing release and snapshot builds. Gradle either uploads the artifacts into the `mvn-repo` or `mvn-repo-snapshot` repository depending on the version of the library. A snapshot build has the suffix '`-SNAPSHOT`'. You find the version string in the '`build.gradle`' file in each library.

Do the following in order to upload a new artifact:

  * navigate either into the `/PersoApp-Core` or `/PersoApp-Android/PersoApp-Lib-JAXB` folder
  * run '`gradle uploadArchives`' or '`gradlew uploadArchives`' in a terminal

Do not forget to update the dependencies in the Android client project in order to use the new artifacts. A snapshot version requires here a '`-SNAPSHOT`' suffix in the version as well.


# JarJar Plugin #

You may notice that the Android client project has '`jarjar`' dependencies. The JarJar plugin fetches all of these dependencies, builds one single fat `.jar` file and runs the the jarjar tool.

You find the documentation and source code of this plugin here: https://github.com/vRallev/jarjar-gradle

You find the documentation for the jarjar tool here: https://code.google.com/p/jarjar/

The JarJar plugin integrates its tasks into the build process. It detects dependency changes automatically and creates a new result `.jar` file if necessary.


# Importing the project into Android Studio #

Import the `/PersoApp-Android/PersoApp-Client` folder as Gradle project. Android Studio will fetch all dependencies automatically and build the project.


# Importing the project into eclipse #

The ADT plugin for eclipse can't handle Gradle's file structure. However, there are already Gradle tasks prepared, which generate all needed eclipse project files and symlinks to ease the import process. Notice that most Gradle features don't work with eclipse, e.g. build types and built-in dependency management through Maven and/or Ivy.

All `.jar` file dependencies are automatically resolved. However, eclipse can't handle `.aar` file dependencies. In order to include these dependencies, all corresponding library projects are added in the `/PersoApp-Android/eclipse-compatibility/libs` folder.

Do the following to create a workspace environment for eclipse.

  * run '`gradle eclipse`' or '`gradlew eclipse`' in the Android client project (`/PersoApp-Android/PersoApp-Client`) and all three library projects (`/PersoApp-Android/eclipse-compatibility/libs/android-base-master/Android-Base-Project`, `/PersoApp-Android/eclipse-compatibility/libs/Crouton-master/Crouton` and `/PersoApp-Android/eclipse-compatibility/libs/PagerSlidingTabStrip-master`)
  * Import all four eclipse projects (select '`Existing Projects into Workspace`' in the eclipse importer dialog)
  * If you run '`gradle cleanEclipse`' or '`gradlew cleanEclipse`' all generated eclipse project files are deleted. If you update the Gradle eclipse tasks or configurations, you usually run as chained command '`gradle cleanEclipse eclipse`' or '`gradlew cleanEclipse eclipse`' in order to update the project files.