/**
 * Created by Administrator on 2017/3/16.
 */
$(document).ready(function(){
    //附件显示更多
	var oBox=document.getElementById('box');
    var aLi=oBox.children;
    var len=aLi.length;
    var bFlag=false;//显示隐藏的状态
    if (len == 0){
    	$('.new_fujian').css('display','none');
    	$('#fjyct').css('display','block');
    }
    if (len>6){
        for (var i=6;i<len;i++){
            aLi[i].style.display='none';
        }
    }

    var oBtn=document.getElementById('btn');
    oBtn.onclick=function(){
    	oBox=document.getElementById('box');
        aLi=oBox.children;
        len=aLi.length;
        bFlag=!bFlag;
   		if (bFlag){
            for (var i=0;i<len;i++){
                try{
                    aLi[i].style.display='block';
                    oBtn.style.background="url(/resources/images/heqi.png)";
                }catch(e){
                }
            }
        }else{
            for (var i=6;i<len;i++){
                try{
                    aLi[i].style.display='none';
                    oBtn.style.background="url(/resources/images/xiala.png)";
                }catch(e){
                }
            }
        }
    };

    //tab切换
    $('.daibanTab ul li').on('click',function(){
        $(this).addClass('active').siblings().removeClass('active');
        $('.new_tableBox').eq($(this).index()).show().siblings().hide();
    });
})
