<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="hk.franks.newsletter.pojo.RestaurantPojo"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page trimDirectiveWhitespaces="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>NewsLetter -- Login</title>

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- Title will be insert by this file -->
		<%@ include file="include/head.jsp"%>



		<script>
	$(document).ready(function() {
		$('#enter').delay(1800).fadeIn(1000);
	});

	function login() {

		// encrypt password
		$('#encryptedPassword').val(hex_md5($('#encryptedPassword').val()));

		$.post("login.action?action=login", $('#loginForm').serialize(),
				function(data) {
					//alert(data);

					var obj = jQuery.parseJSON(data);

					if ("S" == obj.result) {
						loginSucceed();
					} else {
						loginError();
					}

				});

	}

	function loginSucceed() {
		$('#encryptedPassword').val("");
		window.open("member/main.jsp", "_self");
	}

	function loginError(msg) {

		$('#encryptedPassword').val("");

		$("#msg").addClass("formee-msg-error");
		$("#msg").html("Error La");
		//$("#msg").fadeIn("slow");
		$("#loginBox").effect("shake", {
			times : 5
		}, 50);

	}
	
	
	
	$.noty.defaults = {
		  layout: 'topCenter',
		  theme: 'default',
		  type: 'alert',
		  text: '',
		  dismissQueue: false, // If you want to use queue feature set this true
		  template: '<div class="noty_message font_NeoSansRegular"><span class="noty_text"></span><div class="noty_close"></div></div>',
		  animation: {
		    open: {height: 'toggle'},
		    close: {height: 'toggle'},
		    easing: 'swing',
		    speed: 500 // opening & closing animation speed
		  },
		  timeout: 3000, // delay for closing event. Set false for sticky notifications
		  force: false, // adds notification to the beginning of queue when set to true
		  modal: false,
		  closeWith: ['click'], // ['click', 'button', 'hover']
		  callback: {
		    onShow: function() {},
		    afterShow: function() {},
		    onClose: function() {},
		    afterClose: function() {}
		  },
		  buttons: false // an array of buttons
};

	function validateBeforeSubmit() {
		var errorMsg = '';
		
		
		if ( $('#email').val().trim() == '' ) {
			errorMsg += "Please enter email.<br/>";
		} else if (!isValidEmailAddress($('#email').val())) {
			errorMsg += "Please enter a valid email address.<br/>";
		}
		
		
		
		var aoi = $('.Checkbox:checked').map(function() {return this.value;}).get().join(',');
		
		if (aoi == null || aoi == '') {
			errorMsg += "Please choose something you like.<br/>";
		}	
		
			
		
		
		if (!('' == errorMsg)) {
			showError(errorMsg);
		} else {
				
				$('#aoi').val(aoi);
		
				var nbn = noty({text: 'Submitting your request...',type: 'information',modal: true,dismissQueue: true,force: true, timeout: false});
				
				
				$.post("account.action?action=register", $('#registerForm').serialize(),function(data){
				
					var closeProcessing = window.setTimeout(function(){
							nbn.close();
					},1000);
				
				if (data == null || data == '') {
					var nbn2 = noty({text: 'System Error.<br/>Click this message to close.', layout: 'center',type: 'error',modal: true,force: true, 
					   template: '<div class="noty_message font_NeoSansRegular"><span class="noty_text"></span><div class="noty_close"></div></div>',
					   timeout: false});
				}  else {
				
									var obj = jQuery.parseJSON(data);
				
				
				
				
										if ("S" == obj.result) {
										
											var nbn2 = noty({text: '<b>We have successfully received your request</b><br/><br/>We will review your information and notification email with your login details will be sent out when your request has been approved.<br/><br/>Click this message to close.', layout: 'center',type: 'success',modal: true,force: true, 
											   template: '<div class="noty_message font_NeoSansRegular"><span class="noty_text"></span><div class="noty_close"></div></div>',
											   timeout: false});
											   
										} else {
											
											var nbn2 = noty({text: obj.message+'<br/>Click this message to close.', layout: 'center',type: 'error',modal: true,force: true, 
											   template: '<div class="noty_message font_NeoSansRegular"><span class="noty_text"></span><div class="noty_close"></div></div>',
											   timeout: false});
										}
				
				}
				

			
			});
			
		}
		
	}
	
	function showError(msg){	
		var n = noty({text: msg,type: 'information'});	
	}


	
