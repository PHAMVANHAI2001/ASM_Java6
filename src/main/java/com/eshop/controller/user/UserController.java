package com.eshop.controller.user;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eshop.constant.SessionConstant;
import com.eshop.dto.EditProfileDto;
import com.eshop.dto.RegisterDto;
import com.eshop.entities.Cart;
import com.eshop.entities.User;
import com.eshop.service.CookieService;
import com.eshop.service.MailerService;
import com.eshop.service.ParamService;
import com.eshop.service.SessionService;
import com.eshop.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
//    Service
	@Autowired
	private UserService userService;
	@Autowired
	CookieService cookieService;
	@Autowired
	ParamService paramService;
	@Autowired
	SessionService sessionService;
	@Autowired
	MailerService mailer;

	@Autowired
	ModelMapper modelMapper;
	@Autowired
	private ServletContext app;

//	public double getTotalPrice(List<Cart> cartList) {
//		double totalPrice = 0;
//		for (Cart cart : cartList) {
//			totalPrice += cart.getProduct().getUnitPrice() * cart.getQuantity();
//		}
//		return totalPrice;
//	}

	public void givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		System.out.println(generatedString);
	}

	@GetMapping("profile")
	public String doGetProfile(Model model, HttpSession session) {
		User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		EditProfileDto userMapper = modelMapper.map(currentUser, EditProfileDto.class);
		model.addAttribute("userRequest", userMapper);
		return "site/user/profile";
	}

	@PostMapping("profile")
	public String doPostProfile(Model model, @Validated @ModelAttribute("userRequest") EditProfileDto userResponse,
			BindingResult errors, RedirectAttributes redirectAttributes, HttpSession session) {
		User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		if (errors.hasErrors()) {
			redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng nhập các thông tin cần thiết");
		}
		this.uploadFile(userResponse, userResponse.getFilePhoto());
		modelMapper.map(userResponse, currentUser);
		userService.editProfile(currentUser);
		model.addAttribute("succeedMessage", "Cập nhật thông tin thành công");
		return "site/user/profile";
	}

	@GetMapping("change-pass")
	public String doGetChangePass() {
		return "site/user/change-pass";
	}

	@RequestMapping("change-pass")
	public String doPostChangePass(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword) {
		userService.changePassword(oldPassword, newPassword, confirmPassword);
		return "redirect:/change-pass";
	}

	public void uploadFile(EditProfileDto user, MultipartFile filePhoto) {
		if (filePhoto.getOriginalFilename() != null && !filePhoto.getOriginalFilename().isBlank()) {
			try {
				String path = new File("src/main/resources/static/assets/images/avatar/").getAbsolutePath();
				String fileName = user.getUsername() + "." + filePhoto.getContentType().split("/")[1];
				user.setPhoto(fileName);
				filePhoto.transferTo(new File(path + "/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
