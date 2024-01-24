package com.sist.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FoodController {
	// food/detail_before.do
	@GetMapping("food/detail_before.do")
	public String food_detail_before(int fno, HttpServletResponse response, RedirectAttributes ra) // RedirectAttributes : ? 안붙여줘도 된다.
	{
		ra.addAttribute("fno", fno);
		return "redirect:../food/detail.do";
	}
}
