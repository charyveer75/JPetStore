<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/views/modules/common/IncludeTop.jsp" %>

<jsp:useBean id="account" class="com.nevercome.jpetstore.domain.model.Account" />

<%--<script type="text/javascript" src="${ctxJs}/account/checkUserId.js"></script>--%>

<div id="Catalog">
    <%--<stripes:form--%>
    <%--beanclass="org.mybatis.jpetstore.web.actions.AccountActionBean"--%>
    <%--focus="">--%>
    <%--<h3>User Information</h3>--%>
    <%--<table>--%>
    <%--<tr>--%>
    <%--<td>User ID:</td>--%>
    <%--<td>${account.userId}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>New password:</td>--%>
    <%--<td><stripes:text name="password"/></td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>Repeat password:</td>--%>
    <%--<td><stripes:text name="repeatedPassword"/></td>--%>
    <%--</tr>--%>
    <%--</table>--%>
    <%--<%@ include file="IncludeAccountFields.jsp" %>--%>
    <%--<stripes:submit name="editAccount" value="Save Account Information"/>--%>
    <%--</stripes:form>--%>

    <form action="${ctx}/account/editAccount">
        <h3>User Information</h3>
        <table>
            <tr>
                <td>User ID:</td>
                <td><input type="text" readonly="readonly" value="${sessionScope.account.userId}"></td>
            </tr>
            <tr>
                <td>New password:</td>
                <td><input type="text" name="account.password" /></td>
            </tr>
            <tr>
                <td>Repeat password:</td>
                <td><input type="text" name="repeatedPassword"/></td>
            </tr>
        </table>
        <%@ include file="IncludeAccountFields.jsp" %>
        <input type="submit" value="Save Account Information"/>
    </form>

    <%--<stripes:link--%>
            <%--beanclass="org.mybatis.jpetstore.web.actions.OrderActionBean"--%>
            <%--event="listOrders">My Orders--%>
    <%--</stripes:link>--%>
    <a href="${ctx}/order/listOrders">My Orders</a>
</div>

<%@ include file="/WEB-INF/views/modules/common/IncludeBottom.jsp" %>
