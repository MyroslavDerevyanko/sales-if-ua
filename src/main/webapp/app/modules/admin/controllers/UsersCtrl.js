admin.controller('UsersCtrl', ['$scope', '$http', function ($scope, $http) {
    $http.get('/Practice/user/client').then(function (response) {
        $scope.users = response.data;
        if($scope.users[index].isBlocked == false){
            $scope.users[index].isBlocked = "active";
        }else $scope.users[index].isBlocked = "blocked";
    });
    $scope.deleteAlert = function(index){
        if(confirm('You want to delete user:'+$scope.users[index].firstName+' '+$scope.users[index].lastName)){
            $http.get('/Practice/user/delete/'+$scope.users[index].id);
            $http.get('/Practice/user/client').then(function (response) {
                $scope.users = response.data;})
        }

    };
    $scope.changeStatusBlocked = function (index) {
        $scope.users[index].isBlocked = "blocked";
    };
    $scope.changeStatusActive = function (index) {
        $scope.users[index].isBlocked = "active";
    };
    $scope.btnBlockName = "block";
    $scope.showMessage = function (status) {
        if (status == false){return "block";}
        else
            return "unblock";
    }
    $scope.changeButtonName = function (index) {
        if ($scope.users[index].status == 'blocked') {
            $scope.btnBlockName = 'unblock';
            $scope.users[index].status = "active";
        }
        else {
            $scope.btnBlockName = 'block';
            $scope.users[index].status = "blocked";
        }
    }
    $scope.getClass = function (status) {
        if (status == "blocked")
            return {
                'fa-unlock': true
            }
        else
            return {
                'fa-lock': true
            };
    }
    $scope.getRowStatus = function (status) {
        if (status == "blocked")
            return {
                'adm-PR1': true
            }
        else
            return {
                'adm-PR1': false
            };
    }
}]);