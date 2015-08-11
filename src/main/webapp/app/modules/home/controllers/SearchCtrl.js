home.controller('SearchCtrl', ['$scope', '$http', '$location', function($scope, $http, $location){
	$scope.searchData = "";

	$scope.searchProduct = function(){
		// alert("Hi");
		$location.path('/stuff').search({name: $scope.searchData});	
	};
}]);