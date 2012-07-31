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
		
		
		if ($('#screenname').val().trim() == '') {
			errorMsg += "<li>Please choose a screen name.</li>";
		}
		
		if ( $('#email').val().trim() == '' ) {
			errorMsg += "<li>Please enter email.</li>";
		} else if (!isValidEmailAddress($('#email').val())) {
			errorMsg += "<li>Please enter a valid email address.</li>";
		}
		
		
		
		var aoi = $('.Checkbox:checked').map(function() {return this.value;}).get().join(',');
		
		if (aoi == null || aoi == '') {
			errorMsg += "<li>Please choose something you like.</li>";
		}	
		
			
		
		
		if (!('' == errorMsg)) {
			showError(errorMsg);
		} else {
				
				$('#aoi').val(aoi);
		
				var nbn = noty({text: 'Submitting your request...',type: 'information',modal: true,dismissQueue: true,force: true,
					 animation: {
				    open: {height: 'toggle'},
				    close: {height: 'toggle'},
				    easing: 'swing',
				    speed: 100 // opening & closing animation speed
				  },
				 timeout: false});
				
				
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
											   callback: {
											    onShow: function() {},
											    afterShow: function() {},
											    onClose: function() {window.open('index.jsp','_self')},
											    afterClose: function() {}
											  },
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
						
						<div class="grid-6-12">
							<label>
								Screen Name
								<em class="formee-req">*</em>
							</label>
							<input type="text" value="" name="screenname" id="screenname"/>
						</div>
						
						<div class="grid-5-12">
							<label>
								Gender
								<em class="formee-req">*</em>
							</label>
							<select class="formee-medium" name="gender" id=""gender"">
								<option value="F">
									Girl
								</option>
								<option value="M">
									Boy
								</option>
							</select>
						</div>
						
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
        <li><input type="checkbox" name="areaofinterest" value="acts_act" id="acts_act" class="Checkbox"> Activism</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_ant" id="acts_ant" class="Checkbox"> Antiques / Collectibles</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_ast" id="acts_ast" class="Checkbox"> Astrology</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_boo" id="acts_boo" class="Checkbox"> Books / Reading</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_cam" id="acts_cam" class="Checkbox" > Camping / Outdoors</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_car" id="acts_car" class="Checkbox" > Cars</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_col" id="acts_col" class="Checkbox" > Collecting</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_com" id="acts_com" class="Checkbox"> Computers</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_dra" id="acts_dra" class="Checkbox"> Drag</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_fas" id="acts_fas" class="Checkbox"> Fashion</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_inv" id="acts_inv" class="Checkbox"> Investing</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_lea" id="acts_lea" class="Checkbox"> Leather</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_mot" id="acts_mot" class="Checkbox"> Motorcycles</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_pho" id="acts_pho" class="Checkbox"> Photography</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_pol" id="acts_pol" class="Checkbox"> Politics</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_sho" id="acts_sho" class="Checkbox" > Shopping</li>
        <li><input type="checkbox" name="areaofinterest" value="acts_tra" id="acts_tra" class="Checkbox" > Travel</li>
        </ul>
</fieldset>
<fieldset>
	<legend><span class="legend_css">Entertainment</span></legend>
        <ul class='formee-list'>
        <li><input type="checkbox" name="areaofinterest" value="ent_club" id="ent_club" class="Checkbox"> Clubbing / Parties</li>
        <li><input type="checkbox" name="areaofinterest" value="ent_comi" id="ent_comi" class="Checkbox"> Comics / Anime</li>
        <li><input type="checkbox" name="areaofinterest" value="ent_comp" id="ent_comp" class="Checkbox" > Computer</li>
        <li><input type="checkbox" name="areaofinterest" value="ent_danc" id="ent_danc" class="Checkbox"> Dancing</li>
        <li><input type="checkbox" name="areaofinterest" value="ent_gamb" id="ent_gamb" class="Checkbox"> Gambling</li>
        <li><input type="checkbox" name="areaofinterest" value="ent_live" id="ent_live" class="Checkbox" > Live music / Music</li>
        <li><input type="checkbox" name="areaofinterest" value="ent_movi" id="ent_movi" class="Checkbox" > Movies / Film</li>
        <li><input type="checkbox" name="areaofinterest" value="ent_symp" id="ent_symp" class="Checkbox"> Symphony / Opera</li>
        <li><input type="checkbox" name="areaofinterest" value="ent_thea" id="ent_thea" class="Checkbox" > Theater Arts</li>
        <li><input type="checkbox" name="areaofinterest" value="ent_tvvi" id="ent_tvvi" class="Checkbox" > TV / Videos / DVDs</li>
        <li><input type="checkbox" name="areaofinterest" value="ent_wine" id="ent_wine" class="Checkbox"> Wine Tasting</li>
        </ul>
        
        </fieldset>
        <fieldset>
	<legend><span class="legend_css">Music</span></legend>
        <ul class='formee-list'>
        <li><input type="checkbox" name="areaofinterest" value="mu_alt" id="mu_alt" class="Checkbox"> Alternative</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_cla" id="mu_cla" class="Checkbox" > Classical</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_cou" id="mu_cou" class="Checkbox"> Country</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_dan" id="mu_dan" class="Checkbox"> Dance</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_gos" id="mu_gos" class="Checkbox" > Gospel</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_jaz" id="mu_jaz" class="Checkbox"> Jazz</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_met" id="mu_met" class="Checkbox"> Metal</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_new" id="mu_new" class="Checkbox"> New Age</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_ope" id="mu_ope" class="Checkbox" > Opera</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_pop" id="mu_pop" class="Checkbox" > Pop</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_roc" id="mu_roc" class="Checkbox" > Rock</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_rnb" id="mu_rnb" class="Checkbox" > R&B</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_voc" id="mu_voc" class="Checkbox"> Vocal</li>
        <li><input type="checkbox" name="areaofinterest" value="mu_wor" id="mu_wor" class="Checkbox"> World</li>
        </ul></fieldset>
        
<fieldset>
	<legend><span class="legend_css">Health,&nbsp;&nbsp;Sports&nbsp;&nbsp;and&nbsp;&nbsp;Fitness</span></legend>
        <ul class='formee-list'>
        <li><input type="checkbox" name="areaofinterest" value="heh_aero" id="heh_aero" class="Checkbox"> Aerobics / Exercise</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_amer" id="heh_amer" class="Checkbox"> American Football</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_base" id="heh_base" class="Checkbox"> Baseball / Softball</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_bask" id="heh_bask" class="Checkbox"> Basketball</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_beac" id="heh_beac" class="Checkbox"> Beach volleyball</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_biki" id="heh_biki" class="Checkbox" > Biking</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_bill" id="heh_bill" class="Checkbox"> Billiards / Pool</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_body" id="heh_body" class="Checkbox"> Bodybuilding</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_extr" id="heh_extr" class="Checkbox"> Extreme sports</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_figu" id="heh_figu" class="Checkbox"> Figure skating</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_golf" id="heh_golf" class="Checkbox"> Golf</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_hiki" id="heh_hiki" class="Checkbox" > Hiking</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_hock" id="heh_hock" class="Checkbox"> Hockey</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_holi" id="heh_holi" class="Checkbox"> Holistic Health</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_inli" id="heh_inli" class="Checkbox"> Inline skating</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_mart" id="heh_mart" class="Checkbox"> Martial Arts</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_medi" id="heh_medi" class="Checkbox" > Meditation</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_nutr" id="heh_nutr" class="Checkbox"> Nutrition</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_rugb" id="heh_rugb" class="Checkbox"> Rugby</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_runn" id="heh_runn" class="Checkbox"> Running</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_sail" id="heh_sail" class="Checkbox"> Sailing / Boating</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_scub" id="heh_scub" class="Checkbox"> Scuba diving</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_skat" id="heh_skat" class="Checkbox"> Skateboarding</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_skii" id="heh_skii" class="Checkbox"> Skiing</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_snow" id="heh_snow" class="Checkbox"> Snowboarding</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_socc" id="heh_socc" class="Checkbox"> Soccer / Football</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_surf" id="heh_surf" class="Checkbox"> Surfing</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_swim" id="heh_swim" class="Checkbox"> Swimming</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_tenn" id="heh_tenn" class="Checkbox"> Tennis / Racquet sports</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_tria" id="heh_tria" class="Checkbox"> Triathlon</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_voll" id="heh_voll" class="Checkbox"> Volleyball</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_walk" id="heh_walk" class="Checkbox" > Walking</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_weig" id="heh_weig" class="Checkbox"> Weight Training</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_wres" id="heh_wres" class="Checkbox"> Wrestling</li>
        <li><input type="checkbox" name="areaofinterest" value="heh_yoga" id="heh_yoga" class="Checkbox"> Yoga</li>
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