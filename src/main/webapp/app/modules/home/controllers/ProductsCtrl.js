home.controller('ProductsCtrl', ['$scope', '$http', '$location', function($scope, $http, $location){
	$scope.goods=[];
	// Fetching data from server
	// $http.get('http://localhost:8080/Practice/goods/search?name=Sony').then(function(response){
	// 	$scope.goods = response.data;
	// });

	// $http.get('http://');

	$scope.inputData = $location.search().name;

	$http.get('http://localhost:8080/Practice/goods/search?name='+$location.search().name).then(function(response){
		$scope.goods = response.data;
	});
}]);