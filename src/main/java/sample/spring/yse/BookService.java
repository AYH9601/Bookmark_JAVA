package sample.spring.yse;

import java.util.List;
import java.util.Map;
// 서비스 인터페이스는 직접 탐색기에서 생성하지 않고 클래스에서 생성한다.
// 서비스 메소드는 서비스 구현체인 ServiceImpl 클래스에 작성한다.
public interface BookService {

	String create(Map<String, Object> map);

	//메소드 시그니쳐. 요기는 그냥 클래스 메소드에서 빨간줄떴을때 클릭해서 생성해주자.
	Map<String, Object> detail(Map<String, Object> map);

	boolean edit(Map<String, Object> map);

	boolean remove(Map<String, Object> map);

	List<Map<String, Object>> list(Map<String, Object> map);

}
