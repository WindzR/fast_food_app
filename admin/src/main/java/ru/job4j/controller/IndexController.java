package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dto.DishDTO;
import ru.job4j.service.DishService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admins")
public class IndexController {

    private final DishService service;

    public IndexController(final DishService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String getAll(Model model) {
        List<DishDTO> allDishes = service.getAllDishes();
        System.out.println(allDishes);
        model.addAttribute("dishes", allDishes);
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
        service.addNewDish(dto);
        return "redirect:/admins/index";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var updateDish = service.getDishById(id);
        if (updateDish.isEmpty()) {
            model.addAttribute("message", "Dish with such id IS NOT FOUND!");
            return "errors/404";
        }
        model.addAttribute("dish", updateDish.get());
        return "admins/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute DishDTO dish, Model model) {
        boolean isUpdated = service.changeDish(dish.getId(), dish);
        System.out.println("STATUS OF UPDATING DISH : " + isUpdated);
        if (!isUpdated) {
            model.addAttribute("message", "Dish with such id IS NOT FOUND!");
            return "errors/404";
        }
        return "redirect:/admins/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        boolean isDeleted = service.removeDish(id);
        if (!isDeleted) {
            model.addAttribute("message", "Dish with such id IS NOT FOUND!");
            return "errors/404";
        }
        return "redirect:/admins/index";
    }
}
