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
	@Autowired
	BookService bookService;
	
	
	//입력
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
	    return new ModelAndView("book/create");
	}
	
	
	//입력
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
	
	
	//상세 페이지
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
	
	
	//수정
	@RequestMapping(value = "/update", method = RequestMethod.GET)  
	public ModelAndView update(@RequestParam Map<String, Object> map) {  
		Map<String, Object> detailMap = this.bookService.detail(map);  
	
		ModelAndView mav = new ModelAndView();  
		mav.addObject("data", detailMap);  
		mav.setViewName("/book/update");  
		return mav;  
	} 
	
	
	//수정2
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
	
	
	//삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)  
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {  
		ModelAndView mav = new ModelAndView();  
	
		//삭제가 성공했는지 확인.
		boolean isDeleteSuccess = this.bookService.remove(map);  
		if (isDeleteSuccess) {
		//삭제 성공했으면 상세페이지가 없으므로 목록으로 리다이렉트.	
		mav.setViewName("redirect:/list");  
		}else {  
		String bookId = map.get("bookId").toString();
		//삭제 실패 시 다시 상세 페이지로 이동.
		mav.setViewName("redirect:/detail?bookId=" + bookId);  
		}  
	
		return mav;  
	}
	
	
	@RequestMapping(value = "list")  
	public ModelAndView list(@RequestParam Map<String, Object> map) {  

		//책 목록을 데이터베이스에서 가지고 온다.
		List<Map<String, Object>> list = this.bookService.list(map);  
	
		ModelAndView mav = new ModelAndView();  
		//데이터를 뷰에 전달할 수 있게 mav 객체에 넣는다.
		mav.addObject("data", list);  
		
		if (map.containsKey("keyword")) {  
			mav.addObject("keyword", map.get("keyword"));  
		}  
		
		//뷰를 리턴
		mav.setViewName("/book/list");  
		return mav;  
	}
}
