package com.eshop.controller.user;

import com.eshop.dto.EditProfile;
import com.eshop.dto.UserRegister;
import com.eshop.entities.*;
import com.eshop.jpaRepository.*;
import com.eshop.service.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/")
public class UserController {

//	Repository
	@Autowired
	UserRepository userRepo;
	@Autowired
	CartRepository cartDAO;
	@Autowired
	ProductRepository productDAO;
	@Autowired
	OrderRepository orderDAO;
	@Autowired
	OrderDetailRepository orderDetailDAO;

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

	public double getTotalPrice(List<Cart> cartList) {
		double totalPrice = 0;
		for (Cart cart : cartList) {
			totalPrice += cart.getProduct().getUnitPrice() * cart.getQuantity();
		}
		return totalPrice;
	}

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

	@GetMapping("login")
	public String doGetLogin(Model model) {
		model.addAttribute("userRequest", new User());
		return "site/user/login";
	}

//	@PostMapping("login")
//	public String doPostLogin(Model model, @ModelAttribute("userRequest") User userRequest, HttpSession session) {
//		User userReponse = userService.doLogin(userRequest.getUsername(), userRequest.getPassword());
//		if (userReponse != null) {
//			session.setAttribute("currentUser", userReponse);
//			System.out.println(session.getAttribute("currentUser"));
//			return "redirect:/home";
//		} else {
//			model.addAttribute("messageFailed", "Đăng nhập thất bại");
//			return "redirect:/login";
//		}
//	}

	@RequestMapping("logout")
	public String doGetLogout(HttpSession session) {
		session.removeAttribute("currentUser");;
		return "redirect:/home";
	}

	@GetMapping("register")
	public String doGetRegister(Model model) {
		model.addAttribute("userRequest", new UserRegister());
		return "site/user/register";
	}

	@PostMapping("register")
	public String doPostRegister(Model model, @ModelAttribute("userRequest") @Validated UserRegister register,
			BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("messageFailed", "Đăng Ký Thất Bại");
			return "user/register";
		}

