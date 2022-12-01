 let host = "http://localhost:8080/rest";
app.controller("account-ctrl",function($scope,$http){
	$scope.form={};
	$scope.items={};
	$scope.create = function(){
		
  	var item =
    [{
        "username": "12222",
        "password": "1",
        "fullname": "1",
        "email": "1",
        "photo": "123"
    }]
       var url = `${host}/accounts`;
             var url = `${host}/accounts`;
            $http.post(url,item).then(resp => {
                $scope.items.push(item)
	})
	
	}
})
 
 
 
 
 




