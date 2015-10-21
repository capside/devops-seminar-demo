+function ($) {
    var $queryCommand = $('#queryCommand');
    var $resultsInfo = $('#results-info');
    var $resultsData = $('#results-data');

    var $firstTeamInput = $('#firstTeamInput');
    var $firstTeamName = $('#firstTeamName');
    var $firstTeamWins = $('#firstTeamWins');
    var $secondTeamInput = $('#secondTeamInput');
    var $secondTeamName = $('#secondTeamName');
    var $secondTeamWins = $('#secondTeamWins');
    var $draws = $('#draws');

    function retrieveStatistics(firstTeam, secondTeam) {
        var url = '/games/stats/{first}-vs-{second}';
        url = url.replace('{first}', firstTeam)
                 .replace('{second}', secondTeam);
        var promise = $.ajax({
            dataType: "json",
            url: url
        }).done(function (stats) {
        }).fail(function (error) {
            console.error('Error:', error);
        });
        return promise;
    }
    
    function showStats(stats) {
        $resultsInfo.hide();
        $firstTeamName.text(stats.firstTeam);
        $firstTeamWins.text(stats.firstTeamWins);
        $secondTeamName.text(stats.secondTeam);
        $secondTeamWins.text(stats.secondTeamWins);
        $draws.text(stats.draws);
        $resultsData.show();
    }
    
    function showInfo(message) {
        $resultsData.hide();
        $resultsInfo.find('h2').text(message);
        $resultsInfo.show();
    }
    
    function executeQuery() {
        showInfo('Cargando datos.');
        var promise = retrieveStatistics($firstTeamInput.val(), $secondTeamInput.val());
        promise.done(function(response) {
            if (response === null) {
                showInfo('La propuesta no encontró resultados.');
            } else {
                showStats(response.statistics);
            }
        }).fail(function(error) {
            var message;
            if (error.responseJSON === undefined) {
                message = 'Error recuperando datos.';
            } else if (error.status === 404) {
                message = 'No se encuentra ningún match para estos equipos.';
            } else {
                message = error.responseJSON.message;
            }
            showInfo(message);
        });                
    }
    
    $(document).ready(function() {
        $queryCommand.click(function(evt) {
            executeQuery();
        });
    });

}(jQuery);