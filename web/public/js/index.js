//标题栏可修改插件
function ShowElement(element) {
    var oldhtml = element.innerHTML;
    var newobj = document.createElement('input');
    var $newObj = $(newobj);
    $newObj.addClass('title_define');
    newobj.type = 'text';
    newobj.value = oldhtml;
    newobj.onblur = function() {
        element.innerHTML = this.value == oldhtml ? oldhtml : this.value;
        element.setAttribute("ondblclick", "ShowElement(this);");
    }
    element.innerHTML = '';
    element.appendChild(newobj);
    newobj.setSelectionRange(0, oldhtml.length);
    newobj.focus();
    newobj.parentNode.setAttribute("ondblclick", "");
}

//二级菜单选择插件
var $list = $('#data-list');
var list_count = 0;
$('#nav_btn').on('click',function() {
    if(list_count%2 == 0){
        $list.slideDown();
        $('.nav_btn').css('background-color', 'rgb(137, 158, 110)');
        list_count += 1;
    }
    else if(list_count%2 == 1){
        $list.slideUp();
        $('.nav_btn').css('background-color', '');
        list_count += 1;
    }
});

//代码高亮
var editor = CodeMirror.fromTextArea($('#code_input')[0], {
    lineNumbers: true,
    mode: "scheme",
    theme: 'idea',
    lineWrapping: true
});
editor.setOption("mode", "scheme");
editor.setValue('( define a 1 )');

//主题更换
$('.mySwitch').on('click', function(){
    var $switch = $(this);
    if($switch.prop('checked') == true){
        var editor_value = editor.getValue();
        $('.CodeMirror').remove();
        $('#theme').attr('href', 'public/css/index-darcula.css');
        $list.slideUp();
        $('.nav_btn').css('background-color', '');
        list_count += 1;
        editor = CodeMirror.fromTextArea($('#code_input')[0], {
            lineNumbers: true,
            mode: "scheme",
            theme: 'darcula',
            lineWrapping: true
        });
        editor.setValue(editor_value);
    } else {
        var editor_value = editor.getValue();
        $('.CodeMirror').remove();
        $('#theme').attr('href', 'public/css/index-idea.css');
        $list.slideUp();
        $('.nav_btn').css('background-color', '');
        list_count += 1;
        editor = CodeMirror.fromTextArea($('#code_input')[0], {
            lineNumbers: true,
            mode: "scheme",
            theme: 'idea',
            lineWrapping: true
        });
        editor.setValue(editor_value);
    }
});

//词法生成
function showToken(tokenList){
    var myToken = $('#cifa .right_bar');
    myToken[0].innerText = '';
    myToken.append("<table></table>");
    var table = myToken.find('table');
    for(var i = 0; i < tokenList.length; i++){
        var str = tokenList[i]['str'],
            type = tokenList[i]['type'];
        var node = "<tr>"+"<td>"+str+"</td>"+"<td>"+type+"</td>"+"</tr>";
        table.append(node);
    }
}

//语法树绘制
var myChart;
function showTree(myChart, tree){
    myChart.hideLoading();
    myChart.setOption(option = {
        tooltip: {
            trigger: 'item',
            triggerOn: 'mousemove'
        },
        series:[
            {
                type: 'tree',
                data: [tree],
                left: '2%',
                right: '2%',
                top: '8%',
                bottom: '20%',
                symbol: 'emptyCircle',
                orient: 'vertical',
                expandAndCollapse: true,
                label: {
                    normal: {
                        position: 'top',
                        rotate: -90,
                        verticalAlign: 'middle',
                        align: 'right',
                        fontSize: 9
                    }
                },
                leaves: {
                    label: {
                        normal: {
                            position: 'bottom',
                            rotate: -90,
                            verticalAlign: 'middle',
                            align: 'left'
                        }
                    }
                },
                animationDurationUpdate: 750
            }
        ]
    });
}

//按键事件
$('.tools_btn').bind('click', function() {
    var $view = $(this).parents('body'),
        code_str = editor.getValue();
    var tag = $(this)[0].innerText;
    editor.setValue(code_str + tag);
});
$('.run_input').bind('click', function() {
    $('.console_block').hide();
    $('#yufa').show(500);
    var $box_item = $('.box_item');
    $box_item.removeClass('select');
    $($box_item[0]).addClass('select');

    var $view = $(this).parents('body'),
        $output = $('.console_block .right_bar'),
        code = editor.getValue();

    $.ajax({
        type: "POST",
        data: code,
        url: "http://182.254.220.56:8080/Scheme/scheme",
        success:function(data){
            data = JSON.parse(data);
            if(data.ErrorInfo){
                var errInfo = data.ErrorInfo;
                $output[2].innerText = errInfo;
                $('.console_block').hide();
                $box_item.removeClass('select');
                $($box_item[3]).addClass('select');
                $('#cuowu').show(500);
            } else {
                $output[2].innerText = "No Error，All code accepted.";
            }
            if(data.errPos){
                var errPos = data.ErrorPosition;
            }
            var result = data.Result,
                scheme_result = data.SchemeResult,
                tokenList = data.TokenList,
                tree = data.tree;
            $output[3].innerText = result;

            if (myChart != null && myChart != "" && myChart != undefined) {
                myChart.dispose();
            }
            myChart = echarts.init(document.getElementById('yufa_tree'));
            myChart.showLoading();
            showToken(tokenList);
            showTree(myChart, tree);
        },
        error:function(){
            alert("输出出错！")
        }
    });
});
$('.clear_input').bind('click', function() {
    var $view = $(this).parents('body'),
        $output = $('.console_block .right_bar');
    editor.setValue('');
    for(var i = 0; i < 4; i++){
        $output[i].innerHTML = '';
    }
});
$('.box_item').bind('click', function() {
    var $box_item = $('.box_item'),
        $view = $(this).parents('body'),
        $item = $(this);
    $box_item.removeClass('select');
    $item.addClass('select');
    var data_block = '#' + $item.data('block');
    $view.find('.console_block').hide();
    $view.find(data_block).show(500);
});
$('.btn_console').bind('click', function() {
    var $view = $(this).parents('body'),
        $output = $('.console_block .right_bar'),
        $btn = $(this);
    for(var i = 0; i < 4; i++){
        $output[i].innerHTML = '';
    }
});