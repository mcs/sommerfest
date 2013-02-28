"use script";

angular.module('fitness', []);

function PlanController($scope) {
    var days, i, day, rounds, roundCount;

    // create some test data; should be fetched from a resource
    days = [];
    for (i = 1; i <= 5; i++) {
        day = {date: i + ".1.2013"};
        days.push(day);
        rounds = [];
        for (roundCount = 0; roundCount < 3; roundCount += 1) {
            rounds.push({value: (roundCount / 2 + i)});
        }
        day.exercises = [
            {name: ("Exercise " + i), rounds: rounds}
        ];
    }

    $scope.days = days;
    $scope.exercises = days[0].exercises;
}