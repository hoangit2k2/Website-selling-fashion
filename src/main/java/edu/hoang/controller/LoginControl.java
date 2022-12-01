package edu.hoang.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hoang.entity.Account;
import edu.hoang.service.AuthService;
import edu.hoang.service.UserService;

@Controller
public class LoginControl {

	//_______________________________________________ PAGE
	@GetMapping("/page/{page}") public String getPage(@PathVariable("page") String page) {
		return String.format("/page_auth/%s/home", page);
	}
	
	@GetMapping("/secutity/myInfo") public String getInfo() {
		return "/security/info";
	}
	
	//_______________________________________________ SECURITY - LOGIN
	@RequestMapping("/security/login/form") public String form() {
		return "security/login";
	}
	
	@RequestMapping("/login/success")
	public String success(HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		request.setAttribute("message", principal.getName() + " đăng nhập thành công!");
		return "redirect:/product/list";
	}

	@RequestMapping("/login/failed")
	public String failed(HttpServletRequest request) {
		request.setAttribute("message", "Sai thông tin tài khoản!");
		return "/security/login";
	}

	@RequestMapping("/login/error")
	public String error(HttpServletRequest request) {
		request.setAttribute("message", "Lỗi đăng nhập!");
		return "/security/login";
	}
	
	@RequestMapping("/login/error/role")
	public String notAccess(HttpServletRequest request) {
		String message = String.format("<a href='/secutity/myInfo'><b>%s</b></a> không có quyền truy cập trang này", request.getUserPrincipal().getName());
		request.setAttribute("message", message);
		return "/security/login";
	}
	
	//_______________________________________________ SECURITY - LOGOUT
	@RequestMapping("/logout/success")
	public String logout(HttpServletRequest request) {
		request.setAttribute("message", "đăng xuất thành công!");
		return "redirect:/product/list";
	}
	@Autowired 
	AuthService userService;
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2){
		userService.loginFormOAuth2(oauth2);
	return "forward:/product/list";
	}
	@RequestMapping("/login/login1")
	public String login111() {
		return "/security/login1";
	}
}
