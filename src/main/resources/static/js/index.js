$(document).ready(function () {
    $('#ceo-cw').click(function () {
    $('.pop').show();
    });


    $('#jz').click(function () {
        $('.pop-jz').show()
    });


    $('.pop-close').click(function () {
        $('.pop-jz').hide();
    });




    $('.pop-close').click(function () {
        $('.pop').hide();
    });
    $('#ceo-sc').click(function () {
        $('.pop2').show();
    });
    $('.pop-close').click(function () {
        $('.pop2').hide();
    });
    $('#order').click(function () {
        $('.pop3').show();
    });
    $('.pop-close').click(function () {
        $('.pop3').hide();
    });

    $('.pop-close').click(function () {
        $('.pop4').hide();
    });
    $('#ceo-cz-c').click(function () {
        $('.pop-s1').show();
    });
    $('.pop-ok').click(function () {
        $('.pop-s1').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s1').hide();
    });
    $('#ceo-cz-d').click(function () {
        $('.pop-s2').show();
    });
    $('.pop-ok').click(function () {
        $('.pop-s2').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s2').hide();
    });
    $('#ceo-cz-gl').click(function () {
        $('.pop-s3').show();
    });
    $('.pop-ok').click(function () {
        $('.pop-s3').hide();
    });
    $('.pop-cancel').click(function () {
        $('.pop-s3').hide();
    });
    $('#ceo-tz-ma1').click(function () {
        $('.pop-s4').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-s4').hide();
    });
    $('#ok-qy').click(function () {
        $('.pop-s4').hide()
        $('#ceo-tz-ma1').removeClass('tz1')
        $('#ceo-tz-ma1').addClass('tz5')
    })
    $('#ceo-tz-ma2').click(function () {
        $('.pop-s5').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-s5').hide();
    });
    $('#ok-gn').click(function () {
        $('.pop-s5').hide()
        if($("#ceo-tz-ma2").hasClass("tz1")){
            $('#ceo-tz-ma2').removeClass('tz1')
            $('#ceo-tz-ma2').addClass('tz3')
        }else{
            $('#ceo-tz-ma2').removeClass('tz3')
            $('#ceo-tz-ma2').addClass('tz5')
        }
    })
    $('#ceo-tz-ma3').click(function () {
        $('.pop-s6').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-s6').hide();
    });
    $('#ok-yz').click(function () {
        $('.pop-s6').hide()
        if($("#ceo-tz-ma3").hasClass("tz1")){
            $('#ceo-tz-ma3').removeClass('tz1')
            $('#ceo-tz-ma3').addClass('tz2')
        }else if($("#ceo-tz-ma3").hasClass("tz2")){
            $('#ceo-tz-ma3').removeClass('tz2')
            $('#ceo-tz-ma3').addClass('tz3')
        }else {
            $('#ceo-tz-ma3').removeClass('tz3')
            $('#ceo-tz-ma3').addClass('tz5')
        }
    })
    $('#ceo-tz-ma4').click(function () {
        $('.pop-s7').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-s7').hide();
    });
    $('#ok-gj').click(function () {
        $('.pop-s7').hide()
        if($("#ceo-tz-ma4").hasClass("tz1")){
            $('#ceo-tz-ma4').removeClass('tz1')
            $('#ceo-tz-ma4').addClass('tz2')
        }else if($("#ceo-tz-ma4").hasClass("tz2")){
            $('#ceo-tz-ma4').removeClass('tz2')
            $('#ceo-tz-ma4').addClass('tz3')
        }else if($("#ceo-tz-ma4").hasClass("tz3")){
            $('#ceo-tz-ma4').removeClass('tz3')
            $('#ceo-tz-ma4').addClass('tz4')
        }else {
            $('#ceo-tz-ma4').removeClass('tz4')
            $('#ceo-tz-ma4').addClass('tz5')
        }
    })
    var battery=["tz1","tz1_5","tz2","tz3","tz3_5","tz4","tz5"]
    $('#ceo-tz-pr2').click(function () {
        $('.pop-s9').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-s9').hide();
    });
    $('#ok-p2').click(function () {
        $('.pop-s9').hide()
        for(var i =0;i<battery.length-1;i++){
            if($('#ceo-tz-pr2').hasClass(battery[i])){
                $('#ceo-tz-pr2').removeClass(battery[i])
                $('#ceo-tz-pr2').addClass(battery[i+1])
                return
            }
        }
    })
    $('#ceo-tz-pr3').click(function () {
        $('.pop-s10').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-s10').hide();
    });
    $('#ok-p3').click(function () {
        $('.pop-s10').hide()
        for(var i =0;i<battery.length-1;i++){
            if($('#ceo-tz-pr3').hasClass(battery[i])){
                $('#ceo-tz-pr3').removeClass(battery[i])
                $('#ceo-tz-pr3').addClass(battery[i+1])
                return
            }
        }
    })
    $('#ceo-tz-pr4').click(function () {
        $('.pop-s11').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-s11').hide();
    });
    $('#ok-p4').click(function () {
        $('.pop-s11').hide()
        for(var i =0;i<battery.length-1;i++){
            if($('#ceo-tz-pr4').hasClass(battery[i])){
                $('#ceo-tz-pr4').removeClass(battery[i])
                $('#ceo-tz-pr4').addClass(battery[i+1])
                return
            }
        }
    })
    $('#ceo-tz-iso1').click(function () {
        $('.pop-s12').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-s12').hide();
    });
    $('#ok-9k').click(function () {
        $('.pop-s12').hide()
        if($("#ceo-tz-iso1").hasClass("tz1")){
            $('#ceo-tz-iso1').removeClass('tz1')
            $('#ceo-tz-iso1').addClass('tz3')
        }else{
            $('#ceo-tz-iso1').removeClass('tz3')
            $('#ceo-tz-iso1').addClass('tz5')
        }
    })
    $('#ceo-tz-iso2').click(function () {
        $('.pop-s13').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-s13').hide();
    });

    //订货会弹窗-广告费
    $('#order').click(function () {
        var isAd = $("#market-page-manager").val();
        if(isAd == "0")
        {$('.pop-advertise').show()};
        if(isAd == "1")
        {$('.pop-marketing').show()};


    });
    $('.pop-cancel').click(function () {
        $('.pop-advertise').hide();
    });
    $('.pop-ok').click(function () {
        $('.pop-advertise').hide();
    });

    $('.pop-cancel').click(function () {
        $('.pop-marketing').hide();
    });


    $('.pop-cancel').click(function () {
        $('.pop-p1').hide()
    });
    $('#p2-list').click(function () {
        $('.pop-p2').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-p2').hide()
    });
    $('#p3-list').click(function () {
        $('.pop-p3').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-p3').hide()
    });
    $('#p4-list').click(function () {
        $('.pop-p4').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-p4').hide()
    });



    //原材料订单的弹出页面
    $('#r1-1').click(function () {
        $('.pop-r1-1').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-r1-1').hide();
    });
    $('.pop-ok').click(function () {
        $('.pop-r1-1').hide();
    });
    $('#r2-1').click(function () {
        $('.pop-r2-1').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-r2-1').hide();
    });
    $('.pop-ok').click(function () {
        $('.pop-r2-1').hide();
    });

    $('#r3-2').click(function () {
        $('.pop-r3-2').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-r3-2').hide();
    });
    $('.pop-ok').click(function () {
        $('.pop-r3-2').hide();
    });

    $('#r4-2').click(function () {
        $('.pop-r4-2').show()
    });
    $('.pop-cancel').click(function () {
        $('.pop-r4-2').hide();
    });
    $('.pop-ok').click(function () {
        $('.pop-r4-2').hide();
    });

    $('#ok-14k').click(function () {
        $('.pop-s13').hide()
        if($("#ceo-tz-iso2").hasClass("tz1")){
            $('#ceo-tz-iso2').removeClass('tz1')
            $('#ceo-tz-iso2').addClass('tz3')
        }else{
            $('#ceo-tz-iso2').removeClass('tz3')
            $('#ceo-tz-iso2').addClass('tz5')
        }
    })





})





