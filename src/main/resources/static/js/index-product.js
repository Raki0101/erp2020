$(document).ready(function () {

    //生产显示BEGIN
    $.ajax({
        url:"/ProductLine/list",
        type:"GET",
        dataType:"json",
        contentType: "application/json;charset=utf-8;",
        success: function(data){
            data = data['data'];
            console.log('生产线：');
            console.log(data);
            console.log('获取数据');
            var len = data.length;
            for(var i=0; i<len;i++){
                var productLineNumber = data[i].productLineNumber;
                var state = Number(data[i].state);
                var productLineTypeId = data[i].productLineTypeId;
                console.log('生产线编码：'+productLineNumber);

                switch (state) {
                    case 0:
                        statusByPln(productLineNumber,"在建");
                        showPLByPln(productLineNumber,productLineTypeId);
                        break;
                    case 1:
                        statusByPln(productLineNumber,"生产");
                        showPLByPln(productLineNumber,productLineTypeId);
                        break;
                    case 2:
                        statusByPln(productLineNumber,"停产");
                        showPLByPln(productLineNumber,productLineTypeId);
                        break;
                    case 3:
                        statusByPln(productLineNumber,"转产");
                        showPLByPln(productLineNumber,productLineTypeId);
                        break;

                };

            }
        }
    })

    //生产显示部分END




    //生产线1-10号的点击弹窗功能，传递生产线编号参数。
    $('#pro-l-1').click(function () {
        //获取第一条生产线的信息。
        var ProductLine = {
            productLineNumber: "1"
        };
       $('#plnValue').val("1");
        showProductPop(ProductLine);
        $('.pop-pro').show();
    });
    $('#pro-l-2').click(function () {
        //获取第一条生产线的信息。
        var ProductLine = {
            productLineNumber: "2"
        };
        $('#plnValue').val("2");
        showProductPop(ProductLine);
        $('.pop-pro').show();
    });
    $('#pro-l-3').click(function () {
        //获取第一条生产线的信息。
        var ProductLine = {
            productLineNumber: "3"
        };
        $('#plnValue').val("3");
        showProductPop(ProductLine);
        $('.pop-pro').show();
    });
    $('#pro-l-4').click(function () {
        //获取第一条生产线的信息。
        var ProductLine = {
            productLineNumber: "4"
        };
        $('#plnValue').val("4");
        showProductPop(ProductLine);
        $('.pop-pro').show();
    });
    $('#pro-l-5').click(function () {
        //获取第一条生产线的信息。
        var ProductLine = {
            productLineNumber: "5"
        };
        $('#plnValue').val("5");
        showProductPop(ProductLine);
        $('.pop-pro').show();
    });
    $('#pro-l-6').click(function () {
        //获取第一条生产线的信息。
        var ProductLine = {
            productLineNumber: "6"
        };
        $('#plnValue').val("6");
        showProductPop(ProductLine);
        $('.pop-pro').show();
    });


    $('#pro-r-1').click(function () {
        //获取第一条生产线的信息。
        var ProductLine = {
            productLineNumber: "7"
        };
        $('#plnValue').val("7");
        showProductPop(ProductLine);
        $('.pop-pro').show();
    });
    $('#pro-r-2').click(function () {
        //获取第一条生产线的信息。
        var ProductLine = {
            productLineNumber: "8"
        };
        $('#plnValue').val("8");
        showProductPop(ProductLine);
        $('.pop-pro').show();
    });
    $('#pro-r-3').click(function () {
        //获取第一条生产线的信息。
        var ProductLine = {
            productLineNumber: "9"
        };
        $('#plnValue').val("9");
        showProductPop(ProductLine);
        $('.pop-pro').show();
    });
    $('#pro-r-4').click(function () {
        //获取第一条生产线的信息。
        var ProductLine = {
            productLineNumber: "10"
        };
        $('#plnValue').val("10");
        showProductPop(ProductLine);
        $('.pop-pro').show();
    });




    $('.pop-cancel').click(function () {
        $('.pop-pro').hide();
    });
    $('#ok-pop-pro').click(function () {

        $('.pop-pro').hide();
    });

    //当新建生产线按钮被点击
    $('#ok-pro-newBuild').click(function () {
        var myPlnValue =    $('#plnValue').val();
        var currentAp =  $('#currentAp').val();
        var currentTeam =   $('#currentTeam').val();
        var factoryNumber ="";
        if(Number(myPlnValue) <7) { factoryNumber ="大厂房";}else{factoryNumber ="小厂房";}
        var productLineTypeId =  $("#productLineTypeIdList").find("option:selected").val();
        console.log("新建生产线：");
        console.log(productLineTypeId);
        var deviceValue = 0;
        var processingCycle = 0;
        if(productLineTypeId =="手工线")
        {
            deviceValue = 5;
            processingCycle = 3;
        }
        if(productLineTypeId =="半自动")
        {
            deviceValue = 5;
            processingCycle = 3;
        }
        if(productLineTypeId =="全自动")
        {
            deviceValue = 5;
            processingCycle = 3;
        }
        if(productLineTypeId =="柔性线")
        {
            deviceValue = 5;
            processingCycle = 3;
        }
        var ProductLine = {
            teamCount:currentTeam,
            period:currentAp,
            productLineNumber: myPlnValue,
            factoryNumber:factoryNumber,
            productLineTypeId:productLineTypeId,
            deviceValue:deviceValue,
            processingCycle:processingCycle
        };
        console.log(ProductLine)

        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ProductLine/build",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ProductLine),
            success: function (data) {
                alert("投资成功");
                statusByPln(myPlnValue,"在建");
                showPLByPln(myPlnValue,productLineTypeId);

            }
        })

        $('.pop-pro').hide();
    });


    //当后续投资生产线按钮被点击
    $('#ok-pro-build').click(function () {
        var myPlnValue =    $('#plnValue').val();
        var currentAp =  $('#currentAp').val();
        var currentTeam =   $('#currentTeam').val();
        var factoryNumber ="";
        if(number(myPlnValue) <7) { factoryNumber ="大厂房";}else{factoryNumber ="小厂房";}
        var productLineTypeId =  $("#productLineTypeIdList").find("option:selected").val();


        var ProductLine = {
            teamCount:currentTeam,
            period:currentAp,
            productLineNumber: myPlnValue,
            factoryNumber:factoryNumber,
            productLineTypeId:productLineTypeId
        };

        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ProductLine/build",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ProductLine),
            success: function (data) {
                alert("投资成功");
                statusByPln(myPlnValue,"在建");
            }
        })
        $('.pop-pro').hide();
    });


    //当生产线转产按钮被点击
    $('.ok-pro-switching').click(function () {
        var myPlnValue =    $('#plnValue').val();
        var currentAp =  $('#currentAp').val();
        var currentTeam =   $('#currentTeam').val();
        var factoryNumber ="";
        if(number(myPlnValue) <7) { factoryNumber ="大厂房";}else{factoryNumber ="小厂房";}



        var ProductLine = {
            teamCount:currentTeam,
            period:currentAp,
            productLineNumber: myPlnValue,
            factoryNumber:factoryNumber,
            productLineTypeId:productLineTypeId
        };

        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ProductLine/switching",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ProductLine),
            success: function (data) {
                alert("转产成功");
                statusByPln(myPlnValue,"转产");

            }
        })
        $('.pop-pro').hide();
    });



    //当生产线投产按钮被点击
    $('.ok-pro-produce').click(function () {
        var myPlnValue =    $('#plnValue').val();
        var currentAp =  $('#currentAp').val();
        var currentTeam =   $('#currentTeam').val();
        var factoryNumber ="";
        if(number(myPlnValue) <7) { factoryNumber ="大厂房";}else{factoryNumber ="小厂房";}
        var productC =  $("#productCList").find("option:selected").val();


        var ProductLine = {
            teamCount:currentTeam,
            period:currentAp,
            productLineNumber: myPlnValue,
            factoryNumber:factoryNumber,
            productC:productC
        };

        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ProductLine/inputToProduce",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ProductLine),
            success: function (data) {
                alert("投产成功");
                statusByPln(myPlnValue,"生产");

            }
        })
        $('.pop-pro').hide();
    });



    //当生产线出售按钮被点击
    $('.ok-pro-sale').click(function () {
        var myPlnValue =    $('#plnValue').val();
        var currentAp =  $('#currentAp').val();
        var currentTeam =   $('#currentTeam').val();
        var factoryNumber ="";
        if(number(myPlnValue) <7) { factoryNumber ="大厂房";}else{factoryNumber ="小厂房";}



        var ProductLine = {
            teamCount:currentTeam,
            period:currentAp,
            productLineNumber: myPlnValue,
            factoryNumber:factoryNumber,
        };

        $.ajax({
            type: "post",
            dataType: "json",
            url: "/ProductLine/sale",
            contentType: "application/json;charset=utf-8;",
            data: JSON.stringify(ProductLine),
            success: function (data) {
                alert("出售生产线成功");
                statusByPln(myPlnValue,"生产");

            }
        })
        $('.pop-pro').hide();
    });





})

