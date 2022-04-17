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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String doGetProfile(Model model, HttpSession session, @ModelAttribute("userProfile") EditProfileDto editProfileDto) {
		User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		editProfileDto = modelMapper.map(currentUser, EditProfileDto.class);
		model.addAttribute("userProfile", editProfileDto);
		return "site/user/profile";
	}

//    @GetMapping("profile")
//    public String doGetProfile(Model model, @ModelAttribute("editProfile") EditProfile editProfile) {
//        String username = sessionService.get("username", null);
//        User user = userRepo.findByUsername(username);
//        editProfile = modelMapper.map(user, EditProfile.class);
//        model.addAttribute("editProfile", editProfile);
//        return "user/profile";
//    }

//	@PostMapping("profile")
//	public String doPostProfile(Model model, @ModelAttribute("editProfile") @Validated EditProfile editProfile,
//			BindingResult result) throws IOException {
//		if (result.hasErrors()) {
//			System.out.println(result.getAllErrors());
//			model.addAttribute("messageFailed", "Cập nhật thông tin thất bại");
//			return "site/user/profile";
//		}
//		User createdUser = userRepo.findByUsername(sessionService.get("username", null));
//		createdUser.setCreatedDate(new Date());
//		createdUser.setEmail(editProfile.getEmail());
//		createdUser.setFullname(editProfile.getFullname());
//		createdUser.setAddress(editProfile.getAddress());
//		createdUser.setPhoneNumber(editProfile.getPhoneNumber());
//		this.uploadPhoto(createdUser, editProfile.getFilePhoto());
//		User userResult = userRepo.saveAndFlush(createdUser);
//		editProfile = modelMapper.map(userResult, EditProfile.class);
//		model.addAttribute("editProfile", editProfile);
//		model.addAttribute("messageSuccess", "Cập nhật thông tin thành công");
//		return "site/user/profile";
//	}

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

//	@PostMapping("change-pass")
//	public String doPostChangePass(Model model, @RequestParam("oldPassword") String oldPassword,
//			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword) {
//		String username = sessionService.get("username", null);
//		User user = userRepo.findByUsername(username);
//		if (user.getPassword().equals(oldPassword)) {
//			if (newPassword.equals(confirmPassword)) {
//				user.setPassword(newPassword);
//				userRepo.save(user);
//				model.addAttribute("message", "Đổi mật khẩu thành công");
//			} else {
//				model.addAttribute("message", "Mật khẩu mới và xác nhận mật khẩu không khớp");
//			}
//		} else {
////            model.addAttribute("message", "Mật khẩu cũ không đúng");
//			System.out.println("Mật khẩu cũ không đúng");
//		}
//		return "redirect:site/user/change-pass";
//	}

//	@GetMapping("forgot-pass")
//	public String doGetForgotPass() {
//		return "site/user/forgot-pass";
//	}

//	@PostMapping("forgot-pass")
//	public String doPostForgotPass(Model model, @RequestParam("email") String email) {
//		boolean isExistEmail = userRepo.existsByEmail(email);
//		if (isExistEmail) {
//			User user = userRepo.findByEmail(email);
//			String newPassword = RandomStringUtils.randomAlphanumeric(9);
//			user.setPassword(newPassword);
//			mailer.queue(email, "Eshop xin gửi bạn mật khẩu mới", "Mật khẩu mới của bạn là: " + newPassword);
//			userRepo.save(user);
//			model.addAttribute("messageSuccess", "Mật khẩu mới đã được gửi tới email của bạn!");
//		} else {
//			model.addAttribute("messageFailed", "Email không tồn tại");
//		}
//		return "site/user/forgot-pass";
//	}

//	@RequestMapping("order-history")
//	public String doGetOrder() {
//		return "user/order-history";
//	}

