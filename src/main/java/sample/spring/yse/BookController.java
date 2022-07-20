package sample.spring.yse;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
	// BookService bookService�� å �Է±�� ���񽺸� ȣ���ϱ� ���� ���� ��.
	// ���񽺸� ȣ���ϱ� ���� BookService�� �������� �����Ѱ�. �̶� BookService�������̽��� ���� ��
	@Autowired
	BookService bookService;
	
	
	//�Է�.
	//create �޼ҵ�� /create �ּҰ� GET������� �ԷµǾ����� book/create ����� �並 ������.
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
	    return new ModelAndView("book/create");
	}
	
	
	//�Է�
	//���񽺸� �̿��� å�� �Է��ϴ� ��Ʈ�ѷ� �޼ҵ�.
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
	    ModelAndView mav = new ModelAndView();

	    String bookId = this.bookService.create(map);
	    if (bookId == null) {
	        mav.setViewName("redirect:/create");
	    }else {
	        mav.setViewName("redirect:/detail?bookId=" + bookId); 
	    }  

	    return mav;
	}
	
	
	//�� ������
	//å �� ������ URL�� �ԷµǴ� ����Ǵ� �޼ҵ�.
	//RequestParam ������̼ǿ� ���� ���� ��Ʈ�� �Ķ���͸� �д°��̴�.
	//�������� http �޼ҵ带 �������� �ʰ� �Ķ���͸� GET, POST ������ ������� ���� �� �ְ� ���ش�.
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
	    Map<String, Object> detailMap = this.bookService.detail(map);

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("data", detailMap);
	    String bookId = map.get("bookId").toString();
	    mav.addObject("bookId", bookId);
	    mav.setViewName("/book/detail");
	    return mav;
	}
	
	
	//����
	//å ����ȭ���� å �Է�ȭ���� ȭ�������� �״�� ���󰡰� �����ͺ��̽��� ����� �����͸� �׷��ָ� ��.
	//���� å �����ʹ� ��ȭ�鿡�� �״�� �ܾ����, ��� å �Է� ȭ���� �������ش�.
	@RequestMapping(value = "/update", method = RequestMethod.GET)  
	public ModelAndView update(@RequestParam Map<String, Object> map) {  
		Map<String, Object> detailMap = this.bookService.detail(map);  
	
		ModelAndView mav = new ModelAndView();  
		mav.addObject("data", detailMap);  
		mav.setViewName("/book/update");  
		return mav;  
	} 
	
	
	//����2
	//å ���� "ȭ��"���� å ���� "���"���� �����ִ� �Ķ���ʹ� �� 4����, �ϳ��� GET�Ķ���ͷ� ���޵Ǵ�
	//bookId�̸� ������ ������ form �±׸� ���� ���޵Ǵ� title, category,price��.
	//�������� http �޼ҵ尡 GET���� POST���� ���ġ �ʰ� @RequestMapping ������̼��� ������
	//������ �Ķ���͸� �־���. ���� �Ķ���� map �ȿ��� 4���� �����Ͱ� �� ����ִ�.
	@RequestMapping(value = "update", method = RequestMethod.POST)  
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {  
		ModelAndView mav = new ModelAndView();  
	
		boolean isUpdateSuccess = this.bookService.edit(map);  
		if (isUpdateSuccess) {  
		String bookId = map.get("bookId").toString();  
		mav.setViewName("redirect:/detail?bookId=" + bookId);  
		}else {  
		mav = this.update(map);  
		}  
	
		return mav;  
	}  
	
	
	//����
	@RequestMapping(value = "/delete", method = RequestMethod.POST)  
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {  
		ModelAndView mav = new ModelAndView();  
	
		//������ �����ߴ��� Ȯ��.
		boolean isDeleteSuccess = this.bookService.remove(map);  
		if (isDeleteSuccess) {
		//���� ���������� ���������� �����Ƿ� ������� �����̷�Ʈ.	
		mav.setViewName("redirect:/list");  
		}else {  
		String bookId = map.get("bookId").toString();
		//���� ���� �� �ٽ� �� �������� �̵�.
		mav.setViewName("redirect:/detail?bookId=" + bookId);  
		}  
	
		return mav;  
	}
	
	
	@RequestMapping(value = "list")  
	public ModelAndView list(@RequestParam Map<String, Object> map) {  

		//å ����� �����ͺ��̽����� ������ �´�.
		List<Map<String, Object>> list = this.bookService.list(map);  
	
		ModelAndView mav = new ModelAndView();  
		//�����͸� �信 ������ �� �ְ� mav ��ü�� �ִ´�.
		mav.addObject("data", list);  
		
		if (map.containsKey("keyword")) {  
			mav.addObject("keyword", map.get("keyword"));  
		}  
		
		//�並 ����
		mav.setViewName("/book/list");  
		return mav;  
	}
}
