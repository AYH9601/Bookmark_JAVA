package sample.spring.yse;

import java.util.List;
import java.util.Map;
// ���� �������̽��� ���� Ž���⿡�� �������� �ʰ� Ŭ�������� �����Ѵ�.
// ���� �޼ҵ�� ���� ����ü�� ServiceImpl Ŭ������ �ۼ��Ѵ�.
public interface BookService {

	String create(Map<String, Object> map);

	//�޼ҵ� �ñ״���. ���� �׳� Ŭ���� �޼ҵ忡�� �����ٶ����� Ŭ���ؼ� ����������.
	Map<String, Object> detail(Map<String, Object> map);

	boolean edit(Map<String, Object> map);

	boolean remove(Map<String, Object> map);

	List<Map<String, Object>> list(Map<String, Object> map);

}