//	@RequestMapping("shopping-cart")
//	public String doGetShoppingCart(Model model) {
//		String username = sessionService.get("username", null);
//		User user = userRepo.findByUsername(username);
//		List<Cart> cartList = cartDAO.findByUserId(user.getId());
//		model.addAttribute("cartList", cartList);
//		model.addAttribute("totalUnitPrice", getTotalPrice(cartList));
//		return "site/user/shopping-cart";
//	}

//	@RequestMapping("shopping-cart/add/{slug}")
//	public String addCart(Model model, @PathVariable String slug) {
//		String username = sessionService.get("username", null);
//		if (username == null) {
//			return "redirect:site/user/login";
//		}
//		User user = userRepo.findByUsername(username);
//		Product productAdd = productDAO.findBySlug(slug);
//		if (!cartDAO.existsShoppingCartByUserIdAndProductId(user.getId(), productAdd.getId())) {
//			Cart cart = new Cart();
//			cart.setUser(user);
//			cart.setProduct(productAdd);
//			cart.setQuantity(1);
//			cart.setCreatedDate(new Date());
//			cartDAO.save(cart);
//		} else {
//			Cart cart = cartDAO.findByProductId(productAdd.getId());
//			cart.setQuantity(cart.getQuantity() + 1);
//			cart.setCreatedDate(new Date());
//			cartDAO.flush();
//		}
//		List<Cart> cartList = cartDAO.findByUserId(user.getId());
//		model.addAttribute("cartList", cartList);
//		model.addAttribute("totalUnitPrice", getTotalPrice(cartList));
//		return "site/user/shopping-cart";
//	}

//	@RequestMapping("shopping-cart/remove/{id}")
//	public String removeCart(Model model, @PathVariable String id) {
//		cartDAO.deleteById(Long.valueOf(id));
//		String username = sessionService.get("username", null);
//		User user = userRepo.findByUsername(username);
//		List<Cart> cartList = cartDAO.findByUserId(user.getId());
//		model.addAttribute("cartList", cartList);
//		model.addAttribute("totalUnitPrice", getTotalPrice(cartList));
//		return "site/user/shopping-cart";
//	}

//	@RequestMapping("shopping-cart/order")
//	public String orderCart(Model model) {
//		String username = sessionService.get("username", null);
//		User user = userRepo.findByUsername(username);
//		List<Cart> cartList = cartDAO.findByUserId(user.getId());
//
//		Order newOrder = new Order();
//		newOrder.setOrderCode(RandomStringUtils.randomAlphanumeric(11));
//		newOrder.setUser(user);
//		newOrder.setCreatedDate(new Date());
//		newOrder.setStatus(0);
//		newOrder.setFullname(user.getFullname());
//		newOrder.setAddress(user.getAddress());
//		newOrder.setEmail(user.getEmail());
//		newOrder.setPhoneNumber(user.getPhoneNumber());
//		newOrder.setTotalUnitPrice(getTotalPrice(cartDAO.findByUserId(user.getId())));
//		orderDAO.save(newOrder);
//
//		for (Cart cart : cartList) {
//			OrderDetail newOrderDetail = new OrderDetail();
//			newOrderDetail.setOrder(newOrder);
//			newOrderDetail.setProduct(cart.getProduct());
//			newOrderDetail.setQuantity(cart.getQuantity());
//			newOrderDetail.setTotalUnitPrice(cart.getProduct().getUnitPrice() * cart.getQuantity());
//			orderDetailDAO.save(newOrderDetail);
//		}
//
//		cartDAO.deleteAll();
//		return "site/user/shopping-cart";
//	}

	private void uploadPhoto(User user, MultipartFile filePhoto) throws IOException {
		if (filePhoto.getOriginalFilename() != null && filePhoto.getOriginalFilename().length() > 0) {
			try {
				String path = app.getRealPath("/assets/images/avatar/");
				String name = user.getUsername() + "."
						+ Objects.requireNonNull(filePhoto.getContentType()).split("/")[1];
				user.setPhoto(name);
				filePhoto.transferTo(new File(path + name));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
