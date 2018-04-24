app.controller('userController', function($scope,$http,$window,$location) {
    $scope.logout = function () {
        console.log("logoutAction!");
        $window.sessionStorage.setItem('userInfo-token',undefined);
        $window.sessionStorage.setItem('userInfo-userId',undefined);
        $location.path("/");
    };
    $scope.treatmentsForUser=[];
    $scope.getTreatmentsForUser = function () {
        $http.get("/getTreatmentsForUser/"+$window.sessionStorage.getItem('userInfo-userId'))
            .then(function (response) {
                console.log(response.data);
                $scope.treatmentsForUser = response.data;

            }).catch(function (reason) {  });
    }
});