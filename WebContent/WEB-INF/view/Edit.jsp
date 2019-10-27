<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*"%>
<%@page import="com.app.dto.SyainDto"%>
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
    <link href="<%=cnt %>/css/lib/modal.css" rel="stylesheet">

    <!-- common.css -->
    <link href="<%=cnt %>/css/assets/common.css" rel="stylesheet">

    <!-- CSS -->
    <link href="<%=cnt %>/css/assets/edit.css" rel="stylesheet">
  </head>

  <body>
    <div id="modal">
      <div id="modal_content">
        <div id="content-list">
          <p>社員リスト</p>
          <div class="participant-list">
            <ol>
              <%
                List<Object> masterList = (List<Object>)request.getAttribute("syainMasterList");
                for(int i=0; i<masterList.size(); i++){
                SyainDto masterDto = (SyainDto)masterList.get(i);
              %>
                  <li>
                    <input type="checkbox" name="chkDelete">
                    <%=masterDto.getName() %>
                    <input type="hidden" class="syainCode" name="syainCode" value="<%=masterDto.getCode() %>">
                  </li>
              <%
                }
              %>
            </ol>
          </div>
        </div>
        <button id="btnModalChoice" class="btn_close">選択</button>
        <button id="btnModalClose" class="btn_close">閉じる</button>
      </div>
    </div>

    <form action="<%=cnt %>/${servretPath}">
      <div class="container">
        <div class="row">
          <button id="return" type="button" class="btn btn-primary btn-lg">戻る</button>
        </div>
        <div class="row">
          <div class="col-7 detail">
            <div class="col-12">
              <label>タイトル:</label>
              <input type="text" name="title" placeholder="タイトル" value="${minutes.name}">
            </div>
            <div class="col-12">
              <label>日時:</label>
              <input type="text" name="date-time" placeholder="日時" value="${minutes.dateTime}">
            </div>
            <div class="col-12">
              <label>場所:</label>
              <input type="text" name="place" placeholder="場所" value="${minutes.place}">
            </div>
          </div>
          <div class="col-5 participant">
            参加者一覧
            <div class="row">
              <div id="participant-list" class="col-md-8 col-lg-9 participant-list">
                <ol>
              <%
                List<Object> list = (List<Object>)request.getAttribute("sankaSyain");
                for(int i=0; i<list.size(); i++){
                SyainDto dto = (SyainDto)list.get(i);
              %>
                  <li>
                    <input type="checkbox" name="chkDelete">
                    <%=dto.getName() %>
                    <input type="hidden" class="syainCode" name="syainCode" value="<%=dto.getCode() %>">
                  </li>
              <%
                }
              %>
                </ol>
              </div>
              <div class="btn-area col-md-4 col-lg-3 text-center">
                <button id="btnAddSyain" type="button" class="btn btn-primary btn-sm">追加</button>
                <button id="btnDeleteSyain" type="button" class="btn btn-primary btn-sm">削除</button>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <textarea name="content">${minutes.content}</textarea>
        </div>
        <div class="row">
          <div class="btn-area col-12 text-center">
            <button id="btnUpdate" type="button" class="btn btn-primary btn-lg">登録</button>
            <button id="btnDelete" type="button" class="btn btn-primary btn-lg">削除</button>
          </div>
        </div>
      </div><!-- /.container -->
      <input type="hidden" name="targetSeqno" value="${minutes.seqno}">
      <input type="hidden" id = "mode" name="mode">
    </form><!-- /form -->

    <!-- javascript -->
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="<%=cnt %>/js/lib/bootstrap.min.js"></script>
    <script src="<%=cnt %>/js/lib/modal.js"></script>


    <!-- assets -->
    <script src="<%=cnt %>/js/assets/Edit.js"></script>

  </body>
</html>