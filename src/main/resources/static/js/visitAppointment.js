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
        $scope.treatmentsSchedules.splice(0,$scope.treatmentsSchedules.length);
        $scope.selectedDoctor = doctorId;
        console.log("selectedDoctor:");
        console.log($scope.selectedDoctor);
        console.log($scope.treatmentsSchedules);
        $scope.getTreatmentsForDoctor(doctorId);
    };

    $scope.getAvailableDoctorsForTreatment();

    $scope.uiConfig = {
        calendar: {
            defaultView: 'agendaWeek',

            minTime: "08:00:00",
            maxTime: "19:00:00",
            weekends: false,
            height: 400,
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
            selectHelper: true,
            unselectAuto: true,
            select: function (start, end, moment) {
                var title = prompt('Event Title:');
                var eventData;
                console.log("another:");
                console.log(moment);
                if (title) {
                    eventData = {
                        notes: title,
                        treatmentDate: start._d,
                        userId: $window.sessionStorage.getItem('userInfo-userId'),
                        doctorId: $scope.selectedDoctor,
                        treatmentId: $scope.treatmentId,
                        token: $window.sessionStorage.getItem('userInfo-token')
                    };
                    $scope.addEvent(eventData);
                }
            }
        }
    };
    $scope.addEvent = function (eventData) {
        $http.post('/postVisit', eventData).then(
            function (response) {
                console.log(response.data);
                $window.location.reload();
            }, function (error) {
                console.log(error)
            }
        );
    }

});
