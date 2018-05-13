app.controller('doctorHomeController', function ($scope, $http, $window, $location, uiCalendarConfig) {
    $scope.treatmentsScheduleForDoctor = [];
    $scope.eventSources = [$scope.treatmentsScheduleForDoctor];

    $scope.getReservedTreatmentsScheduleForDoctor = function () {
        $http.get("/getReservedTreatmentsScheduleForDoctor/" + $window.sessionStorage.getItem('userInfo-userId') + "/" + $window.sessionStorage.getItem('userInfo-token'))
            .then(function (response) {
                $scope.treatmentsScheduleForDoctor.slice(0, $scope.treatmentsScheduleForDoctor.length);
                angular.forEach(response.data, function (value) {
                    $scope.treatmentsScheduleForDoctor.push({
                        title: value.title,
                        id: value.id,
                        start: value.start,
                        end: value.end,
                        stick: true
                    })
                });
                console.log($scope.treatmentsScheduleForDoctor);

            });
    };

    $scope.getReservedTreatmentsScheduleForDoctor();

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
            editable: false,
            stick: true,
            header: {
                left: 'month,agendaWeek,agendaDay',
                center: 'title',
                right: 'prev,next today'
            },
            eventClick: $scope.alertOnEventClick,
            eventRender: function (eventObj, $el) {
                $el.popover({
                    title: eventObj.title,
                    trigger: 'hover',
                    placement: 'bottom',
                    container: 'body'
                });
            },
            eventDrop: $scope.alertOnDrop,
            eventResize: $scope.alertOnResize,
            // Select options
            selectable: false,
            selectHelper: true,
            unselectAuto: true
        }
    };
});