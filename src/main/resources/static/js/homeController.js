app.controller('homeController', function($scope,$window) {
    // create a message to display in our view
    var rotator;
    var rotatedImageId = 'rotator';
    var images = ['/images/image1.jpg','/images/image2.jpg'];
    var num =0;
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

    window.setInterval(function() {$scope.rotateImages();},5000);
});