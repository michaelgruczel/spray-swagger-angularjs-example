package com.mlh.sprayswaggersample

import com.wordnik.swagger.annotations._
import javax.ws.rs.Path
import spray.routing.HttpService
import spray.json._
import spray.json.DefaultJsonProtocol._
import org.json4s.JsonAST.JObject

// check more here https://github.com/mhamrah/spray-swagger-sample/blob/master/src/main/scala/PetHttpService.scala
@Api(value = "/todo", description = "Operations about todos.", position = 0)
trait TodoListHttpService extends HttpService {

  import Json4sSupport._

  val routes = readRoute ~ updateRoute ~ deleteRoute ~ listRoute

  var todos = scala.collection.mutable.Map[String, Todo]();

  @ApiOperation(value = "Find a todo list entry by ID", notes = "Returns a todo based on ID", httpMethod = "GET", response = classOf[Todo])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "todoId", value = "ID of todo that needs to be fetched", required = true, dataType = "integer", paramType = "path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "Todo not found"),
    new ApiResponse(code = 400, message = "Invalid ID supplied")
  ))
  def readRoute = get { path("todo" / Segment) { id =>
    //complete(Todo(id, "clean up"))
    complete(todos.get(id))
  }}

  @ApiOperation(value = "Adds or updates a todo with form data.", notes = "", nickname = "updateTodoWithForm", httpMethod = "POST", consumes="application/json, application/vnd.custom.Todo")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value = "Updated content of the todo.", dataType = "Todo", required = true, paramType="body")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 405, message = "Invalid input")
  ))
  def updateRoute = post {
    entity(as[JObject]) { someObject =>
          val aTodo = someObject.extract[Todo]
          todos.put(aTodo.id , aTodo)
          complete(someObject)
    } 
  }

  @ApiOperation(value = "Deletes a Todo", nickname="deleteTodo", httpMethod = "DELETE")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "todoId", value = "Todo id to delete", required = true, dataType="string", paramType="path")
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 400, message = "Invalid todo id")
  ))
  def deleteRoute = delete { 
    path("todo" / Segment) { 
      id => {
        todos.remove(id)
        complete(s"Deleted $id") 
      }
    } 
  }

  @ApiOperation(value = "Find all todo list entries", notes = "Returns all todos", httpMethod = "GET", response = classOf[List[Todo]])
  def listRoute = get { path("todo") {
    complete(todos.toList)
    
  }}
  
}

@ApiModel(description = "A Todo object")
case class Todo(id: String, content: String)

