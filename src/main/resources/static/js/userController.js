app.controller('userController', function($scope,$http,$window,$location) {
    $scope.logout = function () {
        console.log("logoutAction!");
        $window.sessionStorage.setItem('userInfo-token',undefined);
        $window.sessionStorage.setItem('userInfo-userId',undefined);
        $location.path("/");
    };
});