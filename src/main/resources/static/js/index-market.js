$(document).ready(function () {


    //已获取的订单弹窗-P1
    $('#p1-list').click(function () {
        $('.pop-p1').show();
        var myquery1 = { productId : "P1"} ;
        $.ajax({
            type: "post",
            dataType: "json",
            data: JSON.stringify(myquery1),
            url: "/OrderManagement/list",
            contentType: "application/json;charset=utf-8;",
            success:function(data){
                data = data['data'];
                console.log(data);
                var tableStr = "<table border='0' cellspacing='' cellpadding=''>";
                tableStr = tableStr
                    + "<tr>"
                    +"<td >编号</td>" +"<td >产品</td>" +"<td >数量</td>" +"<td >金额</td>"
                    +"<td >操作</td>"+"</tr>";
                var len = data.length;
                for ( var i = 0; i < len; i++) {
                    tableStr = tableStr + "<tr>"
                        +"<td>"+ data[i].orderId + "</td>"
                        + "<td>"+ data[i].productId + "</td>"
                        + "<td>"+ data[i].amount + "</td>"
                        +"<td>"+data[i].money+"</td>"
                        +"<td ><button type='button' class='btn  btn-mini btn-stockout''  onclick=''>交货</button></td>"
                        +"</tr>";
                }
                if(len==0){
                    tableStr = tableStr + "<tr style='text-align: center'>"
                        +"<td colspan='6'><font color='#cd0a0a'>"+ '无订单' + "</font></td>"
                        +"</tr>";
                }
                tableStr = tableStr + "</table>";
                //添加到div中
                $("#ordermanagement1").html(tableStr);
                $("#ordermanagement1").delegate("button.btn-stockout", "click",
                    function(e) {
                        var myOrderName = "xxx";
                        myOrderId = $(e.currentTarget).parent("td").parent("tr").children("td:first-child").text();
                        console.log(myOrderName);
                        var myJson = { orderId : myOrderId} ;

                        $.ajax({
                            type: "post",
                            dataType: "json",
                            data: JSON.stringify(myJson),
                            url: "/OrderManagement/stockout",
                            contentType: "application/json;charset=utf-8;",
                            success: function (data) {
                                alert("交货成功");
                            }
                        });

                    });
            }
        })

    });


})