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
	
	// å ������ ���õ� ������ �����ϴ� DAO �޼ҵ�
	public int insert(Map<String, Object> map) {
		  return this.sqlSessionTemplate.insert("book.insert", map);
	}

	
	// sqlSessionTemplate�� selectOne �޼ҵ�� null�� ��ȯ�ϰ� �Ǹ� ���� ����� ��������
	// TooManyResultsException �̶�� ���ܸ� ������.
	public Map<String, Object> selectDetail(Map<String, Object> map) {
	    return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	}
	
	
	// sqlSessionTemplate ��ü�� update �޼ҵ�� insert �޼ҵ�� ������ �����ϴ�.
	public int update(Map<String, Object> map) {  
		return this.sqlSessionTemplate.update("book.update", map);  
	}
	
	
	// sqlSettionTemplate ��ü�� delete �޼ҵ�� update �޼ҵ�� ������ ������.
	// ù��° �Ķ���ʹ� ����ID, �ι�° �Ķ���ʹ� ���� �Ķ�����̸� ��ȯ���� ������� ���� ��.
	public int delete(Map<String, Object> map) {  
		return this.sqlSessionTemplate.delete("book.delete", map);  
	}  
	
	
	// å ��� �����ͺ��̽��� �����ϴ� �޼ҵ带 �ۼ�.
	// ���� ����� ������� �ޱ� ���� sqlSessionTemplate.selectList�� ����� �� ����.
	// ù��° �Ķ���ʹ� ���� ID, �ι�° ID�� ���� �Ķ����.
	// sqlSessionTemplate.selectList �޼ҵ�� ��� ���� ����� ��ȯ��. ���� ��� ���� Ÿ���� 
	// <Map<String, Object>>�� ��� List Ÿ������ �о���� �� ����.
	public List<Map<String, Object>> selectList(Map<String, Object> map) {  
		return this.sqlSessionTemplate.selectList("book.select_list", map);  
	}  
	
}