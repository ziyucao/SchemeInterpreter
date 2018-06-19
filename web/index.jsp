<%--
  Created by IntelliJ IDEA.
  User: knight
  Date: 2018/6/18
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="GBK" %>

<!DOCTYPE HTML>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <title>Scheme在线编译</title>
  <link rel="stylesheet" href="public/css/font-awesome.css">
  <link rel="stylesheet" href="https://cdn.bootcss.com/foundation/5.5.3/css/foundation.min.css">
  <link rel="stylesheet" href="public/codemirror/lib/codemirror.css">
  <link rel="stylesheet" href="public/codemirror/theme/darcula.css">
  <link rel="stylesheet" href="public/codemirror/theme/idea.css">
  <link id="theme" rel="stylesheet" href="public/css/index-idea.css">
  <script src="public/controller/utils.js"></script>
  <script src="public/js/vendor/jquery.js"></script>
  <script src="public/js/vendor/what-input.js"></script>
  <script src="public/js/vendor/foundation.min.js"></script>
  <script src="public/codemirror/lib/codemirror.js"></script>
  <script src="public/codemirror/mode/scheme/scheme.js"></script>
  <script type="text/javascript">
      $(function($) {
          $("#record").append("当前测试页面完成加载。<br/>");
      });
      function getFirstFloorValue(element) {
          $("#record").append("<br/>获取到信息：您将要取得第一级选项信息……");
          $("#record").append("<br/>正在使用ajax为您获取数据，您可以继续停留在页面并进行其他操作。");
          $.ajax({
              url : "value.get",
              type : "POST",
              data : "action=GetFirstFloorValue",
              datatype : "json",
              success : function(data) {
                  $("#record").append("<br/>操作成功，正在为您准备数据……");
                  $(element).empty();
                  $("#record").append("<br/>清除原始数据成功！");
                  var ops = $.parseJSON(data);
                  $("#record").append("<br/>即时数据准备成功！");
                  for ( var i = 0; i < ops.length; i++)
                      $(element).append(
                          "<option value=\"" + ops[i] + "\">" + ops[i]
                          + "</option>");
                  $("#record").append("<br/>更新列表成功！<br/>");
              }
          });

          // 相比之下，此方法比上面的方法更加简洁，上面的方法具有更好的可控性
          /*$.getJSON("value.get", {
              "action" : "GetFirstFloorValue"
          }, function(data) {
              var ml = "";
              $(data).each(function(i) {
                  ml += "<option value="+ data[i] +">" + data[i] + "</option>";
              });
              $(element).html(ml);
          });*/
      }
  </script>
</head>
<body>
<div class="main_bar">
  <div class="main_title">Scheme在线编译</div>
  <div class="console_title_btn">
    <span class="button warning console_btn clear_input">清空</span>
    <span class="button success console_btn run_input">运行</span>
  </div>
  <div class="main_bar_child nav_btn">
    <i class="fa fa-align-justify" id="nav_btn"></i>
    <ul id="data-list" style="display: none">
      <li>Scheme语法说明</li>
      <li style="position: relative;">
        夜间模式
        <form style="position: absolute; right: 8px; top:10px;">
          <div class="switch round tiny">
            <input id="mySwitch" type="checkbox">
            <label for="mySwitch"></label>
          </div>
        </form>
      </li>
      <li>关于我们</li>
    </ul>
  </div>
</div>
<div class="container">
  <div class="console_title">
    <p class="input_title" onclick="ShowElement(this)">请输入文件名</p>
    <div class="console_title_btn">
      <span class="button warning console_btn clear_input">清空</span>
      <span class="button success console_btn run_input">运行</span>
    </div>
  </div>
  <div class="code_input">
    <nav class="tools_bar">
      <div class="tools_btn">{</div>
      <div class="tools_btn">}</div>
      <div class="tools_btn">(</div>
      <div class="tools_btn">)</div>
      <div class="tools_btn">;</div>
      <div class="tools_btn">:</div>
      <div class="tools_btn">.</div>
      <div class="tools_btn">'</div>
      <div class="tools_btn">"</div>
      <div class="tools_btn">=</div>
    </nav>
    <div class="input_inner">
      <textarea class="text_input" id="code_input" row="15"></textarea>
    </div>
  </div>
  <div class="console_log">Console Log</div>
  <div class="code_output">
    <div class="console_block" id="cifa">
      <div class="left_bar">
        <i class="fa fa-trash btn_console"></i>
        <i class="fa fa-expand btn_console"></i>
      </div>
      <div class="right_bar">
        (<br>
        a<br>
        1<br>
        )<br>
      </div>
    </div>
    <div class="console_block" id="yufa" style="display: none;">
      <div class="left_bar">
        <i class="fa fa-trash btn_console"></i>
        <i class="fa fa-expand btn_console"></i>
      </div>
      <div class="right_bar">
        (<br>
        a<br>
        1<br>
        )<br>
      </div>
    </div>
    <div class="console_block" id="yuyi" style="display: none;">
      <div class="left_bar">
        <i class="fa fa-trash btn_console"></i>
        <i class="fa fa-expand btn_console"></i>
      </div>
      <div class="right_bar">
        (<br>
        a<br>
        1<br>
        )<br>
      </div>
    </div>
    <div class="console_block" id="jieguo" style="display: none;">
      <div class="left_bar">
        <i class="fa fa-trash btn_console"></i>
        <i class="fa fa-expand btn_console"></i>
      </div>
      <div class="right_bar">
        (<br>
        a<br>
        1<br>
        )<br>
      </div>
    </div>
  </div>
  <div class="footer flex_box">
    <div class="box_item select" data-block="cifa">词法分析</div>
    <div class="box_item" data-block="yufa">语法分析</div>
    <div class="box_item" data-block="yuyi">语义分析</div>
    <div class="box_item" data-block="jieguo">代码结果</div>
  </div>
</div>
<script src="public/js/index.js"></script>
<script>
    $(document).foundation();
</script>
</body>
</html>

