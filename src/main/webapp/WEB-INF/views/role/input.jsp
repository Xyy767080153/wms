<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>WMS-演示版</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/form/jQueryForm.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#editForm").ajaxForm(function (data) {
                if (data.success){
                    showDialog("操作成功!",function () {
                        location.href = "/role/list.do";
                    })
                }
            });

            //手动提交表单
            $(".btn_submit").click(function () {
                //先选中右边所有的选项,再提交表单
                $(".btn_right option").prop("selected",true);
                $("#editForm").submit();
            });

            //权限的移动
            $("#select").click(function () {
                $(".btn_left option:selected").appendTo(".btn_right")
            });
            $("#deselect").click(function () {
                $(".btn_right option:selected").appendTo(".btn_left")
            });
            $("#selectAll").click(function () {
                $(".btn_left option").appendTo(".btn_right")
            });
            $("#deselectAll").click(function () {
                $(".btn_right option").appendTo(".btn_left")
            });

            //去除重复的选项
            var permissionIds = $.map($(".btn_right option"),function (ele) {
                return ele.value;
            });
            $.each($(".btn_left option"),function (i,ele) {
                //选项重复
                if ($.inArray(ele.value,permissionIds) != -1) {
                    $(ele).remove();
                }
            })
        });
    </script>
</head>
<body>
<form id="editForm" action="/role/saveOrUpdate.do" method="post">
    <input type="hidden" name="id" value="${entity.id}">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">角色编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">角色名称</td>
                    <td class="ui_text_lt">
                        <input name="name" value="${entity.name}" class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">角色编码</td>
                    <td class="ui_text_lt">
                        <input name="sn" value="${entity.sn}"
                               class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">权限</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                    <select multiple="true" class="ui_multiselect01 btn_left">
                                        <c:forEach items="${permissions}" var="p">
                                            <option value="${p.id}">${p.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td align="center">
                                    <input type="button" id="select" value="-->" class="left2right"/><br/>
                                    <input type="button" id="selectAll" value="==>" class="left2right"/><br/>
                                    <input type="button" id="deselect" value="<--" class="left2right"/><br/>
                                    <input type="button" id="deselectAll" value="<==" class="left2right"/>
                                </td>
                                <td>
                                    <select name="permissionIds" multiple="true" class="ui_multiselect01 btn_right">
                                        <c:forEach items="${entity.permissions}" var="p">
                                            <option value="${p.id}">${p.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="button" value="确定保存" class="ui_input_btn01 btn_submit"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>
