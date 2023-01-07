package ru.job4j.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/create")
    public String getCreationPage() {
        return "dishes/create";
    }

    @PostMapping("/create")
    public String create(HttpServletRequest request) {
        var name = request.getParameter("name");
        long cookingTime = Long.parseLong(request.getParameter("cookingTime"));
        DishDTO dto = DishDTO.of(0, name, cookingTime);
        repository.saveDish(dto);
        return "redirect:/index";
    }
}
