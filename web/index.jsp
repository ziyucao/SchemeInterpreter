<%--
  Created by IntelliJ IDEA.
  User: knight
  Date: 2018/6/18
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="GBK" %>

<!doctype html>
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
  <script src="public/js/vendor/jquery.js"></script>
  <script src="public/js/vendor/what-input.js"></script>
  <script src="public/js/vendor/foundation.min.js"></script>
  <script src="public/codemirror/lib/codemirror.js"></script>
  <script src="public/codemirror/mode/scheme/scheme.js"></script>
  <script src="public/js/echarts.js"></script>
  <script src="public/js/echarts-gl.js"></script>
</head>
<body>
<div class="main_bar">
  <div class="main_title">Scheme在线编译</div>
  <div class="console_title_btn">
    <span class="button warning console_btn clear_input" id="clear_input">清空</span>
    <span class="button success console_btn run_input" id="run_input">运行</span>
  </div>
  <div class="main_bar_child nav_btn">
    <i class="fa fa-align-justify" id="nav_btn"></i>
    <ul id="data-list" style="display: none">
      <li><a href="https://www.scheme.com/tspl4/" style="text-decoration:none; color:#fff;">Scheme语法说明</a></li>
      <li style="position: relative;">
        夜间模式
        <form style="position: absolute; right: 8px; top:10px;">
          <div class="switch round tiny">
            <input id="mySwitch" class="mySwitch" type="checkbox">
            <label for="mySwitch"></label>
          </div>
        </form>
      </li>
      <li><a href="https://github.com/ziyucao/SchemeInterpreter" style="text-decoration:none; color:#fff;">关于我们</a></li>
    </ul>
  </div>
</div>
<div class="left_main_bar">
  <ul id="data-list">
    <li><a href="https://www.scheme.com/tspl4/" style="text-decoration:none; color:#fff;">Scheme语法说明</a></li>
    <li style="position: relative;">
      夜间模式
      <form style="position: absolute; right: 8px; top:11px;">
        <div class="switch round tiny">
          <input id="mySwitch_nex" class="mySwitch" type="checkbox">
          <label for="mySwitch_nex"></label>
        </div>
      </form>
    </li>
    <li><a href="https://github.com/ziyucao/SchemeInterpreter" style="text-decoration:none; color:#fff;">关于我们</a></li>
  </ul>
</div>
<div class="container">
  <div class="console_title">
    <p class="input_title" onclick="ShowElement(this)">请输入文件名</p>
    <div class="console_title_btn">
      <span class="button warning console_btn clear_input" id="clear_input">清空</span>
      <span class="button success console_btn run_input" id="run_input">运行</span>
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
    <div class="console_block" id="yufa" style="display: block;">
      <div class="console_inner_title">语法分析</div>
      <div class="left_bar">
        <i class="fa fa-trash btn_console"></i>
      </div>
      <div class="right_bar" id="yufa_tree"></div>
    </div>
    <div class="console_block" id="cifa">
      <div class="console_inner_title">词法分析</div>
      <div class="left_bar">
        <i class="fa fa-trash btn_console"></i>
      </div>
      <div class="right_bar"></div>
    </div>
    <div class="console_block" id="cuowu">
      <div class="console_inner_title">错误处理</div>
      <div class="left_bar">
        <i class="fa fa-trash btn_console"></i>
      </div>
      <div class="right_bar"></div>
    </div>
    <div class="console_block" id="jieguo">
      <div class="console_inner_title">代码结果</div>
      <div class="left_bar">
        <i class="fa fa-trash btn_console"></i>
      </div>
      <div class="right_bar"></div>
    </div>
  </div>
  <div class="footer flex_box">
    <div class="box_item select" data-block="yufa">语法分析</div>
    <div class="box_item" data-block="cifa">词法分析</div>
    <div class="box_item" data-block="cuowu">错误处理</div>
    <div class="box_item" data-block="jieguo">代码结果</div>
  </div>
</div>
<script src="public/js/index.js"></script>
<script>
    $(document).foundation();
</script>
</body>
</html>


