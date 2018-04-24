app.controller('visitAppointmentController', function ($scope, $http, $window, $location, uiCalendarConfig) {
    $scope.today = new Date();
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    $scope.treatmentsSchedules = [];
    $scope.eventSources = [$scope.treatmentsSchedules];
    $http.get("/getTreatmentsSchedule/"+$window.sessionStorage.getItem('userInfo-userId'))
        .then(function (response) {
            $scope.treatmentsSchedules.slice(0, $scope.treatmentsSchedules.length);
            angular.forEach(response.data, function (value) {
               $scope.treatmentsSchedules.push({
                   title:value.title,
                   id: value.id,
                   start: value.start,
                   end:value.end
               })
            });
            console.log($scope.treatmentsSchedules);

        });

    $scope.uiConfig = {
        calendar: {
            /*defaultView:'agendaWeek',*/

            /*minTime: "08:00:00",
            maxTime: "17:00:00",*/
            weekends: false,
            height: 400,
            firstDay: 1,
            locale: 'pl',
            lang: 'pl',
            timezone: false,
            editable: true,
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
                console.log("start:");
                console.log(start._d.getUTCDate());
                console.log("end:");
                console.log(end);
                console.log("another:");
                console.log(moment);
                if (title) {
                    eventData = {
                        title: title,
                        start: start._d,
                        end: end._d
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
            }, function (error) { console.log(error) }
        );
    }

});