function showProductPop(ProductLine) {

    $('.pro-status').hide();
    $('.newBuild').hide();
    $('.pro-build').hide();
    $('.pro-produce').hide();
    $('.pro-status').hide();

    $.ajax({
        type: "post",
        dataType: "json",
        url: "/ProductLine/listDetail",
        contentType: "application/json;charset=utf-8;",
        data: JSON.stringify(ProductLine),
        success: function (data) {
            var data = data['data'];
            console.log("弹窗生产状态");
            console.log(data[0]);
            if(data.length == 1)  //如果生产线存在
            { console.log("弹窗生产状态2");
                console.log(data[0].state);

                if( data[0].state == "0" ) //生产线为在建
                {
                    console.log("弹窗建设工程状态3");
                    $('#productLineTypeId-build').html(data[0].productLineTypeId);
                    $('#deviceValue').html(data[0].deviceValue);
                    $('#investmentAmountA').html(data[0].investmentAmountA);

                    $('.pro-build').show();
                }

                if( data[0].state == "1" )  //生产线为在产
                {
                    console.log("弹窗生产状态3");
                    $('#productLineTypeId').html(data[0].productLineTypeId);
                    $('#productC').html(data[0].productC);
                    $('#processingCycle').html(data[0].processingCycle);
                    $('#processingCycleB').html(data[0].processingCycleB);
                    $('.pro-status').show();
                }


                if( data[0].state == "2" ) //生产线为停产
                {

                    $('.pro-produce').show();
                }

                if( data[0].state == "3" ) //生产线为转产
                {
                    $('.pro-produce').show();
                }


            }
            if($.isEmptyObject(data) == true)
            {    console.log("弹窗新建生产线4");

                $('.newBuild').show();
            }
        }
    })
}