</script>

		<!--[if IE]>
        <link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_index_IE_only.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/css/main/bc_common_IE_only.css" />
<![endif]-->

<style>
	.legend_css {
		font-size:13px;
		color:#999;
		margin:6px;
	}
</style>


	</head>
	
	<body>
		<form action="" name="registerForm" id="registerForm">
			<div
				style="width: 750px; border-bottom: 1px solid #d9d9d9; margin-top: -300px; margin-left: -375px; padding-top: 30px; padding-bottom: 30px;"
				class="position_centered_absolute font_NeoSansRegular formee" id="header-join-u">
				<span style="font-size: 30px;"><span
					style="font-size: 30px; font-weight: bolder;">Join Us</span>
				</span>
			</div>

			<div
				style="width: 750px; margin-top: -230px; margin-left: -375px; padding-top: 30px; padding-bottom: 30px;"
				class="position_centered_absolute font_NeoSansRegular formee" id="header-new-m">
				<span style="font-size: 17px; line-height: 110%">New members
					are always welcome. <br />
					<br />
					<span style="font-size: 14px; color: #999;">Please read the
						brief intro. of our project first, then complete below request
						form. We will process your request within 2 working days. Login
						details will be sent to your email afterwards.</span>
				</span>
			</div>

			<div id="msg"
				style="width: 390px; margin-top: -185px; margin-left: -335px; display: none; font-size: 20px;"
				class="position_centered_absolute font_NeoSansRegular formee "></div>


			<div id="loginBox"
				style="width: 750px; margin-top: -105px; margin-left: -375px; padding-top: 30px; padding-bottom: 30px;"
				class="position_centered_absolute font_NeoSansRegular formee">

				<fieldset>
					<div class="login" style="margin: 30px;">
						<div class="grid-12-12">
							<label>
								Email
								<em class="formee-req">*</em>
							</label>
							<input type="text" value="" name="email" id="email"/>
						</div>

						<div class="grid-12-12">
							<label>
								Location
								<em class="formee-req">*</em>
							</label>
							<select class="formee-medium" name="location" id="location">
								<option value="">
									Select One
								</option>
								<option value="af">
									Afghanistan
								</option>
								<option value="ax">
									Aland Islands
								</option>
								<option value="al">
									Albania
								</option>
								<option value="dz">
									Algeria
								</option>
								<option value="as">
									American Samoa
								</option>
								<option value="ad">
									Andorra
								</option>
								<option value="ao">
									Angola
								</option>
								<option value="ai">
									Anguilla
								</option>
								<option value="aq">
									Antarctica
								</option>
								<option value="ag">
									Antigua and Barbuda
								</option>
								<option value="ar">
									Argentina
								</option>
								<option value="am">
									Armenia
								</option>
								<option value="aw">
									Aruba
								</option>
								<option value="au">
									Australia
								</option>
								<option value="at">
									Austria
								</option>
								<option value="az">
									Azerbaijan
								</option>
								<option value="bs">
									Bahamas
								</option>
								<option value="bh">
									Bahrain
								</option>
								<option value="bd">
									Bangladesh
								</option>
								<option value="bb">
									Barbados
								</option>
								<option value="by">
									Belarus
								</option>
								<option value="be">
									Belgium
								</option>
								<option value="bz">
									Belize
								</option>
								<option value="bj">
									Benin
								</option>
								<option value="bm">
									Bermuda
								</option>
								<option value="bt">
									Bhutan
								</option>
								<option value="bo">
									Bolivia
								</option>
								<option value="ba">
									Bosnia and Herzegovina
								</option>
								<option value="bw">
									Botswana
								</option>
								<option value="bv">
									Bouvet Island
								</option>
								<option value="br">
									Brazil
								</option>
								<option value="io">
									British Indian Ocean Territory
								</option>
								<option value="vg">
									British Virgin Islands
								</option>
								<option value="bn">
									Brunei
								</option>
								<option value="bg">
									Bulgaria
								</option>
								<option value="bf">
									Burkina Faso
								</option>
								<option value="bi">
									Burundi
								</option>
								<option value="kh">
									Cambodia
								</option>
								<option value="cm">
									Cameroon
								</option>
								<option value="ca">
									Canada
								</option>
								<option value="cv">
									Cape Verde
								</option>
								<option value="ky">
									Cayman Islands
								</option>
								<option value="cf">
									Central African Republic
								</option>
								<option value="td">
									Chad
								</option>
								<option value="cl">
									Chile
								</option>
								<option value="cn">
									China
								</option>
								<option value="cx">
									Christmas Island
								</option>
								<option value="cc">
									Cocos (Keeling) Islands
								</option>
								<option value="co">
									Colombia
								</option>
								<option value="km">
									Union of the Comoros
								</option>
								<option value="cg">
									Congo
								</option>
								<option value="ck">
									Cook Islands
								</option>
								<option value="cr">
									Costa Rica
								</option>
								<option value="hr">
									Croatia
								</option>
								<option value="cu">
									Cuba
								</option>
								<option value="cy">
									Cyprus
								</option>
								<option value="cz">
									Czech Republic
								</option>
								<option value="cd">
									Democratic Republic of Congo
								</option>
								<option value="dk">
									Denmark
								</option>
								<option value="xx">
									Disputed Territory
								</option>
								<option value="dj">
									Djibouti
								</option>
								<option value="dm">
									Dominica
								</option>
								<option value="do">
									Dominican Republic
								</option>
								<option value="tl">
									East Timor
								</option>
								<option value="ec">
									Ecuador
								</option>
								<option value="eg">
									Egypt
								</option>
								<option value="sv">
									El Salvador
								</option>
								<option value="gq">
									Equatorial Guinea
								</option>
								<option value="er">
									Eritrea
								</option>
								<option value="ee">
									Estonia
								</option>
								<option value="et">
									Ethiopia
								</option>
								<option value="fk">
									Falkland Islands
								</option>
								<option value="fo">
									Faroe Islands
								</option>
								<option value="fm">
									Federated States of Micronesia
								</option>
								<option value="fj">
									Fiji
								</option>
								<option value="fi">
									Finland
								</option>
								<option value="fr">
									France
								</option>
								<option value="gf">
									French Guyana
								</option>
								<option value="pf">
									French Polynesia
								</option>
								<option value="tf">
									French Southern Territories
								</option>
								<option value="ga">
									Gabon
								</option>
								<option value="gm">
									Gambia
								</option>
								<option value="ge">
									Georgia
								</option>
								<option value="de">
									Germany
								</option>
								<option value="gh">
									Ghana
								</option>
								<option value="gi">
									Gibraltar
								</option>
								<option value="gr">
									Greece
								</option>
								<option value="gl">
									Greenland
								</option>
								<option value="gd">
									Grenada
								</option>
								<option value="gp">
									Guadeloupe
								</option>
								<option value="gu">
									Guam
								</option>
								<option value="gt">
									Guatemala
								</option>
								<option value="gn">
									Guinea
								</option>
								<option value="gw">
									Guinea-Bissau
								</option>
								<option value="gy">
									Guyana
								</option>
								<option value="ht">
									Haiti
								</option>
								<option value="hm">
									Heard Island and McDonald Islands
								</option>
								<option value="hn">
									Honduras
								</option>
								<option value="hk" SELECTED>
									Hong Kong
								</option>
								<option value="hu">
									Hungary
								</option>
								<option value="is">
									Iceland
								</option>
								<option value="in">
									India
								</option>
								<option value="id">
									Indonesia
								</option>
								<option value="ir">
									Iran
								</option>
								<option value="iq">
									Iraq
								</option>
								<option value="xe">
									Iraq-Saudi Arabia Neutral Zone
								</option>
								<option value="ie">
									Ireland
								</option>
								<option value="il">
									Israel
								</option>
								<option value="it">
									Italy
								</option>
								<option value="ci">
									Ivory Coast
								</option>
								<option value="jm">
									Jamaica
								</option>
								<option value="jp">
									Japan
								</option>
								<option value="jo">
									Jordan
								</option>
								<option value="kz">
									Kazakhstan
								</option>
								<option value="ke">
									Kenya
								</option>
								<option value="ki">
									Kiribati
								</option>
								<option value="kw">
									Kuwait
								</option>
								<option value="kg">
									Kyrgyz Republic
								</option>
								<option value="la">
									Laos
								</option>
								<option value="lv">
									Latvia
								</option>
								<option value="lb">
									Lebanon
								</option>
								<option value="ls">
									Lesotho
								</option>
								<option value="lr">
									Liberia
								</option>
								<option value="ly">
									Libya
								</option>
								<option value="li">
									Liechtenstein
								</option>
								<option value="lt">
									Lithuania
								</option>
								<option value="lu">
									Luxembourg
								</option>
								<option value="mo">
									Macau
								</option>
								<option value="mk">
									Macedonia
								</option>
								<option value="mg">
									Madagascar
								</option>
								<option value="mw">
									Malawi
								</option>
								<option value="my">
									Malaysia
								</option>
								<option value="mv">
									Maldives
								</option>
								<option value="ml">
									Mali
								</option>
								<option value="mt">
									Malta
								</option>
								<option value="mh">
									Marshall Islands
								</option>
								<option value="mq">
									Martinique
								</option>
								<option value="mr">
									Mauritania
								</option>
								<option value="mu">
									Mauritius
								</option>
								<option value="yt">
									Mayotte
								</option>
								<option value="mx">
									Mexico
								</option>
								<option value="md">
									Moldova
								</option>
								<option value="mc">
									Monaco
								</option>
								<option value="mn">
									Mongolia
								</option>
								<option value="ms">
									Montserrat
								</option>
								<option value="ma">
									Morocco
								</option>
								<option value="mz">
									Mozambique
								</option>
								<option value="mm">
									Myanmar
								</option>
								<option value="na">
									Namibia
								</option>
								<option value="nr">
									Nauru
								</option>
								<option value="np">
									Nepal
								</option>
								<option value="nl">
									Netherlands
								</option>
								<option value="an">
									Netherlands Antilles
								</option>
								<option value="nc">
									New Caledonia
								</option>
								<option value="nz">
									New Zealand
								</option>
								<option value="ni">
									Nicaragua
								</option>
								<option value="ne">
									Niger
								</option>
								<option value="ng">
									Nigeria
								</option>
								<option value="nu">
									Niue
								</option>
								<option value="nf">
									Norfolk Island
								</option>
								<option value="kp">
									North Korea
								</option>
								<option value="mp">
									Northern Mariana Islands
								</option>
								<option value="no">
									Norway
								</option>
								<option value="om">
									Oman
								</option>
								<option value="pk">
									Pakistan
								</option>
								<option value="pw">
									Palau
								</option>
								<option value="ps">
									Palestinian Territories
								</option>
								<option value="pa">
									Panama
								</option>
								<option value="pg">
									Papua New Guinea
								</option>
								<option value="py">
									Paraguay
								</option>
								<option value="pe">
									Peru
								</option>
								<option value="ph">
									Philippines
								</option>
								<option value="pn">
									Pitcairn Islands
								</option>
								<option value="pl">
									Poland
								</option>
								<option value="pt">
									Portugal
								</option>
								<option value="pr">
									Puerto Rico
								</option>
								<option value="qa">
									Qatar
								</option>
								<option value="re">
									Reunion
								</option>
								<option value="ro">
									Romania
								</option>
								<option value="ru">
									Russia
								</option>
								<option value="rw">
									Rwanda
								</option>
								<option value="sh">
									Saint Helena and Dependencies
								</option>
								<option value="kn">
									Saint Kitts & Nevis
								</option>
								<option value="lc">
									Saint Lucia
								</option>
								<option value="pm">
									Saint Pierre and Miquelon
								</option>
								<option value="vc">
									Saint Vincent and the Grenadines
								</option>
								<option value="ws">
									Samoa
								</option>
								<option value="sm">
									San Marino
								</option>
								<option value="st">
									Sao Tome and Principe
								</option>
								<option value="sa">
									Saudi Arabia
								</option>
								<option value="sn">
									Senegal
								</option>
								<option value="sc">
									Seychelles
								</option>
								<option value="sl">
									Sierra Leone
								</option>
								<option value="sg">
									Singapore
								</option>
								<option value="sk">
									Slovakia
								</option>
								<option value="si">
									Slovenia
								</option>
								<option value="sb">
									Solomon Islands
								</option>
								<option value="so">
									Somalia
								</option>
								<option value="za">
									South Africa
								</option>
								<option value="gs">
									South Georgia and the South Sandwich Islands
								</option>
								<option value="kr">
									South Korea
								</option>
								<option value="es">
									Spain
								</option>
								<option value="pi">
									Spratly Islands
								</option>
								<option value="lk">
									Sri Lanka
								</option>
								<option value="sd">
									Sudan
								</option>
								<option value="sr">
									Suriname
								</option>
								<option value="sj">
									Svalbard and Jan Mayen Islands
								</option>
								<option value="sz">
									Swaziland
								</option>
								<option value="se">
									Sweden
								</option>
								<option value="ch">
									Switzerland
								</option>
								<option value="sy">
									Syria
								</option>
								<option value="tw">
									Taiwan
								</option>
								<option value="tj">
									Tajikistan
								</option>
								<option value="tz">
									Tanzania
								</option>
								<option value="th">
									Thailand
								</option>
								<option value="tg">
									Togo
								</option>
								<option value="tk">
									Tokelau
								</option>
								<option value="to">
									Tonga
								</option>
								<option value="tt">
									Trinidad and Tobago
								</option>
								<option value="tn">
									Tunisia
								</option>
								<option value="tr">
									Turkey
								</option>
								<option value="tm">
									Turkmenistan
								</option>
								<option value="tc">
									Turks and Caicos Islands
								</option>
								<option value="tv">
									Tuvalu
								</option>
								<option value="ug">
									Uganda
								</option>
								<option value="ua">
									Ukraine
								</option>
								<option value="ae">
									United Arab Emirates
								</option>

							</select>
						</div>

						<div class="grid-12-12">
							<label>
								Native Language
								<em class="formee-req">*</em>
							</label>
							<select class="formee-medium" name="language" id="language">
								
								
								<option value="English">English</option>
								<option value="Chinese-Cantonese">Chinese, Cantonese</option>
								<option value="Chinese-Mandarin">Chinese, Mandarin</option>
								<option value="Chinese-Other">Chinese, other</option>
								<option value="French">French</option>
								<option value="German">German</option>
								<option value="Italian">Italian</option>
								<option value="Japanese">Japanese</option>
								<option value="Korean">Korean</option>
								<option value="Malay">Malay</option>
								<option value="Russian">Russian</option>
								<option value="Spanish">Spanish</option>
								<option value="Other">Other</option>
							</select>
						</div>

						<div class="grid-12-12"><br/>
							<label>
								Area of Interest
								<em class="formee-req">*</em>
							</label>
							
