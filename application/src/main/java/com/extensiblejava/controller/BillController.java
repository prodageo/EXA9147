package com.extensiblejava.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.extensiblejava.bill.Bill;
import com.extensiblejava.repository.BillRepository;

@Controller
@RequestMapping("/bills/")
public class BillController {

	private final BillRepository BillRepository;

	@Autowired
	public BillController(BillRepository BillRepository) {
		this.BillRepository = BillRepository;
	}

	@GetMapping("signup")
	public String showSignUpForm(Bill Bill) {
		return "add-Bill";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		// model.addAttribute("Bills", BillRepository.findAll());
		return "index-bill";
		// return "index-student";
	}

	@PostMapping("add")
	public String addBill(@Valid Bill Bill, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-Bill";
		}

		BillRepository.save(Bill);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Bill Bill = BillRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Bill Id:" + id));
		model.addAttribute("Bill", Bill);
		return "update-Bill";
	}

	@PostMapping("update/{id}")
	public String updateBill(@PathVariable("id") long id, @Valid Bill Bill, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			Bill.setId(id);
			return "update-Bill";
		}

		BillRepository.save(Bill);
		model.addAttribute("Bills", BillRepository.findAll());
		return "index-bill";
	}

	@GetMapping("delete/{id}")
	public String deleteBill(@PathVariable("id") long id, Model model) {
		Bill Bill = BillRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Bill Id:" + id));
		BillRepository.delete(Bill);
		model.addAttribute("Bills", BillRepository.findAll());
		return "index-bill";
	}
}
