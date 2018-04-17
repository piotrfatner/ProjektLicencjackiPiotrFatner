app.controller('userController', function($scope,$http,$window,$location) {
    $scope.logout = function () {
        $window.sessionStorage.setItem('userInfo-token',undefined);
        $window.sessionStorage.setItem('userInfo-userId',undefined);
        $location.path("/");
    }
});