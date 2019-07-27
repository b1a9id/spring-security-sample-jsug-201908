package com.b1a9idps.springsecuritysamplejsug201908.controller;

import com.b1a9idps.springsecuritysamplejsug201908.enums.Gender;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;
import com.b1a9idps.springsecuritysamplejsug201908.form.UserCreateForm;
import com.b1a9idps.springsecuritysamplejsug201908.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/users")
@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ModelAttribute("form")
	public UserCreateForm setUpUserCreateForm() {
		return new UserCreateForm();
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user/list";
	}

	@GetMapping("{id}")
	public String get(@PathVariable Integer id, Model model) {
		model.addAttribute("user", userService.findOne(id));
		return "user/detail";
	}

	@GetMapping("create")
	public String create(Model model) {
		model.addAttribute("genders", Gender.values());
		model.addAttribute("roles", Role.values());
		return "user/create";
	}

	@PostMapping("create")
	public String create(
			@ModelAttribute("form") @Validated UserCreateForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			Model model) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("form", form);
			return "redirect:/users/create";
		}
		model.addAttribute("user", userService.create(form));
		return "user/detail";
	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable Integer id) {
		userService.delete(id);
		return "redirect:/users";
	}
}
