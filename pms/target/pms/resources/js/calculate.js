/*
 * 自定义的加减乘除四则运算，因为JS原生的+ - * / 对有小数点的浮点数是有精度丢失的
 */

//加法函数
function accAdd(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split(".")[1].length;
	}
	catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	}
	catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (arg1 * m + arg2 * m) / m;
} 
//给Number类型增加一个add方法，，使用时直接用 .add 即可完成计算。 
Number.prototype.add = function (arg) {
	return accAdd(arg, this);
};

//减法函数
function Subtr(arg1, arg2) {
	var r1, r2, m, n;
	try {
		r1 = arg1.toString().split(".")[1].length;
	}
	catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	}
	catch (e) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
     //last modify by deeka
     //动态控制精度长度
	n = (r1 >= r2) ? r1 : r2;
	return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

//给Number类型增加一个add方法，，使用时直接用 .sub 即可完成计算。 
Number.prototype.sub = function (arg) {
	return Subtr(this, arg);
};

//乘法函数
function accMul(arg1, arg2) {
	var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	try {
		m += s1.split(".")[1].length;
	}
	catch (e) {
	}
	try {
		m += s2.split(".")[1].length;
	}
	catch (e) {
	}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
} 
//给Number类型增加一个mul方法，使用时直接用 .mul 即可完成计算。 
Number.prototype.mul = function (arg) {
	return accMul(arg, this);
}; 

//除法函数
function accDiv(arg1, arg2) {
	var t1 = 0, t2 = 0, r1, r2;
	try {
		t1 = arg1.toString().split(".")[1].length;
	}
	catch (e) {
	}
	try {
		t2 = arg2.toString().split(".")[1].length;
	}
	catch (e) {
	}
	with (Math) {
		r1 = Number(arg1.toString().replace(".", ""));
		r2 = Number(arg2.toString().replace(".", ""));
		return (r1 / r2) * pow(10, t2 - t1);
	}
} 
//给Number类型增加一个div方法，，使用时直接用 .div 即可完成计算。 
Number.prototype.div = function (arg) {
	return accDiv(this, arg);
}; 


//保留两位小数   
//功能：将浮点数四舍五入，取小数点后2位  
function toDecimal(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return;  
    }  
    f = Math.round(x*100)/100;  
    return f;  
}  


//制保留2位小数，如：2，会在2后面补上00.即2.00  
function toDecimal2(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return false;  
    }  
    var f = Math.round(x*100)/100;  
    var s = f.toString();  
    var rs = s.indexOf('.');  
    if (rs < 0) {  
        rs = s.length;  
        s += '.';  
    }  
    while (s.length <= rs + 2) {  
        s += '0';  
    }  
    return s;  
}
//强制保留4位小数，如：2即2.0000  
function toDecimal4(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return false;  
    }  
    var f = Math.round(x*10000)/10000;  
    var s = f.toString();  
    var rs = s.indexOf('.');  
    if (rs < 0) {
        rs = s.length;
        s += '.';  
    }  
    while (s.length <= rs + 4) {
        s += '0';
    }
    return s;
}
/*document.write("四舍五入  <br/>")
document.write("3.14559267保留2位小数：" + toDecimal(3.14559267)+"<br/>");  
document.write("3.14159267强制保留2位小数：" + toDecimal2(3.14159267)+"<br/>");  
document.write("3.14559267保留2位小数：" + toDecimal(3.14559267)+"<br/>");  
document.write("3.15859267强制保留2位小数：" + toDecimal2(3.15859267)+"<br/>");  
document.write("3保留2位小数：" + toDecimal(3)+"<br/>");   
document.write("3强制保留2位小数：" + toDecimal2(3)+"<br/>");*/
//格式化成金额格式
function fmoney(s, n)   
{   
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return " " + t.split("").reverse().join("") + "." + r;   
}
//还原成数值
function rmoney(s)   
{   
   return parseFloat(s.replace(/[^\d\.-]/g, ""));   
} 