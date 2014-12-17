todoApp.controller('TestCtrl', function ($scope, TodoService) {
  
  $scope.todos = [
      {text:'learn angular', done:true},
      {text:'build an angular app', done:false}];
 
  $scope.updateTodo = function() {
      $scope.todos.push({text:$scope.todoText, done:false});
      $scope.todoText = '';
  };

});

todoApp.service('TodoService', function() {

    var lastLoginResult = "";
    var lastLoginFailed = false;

});
