package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dto.DishDTO;
import ru.job4j.repository.MemoryRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dishes")
public class IndexController {

    private final MemoryRepository repository;

    public IndexController(final MemoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/index")
    public String getAll(Model model) {
        model.addAttribute("dishes", repository.getDishes());
        return "index";
    }

    @GetMapping("/create-page")
    public String getCreationPage() {
        return "dishes/create";
    }

    @GetMapping("/update-page")
    public String getUpdatePage() {
        return "dishes/update";
    }

    @PostMapping("/create")
    public String create(HttpServletRequest request) {
        var name = request.getParameter("name");
        long cookingTime = Long.parseLong(request.getParameter("cookingTime"));
        DishDTO dto = DishDTO.of(0, name, cookingTime);
        repository.saveDish(dto);
        return "redirect:/dishes/index";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var updateDish = repository.getDishById(id);
        if (updateDish.isEmpty()) {
            model.addAttribute("message", "Dish with such id IS NOT FOUND!");
            return "errors/404";
        }
        model.addAttribute("dish", updateDish.get());
        return "dishes/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute DishDTO dish, Model model) {
        boolean isUpdated = repository.updateDish(dish);
        System.out.println("STATUS OF UPDATING DISH : " + isUpdated);
        if (!isUpdated) {
            model.addAttribute("message", "Dish with such id IS NOT FOUND!");
            return "errors/404";
        }
        return "redirect:/dishes/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        boolean isDeleted = repository.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Dish with such id IS NOT FOUND!");
            return "errors/404";
        }
        return "redirect:/dishes/index";
    }
}
