app.controller('userController', function ($scope, $http, $window, $location) {
    $scope.logout = function () {
        console.log("logoutAction!");
        $window.sessionStorage.setItem('userInfo-token', undefined);
        $window.sessionStorage.setItem('userInfo-userId', undefined);
        $location.path("/");
    };
    $scope.treatmentsForUser = [];
    $scope.treatmentsHistoryForUser = [];
    $scope.reservedTreatmentIdToCancel = "";
    $scope.getTreatmentsForUser = function () {
        $http.get("/getTreatmentsForUser/" + $window.sessionStorage.getItem('userInfo-userId') + "/" + $window.sessionStorage.getItem('userInfo-token'))
            .then(function (response) {
                console.log(response.data);
                $scope.treatmentsForUser = response.data;
                console.log($scope.treatmentsForUser);
            }).catch(function (reason) {
        });

    };

    $scope.getTreatmentsHistoryForUser = function () {
        $http.get("/getTreatmentsHistoryForUser/" + $window.sessionStorage.getItem('userInfo-userId') + "/" + $window.sessionStorage.getItem('userInfo-token'))
            .then(function (response) {
                $scope.treatmentsHistoryForUser = response.data;
            }).catch(function (reason) {
        });

    };

    $scope.showModalOnCancel = function (reservedMeetingId) {
        angular.element("#myModal").modal("show");
        $scope.reservedTreatmentIdToCancel = reservedMeetingId;
    };

    $scope.cancelReservedTreatment = function () {
        $http.post('/cancelReservedTreatment/' + $scope.reservedTreatmentIdToCancel, {token: $window.sessionStorage.getItem('userInfo-token')}).then(function (response) {
            angular.element("#myModal").modal("hide");
            $scope.reservedTreatmentIdToCancel="";
            $scope.getTreatmentsForUser();
        }).catch(function (reason) {
            $scope.reservedTreatmentIdToCancel="";
        });
    };

    $scope.doNotCancel = function () {
        $scope.reservedTreatmentIdToCancel="";
    };
});