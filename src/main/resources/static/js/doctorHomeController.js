app.controller('doctorHomeController', function ($scope, $http, $window, $location, uiCalendarConfig) {
    $scope.eventSources =[];

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
                left: 'month,agendaWeek,agendaDay',
                center: 'title',
                right: 'prev,next today'
            },
            eventClick: $scope.alertOnEventClick,
            eventDrop: $scope.alertOnDrop,
            eventResize: $scope.alertOnResize,
            // Select options
            selectable: false,
            selectHelper: true,
            unselectAuto: true
        }
    };
});