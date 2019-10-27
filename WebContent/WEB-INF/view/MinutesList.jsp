<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*"%>
<%@page import="com.app.dto.MinutesDto"%>
<%
    String cnt = request.getContextPath();
%>

<!doctype html>
<html lang="ja">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">
    <title>Starter Template for Bootstrap</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/starter-template/">
    <!-- Bootstrap core CSS -->
    <link href="<%=cnt %>/css/lib/bootstrap.min.css" rel="stylesheet">

    <!-- common.css -->
    <link href="<%=cnt %>/css/assets/common.css" rel="stylesheet">

    <!-- CSS -->
  </head>
  <head>
  <link href="<%=cnt %>/css/assets/index.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">
      <div class="row">
        <button id="btnNew" type="button" class="btn btn-primary btn-lg">新規作成</button>
      </div>
      <div class="row">
        <div class="minutes-list">
          <ol>
        <%
          List<Object> list = (List<Object>)request.getAttribute("minutesList");
          for(int i=0; i<list.size(); i++){
            MinutesDto dto = (MinutesDto)list.get(i);
        %>
              <li>
                <%= dto.getName()%>
                <input type="hidden" name="seqno" value="<%= dto.getSeqno()%>">
              </li>
        <%
          }
        %>
          </ol>
        </div>
      </div>
    </div><!-- /.container -->
    <form action="<%=cnt %>/${servretPath}">
      <input type="hidden" id="targetSeqno" name="targetSeqno">
      <input type="hidden" id="mode" name="mode">
    </form>

    <!-- javascript -->
    <script src="<%=cnt %>/js/lib/bootstrap.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

    <!-- assets -->
    <script src="<%=cnt %>/js/assets/MinutesList.js"></script>

  </body>
</html>