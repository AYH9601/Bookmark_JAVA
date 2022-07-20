package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 서비스 클래스는 비즈니스 클래스가 위치하는 곳이다. 스프링 MVC구조에서 서비스 클래스는 컨트롤러와 하위 DAO를
// 연결하는 역할을 한다.
// ServiceImpl 클래스는 서비스구현체임.
// 서비스는 DAO를 호출한 결과를 바로 리턴하는 일만 한다.
@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookDao bookDao;
	
	@Override
	public String create(Map<String, Object> map) {
	    int affectRowCount = this.bookDao.insert(map);
	    if (affectRowCount ==  1) {
	        return map.get("book_id").toString();
	    }
	    return null;
	}
	
	
	@Override
	public Map<String, Object> detail(Map<String, Object> map){
	    return this.bookDao.selectDetail(map);
	}

	
	
	//수정의 경우, 입력과는 다르게 PK를 가져오거나 하는 절차를 필요없으므로 그냥 1개의 행이 제대로 영향을 받았는지만 검사하면 됨.
	@Override  
	public boolean edit(Map<String, Object> map) {  
	int affectRowCount = this.bookDao.update(map);  
	return affectRowCount == 1;  
	}
	
	
	//삭제의 경우도 수정과 동일하게 한 개의 행이 제대로 영향을 받았는지만 검사하면 된다.
	@Override  
	public boolean remove(Map<String, Object> map) {  
	int affectRowCount = this.bookDao.delete(map);  
	return affectRowCount == 1;  
	}
	
	
	
	@Override  
	public List<Map<String, Object>> list(Map<String, Object> map){  
	return this.bookDao.selectList(map);  
	}  
}