$(function(){
    $('#tabs a').click(function(e) {
        e.preventDefault();
        $('#tabs li').removeClass("current").removeClass("hoverItem");
        $(this).parent().addClass("current");
        $("#pop-content div").removeClass("show");
        $('#' + $(this).attr('title')).addClass('show');
        // alert($(this).attr('title'));
        if($(this).attr('title')=="tab1"){
            //打开第一页操作
        }
        if($(this).attr('title')=="tab2"){
            //打开第二页操作
        }
    });
    $('#tabs a').hover(function(){
        if(!$(this).parent().hasClass("current")){
            $(this).parent().addClass("hoverItem");
        }}, function(){
        $(this).parent().removeClass("hoverItem");
    });
});
function reinitIframe() {
    var iframe = document.getElementById("marketingpre-wrap");
    try {
        var bHeight = iframe.contentWindow.document.body.scrollHeight;

        iframe.height = bHeight;
    } catch (ex) { };

    var iframe = document.getElementById("advertise-wrap");
    try {
        var bHeight = iframe.contentWindow.document.body.scrollHeight;
           iframe.height = bHeight;
    } catch (ex) { }


}


/*  function ChangeStyle(StID)
    {
        var div1 = $("#ceo-tz-ma1");
        for (var i = 0; i < sts.length; i++) {
            if (sts[i]!=StID)
            {
                if (div1.hasClass(sts[i]))
                {
                    div1.addClass(sts[i+1]);
                }
            }
        }
        div1.addClass(StID);
        //div1.toggleClass(StID);
    }*/
