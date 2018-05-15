app.controller('loginController', function ($scope, $http, $window, $location) {
    $scope.message = 'Contact us! JK. This is just a demo.';
    var self = this;
    $scope.login = function () {
        console.log($scope.username + "   " + $scope.password);
        self.encryptedPassword = $scope.sha1($scope.password);
        console.log(self.encryptedPassword);
        self.loginDto = {
            username: $scope.username,
            password: self.encryptedPassword
        };
        $http.post('/loginAction', self.loginDto).then(
            function (response) {
                console.log(response.data);
                $window.sessionStorage.setItem('userInfo-token', response.data.token);
                $window.sessionStorage.setItem('userInfo-userId', response.data.userId);
                $window.sessionStorage.setItem('userInfo-isDoctor', response.data.isDoctor);
                $location.path(response.data.redirectSide);
            }, function (error) {
                console.log(error);
                $scope.invalidCredentials = "Błąd logowania! Niepoprawny login lub hasło!";
            }
        );
    };


    $scope.validatePassword = function () {
        console.log($scope.passwordReg);
        console.log($scope.passwordRepeatReg);
        if ($scope.passwordReg != $scope.passwordRepeatReg) {
            $scope.differentPasswords = true;
            return false;
        }
        $scope.differentPasswords = false;
        return true;
    };

    $scope.register = function () {
        if ($scope.validatePassword()) {
            //doSomething
            console.log("I am doing reg!");
            self.encryptedPasswordReg = $scope.sha1($scope.passwordReg);
            self.registrationDto = {
                username: $scope.loginReg,
                firstName: $scope.firstNameReg,
                lastName: $scope.lastNameReg,
                email: $scope.emailReg,
                password: self.encryptedPasswordReg
            };
            $http.post('/registerAction', self.registrationDto).then(
                function (response) {
                    $scope.modalBodyRegister = "Rejestracja przebiegła pomyślnie!";
                    angular.element("#myModal").modal("show");
                }, function (error) {
                    console.log(error);
                    $scope.modalBodyRegister = "Rejestracja nie udała się!";
                    angular.element("#myModal").modal("show");
                }
            );
        }
        console.log("Do nothing!");
    };

    $scope.refresh = function () {
      $location.path("/login");
    };

    $scope.sha1 = function (msg) {
        if (msg != undefined) {
            function rotl(n, s) {
                return n << s | n >>> 32 - s;
            };
            function tohex(i) {
                for (var h = "", s = 28; ; s -= 4) {
                    h += (i >>> s & 0xf).toString(16);
                    if (!s) return h;
                }
            };
            var H0 = 0x67452301, H1 = 0xEFCDAB89, H2 = 0x98BADCFE, H3 = 0x10325476, H4 = 0xC3D2E1F0, M = 0x0ffffffff;
            var i, t, W = new Array(80), ml = msg.length, wa = new Array();
            msg += String.fromCharCode(0x80);
            while (msg.length % 4) msg += String.fromCharCode(0);
            for (i = 0; i < msg.length; i += 4) wa.push(msg.charCodeAt(i) << 24 | msg.charCodeAt(i + 1) << 16 | msg.charCodeAt(i + 2) << 8 | msg.charCodeAt(i + 3));
            while (wa.length % 16 != 14) wa.push(0);
            wa.push(ml >>> 29), wa.push((ml << 3) & M);
            for (var bo = 0; bo < wa.length; bo += 16) {
                for (i = 0; i < 16; i++) W[i] = wa[bo + i];
                for (i = 16; i <= 79; i++) W[i] = rotl(W[i - 3] ^ W[i - 8] ^ W[i - 14] ^ W[i - 16], 1);
                var A = H0, B = H1, C = H2, D = H3, E = H4;
                for (i = 0; i <= 19; i++) t = (rotl(A, 5) + (B & C | ~B & D) + E + W[i] + 0x5A827999) & M, E = D, D = C, C = rotl(B, 30), B = A, A = t;
                for (i = 20; i <= 39; i++) t = (rotl(A, 5) + (B ^ C ^ D) + E + W[i] + 0x6ED9EBA1) & M, E = D, D = C, C = rotl(B, 30), B = A, A = t;
                for (i = 40; i <= 59; i++) t = (rotl(A, 5) + (B & C | B & D | C & D) + E + W[i] + 0x8F1BBCDC) & M, E = D, D = C, C = rotl(B, 30), B = A, A = t;
                for (i = 60; i <= 79; i++) t = (rotl(A, 5) + (B ^ C ^ D) + E + W[i] + 0xCA62C1D6) & M, E = D, D = C, C = rotl(B, 30), B = A, A = t;
                H0 = H0 + A & M;
                H1 = H1 + B & M;
                H2 = H2 + C & M;
                H3 = H3 + D & M;
                H4 = H4 + E & M;
            }
            return tohex(H0) + tohex(H1) + tohex(H2) + tohex(H3) + tohex(H4);
        }
    }
});