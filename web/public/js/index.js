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
editor.setValue('(a 1)');

//主题更换
$('#mySwitch').on('click', function(){
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


//按键事件
$('.tools_btn').bind('click', function() {
    var $view = $(this).parents('body'),
        code_str = editor.getValue();
    var tag = $(this)[0].innerText;
    editor.setValue(code_str + tag);
});
$('.run_input').bind('click', function() {
    var $view = $(this).parents('body'),
        $output = $('.console_block .right_bar'),
        $code_input = editor.getValue();
    var code = {
        "code_input": $code_input
    };
    $.ajax({
        type: "POST",
        data: code,
        url: "",
        success:function(data){

        },
        error:function(){
            alert("输出出错！")
        }
    });

    $output[0].innerText = editor.getValue();
    $output[1].innerText = editor.getValue();
    $output[2].innerText = editor.getValue();
    $output[3].innerText = editor.getValue();
});
$('.clear_input').bind('click', function() {
    var $view = $(this).parents('body'),
        $output = $('.console_block .right_bar');
    editor.setValue('');
    for(var i = 0; i < 4; i++){
        $output[i].innerText = '';
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
        $output[i].innerText = '';
    }
});