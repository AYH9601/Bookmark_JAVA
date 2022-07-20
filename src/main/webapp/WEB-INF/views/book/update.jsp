<%@ page pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>  
<html>  
	<head>  
		<title>책 수정</title>  
	</head>  
	
	<body>  
	<h1>책 수정</h1>  
		<form method="POST">  
			<p>제목 : <input type="text" name="title" value="${ data.title }" /></p>  
			<p>카테고리 : <input type="text" name="category"  value="${ data.category }" /></p>  
			<p>가격 : <input type="text" name="price"  value="${ data.price }" /></p>  
			<p><input type="submit" value="저장" />  
		</form>  
	</body> 
	 
</html> 

<!-- 책 수정은 책 입력에서 화면을 복사해준것이다. 그리고 입력항목의 value항목들은 컨트롤러에서 전달받은 값을 보여주도록 수정되었음. -->
