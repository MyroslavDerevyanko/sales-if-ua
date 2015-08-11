admin.controller('HighchartsCtrl', ['$scope', '$http', function ($scope, $http) {
    $scope.checkbox1 = '';
    $scope.checkbox2 = '';
    $scope.checkbox3 = '';
    $scope.checkbox4 = '';
    $scope.date1 = '';
    $scope.date2 = '';
    $scope.getdate = '';
    //Highcharts

    //Highcharts end
    $scope.getAddressAll = function () {
        $scope.getdate = '/Practice/analytics/all';
        return $scope.getdate;
    };
    $scope.getAddress = function () {
        var dateR = $scope.date2.getTime()+ 86400000;
        $scope.getdate = '/Practice/analytics/byPeriod?from=' + $scope.date1.getTime() + '&to=' + dateR;
        return $scope.getdate;
    };
    $scope.buildChart = function (getdate) {

        $http.get(getdate).then(function (response) {
            $scope.data = [];
            $scope.date = [];
            $scope.time = [];
            $scope.month = [];
            $scope.cost = [];
            $scope.users = [];
            $scope.shops = [];
            $scope.products = [];
            $scope.data = response.data;
            for (var i = 0; i < $scope.data.length; i++) {
                var time = $scope.data[i].date;
                var date = new Date(time);
                var month = date.getMonth() + 1;
                $scope.date[i] = date.getDate() + "/" + month + "/" + date.getFullYear();
                //get date with hour
                //$scope.date[i] = new Date($scope.data[i].date);
                $scope.cost[i] = $scope.data[i].cost;
                $scope.users[i] = $scope.data[i].usersAmount;
                $scope.shops[i] = $scope.data[i].shopsAmount;
                $scope.products[i] = $scope.data[i].soldGoods;
            }
            //console.log($scope.date);
            $(function () {
                $('#container').highcharts({
                    chart: {
                        type: 'area',
                        zoomType: 'x'
                    },
                    title: {
                        text: 'Users statistics'
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'left',
                        verticalAlign: 'top',
                        x: 150,
                        y: 100,
                        floating: true,
                        borderWidth: 1,
                        backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
                    },
                    xAxis: {
                        categories: $scope.date
                    },
                    yAxis: {
                        title: {
                            text: 'new members'
                        }
                    },
                    tooltip: {
                        shared: true,
                        valueSuffix: ''
                    },
                    credits: {
                        enabled: false
                    },
                    plotOptions: {
                        areaspline: {
                            fillOpacity: 0.5
                        }
                    },
                    series: [{
                        name: 'New Users',
                        data: $scope.users,
                        //Chart line width
                        lineWidth: 1,
                        //Points selection
                        allowPointSelect: true
                    }, {
                        name: 'New Shops',
                        data: $scope.shops
                    }]
                });
            });
            //Second chart witch shows money transactions
            $(function () {
                $('#money-transactions').highcharts({
                    chart: {
                        zoomType: 'x'
                    },
                    title: {
                        text: 'Money transactions'
                    },
                    subtitle: {
                        text: document.ontouchstart === undefined ?
                            'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
                    },
                    xAxis: {
                        categories: $scope.date
                    },
                    yAxis: {
                        title: {
                            text: 'Transactions rate'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    credits: {
                        enabled: false
                    },
                    plotOptions: {
                        area: {
                            fillColor: {
                                linearGradient: {
                                    x1: 0,
                                    y1: 0,
                                    x2: 0,
                                    y2: 1
                                },
                                stops: [
                                    [0, Highcharts.getOptions().colors[0]],
                                    [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                                ]
                            },
                            marker: {
                                radius: 2
                            },
                            lineWidth: 1,
                            states: {
                                hover: {
                                    lineWidth: 1
                                }
                            },
                            threshold: null
                        }
                    },

                    series: [{
                        type: 'area',
                        name: 'Summary amount',
                        data: $scope.cost
                    }]
                });
            });
            //third hichchart
            $(function () {
                $('#products').highcharts({
                    chart: {
                        zoomType: 'x'
                    },
                    title: {
                        text: 'Products sold'
                    },
                    subtitle: {
                        text: document.ontouchstart === undefined ?
                            'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
                    },
                    xAxis: {
                        categories: $scope.date
                    },
                    yAxis: {
                        title: {
                            text: 'Sales rate'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    credits: {
                        enabled: false
                    },
                    plotOptions: {
                        area: {
                            fillColor: {
                                linearGradient: {
                                    x1: 0,
                                    y1: 0,
                                    x2: 0,
                                    y2: 1
                                },
                                stops: [
                                    [0, Highcharts.getOptions().colors[0]],
                                    [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                                ]
                            },
                            marker: {
                                radius: 2
                            },
                            lineWidth: 1,
                            states: {
                                hover: {
                                    lineWidth: 1
                                }
                            },
                            threshold: null
                        }
                    },

                    series: [{
                        type: 'area',
                        name: 'Summary amount',
                        data: $scope.products
                    }]
                });
            });
        })

    };
}])