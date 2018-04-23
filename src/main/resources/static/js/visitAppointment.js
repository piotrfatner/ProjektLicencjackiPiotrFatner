app.controller('visitAppointmentController', function($scope,$http,$window,$location,uiCalendarConfig) {
    $scope.today = new Date();
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    $scope.calEventsPers = {
        id: 0,
        visible: true,
        className: ['fc-id-0'],
        events: [
            { id: 324, title: 'All Day Event', start: new Date(y, m, 1) },
            { title: 'Long Event', start: new Date(y, m, d - 5), end: new Date(y, m, d - 2) },
            { id: 999, title: 'Repeating Event', start: new Date(y, m, d - 3, 16, 0), allDay: false },
            { id: 999, title: 'Repeating Event', start: new Date(y, m, d + 4, 16, 0), allDay: false },
            { title: 'test123', start: new Date(2018, 3, 24, 10, 30, 0, 0), end: new Date(2018, 3, 24, 11, 30, 0, 0)},
            { title: 'Birthday Party', start: new Date(y, m, d + 1, 19, 0), end: new Date(y, m, d + 1, 22, 30), allDay: false },
            { title: 'Click for Google', start: new Date(y, m, 28), end: new Date(y, m, 29), url: 'http://google.com/' }
        ]
    };
    $scope.eventSources = [$scope.calEventsPers];

    $scope.uiConfig = {
        calendar: {
            defaultView:'agendaWeek',

            minTime: "08:00:00",
            maxTime: "17:00:00",
            weekends: false,
            height: 400,
            firstDay:1,
            locale: 'pl',
            lang: 'pl',
            timezone:false,
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
                        start: new Date(2018, 4, 23, 10, 33, 0, 0),
                        end: new Date(2018, 4, 23, 10, 33, 30, 0)
                    };
                    $scope.addEvent(eventData);
                }
            }
        }          };
    $scope.addEvent = function (eventData) {
        console.log(eventData);
        $scope.calEventsPers.events.push(eventData);
        console.log($scope.calEventsPers.events);
    }

});
