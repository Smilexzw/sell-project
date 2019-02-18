$(function () {
    //加的效果
    $("#left li:first-child").addClass("active");
    var e;
    $(".add").click(function(){
        $(".subFly").show();
        var n = $(this).prev().text();
        var num;
        if(n==0){
            num =1
        }else{
            num = parseFloat(n);
        }
        $(".ad").prev().text(num);
        e = $(this).prev();
        var parent = $(this).parent();
        var name=parent.parent().children("h4").text()
        var price = parseFloat(parent.prev().children("b:nth-child(2)").text());
        var src = $(this).parent().parent().prev().children()[0].src;
        console.log(name,price,src);
        $(".subName dd p:nth-child(1)").html(name);
        $(".pce").text(price);
        $(".imgPhoto").attr('src',src);
        $(".price").text(price);
        $(".choseValue").text($(".subChose .m-active").text());
        var dataIcon=$(this).parent().parent().children("h4").attr("data-icon");
        $(".subName dd p:first-child").attr("data-icon",dataIcon)
    });
    $(".minus").click(function (){
        $('.shopcart-list').show();

    });
    var dd;
    $(".ad").click(function () {
        var n = parseFloat($(this).prev().text())+1;
           if (n == 0) { return; }
        $(this).prev().text(n);
        var danjia = $(this).next().text();//获取单价
        var a = $("#totalpriceshow").html();//获取当前所选总价
        $("#totalpriceshow").html((a * 1 + danjia * 1).toFixed(2));//计算当前所选总价
        var nm = $("#totalcountshow").html();//获取数量
        $("#totalcountshow").html(nm*1+1);
    });

    $(".up").click(function(){
        $(".subFly").hide();
    });
    $(".foot").click(function () {
        var n = $('.ad').prev().text();
        var num = parseFloat(n) + 1;
        if (num == 0) { return; }
        $('.ad').prev().text(num);
        var danjia = $('.ad').next().text();//获取单价
        var a = $("#totalpriceshow").html();//获取当前所选总价
        $("#totalpriceshow").html((a * 1 + danjia * 1).toFixed(2));//计算当前所选总价
        var nm = $("#totalcountshow").html();//获取数量
        $("#totalcountshow").html(nm*1+1);
        jss();//  改变按钮样式
        $(".subFly").hide();
        var ms = e.text(num-1);
        if(ms!=0){
            e.css("display","inline-block");
            e.prev().css("display","inline-block")
        }
        var m = $(".subName dd:nth-child(2) p:nth-child(1)").text();
        var taste = $(".subChose .m-active").text();
        var acount =n;
        var sum =parseFloat($(".subName dd p:nth-child(2) span:nth-child(2)").text())*acount;
        var price =parseFloat($(".subName dd p:nth-child(2) span:nth-child(2)").text());
        var dataIconN = $(this).parent().children(".subName").children("dd").children("p:first-child").attr("data-icon")
        var data=[m,taste,sum,acount,price,dataIconN];
        add(data);

    });
    $(".subChose dd").click(function(){
        $(this).addClass("m-active").siblings().removeClass("m-active");
        $(".choseValue").text($(".subChose .m-active").text());
    })
    //减的效果
    $(".ms").click(function () {
        var n = $(this).next().text();
        if(n>1){
            var num = parseFloat(n) - 1;
            $(this).next().text(num);//减1

            var danjia = $(this).nextAll(".price").text();//获取单价
            var a = $("#totalpriceshow").html();//获取当前所选总价
            $("#totalpriceshow").html((a * 1 - danjia * 1).toFixed(2));//计算当前所选总价

            var nm = $("#totalcountshow").html();//获取数量
            $("#totalcountshow").html(nm * 1 - 1);
        }

        //如果数量小于或等于0则隐藏减号和数量
        if (num <= 0) {
            $(this).next().css("display", "none");
            $(this).css("display", "none");
            jss();//改变按钮样式
            return
        }
    });
    function add(data) {
        $(".list-content>ul").append( '<li class="food"><div><span class="accountName" data-icon="'+data[5]+'">'+data[0]+'</span><span>'+data[1]+'</span></div><div><span>￥</span><span class="accountPrice">'+data[2]+'</span></div><div class="btn"><button class="ms2" style="display: inline-block;"><strong></strong></button> <i style="display: inline-block;">'+data[3]+'</i><button class="ad2"> <strong></strong></button><i style="display: none;">'+data[4]+'</i></div></li>');
        var display = $(".shopcart-list.fold-transition").css('display');
        if(display=="block"){
            $("document").click(function(){
                $(".shopcart-list.fold-transition").hide();
            })
        }
        /* 购物车加减*/
        $(".ad2").click(function () {
            var n = parseInt($(this).prev().text())+1;
            $(this).prev().text(n);
            var p = parseFloat($(this).next().text());
            $(this).parent().prev().children("span:nth-child(2)").text(p*n);
            $("#totalcountshow").text(parseFloat($("#totalcountshow").text())+1);
            $("#totalpriceshow").text(parseFloat($("#totalpriceshow").text())+p);
            if (n == 0) {
                $(".shopcart-list").hide();
            }
        });

        $(".ms2").click(function () {
            var a = parseInt($(this).next().text());
            var n = parseInt($(this).next().text())-1;
            var s = parseFloat($("#totalpriceshow").text())
            if (n == 0) {
                $(this).parent().parent().remove();
                $(".up1").hide()
            }
            $(this).next().text(n);
            /*问题*/
            $(this).parent().prev().children("span:nth-child(2)").text(a*n);
            $("#totalcountshow").text(parseInt($("#totalcountshow").text())-1);
            $("#totalpriceshow").text(s-a);
            if(parseFloat($("#totalcountshow").text())==0){
                $(".shopcart-list").hide();
            }
        })
    }
    function jss() {
        var m = $("#totalcountshow").html();
        if (m > 0) {
            $(".right").find("a").removeClass("disable");
        } else {
            $(".right").find("a").addClass("disable");
        }
    };
    //选项卡
    $(".con>div").hide();
    $(".con>div:eq(0)").show();
    $(".left-menu li").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
        var n = $(".left-menu li").index(this);
        $(".left-menu li").index(this);
        $(".con>div").hide();
        $(".con>div:eq("+n+")").show();
    });
    $(".subFly").hide();
    $(".close").click(function(){
        $(".subFly").hide();
    });
    $(".footer>.left").click(function(){
        var content = $(".list-content>ul").html();
        if(content!=""){
            $(".shopcart-list.fold-transition").toggle();
            $(".up1").toggle();
        }
    });
    $(".up1").click(function(){
        $(".up1").hide();
        $(".shopcart-list.fold-transition").hide();
    })
    $(".empty").click(function(){
        $(".list-content>ul").html("");
        $("#totalcountshow").text("0");
        $("#totalpriceshow").text("0");
        $(".minus").next().text("0");
        $(".minus").hide();
        $(".minus").next().hide();
        $(".shopcart-list").hide();
        $(".up1").hide();
        jss();//改变按钮样式
    });

});
