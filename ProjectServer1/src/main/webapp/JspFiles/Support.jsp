<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="jakarta.servlet.http.Cookie , ru.classes.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="Cp1251">
<link rel="stylesheet" type="text/css" href="/public/style.css">
<title>Поддержка</title>
</head>
<body>
	<header>
		 <div class="header-top">
		        <a href="/" >главная</a>
		        <a href="/Catalog"> Магазин</a>
		         <a href="/Support" >Поддержка</a></span>
		        <div class="header-right">
			        <% if(session.getAttribute("current_name") == null)
				  	{%>
					<a href="/Autorization"> Авторизация\Регистрация</a>
					
					<%} 
			        else{%>
			        Пользователь:<%= GetCookie.GetCookie(request, "name")%>
			        <form method="POST" action="/Autorization">
			        <input  type="submit" value="Выйти из аккаунта" name="kill">
			        </form>
			        <%}%>
		         </div>
	      </div>
    </header>
<div >

</div>
<h1 >Техническая поддержка</h1>
<h2 align="justify" class="page-title">Мы обязательно поможем вам в вашей непростой ситуации!</h2>
<div align="justify" class="page-title pageSup ">
<p>Прежде чем обратиться к специалисту, посмотрите на часто задаваемые вопросы:</p><br>
<ol>
  <li>На какие случаи не распространяется гарантия?</li>
	  <ul>
	   <li>Неисправность устройства вызвана нарушением правил его эксплуатации, транспортировки и хранения.</li>
	   <li>На устройстве отсутствует, нарушен или не читается оригинальный серийный номер.</li>
	   <li>На устройстве отсутствуют или нарушены заводские, или гарантийные пломбы и наклейки.</li>
	   <li>Ремонт, техническое обслуживание или модернизация устройства производились лицами, не уполномоченными на то компанией-производителем.</li>
	   <li>Дефекты устройства вызваны использованием устройства с программным обеспечением, не входящим в комплект поставки устройства, или не одобренным для совместного использования производителем устройства.</li></li>
	  </ul>
	  
   <li>Что делать если нет возможности привезти товар в сервисный центр?</li>
	  <ul>
	   <li>Если это крупногабаритная техника, тогда вы можете обратиться в сервисный центр по телефону и заказать услугу вывоза крупногабаритной техники или вызов мастера на дом (если услуга доступна в вашем регионе).</li>
	   <li>Если ваш товар средних размеров и меньше, то вы также можете обратиться в сервисный центр по телефону и вас проконсультируют удаленно по вопросам неисправной работы товара.</li>
	  </ul>
  <li>Мне требуются драйвера для моего товара, где я могу их получить?</li>
   <ul>
	   <li>Драйвера для телефонов:</li>
	   <li><a href="public/drivers/driver1.txt" download>Драйвер для note10 pro </a></li>
	    <li><a href="public/drivers/driver1.txt" download>Драйвер для xiaomi redmi 4x </a></li>
	     <li>Драйвера для Телевизоров:</li>
	   <li><a href="public/drivers/driver1.txt" download>Драйвер для 32inch tcl 32s527 </a></li>
	    <li><a href="public/drivers/driver1.txt" download>Драйвер для 32" polarline </a></li>
	    <li>Если вы не смогли найти интересующий драйвер, то напишите нам. Мы исправим данную проблему!</li>
	  </ul>
  <li>Куда можно обратиться при неисправности или поломке?</li>
  <ul>
    <li>Вы можете обратиться в сервисный центр, который находится в:</li>
    <li>село Еланка, Быково-Отрогское муниципальное образование, Балаковский район, Саратовская область, Россия,Советская улица, 54</li>
    <li>село Ивановка, Быково-Отрогское муниципальное образование, Балаковский район, Саратовская область, Россия,улица Гагарина, 16</li>
    <li>Вольск, Саратовская область, Россия, 412901, улица Клочкова, 38</li>
    <li>село Натальино, Натальинское муниципальное образование, Балаковский район, Саратовская область, Россия,Вишнёвая улица, 16</li>
    <li>Можно удобно рассмотреть данные места на карте:</li>
  </ul>
<iframe src="https://yandex.ru/map-widget/v1/?um=constructor%3A3bcc962c31a9ec4da8a3c6617af446565e15d6ef587c30f69ddeafb37238d945&amp;source=constructor" width="500" height="400" frameborder="0"></iframe><br>

</ol>
</div>
<h2>Если у вас остались вопросы, свяжитесь с нами одним из удобных для вас способов:</h2>
<div align="justify" class="page-title">
по номеру телефона: 88805676052 (работаем с 8 до 20:00)<br>
по почте: example@mail.ru<br>
Также можете обратиться к нам в социальной сети:<br>
<div class="blockSup">
<a class="" href="https://vk.com"><img  src="/public/logoVK.JPG" alt="Логотип" width="30px"><br></a>
<a class="" href="https://instagram.com"><img  src="/public/logoI.png" alt="Логотип" width="30px"><br></a>
<a class="" href="https://facebook.com"><img  src="/public/logoF.png" alt="Логотип" width="30px"><br></a>
</div>


</div>
<br>
</body>
</html>