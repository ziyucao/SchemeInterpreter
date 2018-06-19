//获取XMLHttpsRequest
function getXHR() {
    var xmlHttp;
    try {
        xmlHttp = new XMLHttpRequest();
    } catch (e) {
        try {
            xmlHttp = new ActiveXObject("Msxml2.XMLHTT");
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {
                alert("浏览器不支持ajax请求");
                return false;
            }
        }
    }
    return xmlHttp;
}