<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
	<head>
		<title>감정 분석 결과</title>
		<style>
			table {
				width: 100%;
			}
			th, td {
				padding: 3px;
				text-align: center;
			}
			
		</style>
	</head>
<body>
	<h1>감정 분석된 결과들</h1>
	<h3>${value} 개수 : ${count} </h3>
	<input name="list" type="button" value="모든 데이터 보기" onclick="location.href='list'"> &nbsp;&nbsp;&nbsp;&nbsp;
	<input name="listPos" type="button" value="긍정 데이터만 보기" onclick="location.href='listBySent?value=positive'"> &nbsp;&nbsp;&nbsp;&nbsp;
	<input name="listNeg" type="button" value="부정 데이터만 보기" onclick="location.href='listBySent?value=negative'"> &nbsp;&nbsp;&nbsp;&nbsp;
	<input name="listNeu" type="button" value="중립 데이터만 보기" onclick="location.href='listBySent?value=neutral'"><br><br>
		<table border="1">
			<th>번호</th><th>감정</th><th>긍정값</th><th>부정값</th><th>중립값</th>
			<c:forEach items="${tests}" var="test">
				<tr>
					<td>${test.id}</td>
					<td>${test.sentiment}</td>
					<td>${test.positive_value}</td>
					<td>${test.negative_value}</td>
					<td>${test.neutral_value}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<input name="write" type="button" style="width:325pt;height:40pt;" value="일기작성" onclick="location.href='write'">
	</body>
</html>