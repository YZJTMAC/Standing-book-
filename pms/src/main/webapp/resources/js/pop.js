(function($){
	var pop=(function(){
		function pop(el,opts){
			var me=this;
			this.el=el;
			this.opts=$.extend(true, $.fn.default, opts||{});
			
			this.sPop=$(this.opts.selectors.pop);
			this.sClose=this.sPop.find(this.opts.selectors.close);
			this.mask=$(this.opts.selectors.mask);
			this.el.on('click',function(){
				console.log(me.opts.selectors.pop)
				me.init()
			})
			this.sClose.on('click',function(){
				me.hide();
			});
			if (this.mask) {
				this.mask.on('click',function(){
					me.hide();
				});
			}
		}
		pop.prototype.init=function(){
			var me=this;
			me.pos();
			me.show();
			$(window).on('resise scroll',function(){
				me.pos();
			})
		}
		pop.prototype.pos=function(){
			var me=this;
			var L=($(window).width()-me.sPop.outerWidth(true))/2+$(document).scrollLeft();
			var T=($(window).height()-me.sPop.outerHeight(true))/2+$(document).scrollTop();
			me.sPop.css({
				left:L,
				top:T
			})
			if (me.mask) {
				me.mask.css({
					'width':$(window).width(),
					'height':$(window).height(),
					'left':$(document).scrollLeft(),
					'top':$(document).scrollTop()
				})
			}
		}
		pop.prototype.show=function(){
				if (this.mask) {
					this.mask.show()	
				}
				this.sPop.show()
			},
			pop.prototype.hide=function(){
				if (this.mask) {
					this.mask.hide()	
				}
				this.sPop.hide()
			}	
		return pop
	})()
	$.fn.pop=function(opts){
		return $(this).each(function(index,el){
			var me=$(this),
					instance=me.data('pop');
					if (!instance) {
						instance=new pop(me,opts);
						me.data('pop',instance)
					}
					if ($.type(opts) == 'string') {
						return instance[opts]()
					}
		})
	}
	$.fn.default={
		selectors:{
			pop: '.pop',
			close: '.closeBtn',
			mask: '#mask'
		}
	}
})(jQuery)
