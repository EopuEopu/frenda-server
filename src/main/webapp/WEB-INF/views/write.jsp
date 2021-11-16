<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>일기 감정 분석</title>
		<script>
			function complete() {
				alert("등록되었습니다");
			}
		</script>
	</head>
	
	<body>
		<h1>일기 감정 분석 테스트 페이지</h1>
		<form action="diaryWrite.do" method="post">
			<textarea name="diary" rows="10" cols="50"></textarea>
			<br>
			<br>
			<input name="submit" type="submit" style="width:325pt;height:40pt;" value="작성완료" onclick="complete();">
		</form>
		<br>
			<input name="showList" type="button" style="width:325pt;height:40pt;" value="결과보기" onclick="location.href='list'">
	</body>
</html>