app.controller('userController', function($scope,$http,$window,$location) {
    $scope.logout = function () {
        console.log("logoutAction!");
        $window.sessionStorage.setItem('userInfo-token',undefined);
        $window.sessionStorage.setItem('userInfo-userId',undefined);
        $location.path("/");
    };
    $scope.treatmentsForUser=[];
    $scope.treatmentsHistoryForUser=[];
    $scope.getTreatmentsForUser = function () {
        $http.get("/getTreatmentsForUser/"+$window.sessionStorage.getItem('userInfo-userId')+"/"+$window.sessionStorage.getItem('userInfo-token'))
            .then(function (response) {
                console.log(response.data);
                $scope.treatmentsForUser = response.data;
                console.log($scope.treatmentsForUser);
            }).catch(function (reason) {  });

    };

    $scope.getTreatmentsHistoryForUser = function () {
        $http.get("/getTreatmentsHistoryForUser/"+$window.sessionStorage.getItem('userInfo-userId')+"/"+$window.sessionStorage.getItem('userInfo-token'))
            .then(function (response) {
                $scope.treatmentsHistoryForUser = response.data;
            }).catch(function (reason) {  });

    };
});