<fieldset>
	<legend><span class="legend_css">Activities</span></legend>
	<input id="aoi" name="aoi" type="hidden" value=""></input>
        <ul class='formee-list'>
        <li><input type="checkbox" name="areaofinterest" value="activities_act" id="activities_act" class="Checkbox"> Activism</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_ant" id="activities_ant" class="Checkbox"> Antiques / Collectibles</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_ast" id="activities_ast" class="Checkbox"> Astrology</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_boo" id="activities_boo" class="Checkbox"> Books / Reading</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_cam" id="activities_cam" class="Checkbox" > Camping / Outdoors</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_car" id="activities_car" class="Checkbox" > Cars</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_col" id="activities_col" class="Checkbox" > Collecting</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_com" id="activities_com" class="Checkbox"> Computers</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_dra" id="activities_dra" class="Checkbox"> Drag</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_fas" id="activities_fas" class="Checkbox"> Fashion</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_inv" id="activities_inv" class="Checkbox"> Investing</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_lea" id="activities_lea" class="Checkbox"> Leather</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_mot" id="activities_mot" class="Checkbox"> Motorcycles</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_pho" id="activities_pho" class="Checkbox"> Photography</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_pol" id="activities_pol" class="Checkbox"> Politics</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_sho" id="activities_sho" class="Checkbox" > Shopping</li>
        <li><input type="checkbox" name="areaofinterest" value="activities_tra" id="activities_tra" class="Checkbox" > Travel</li>
        </ul>