//将生产线的状态设置为指定值
function statusByPln(pln,status) {
    var myProductLineNumber = pln;
    var myStatus = status;
    var myObjId = "";
    switch (Number(myProductLineNumber))
    {
        case 1:
            myObjId = "#pro-l-1";

            break
        case 2:
            myObjId = "#pro-l-2";
            break
        case 3:
            myObjId = "#pro-l-3";
            break
        case 4:
            myObjId = "#pro-l-4";
            break
        case 5:
            myObjId = "#pro-l-5";
            break
        case 6:
            myObjId = "#pro-l-6";
            break
        case 7:
            myObjId = "#pro-r-1";
            break
        case 8:
            myObjId = "#pro-r-2";
            break
        case 9:
            myObjId = "#pro-r-3";
            break
        case 10:
            myObjId = "#pro-r-4";
            break

    }
    switch (myStatus) {
        case "生产":
            if (Number(pln) < 7) {
                $(myObjId).addClass('sclsc-l');
            } else {
                $(myObjId).addClass('sclsc-r');
            }

            break
        case "停产":
            if (Number(pln) < 7) {
                $(myObjId).addClass('scltc-l');
            } else {
                $(myObjId).addClass('scltc-r');
            }
            break
        case "转产":
            if (Number(pln) < 7) {
                $(myObjId).addClass('sclzc-l');
            } else {
                $(myObjId).addClass('sclzc-r');
            }
            break
        case "在建":
            if (Number(pln) < 7) {
                $(myObjId).addClass('sclzj-l');
            } else {
                $(myObjId).addClass('sclzj-r');
            }
            break
        case "出售":
            if (Number(pln) < 7) {
                $(myObjId).removeClass();
            } else {
                $(myObjId).removeClass();
            }
            break


    }
}


    //根据生产线参数显示生产线
function showPLByPln(pln,productLineTypeId) {
        var myProductLineNumber = pln;
        var myProductLineTypeId = productLineTypeId;
        var myObjId = "";
        switch (Number(myProductLineNumber)) {
            case 1:
                myObjId = "#pro-l-1";

                break
            case 2:
                myObjId = "#pro-l-2";
                break
            case 3:
                myObjId = "#pro-l-3";
                break
            case 4:
                myObjId = "#pro-l-4";
                break
            case 5:
                myObjId = "#pro-l-5";
                break
            case 6:
                myObjId = "#pro-l-6";
                break
            case 7:
                myObjId = "#pro-r-1";
                break
            case 8:
                myObjId = "#pro-r-2";
                break
            case 9:
                myObjId = "#pro-r-3";
                break
            case 10:
                myObjId = "#pro-r-4";
                break

        }
        switch (myProductLineTypeId) {
            case "手工线":
                myObjId =myObjId+"-sg";
                console.log("手工线："+myObjId);
                $(myObjId).addClass('show');
                break
            case "半自动":
                myObjId =myObjId+"-bzd";
                $(myObjId).addClass('show');
                break
            case "全自动":
                myObjId =myObjId+"-zd";
                $(myObjId).addClass('show');
                break
            case "柔性线":
                myObjId =myObjId+"-rx";
                $(myObjId).addClass('show');
                break


        }
    
}


