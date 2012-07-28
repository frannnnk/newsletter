/**
 * 登录相关的验证类方法JS
 */
function validateEmail(email) {
		var reg = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
		flag =  reg.test(email);
		return flag;
}

