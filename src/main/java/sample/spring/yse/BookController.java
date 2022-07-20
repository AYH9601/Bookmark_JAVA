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
	// BookService bookService는 책 입력기능 서비스를 호출하기 위한 서비스 빈.
	// 서비스를 호출하기 위해 BookService에 의존성을 주입한것. 이때 BookService인터페이스가 사용된 것
	@Autowired
	BookService bookService;
	
	
	//입력.
	//create 메소드는 /create 주소가 GET방식으로 입력되었을때 book/create 경로의 뷰를 보여줌.
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
	    return new ModelAndView("book/create");
	}
	
	
	//입력
	//서비스를 이용해 책을 입력하는 컨트롤러 메소드.
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
	//책 상세 페이지 URL이 입력되는 실행되는 메소드.
	//RequestParam 어노테이션에 의해 쿼리 스트링 파라미터를 읽는것이다.
	//스프링은 http 메소드를 구분하지 않고 파라미터를 GET, POST 동일한 방법으로 읽을 수 있게 해준다.
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
	//책 수정화면은 책 입력화면의 화면형식을 그대로 따라가고 데이터베이스에 저장된 데이터만 그려주면 됨.
	//따라서 책 데이터는 상세화면에서 그대로 긁어오고, 뷰는 책 입력 화면을 복사해준다.
	@RequestMapping(value = "/update", method = RequestMethod.GET)  
	public ModelAndView update(@RequestParam Map<String, Object> map) {  
		Map<String, Object> detailMap = this.bookService.detail(map);  
	
		ModelAndView mav = new ModelAndView();  
		mav.addObject("data", detailMap);  
		mav.setViewName("/book/update");  
		return mav;  
	} 
	
	
	//수정2
	//책 수정 "화면"에서 책 수정 "기능"으로 보내주는 파라미터는 총 4개로, 하나는 GET파라미터로 전달되는
	//bookId이며 나머지 세개는 form 태그를 통해 전달되는 title, category,price임.
	//스프링을 http 메소드가 GET인지 POST인지 상관치 않고 @RequestMapping 어노테이션이 있으면
	//무조건 파라미터를 넣어줌. 따라서 파라미터 map 안에는 4개의 데이터가 다 들어있다.
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
