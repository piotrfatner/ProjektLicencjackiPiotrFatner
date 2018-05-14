app.controller('visitAppointmentController', function ($scope, $http, $window, $location, $routeParams, uiCalendarConfig) {
    $scope.today = new Date();
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    $scope.treatmentId = $routeParams.treatmentId;

    $scope.avaliableDoctors = [];
    $scope.treatmentsSchedules = [];
    $scope.eventSources = [$scope.treatmentsSchedules];
    $scope.selectedDoctor = "";
    $scope.selectedTreatment = [];
    $scope.selectedVisitDate = "";

    $scope.getSelectedTreatment = function () {
        $http.get("/getTreatment/" + $scope.treatmentId + "/" + $window.sessionStorage.getItem('userInfo-token'))
            .then(function (response) {
                $scope.selectedTreatment = response.data;
            }).catch(function (reason) {
        });
    }

    $scope.getTreatmentsForDoctor = function (doctorId) {
        $http.get("/getTreatmentsSchedule/" + doctorId + "/" + $window.sessionStorage.getItem('userInfo-token'))
            .then(function (response) {
                $scope.treatmentsSchedules.slice(0, $scope.treatmentsSchedules.length);
                angular.forEach(response.data, function (value) {
                    $scope.treatmentsSchedules.push({
                        title: value.title,
                        id: value.id,
                        start: value.start,
                        end: value.end,
                        stick: true
                    })
                });
                console.log($scope.treatmentsSchedules);

            });

    };

    $scope.getAvailableDoctorsForTreatment = function () {
        console.log($scope.treatmentId);
        $http.get("/getAvailableDoctors/" + $scope.treatmentId + "/" + $window.sessionStorage.getItem('userInfo-token'))
            .then(function (response) {
                $scope.avaliableDoctors = response.data;
                console.log(response.data);
                console.log($scope.avaliableDoctors[0].doctorId);
                $scope.selectedDoctor = $scope.avaliableDoctors[0].doctorId;
                $scope.getTreatmentsForDoctor($scope.avaliableDoctors[0].doctorId);

            }).catch(function (reason) {
        });
    };

    $scope.changeDoctor = function (doctorId) {
        console.log("new doctor:");
        console.log(doctorId);
        $scope.treatmentsSchedules.splice(0, $scope.treatmentsSchedules.length);
        $scope.selectedDoctor = doctorId;
        console.log("selectedDoctor:");
        console.log($scope.selectedDoctor);
        console.log($scope.treatmentsSchedules);
        $scope.getTreatmentsForDoctor(doctorId);
    };

    $scope.getAvailableDoctorsForTreatment();
    $scope.getSelectedTreatment();

    $scope.uiConfig = {
        calendar: {
            defaultView: 'agendaWeek',

            minTime: "08:00:00",
            maxTime: "18:00:00",
            weekends: false,
            height: 600,
            firstDay: 1,
            locale: 'pl',
            lang: 'pl',
            timezone: 'local',
            editable: true,
            stick: true,
            header: {
                left: 'agendaWeek,agendaDay',
                center: 'title',
                right: 'prev,next today'
            },
            eventClick: $scope.alertOnEventClick,
            eventDrop: $scope.alertOnDrop,
            eventResize: $scope.alertOnResize,
            // Select options
            selectable: true,
            selectConstraint: {
                start: $.fullCalendar.moment().subtract(1, 'hours'),
                end: $.fullCalendar.moment().startOf('month').add(3, 'month')
            },
            selectHelper: true,
            unselectAuto: true,
            select: function (start, end, moment) {
                console.log("select!");
                $scope.modalBody1 = "Umów wizytę na :" + $scope.selectedTreatment.treatmentName;
                $scope.selectedVisitDateUI = start._d;
                $scope.modalBody2 = "Data: ";
                angular.element("#myModal").modal("show");
                $scope.selectedVisitDate = start._d;
            }
        }
    };

    $scope.addEvent = function () {
        self.eventData = {
            notes: $scope.visitDetails,
            treatmentDate: $scope.selectedVisitDate,
            userId: $window.sessionStorage.getItem('userInfo-userId'),
            doctorId: $scope.selectedDoctor,
            treatmentId: $scope.treatmentId,
            token: $window.sessionStorage.getItem('userInfo-token')
        };
        console.log(eventData);
        $http.post('/postVisit', self.eventData).then(
            function (response) {
                $scope.treatmentsSchedules.splice(0, $scope.treatmentsSchedules.length);
                $scope.getTreatmentsForDoctor($scope.selectedDoctor);
                angular.element("#myModal").modal("hide");
            }, function (error) {
                angular.element("#myModal").modal("hide");
                console.log(error)
            }
        );
    };

    $scope.clearModal = function () {
        console.log($scope.visitDetails);
        $scope.visitDetails = "";
        console.log($scope.visitDetails);
    };

});
