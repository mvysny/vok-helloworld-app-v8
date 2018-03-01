[![Build Status](https://travis-ci.org/mvysny/vok-helloword-app.svg?branch=master)](https://travis-ci.org/mvysny/vok-helloword-app)

# Vaadin-on-Kotlin Example App / Archetype

Template for a simple Vaadin-on-Kotlin application that only requires a Servlet 3.0 container to run.
Just clone this repo and start building your awesome app!

To start creating your app, just follow the [Getting Started tutorial](http://www.vaadinonkotlin.eu/gettingstarted.html).

# Getting Started

To quickly start the app, make sure that you have Java 8 JDK installed. Then, just type this into your terminal:

```bash
git clone https://github.com/mvysny/vok-helloword-app
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
| [build.gradle](build.gradle) | [Gradle](https://gradle.org/) build tool configuration files. Gradle is used to compile your app, download all dependency jars and build a war file
| [gradlew](gradlew), [gradlew.bat](gradlew.bat), [gradle/](gradle) | Gradle runtime files, so that you can build your app from command-line simply by running `./gradlew`, without having to download and install Gradle distribution yourself.
| [.travis.yml](.travis.yml) | Configuration file for [Travis-CI](http://travis-ci.org/) which tells Travis how to build the app. Travis watches your repo; it automatically builds your app and runs all the tests after every commit.
| [.gitignore](.gitignore) | Tells [Git](https://git-scm.com/) to ignore files that can be produced from your app's sources - be it files produced by Gradle, Intellij project files etc.
| [web/](web/) | The web Gradle module which will host the web application itself. You can add more Gradle modules as your project will grow. Visit the [web module docs](web/) for more documentation.
| [web/src/main/resources/](web/src/main/resources) | A bunch of static files not compiled by Kotlin in any way; see below for explanation.
| [logback.xml](src/main/resources/logback.xml) | We're using [Slf4j](https://www.slf4j.org/) for logging and this is the configuration file for Slf4j
| [db/migration/](src/main/resources/db/migration) | Database upgrade instructions for the [Flyway](https://flywaydb.org/) framework. Database is upgraded on every server boot, to ensure it's always up-to-date. See the [Migration Naming Guide](https://flywaydb.org/documentation/migrations#naming) for more details.
| [webapp/](src/main/webapp) | contains the implementations of the Polymer components, and the global app CSS file. The [ReviewsList.kt](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/ReviewsList.kt) demonstrates how to use a Polymer component server-side. The CSS file references the Vaadin Lumo theme and configures it by the means of CSS variables.
| [webapp/frontend/](src/main/webapp/frontend) | contains the implementations of the Polymer components, and the global app CSS file.
| [frontend/styles.html](src/main/webapp/frontend/styles.html) | The CSS styles applied to your web app. Vaadin by default uses [Vaadin Lumo Theme](https://vaadin.com/themes/lumo); you can tweak the Lumo theme by the means of setting CSS variables.
| [frontend/reviews-list.html](src/main/webapp/frontend/reviews-list.html) | Contains the client-side implementation of a Polymer web component. The [ReviewsList.kt](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/ReviewsList.kt) then demonstrates how to use a Polymer component server-side.
| [src/main/kotlin/](src/main/kotlin) | The main Kotlin sources of your web app. You'll be mostly editing files located in this folder.
| [Bootstrap.kt](src/main/kotlin/com/vaadin/starter/beveragebuddy/Bootstrap.kt) | When Servlet Container (such as Tomcat) starts your app, it will run the `Bootstrap.contextInitialized()` function before any calls to your app are made. We need to bootstrap the Vaadin-on-Kotlin framework, in order to have support for the database; then we'll run Flyway migration scripts, to make sure that the database is up-to-date. After that's done, your app is ready to be serving client browsers.
| [FlowWorkarounds.kt](src/main/kotlin/com/vaadin/starter/beveragebuddy/FlowWorkarounds.kt) | Contains workarounds for bugs in Vaadin 10. When those bugs are fixed, this file will be removed.
| [MainLayout.kt](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/MainLayout.kt) | The main view of the app, it defines how the UI looks like and how the components are nested into one another. The UI is defined by the means of so-called DSL; see [Karibu-DSL examples](https://github.com/mvysny/karibu-dsl#how-to-write-dsls-for-vaadin-8-and-vaadin8-v7-compat) for more examples.
| [CategoriesList.kt](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/CategoriesList.kt) | An example view which is constructed entirely server-side. Demonstrates the use of the Vaadin Grid component.
| [ReviewsList.kt](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/ReviewsList.kt) | An example view which is a Polymer web component.
| [ConfirmationDialog.kt](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/ConfirmationDialog.kt) | An example of a Yes-No dialog built entirely server-side.
| [AbstractEditorDialog.kt](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/AbstractEditorDialog.kt), [CategoryEditorDialog.kt](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/CategoryEditorDialog.kt), [ReviewEditorDialog.kt](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/ReviewEditorDialog.kt) | Forms editing particular database entities, implemented as a dialogs.
| [converters/](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/converters) | Form helpers. They convert values from raw values as present in the database entity, into a value that's expected by the form components. For example a TextField expects String, but it needs to be converted to Int if editing an age.
| [backend/](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/backend) | Demonstrates the use of the [VoK-ORM](https://github.com/mvysny/vok-orm) framework to represent database rows as objects
| [RestService.kt/](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/backend/RestService.kt) | Demonstrates the possibility of having REST endpoints. See the class sources for details on how to test those endpoints
| [DemoData.kt/](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/backend/DemoData.kt) | Pre-populates the database with some example data.
| [Category.kt/](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/backend/Category.kt), [Review.kt/](src/main/kotlin/com/vaadin/starter/beveragebuddy/ui/backend/Review.kt) | Two entities. Category simply lists a list of beverage categories such as 'Beer'. Review lists reviews made for a particular beverage; it references the beverage category as a foreign key into the Category table.

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