</fieldset>
<fieldset>
	<legend><span class="legend_css">Entertainment</span></legend>
        <ul class='formee-list'>
        <li><input type="checkbox" name="areaofinterest" value="entertainment_club" id="entertainment_club" class="Checkbox"> Clubbing / Parties</li>
        <li><input type="checkbox" name="areaofinterest" value="entertainment_comi" id="entertainment_comi" class="Checkbox"> Comics / Anime</li>
        <li><input type="checkbox" name="areaofinterest" value="entertainment_comp" id="entertainment_comp" class="Checkbox" > Computer</li>
        <li><input type="checkbox" name="areaofinterest" value="entertainment_danc" id="entertainment_danc" class="Checkbox"> Dancing</li>
        <li><input type="checkbox" name="areaofinterest" value="entertainment_gamb" id="entertainment_gamb" class="Checkbox"> Gambling</li>
        <li><input type="checkbox" name="areaofinterest" value="entertainment_live" id="entertainment_live" class="Checkbox" > Live music / Music</li>
        <li><input type="checkbox" name="areaofinterest" value="entertainment_movi" id="entertainment_movi" class="Checkbox" > Movies / Film</li>
        <li><input type="checkbox" name="areaofinterest" value="entertainment_symp" id="entertainment_symp" class="Checkbox"> Symphony / Opera</li>
        <li><input type="checkbox" name="areaofinterest" value="entertainment_thea" id="entertainment_thea" class="Checkbox" > Theater Arts</li>
        <li><input type="checkbox" name="areaofinterest" value="entertainment_tvvi" id="entertainment_tvvi" class="Checkbox" > TV / Videos / DVDs</li>
        <li><input type="checkbox" name="areaofinterest" value="entertainment_wine" id="entertainment_wine" class="Checkbox"> Wine Tasting</li>
        </ul>
        
        </fieldset>
        <fieldset>
	<legend><span class="legend_css">Music</span></legend>
        <ul class='formee-list'>
        <li><input type="checkbox" name="areaofinterest" value="music_alt" id="music_alt" class="Checkbox"> Alternative</li>
        <li><input type="checkbox" name="areaofinterest" value="music_cla" id="music_cla" class="Checkbox" > Classical</li>
        <li><input type="checkbox" name="areaofinterest" value="music_cou" id="music_cou" class="Checkbox"> Country</li>
        <li><input type="checkbox" name="areaofinterest" value="music_dan" id="music_dan" class="Checkbox"> Dance</li>
        <li><input type="checkbox" name="areaofinterest" value="music_gos" id="music_gos" class="Checkbox" > Gospel</li>
        <li><input type="checkbox" name="areaofinterest" value="music_jaz" id="music_jaz" class="Checkbox"> Jazz</li>
        <li><input type="checkbox" name="areaofinterest" value="music_met" id="music_met" class="Checkbox"> Metal</li>
        <li><input type="checkbox" name="areaofinterest" value="music_new" id="music_new" class="Checkbox"> New Age</li>
        <li><input type="checkbox" name="areaofinterest" value="music_ope" id="music_ope" class="Checkbox" > Opera</li>
        <li><input type="checkbox" name="areaofinterest" value="music_pop" id="music_pop" class="Checkbox" > Pop</li>
        <li><input type="checkbox" name="areaofinterest" value="music_roc" id="music_roc" class="Checkbox" > Rock</li>
        <li><input type="checkbox" name="areaofinterest" value="music_rnb" id="music_rnb" class="Checkbox" > R&B</li>
        <li><input type="checkbox" name="areaofinterest" value="music_voc" id="music_voc" class="Checkbox"> Vocal</li>
        <li><input type="checkbox" name="areaofinterest" value="music_wor" id="music_wor" class="Checkbox"> World</li>
        </ul></fieldset>
        
