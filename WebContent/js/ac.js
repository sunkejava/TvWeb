function get(){
		var i1 = W("ke"),
			i0 = W("qp"),
			y1 = document.getElementById("n1"),
			y14 = y1.type,
			azs = parseInt(999999999 * Math.random()) + 1,
			y3 = y1.rel,
			rd1 = yp() ? i1 ? "m2" : "ms" : i1 ? "c2" : "cs",
			vg = document.getElementById("vg"),
			aza = i1 ? "http://1.syasn.com/" : "http://k.syasn.com/",
			azb = i1 ? "http://1.syasn.com/" : "http://k.syasn.com/",
			azc = vg ? "http://9.syasn.com/" : "http://k.syasn.com/";
			PS(y14);
			if ("1" == azs) {
			    var azf = parseInt(3 * Math.random()) + 1;
			    "1" != azf || i1 || (azb = "http://n.syasn.com/");
			    "2" != azf || i1 || (azb = "http://y.syasn.com/");
			    "3" != azf || i1 || (azb = "http://y.syasn.com/");
			    yzz = azb
			} else yzz = aza;
		var yd = yp() ? azc : yzz,
			y16 = i0 ? "p3" == i0 ? y19 : "p2" == i0 ? y18 : "p7" == i0 ? y20a : yd + "0/" : y20,
			ih = ".mp4";
			return (y16+y14+"/"+y3+ih);
		}
function jk() {
    var b = !1;
    top.location != self.location && (b = !0);
    return b
}
function PS(n){
	if(n.indexOf('rv')>=0){
		var	y19 = y20 = y20a = "http://k.syasn.com/";
		return;
	}else if(n.indexOf("v")<0){
		var	y19 = y20 = y20a = "http://k.syasn.com/";	
	}else{
		var	y19 = y20 = y20a = "http://9.syasn.com/";
	}
}
function G(n) {
        $.ajax({
            type: "get",
            async: !1,
            url: "http://h.syasn.com/jk/k.php",
            data: "n=" + n,
            dataType: "json",
            cache: !1,
            success: function(c) {
                mip = c.mip;
                mik = c.mik;
                min = c.min;
                //alert(mip);
            },
        	error:function(){  
      	   //alert("啊哦，出错了！！请稍后再试！");  
         }
        });
		
    }
function rm(g) {
    var p;
    g = g || 32;
    p = "";
    for (var r = 0; g > r; r++) p += "abcdefghijklmnpqrstuvwxyz1234567890".charAt(Math.floor(35 * Math.random()));
    return p
}
function W(b) {
    var a, d = b + "=",
        c = document.cookie.split(";");
    for (b = 0; b < c.length; b++) {
        for (a = c[b];
            " " == a.charAt(0);) a = a.substring(1, a.length);
        if (0 == a.indexOf(d)) return a.substring(d.length, a.length)
    }
    return null
}

function yp() {
    for (var b = navigator.userAgent, a = "Android;iPhone;SymbianOS;Windows Phone;iPad;iPod".split(";"), d = !1, c = 0; c < a.length; c++)
        if (0 < b.indexOf(a[c])) {
            d = !0;
            break;
        }
    return d
}