		if (userService.doRegister(register) != null) {
			model.addAttribute("messageSuccess", "Đăng Ký Thành Công");
		} else {
			model.addAttribute("messageFailed", "Đăng Ký Thất Bại");
		}
		return "site/user/register";
	}

	@GetMapping("profile")
	public String doGetProfile(Model model, @ModelAttribute("userRequest") EditProfile editProfile) {
		model.addAttribute("userRequest", userService.doGetProfile(editProfile));
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

	@PostMapping("profile")
	public String doPostProfile(Model model, @ModelAttribute("editProfile") @Validated EditProfile editProfile,
			BindingResult result) throws IOException {
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());
			model.addAttribute("messageFailed", "Cập nhật thông tin thất bại");
			return "site/user/profile";
		}
		User createdUser = userRepo.findByUsername(sessionService.get("username", null));
		createdUser.setCreatedDate(new Date());
		createdUser.setEmail(editProfile.getEmail());
		createdUser.setFullname(editProfile.getFullname());
		createdUser.setAddress(editProfile.getAddress());
		createdUser.setPhoneNumber(editProfile.getPhoneNumber());
		this.uploadPhoto(createdUser, editProfile.getFilePhoto());
		User userResult = userRepo.saveAndFlush(createdUser);
		editProfile = modelMapper.map(userResult, EditProfile.class);
		model.addAttribute("editProfile", editProfile);
		model.addAttribute("messageSuccess", "Cập nhật thông tin thành công");
		return "site/user/profile";
	}

	@GetMapping("change-pass")
	public String doGetChangePass() {
		return "site/user/change-pass";
	}

	@PostMapping("change-pass")
	public String doPostChangePass(Model model, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword) {
		String username = sessionService.get("username", null);
		User user = userRepo.findByUsername(username);
		if (user.getPassword().equals(oldPassword)) {
			if (newPassword.equals(confirmPassword)) {
				user.setPassword(newPassword);
				userRepo.save(user);
				model.addAttribute("message", "Đổi mật khẩu thành công");
			} else {
				model.addAttribute("message", "Mật khẩu mới và xác nhận mật khẩu không khớp");
			}
		} else {
//            model.addAttribute("message", "Mật khẩu cũ không đúng");
			System.out.println("Mật khẩu cũ không đúng");
		}
		return "redirect:site/user/change-pass";
	}

	@GetMapping("forgot-pass")
	public String doGetForgotPass() {
		return "site/user/forgot-pass";
	}

	@PostMapping("forgot-pass")
	public String doPostForgotPass(Model model, @RequestParam("email") String email) {
		boolean isExistEmail = userRepo.existsByEmail(email);
		if (isExistEmail) {
			User user = userRepo.findByEmail(email);
			String newPassword = RandomStringUtils.randomAlphanumeric(9);
			user.setPassword(newPassword);
			mailer.queue(email, "Eshop xin gửi bạn mật khẩu mới", "Mật khẩu mới của bạn là: " + newPassword);
			userRepo.save(user);
			model.addAttribute("messageSuccess", "Mật khẩu mới đã được gửi tới email của bạn!");
		} else {
			model.addAttribute("messageFailed", "Email không tồn tại");
		}
		return "site/user/forgot-pass";
	}

	@RequestMapping("order-history")
	public String doGetOrder() {
		return "user/order-history";
	}

	@RequestMapping("shopping-cart")
	public String doGetShoppingCart(Model model) {
		String username = sessionService.get("username", null);
		User user = userRepo.findByUsername(username);
		List<Cart> cartList = cartDAO.findByUserId(user.getId());
		model.addAttribute("cartList", cartList);
		model.addAttribute("totalUnitPrice", getTotalPrice(cartList));
		return "site/user/shopping-cart";
	}

	@RequestMapping("shopping-cart/add/{slug}")
	public String addCart(Model model, @PathVariable String slug) {
		String username = sessionService.get("username", null);
		if (username == null) {
			return "redirect:site/user/login";
		}
		User user = userRepo.findByUsername(username);
		Product productAdd = productDAO.findBySlug(slug);
		if (!cartDAO.existsShoppingCartByUserIdAndProductId(user.getId(), productAdd.getId())) {
			Cart cart = new Cart();
			cart.setUser(user);
			cart.setProduct(productAdd);
			cart.setQuantity(1);
			cart.setCreatedDate(new Date());
			cartDAO.save(cart);
		} else {
			Cart cart = cartDAO.findByProductId(productAdd.getId());
			cart.setQuantity(cart.getQuantity() + 1);
			cart.setCreatedDate(new Date());
			cartDAO.flush();
		}
		List<Cart> cartList = cartDAO.findByUserId(user.getId());
		model.addAttribute("cartList", cartList);
		model.addAttribute("totalUnitPrice", getTotalPrice(cartList));
		return "site/user/shopping-cart";
	}

	@RequestMapping("shopping-cart/remove/{id}")
	public String removeCart(Model model, @PathVariable String id) {
		cartDAO.deleteById(Long.valueOf(id));
		String username = sessionService.get("username", null);
		User user = userRepo.findByUsername(username);
		List<Cart> cartList = cartDAO.findByUserId(user.getId());
		model.addAttribute("cartList", cartList);
		model.addAttribute("totalUnitPrice", getTotalPrice(cartList));
		return "site/user/shopping-cart";
	}

	@RequestMapping("shopping-cart/order")
	public String orderCart(Model model) {
		String username = sessionService.get("username", null);
		User user = userRepo.findByUsername(username);
		List<Cart> cartList = cartDAO.findByUserId(user.getId());

		Order newOrder = new Order();
		newOrder.setOrderCode(RandomStringUtils.randomAlphanumeric(11));
		newOrder.setUser(user);
		newOrder.setCreatedDate(new Date());
		newOrder.setStatus(0);
		newOrder.setFullname(user.getFullname());
		newOrder.setAddress(user.getAddress());
		newOrder.setEmail(user.getEmail());
		newOrder.setPhoneNumber(user.getPhoneNumber());
		newOrder.setTotalUnitPrice(getTotalPrice(cartDAO.findByUserId(user.getId())));
		orderDAO.save(newOrder);

		for (Cart cart : cartDAO.findByUserId(user.getId())) {
			OrderDetail newOrderDetail = new OrderDetail();
			newOrderDetail.setOrder(newOrder);
			newOrderDetail.setProduct(cart.getProduct());
			newOrderDetail.setQuantity(cart.getQuantity());
			newOrderDetail.setTotalUnitPrice(cart.getProduct().getUnitPrice() * cart.getQuantity());
			orderDetailDAO.save(newOrderDetail);
		}

		cartDAO.deleteAll();
		return "site/user/shopping-cart";
	}

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
