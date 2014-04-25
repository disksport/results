window.applicationContextPath = window.applicationContextPath || "";
var mocks = parent.parent ? parent.parent.mocks : parent.mocks;
mocks = mocks || [];
var dependencies = mocks.concat(["ngResource"]);
var app = angular.module("ResultsApp", dependencies);

if (undefined != mocks.initializeMocks) {
    app.run(mocks.initializeMocks);
}

app.controller("PlayerCtrl", function ($scope, PlayerDAO)
{
    var EDIT_MODE = "edit";
    var mode;

    function refresh()
    {
        PlayerDAO.query(function (data)
        {
            $scope.players = data;
        });
    }

    $scope.isEditPlayerMode = function ()
    {
        return EDIT_MODE === mode;
    };

    $scope.addPlayer = function ()
    {
        $scope.selectedPlayer = {};
        mode = EDIT_MODE;
    };

    $scope.cancel = function ()
    {
        mode = undefined;
    };

    $scope.save = function ()
    {
        PlayerDAO.save($scope.selectedPlayer, refresh);
        mode = undefined;
    };

    $scope.remove = function (player)
    {
        PlayerDAO.remove({id: player.id}, null, refresh);
    };

    refresh();
});

app.factory("PlayerDAO", function ($resource)
{
    return $resource(window.applicationContextPath + "/rest/player/:id", {id: "@id"});
});
