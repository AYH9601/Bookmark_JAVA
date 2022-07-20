package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BookDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	// 책 데이터 관련된 쿼리를 실행하는 DAO 메소드
	public int insert(Map<String, Object> map) {
		  return this.sqlSessionTemplate.insert("book.insert", map);
	}

	
	// sqlSessionTemplate의 selectOne 메소드는 null을 반환하게 되며 쿼리 결과가 여러개면
	// TooManyResultsException 이라는 예외를 던진다.
	public Map<String, Object> selectDetail(Map<String, Object> map) {
	    return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	}
	
	
	// sqlSessionTemplate 객체의 update 메소드는 insert 메소드와 사용법이 동일하다.
	public int update(Map<String, Object> map) {  
		return this.sqlSessionTemplate.update("book.update", map);  
	}
	
	
	// sqlSettionTemplate 객체의 delete 메소드는 update 메소드와 사용법이 동일함.
	// 첫번째 파라미터는 쿼리ID, 두번째 파라미터는 쿼리 파라미터이며 반환값은 영향받은 행의 수.
	public int delete(Map<String, Object> map) {  
		return this.sqlSessionTemplate.delete("book.delete", map);  
	}  
	
	
	// 책 목록 데이터베이스에 접속하는 메소드를 작성.
	// 쿼리 결과를 목록으로 받기 위해 sqlSessionTemplate.selectList를 사용할 수 있음.
	// 첫번째 파라미터는 쿼리 ID, 두번째 ID는 쿼리 파라미터.
	// sqlSessionTemplate.selectList 메소드는 결과 집합 목록을 반환함. 따라서 결과 집합 타입인 
	// <Map<String, Object>>의 목록 List 타입으로 읽어들일 수 있음.
	public List<Map<String, Object>> selectList(Map<String, Object> map) {  
		return this.sqlSessionTemplate.selectList("book.select_list", map);  
	}  
	
}