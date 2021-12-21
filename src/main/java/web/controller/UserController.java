package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

	private final UserService userService;
	private final RoleService roleService;
	@Autowired
	public UserController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService = roleService;
	}

	@RequestMapping(value = "/users/user.html", method = RequestMethod.GET)
	public String printWelcome(ModelMap model, Principal principal) {
		model.addAttribute("user", userService.findByUsername(principal.getName()));
		return "users/user";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@GetMapping(value = "/")
	public String printWelcome() {
		return "login";
	}

	@GetMapping(value = "/admin/users")
	public String printUser(Model model) {
		model.addAttribute("users", userService.getAllUsers());
		return "/users/users";
	}

	@GetMapping(value = "/admin/users/new")
	public String addUser(Model model) {
		model.addAttribute("user", new User());
		return "/users/new";
	}

	@PostMapping(value = "/admin/users")
	public String create(@ModelAttribute("user") User user) {
		Role role = new Role("ROLE_USER");
		user.addRoleToUser(role);
		userService.saveUser(user);
		return "redirect:/admin/users/";
	}

	@PostMapping(value = "/admin/users/delete/{id}")
	public String deleteUser(@PathVariable("id") int id) {
		userService.removeUserById(id);
		return "redirect:/admin/users/";
	}

	@GetMapping(value = "/admin/users/edit/{id}")
	public String editUser(Model model, @PathVariable("id") int id) {
		model.addAttribute("user", userService.showUser(id));
		return "/users/edit";
	}

	@PostMapping("/admin/users/{id}")
	public String updateUser(@ModelAttribute("user") User user) {
		userService.updateUser(user);
		return "redirect:/admin/users/";
	}
}