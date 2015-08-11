goods.controller('OverviewCtrl', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams){
	$scope.id = $routeParams.goodId;
	$scope.product = {};
	// fetching data for 1 good
	$http.get('http://localhost:8080/Practice/goods?id='+$scope.id).then(function(response){
		$scope.product = response.data;
		console.log($scope.product);
	});
}]);