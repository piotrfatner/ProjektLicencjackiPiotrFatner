app.controller('homeController', function($scope,$window,$location) {
    // create a message to display in our view
    var rotator;
    var rotatedImageId = 'rotator';
    var images = ['/images/image1.jpg','/images/image2.jpg','/images/image3.jpg'];
    var num =0;
    $scope.setScope = function () {
        $scope.isLoggedAction();
        $scope.isDoctorAction();
    };

    $scope.goToVisitAppointment = function (treatmentId) {
        $location.path("/visitAppointment/"+treatmentId);
    };

    $scope.rotateImages = function () {
        if (!rotator)
            rotator = document.getElementById(rotatedImageId);
        if (!rotator)
            return;

        var len = images.length;
        rotator.src = images[num++];
        if (num == len) {
            num = 0;
        }
    };

    $scope.isLoggedAction = function () {
        if($window.sessionStorage.getItem('userInfo-token') != null && $window.sessionStorage.getItem('userInfo-token').length == 40){
            return true;
        }
        return false;
    };

    $scope.isDoctorAction = function () {
        if($window.sessionStorage.getItem('userInfo-isDoctor') != null && $window.sessionStorage.getItem('userInfo-isDoctor') == 'true'){
            return true;
        }
        return false;
    };

    $scope.setScope();

    window.setInterval(function() {$scope.rotateImages();},5000);
});