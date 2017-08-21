
	$(function(){
		$('.close').on('click',function(){
			var parent_pop=$(this).closest('.pop');
			parent_pop.hide();
			$('#mask').hide();
		})
		var $li=$('.menu_list li');
		$li.each(function(i, ele){
			var $menu_btn=$(this).find('.menu_btn');
			var $menu_btn_list=$(this).find('.menu_btn_list');
			var time=null;
			$(this).on('mouseenter',function(){
				$menu_btn.show();
				$menu_btn_list.show();
				clearTimeout(time);
			})
			$(this).on('mouseleave',function(){
				time=setTimeout(function(){
					$menu_btn.hide();
					$menu_btn_list.hide();
				},30)
			})
		})
		
		var $tab_tit=$('.tab_tit');
		$tab_tit.each(function(){
			var _this=$(this);
			var btn=$(this).find('a');
			btn.on('click',function(){
				var parent=$(this).closest('.list_tab');
				var more_box=parent.find('.tab_more');
				var more=more_box.find('a')
				var box=parent.find('.list_tab_box');
				$(this).addClass('active').siblings().removeClass('active');
				more.eq($(this).index()).show().siblings().hide();
				box.eq($(this).index()).show().siblings().hide();	
			})
		})
		
		
		var $tab_one=$('.tab_one');
		$tab_one.each(function(){
			fnTr($(this))
		})
		function fnTr(tab){
			var tr=tab.find('tbody tr');
			tr.each(function(i,ele){
				
				if (i%2) {
					$(this).addClass('f9')
				}else{
					$(this).removeClass('f9')
				}
			})
		}
		var menuArray = [];
		$('.work_tab_list a').each(function(i,ele){
			menuArray.push($(this).attr('id'))
			var i=i+1;
			if (i%4== 0) {
				$(this).addClass('last');
			}
		})
		
		$('.quick_cont_l a').on('click',function(){
			$(this).addClass('active').siblings().removeClass('active')
		})
		$('.quick_tab_list a').each(function(i,ele){
			var i=i+1;
			if (i%6 == 0) {
				$(this).addClass('last');
			}
			if(jQuery.inArray($(this).attr('id'),menuArray) >= 0) {
				$(this).addClass('active');
			}
		})
		
		
		$('.quick_tab_list a').on('click',function(){
			$(this).toggleClass('active')
			editShortcut($(this).attr('id'))
		})
		
		var time1=null;
		$('.fpsq,.xl_box_list').on('mouseenter',function(){
			clearTimeout(time1)
			$('.xl_box_list').show();
			
		})
		$('.fpsq,.xl_box_list').on('mouseleave',function(){
			time1=setTimeout(function(){
				$('.xl_box_list').hide()	
			},100);
		})
		
		$('.tab_btn_list a').on('click',function(){
			var parent=$(this).closest('.xmxq_r');
			var tab_list_li=parent.find('.tab_list_li');
			$(this).addClass('active').siblings().removeClass('active');
			tab_list_li.eq($(this).index()).show().siblings().hide()
			var projectId=$("#projectId").val();
			switch($(this).index())
			{
			case 1:
				tab_list_li.eq($(this).index()).html("正在加载数据……")
				tab_list_li.eq($(this).index()).load('/payMentList/page?projectId='+projectId);
			  	break;
			case 2:
				tab_list_li.eq($(this).index()).html("正在加载数据……")
				tab_list_li.eq($(this).index()).load('/applyForInvoiceList/page?projectId='+projectId);
			  	break;
			case 3:
				tab_list_li.eq($(this).index()).html("正在加载数据……")
				tab_list_li.eq($(this).index()).load('/applyForFundsList/page?projectId='+projectId);
				break;
			case 4:
				tab_list_li.eq($(this).index()).html("正在加载数据……")
				tab_list_li.eq($(this).index()).load('/processList/alreadySend?projectId='+projectId);
				
				  break;
			case 5:
				tab_list_li.eq($(this).index()).html("正在加载数据……")
				tab_list_li.eq($(this).index()).load('/processList/needSend?projectId='+projectId);
				
				  break;
			default:
				break;
			}
				
		})
		
		$('#quick').on('click',function(){
			new pop({
				el:'#quick_pop'
			}).show()
		})

		$('.notice').on('click',function(){
			$(this).hide();
			$('#message_pop').show()
			$('#message_pop').css({
				'right': '20px',
			    'bottom': '20px'
			});
			
		})
		$('.message_close').on('click',function(){
			$('#message_pop').hide();
			$('.notice').show();
		})
		
		
		$('.ykfp').on('click',function(){
//			new pop({
//				el:'#ykfp_pop'
//			}).show()
			var projectId = $('#projectId').val();
			$.ajaxSetup ({
		    	cache:false
		    });
			$("#loadPayment").html("正在加载数据……")
			$("#loadPayment").load('/payment/noInvoicePayment?projectId='+projectId);
			layer.open({
  			  type: 1,
  			  title: '请先选择未开发票的到款记录（一次最多选择100条）',
  			  closeBtn: 0,
  			  //area:  ['860px', '600px'],
  			  area:  ['1000px', '600px'],
			  btnAlign: 'c',
			  btn: ['确定', '取消'],
			  yes: function(index, layero){
				submitPayment();
				layer.close(index);
			  },
  			  shadeClose: false,
  			  content: $('#ykfp_pop')
  			});
		})
		
		
		$('.message_btn_false').on('click',function(){
			$('.message_cont').hide();
		})
		//上传附件按钮的弹窗
		$('#upload_btn').on('click',function(){
//			new pop({
//						el:'#upload'
//					}).show()
			layer.open({
			  type: 1,
			  title: '上传附件',
			  closeBtn: 1,
			  area: '350px',
			  skin: 'layui-layer-lan',
			  offset: ['60px', '250px'],
			  shadeClose: false,
			  content: $('#upload')
			});
		})
		
		$('.field a').on('click',function(){
			$(this).toggleClass('active')
		})
		
		$('.summary_btn').on('click',function(){
			new pop({
						el:'#summary_pop'
					}).show();
		})
		$('#bj_btn').on('click',function(){
			$('.xx_text').hide();
			$('.bj').show()
		})
		$('#qk_btn').on('click',function(){
			$('.xx_text').hide();
			$('.qk').show()
		})
		$('#sc_btn').on('click',function(){
			$('.xx_text').hide();
			$('.sc').show()
		})
		//查看流程的弹窗
		$('#flow_btn').on('click',function(){
//			new pop({
//						el:'#flow_pop'
//					}).show();
			layer.open({
			  type: 1,
			  title: $('#cklcname').html(),
			  closeBtn: 1,
			  area: '950px',
			  skin: 'layui-layer-lan', //没有背景色
			  shadeClose: true,
			  content: $('#cklc')
			});
		})
		$('.field_r_btn').on('click',function(){
			var ele=$('.field_l a');
			ele.each(function(i,ele){
				if ($(this).attr('class')=='active') {
					$(this).appendTo($('.field_r'));
					$(this).removeClass('active');
				}
			})
		})
		
		$('.field_l_btn').on('click',function(){
			var ele=$('.field_r a');
			ele.each(function(i,ele){
				if ($(this).attr('class')=='active') {
					$(this).appendTo($('.field_l'));
					$(this).removeClass('active');
				}
			})
		})
		
//		$('.set_btn').on('click',function(){
//			new pop({
//						el:'#field_pop'
//					}).show();
//		})
		
		layui.use('upload', function(){
		  	layui.upload({
				elem: '#file',
		  		url: '/file/uploadAjax',
				title: '上传附件',
				before: function(input){
					$('#uploadMsg').html('上传中。。。请等待');
					layer.load(1);
					
		      	},
		  		success: function(res){
		  			$('#uploadMsg').html('');
		  			layer.closeAll('loading'); //上传成功返回值，必须为json格式
		  			if(res.result == true){
		  				layer.closeAll();
		  				$('.new_fujian').css('display','block');
		  				$('#fjyct').css('display','none');
		  				$('#box').append("<li><a href='javascript:;' title='"+res.fileName+"'>"+res.fileName+"</a><a href='javascript:;' class='del' onclick='delFujian(this)'></a><span style='display:none'>"+res.attId+"</span></li>")
		  			} else {
		  				alert(res.msg);
		  			}
		  		}
		  	});
		})
	})
	
	
	
	var srFlag = true;
	
	function pop(opts){
		this.opts=$.extend(true, pop.default, opts||{});
		this.Box=$(this.opts.el);
		this.mask=$('#mask');
		
		this.close=this.Box.find('.close');
		this.pos();
		var _this=this;
		this.close.on('click',function(){
			_this.hide();
		});
		var timer=0;
		$(window).on('scroll resize',function(){
			if(timer){
				clearTimeout(timer);
				timer=0;
			}
			timer=setTimeout(function(){
				_this.pos();
			},50);
		})
	}
	pop.prototype.pos=function(){
		if (srFlag) {
			var Width=this.Box.outerWidth();
			var Height=this.Box.outerHeight();
			var L=($(window).width()-Width)/2+$(document).scrollLeft();
			var T=($(window).height()-Height)/2+$(document).scrollTop();
			this.Box.css({
				'left':L,
				'top':T
			})
			this.mask.css({
				'width':$(window).width(),
				'height':$(window).height(),
				'top':$(document).scrollTop(),
				'left':$(document).scrollLeft()
			})
		}
	}
	pop.prototype.show=function(){
		srFlag = true;
		this.Box.show();
		this.mask.show();
	}
	pop.prototype.hide=function(){
		srFlag = false;
		this.Box.hide();
		this.mask.hide()
	}
	pop.default={
		el:'.pop'
	}

	
	