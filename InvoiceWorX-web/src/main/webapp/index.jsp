<!DOCTYPE html>
<html>
   <head>
       <title>Invoice WorX</title>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">

       <link rel="stylesheet" href="css/login.css">    
   </head>

   <body>

       <header>
           <h1>Invoice <i><font style="color: #008dde">worX</font></i></h1>
       </header>

       <section class="loginform">
           <form action="<%=request.getContextPath()%>/SecurityServlet" method="post" >
               <p align="center">${sessionScope.success_msg}</p>
               <input type="hidden" name="action" value="login"/>
                   <p>
                       <input type="text"
                              required
                              value=""
                              name="emailAddress"
                              placeholder="Username" required />
                   </p>
                   <p>
                       <input type="password"
                              required
                              value="password"
                              name="password"
                              placeholder="Password" required />
                   </p>
                   <p>
                       <a href="<%=request.getContextPath()%>/UserServlet?action=prepare-registration">Create a new account?</a>  &nbsp; | &nbsp;
                       <a href="<%=request.getContextPath()%>/SecurityServlet?action=resetPassword">Forgot Password?</a>
                   </p>
                   <p>
                       <input type="submit" value="Login" />
                   </p>
               
           </form>

       </section>
   </body>
</html>