[![Build Status](https://travis-ci.org/mvysny/vok-helloworld-app.svg?branch=master)](https://travis-ci.org/mvysny/vok-helloworld-app)

# Vaadin-on-Kotlin Vaadin 8 Example App / Archetype

Template for a simple Vaadin-on-Kotlin Vaadin 8-based application that only requires a Servlet 3.0 container to run.
Just clone this repo and start building your awesome app!

You'll start with this app when you follow the [Getting Started tutorial](http://www.vaadinonkotlin.eu/gettingstarted.html).

# Getting Started

To quickly start the app, make sure that you have Java 8 JDK installed. Then, just type this into your terminal:

```bash
git clone https://github.com/mvysny/vok-helloworld-app
cd vok-helloworld-app
./gradlew build web:appRun
```

The app will be running on [http://localhost:8080/](http://localhost:8080/).

## The 'complete' sources

You can switch the git branch from 'master' to ['complete'](../../tree/complete), to see the outcome application of the
[Vaadin-on-Kotlin Getting Started](http://www.vaadinonkotlin.eu/gettingstarted.html) tutorial. 

# Workflow

To compile the entire project, run `./gradlew`.

To run the application, run `./gradlew web:appRun` (run the `appRun` task in the `web` Gradle module) and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change `productionMode` to `true` in the servlet class configuration (located in the [MyUI.kt](web/src/main/kotlin/com/example/vok/MyUI.kt) file)
- run `./gradlew`
- You will find the WAR file in `web/build/libs/web.war`

This will allow you to quickly start the example app and allow you to do some basic modifications.
For real development we recommend Intellij IDEA Ultimate, please see below for instructions.

## Client-Side compilation

The generated maven project is using an automatically generated widgetset by default. 
When you add a dependency that needs client-side compilation, the maven plugin will 
automatically generate it for you. Your own client-side customisations can be added into
package "client".

Debugging client side code  @todo revisit with Gradle
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application

## Developing a theme using the runtime compiler

When developing the theme, Vaadin can be configured to compile the SASS based
theme at runtime in the server. This way you can just modify the scss files in
your IDE and reload the browser to see changes.

To use the runtime compilation, run `./gradlew clean appRun`. Gretty will automatically
pick up changes in theme files and Vaadin will automatically compile the theme on
browser refresh. You will just have to give Gretty some time (one second) to register
the change.

When using the runtime compiler, running the application in the "run" mode 
(rather than in "debug" mode) can speed up consecutive theme compilations
significantly.

It is highly recommended to disable runtime compilation for production WAR files.

## Dissection of project files

Let's look at all files that this project is composed of, and what are the points where you'll add functionality:

| Files | Meaning
| ----- | -------
| [build.gradle](build.gradle), [settings.gradle](settings.gradle) | [Gradle](https://gradle.org/) build tool configuration files. Gradle is used to compile your app, download all dependency jars and build a war file
| [gradlew](gradlew), [gradlew.bat](gradlew.bat), [gradle/](gradle) | Gradle runtime files, so that you can build your app from command-line simply by running `./gradlew`, without having to download and install Gradle distribution yourself.
| [.travis.yml](.travis.yml) | Configuration file for [Travis-CI](http://travis-ci.org/) which tells Travis how to build the app. Travis watches your repo; it automatically builds your app and runs all the tests after every commit.
| [.gitignore](.gitignore) | Tells [Git](https://git-scm.com/) to ignore files that can be produced from your app's sources - be it files produced by Gradle, Intellij project files etc.
| [web/](web/) | The web Gradle module which will host the web application itself. You can add more Gradle modules as your project will grow. Visit the [web module docs](web/) for more documentation.

# Development with Intellij IDEA Ultimate

The easiest way (and the recommended way) to develop Karibu-DSL-based web applications is to use Intellij IDEA Ultimate.
It includes support for launching your project in any servlet container (Tomcat is recommended)
and allows you to debug the code, modify the code and hot-redeploy the code into the running Tomcat
instance, without having to restart Tomcat.

1. First, download Tomcat and register it into your Intellij IDEA properly: https://www.jetbrains.com/help/idea/2017.1/defining-application-servers-in-intellij-idea.html
2. Then just open this project in Intellij, simply by selecting `File / Open...` and click on the
   `build.gradle` file. When asked, select "Open as Project".
2. You can then create a launch configuration which will launch the `web` module as `exploded` in Tomcat with Intellij: just
   scroll to the end of this tutorial: https://kotlinlang.org/docs/tutorials/httpservlets.html
3. Start your newly created launch configuration in Debug mode. This way, you can modify the code
   and press `Ctrl+F9` to hot-redeploy the code. This only redeploys java code though, to
   redeploy resources just press `Ctrl+F10` and select "Update classes and resources"
