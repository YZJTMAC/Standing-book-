/**
 * Created by Administrator on 2017/3/16.
 */
$.fn.toggle = function( fn, fn2 ) {
    var args = arguments,guid = fn.guid || $.guid++,i=0,
    toggle = function( event ) {
      var lastToggle = ( $._data( this, "lastToggle" + fn.guid ) || 0 ) % i;
      $._data( this, "lastToggle" + fn.guid, lastToggle + 1 );
      event.preventDefault();
      return args[ lastToggle ].apply( this, arguments ) || false;
    };
    toggle.guid = guid;
    while ( i < args.length ) {
      args[ i++ ].guid = guid;
    }
    return this.click( toggle );
};
$(document).ready(function(){
    //备注显示隐藏
    var oWid=$('.beizhu_tex').css('width');
    var oMarginRight=$('.tablewrap').css('marginRight');
    var oRight=$('.btn_right').css('right');
//console.log(oMarginRight);
//console.log(oMarginRight.substring(0,oMarginRight.indexOf('p'))-oWid.substring(0,oWid.indexOf('p')));
    var difference = oMarginRight.substring(0,oMarginRight.indexOf('p'))-oWid.substring(0,oWid.indexOf('p'));
    $('.beizhu_btn').toggle(function (){
        $('.beizhu_tex').stop().animate({'width':'0px','margin-right':'0'});
        $('.tablewrap').stop().animate({'marginRight':difference-10+'px'});
        $('.btn_right').stop().animate({'right':difference+'px'});
    },function (){
        $('.beizhu_tex').stop().animate({'width':oWid,'margin-right':'10'});
        $('.tablewrap').stop().animate({'marginRight':oMarginRight});
        $('.btn_right').stop().animate({'right':oRight});
    });
})