<fieldset>
	<legend><span class="legend_css">Health,&nbsp;&nbsp;Sports&nbsp;&nbsp;and&nbsp;&nbsp;Fitness</span></legend>
        <ul class='formee-list'>
        <li><input type="checkbox" name="areaofinterest" value="health_aero" id="health_aero" class="Checkbox"> Aerobics / Exercise</li>
        <li><input type="checkbox" name="areaofinterest" value="health_amer" id="health_amer" class="Checkbox"> American Football</li>
        <li><input type="checkbox" name="areaofinterest" value="health_base" id="health_base" class="Checkbox"> Baseball / Softball</li>
        <li><input type="checkbox" name="areaofinterest" value="health_bask" id="health_bask" class="Checkbox"> Basketball</li>
        <li><input type="checkbox" name="areaofinterest" value="health_beac" id="health_beac" class="Checkbox"> Beach volleyball</li>
        <li><input type="checkbox" name="areaofinterest" value="health_biki" id="health_biki" class="Checkbox" > Biking</li>
        <li><input type="checkbox" name="areaofinterest" value="health_bill" id="health_bill" class="Checkbox"> Billiards / Pool</li>
        <li><input type="checkbox" name="areaofinterest" value="health_body" id="health_body" class="Checkbox"> Bodybuilding</li>
        <li><input type="checkbox" name="areaofinterest" value="health_extr" id="health_extr" class="Checkbox"> Extreme sports</li>
        <li><input type="checkbox" name="areaofinterest" value="health_figu" id="health_figu" class="Checkbox"> Figure skating</li>
        <li><input type="checkbox" name="areaofinterest" value="health_golf" id="health_golf" class="Checkbox"> Golf</li>
        <li><input type="checkbox" name="areaofinterest" value="health_hiki" id="health_hiki" class="Checkbox" > Hiking</li>
        <li><input type="checkbox" name="areaofinterest" value="health_hock" id="health_hock" class="Checkbox"> Hockey</li>
        <li><input type="checkbox" name="areaofinterest" value="health_holi" id="health_holi" class="Checkbox"> Holistic Health</li>
        <li><input type="checkbox" name="areaofinterest" value="health_inli" id="health_inli" class="Checkbox"> Inline skating</li>
        <li><input type="checkbox" name="areaofinterest" value="health_mart" id="health_mart" class="Checkbox"> Martial Arts</li>
        <li><input type="checkbox" name="areaofinterest" value="health_medi" id="health_medi" class="Checkbox" > Meditation</li>
        <li><input type="checkbox" name="areaofinterest" value="health_nutr" id="health_nutr" class="Checkbox"> Nutrition</li>
        <li><input type="checkbox" name="areaofinterest" value="health_rugb" id="health_rugb" class="Checkbox"> Rugby</li>
        <li><input type="checkbox" name="areaofinterest" value="health_runn" id="health_runn" class="Checkbox"> Running</li>
        <li><input type="checkbox" name="areaofinterest" value="health_sail" id="health_sail" class="Checkbox"> Sailing / Boating</li>
        <li><input type="checkbox" name="areaofinterest" value="health_scub" id="health_scub" class="Checkbox"> Scuba diving</li>
        <li><input type="checkbox" name="areaofinterest" value="health_skat" id="health_skat" class="Checkbox"> Skateboarding</li>
        <li><input type="checkbox" name="areaofinterest" value="health_skii" id="health_skii" class="Checkbox"> Skiing</li>
        <li><input type="checkbox" name="areaofinterest" value="health_snow" id="health_snow" class="Checkbox"> Snowboarding</li>
        <li><input type="checkbox" name="areaofinterest" value="health_socc" id="health_socc" class="Checkbox"> Soccer / Football</li>
        <li><input type="checkbox" name="areaofinterest" value="health_surf" id="health_surf" class="Checkbox"> Surfing</li>
        <li><input type="checkbox" name="areaofinterest" value="health_swim" id="health_swim" class="Checkbox"> Swimming</li>
        <li><input type="checkbox" name="areaofinterest" value="health_tenn" id="health_tenn" class="Checkbox"> Tennis / Racquet sports</li>
        <li><input type="checkbox" name="areaofinterest" value="health_tria" id="health_tria" class="Checkbox"> Triathlon</li>
        <li><input type="checkbox" name="areaofinterest" value="health_voll" id="health_voll" class="Checkbox"> Volleyball</li>
        <li><input type="checkbox" name="areaofinterest" value="health_walk" id="health_walk" class="Checkbox" > Walking</li>
        <li><input type="checkbox" name="areaofinterest" value="health_weig" id="health_weig" class="Checkbox"> Weight Training</li>
        <li><input type="checkbox" name="areaofinterest" value="health_wres" id="health_wres" class="Checkbox"> Wrestling</li>
        <li><input type="checkbox" name="areaofinterest" value="health_yoga" id="health_yoga" class="Checkbox"> Yoga</li>
        </ul></fieldset>
        
	<fieldset>
		<legend><span class="legend_css">Home&nbsp;/&nbsp;Family</span></legend>
	        <ul class='formee-list'>
	        <li><input type="checkbox" name="areaofinterest" value="home_coo" id="home_coo" class="Checkbox" > Cooking</li>
	        <li><input type="checkbox" name="areaofinterest" value="home_cra" id="home_cra" class="Checkbox"> Crafts</li>
	        <li><input type="checkbox" name="areaofinterest" value="home_dec" id="home_dec" class="Checkbox"> Decorating</li>
	        <li><input type="checkbox" name="areaofinterest" value="home_ent" id="home_ent" class="Checkbox"> Entertaining</li>
	        <li><input type="checkbox" name="areaofinterest" value="home_gar" id="home_gar" class="Checkbox"> Gardening</li>
	        <li><input type="checkbox" name="areaofinterest" value="home_hom" id="home_hom" class="Checkbox"> Home Improvement</li>
	        <li><input type="checkbox" name="areaofinterest" value="home_mar" id="home_mar" class="Checkbox"> Marriage</li>
	        <li><input type="checkbox" name="areaofinterest" value="home_par" id="home_par" class="Checkbox"> Parenting</li>
	        <li><input type="checkbox" name="areaofinterest" value="home_pet" id="home_pet" class="Checkbox" > Pets</li>
	        <li><input type="checkbox" name="areaofinterest" value="home_ret" id="home_ret" class="Checkbox"> Retirement</li>
	        </ul>
	 </fieldset>
	 
	
							
							</div>

							
							
						



						<div class="grid-12-12">
							<input class="right" type="button" title="Login"
								value="Send the form to us" onclick="validateBeforeSubmit();" />
						</div>



					</div>
				</fieldset>
			</div>

		</form>

	</body>
</html>