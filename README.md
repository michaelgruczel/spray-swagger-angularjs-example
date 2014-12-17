spray-swagger-angularjs-example
===============================

    > This code is in beta, do not use now

Simple Todo list example with spray(scala), swagger (api-docu) and angularjs (frontend) and bootstrap (frontend layout)

This is based on:

* https://github.com/gettyimages/spray-swagger
* https://github.com/mhamrah/spray-swagger-sample
* https://github.com/spray/spray-template
* https://github.com/angular/angular.js
* https://github.com/twbs/bootstrap
* https://github.com/swagger-api/swagger-ui

Please follow the License rules of this repos if you want to use this repo.

## How to start

Follow these steps to get started:

1. Git-clone this repository.

    $ git clone git://github.com/spray/spray-template.git my-project

2. Change directory into your clone:

    $ cd my-project

3. Launch SBT:

    $ sbt

4. Compile everything and run all tests:

    > test

5. Start the application:

    > re-start

6. Browse to [http://localhost:8080](http://localhost:8080/) to see the angular js frontend

7. Browse to [http://localhost:8080/swagger](http://localhost:8080/swagger) to see the swagger ui frontend

8. Stop the application:

    > re-stop

9. Learn more at http://www.spray.io/

10. Start hacking on `src/main/scala/com/example/MyService.scala`

11. import to scalaIde (or choose your own IDE):

     $ sbt eclipse
