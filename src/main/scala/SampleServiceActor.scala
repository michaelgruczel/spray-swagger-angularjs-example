package com.mlh.sprayswaggersample

import akka.actor.{Actor, ActorSystem, Props, ActorLogging}
import spray.routing._
import com.gettyimages.spray.swagger._
import scala.reflect.runtime.universe._
import com.wordnik.swagger.model.ApiInfo

class SampleServiceActor
  extends HttpServiceActor
  with ActorLogging {

    override def actorRefFactory = context

    val todolist = new TodoListHttpService {
      def actorRefFactory = context
    }

    //val todolist2 = new TodoListHttpService2 {
    //  def actorRefFactory = context
    //}
    
    def receive = runRoute(todolist.routes ~ swaggerService.routes ~
      get {
        pathPrefix("swagger") { pathEndOrSingleSlash {
            getFromResource("swagger-ui/index.html")
          }
        } ~
        getFromResourceDirectory("swagger-ui")
      } ~
      get {
        pathPrefix("") { pathEndOrSingleSlash {
            getFromResource("angular-ui/index.html")
          }
        } ~
        getFromResourceDirectory("angular-ui")
      })

  val swaggerService = new SwaggerHttpService {
    override def apiTypes = Seq(typeOf[TodoListHttpService]) //, typeOf[TodoListHttpService2], ...
    override def apiVersion = "2.0"
    override def baseUrl = "http://localhost:8080"
    override def docsPath = "api-docs"
    override def actorRefFactory = context
    override def apiInfo = Some(new ApiInfo("Spray-Swagger Sample", "A sample todo list service using spray, spray-swagger and anglar-js", "TOC Url", "Michael Hamrah @mhamrah", "Apache V2", "http://www.apache.org/licenses/LICENSE-2.0"))

    //authorizations, not used
  }
}
