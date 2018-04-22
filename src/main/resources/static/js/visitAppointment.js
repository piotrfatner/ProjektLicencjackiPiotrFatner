app.controller('visitAppointmentController', function($scope,$http,$window,$location) {
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
            { title: 'Birthday Party', start: new Date(y, m, d + 1, 19, 0), end: new Date(y, m, d + 1, 22, 30), allDay: false },
            { title: 'Click for Google', start: new Date(y, m, 28), end: new Date(y, m, 29), url: 'http://google.com/' }
        ]
    };
    $scope.eventSources = [$scope.calEventsPers];

    $scope.uiConfig = {
        calendar: {
            height: 400,
            firstDay:1,
            locale: 'pl',
            lang: 'pl',
            editable: true,
            header: {
                left: 'month,basicWeek,basicDay',
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
            select: function (start, end) {
                var title = prompt('Event Title:');
                var eventData;
                if (title) {
                    eventData = {
                        title: title,
                        start: start.format(),
                        end: end.format()
                    };
                    $scope.addEvent(eventData);
                }
            }
        }          };

});
