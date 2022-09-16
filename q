[1mdiff --git a/src/main/resources/templates/fragments/navbar.html b/src/main/resources/templates/fragments/navbar.html[m
[1mindex df210bf..e6a0e5a 100644[m
[1m--- a/src/main/resources/templates/fragments/navbar.html[m
[1m+++ b/src/main/resources/templates/fragments/navbar.html[m
[36m@@ -4,7 +4,7 @@[m
 [m
 		<div class="nav-centered justify-content-around shadow mb-5 bg-dark d-flex justify-content-around w-100" >[m
 [m
[31m-			<nav th:fragment="navbar(current)" class="navbar navbar-expand-lg navbar-dark bg-dark">[m
[32m+[m			[32m<nav th:fragment="navbar(current)" class="navbar navbar-expand-lg navbar-dark bg-dark ">[m
 [m
 				<div class="nav-wrapper container-fluid ">[m
 [m
[36m@@ -18,7 +18,7 @@[m
 [m
 				    <div class="collapse navbar-collapse" id="navbarNavDropdown">[m
 [m
[31m-						<ul class="navbar-nav ">[m
[32m+[m						[32m<ul class="navbar-nav">[m
 [m
 					        <li class="nav-item">[m
 [m
[36m@@ -34,12 +34,12 @@[m
 					        </li>[m
 						</ul>[m
 				    </div>[m
[31m-						<div class="text-end">[m
[32m+[m						[32m<div class="text-end" id="navbarNavDropdown">[m
 						 [m
 						<ul class="navbar-nav">[m
 					        <li class="nav-item">[m
 	[m
[31m-						        	<a class="nav-link fst-italic" th:classAppend="${current == 'homePage'} ? active : not-active" href="/login">Accedi</a>[m
[32m+[m						[41m        [m	[32m<a class="nav-link fst-italic" href="/login">Accedi</a>[m
 	[m
 						   </li>	[m
 						   				    [m
